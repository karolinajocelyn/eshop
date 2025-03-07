package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        payments = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setProductId("id-dummy");
        product.setProductName("product-dummy");
        product.setProductQuantity(1);
        products.add(product);

        Order order = new Order("order-dummy", products, 2008000030L, "Dummy");
        orders.add(order);

        Map<String, String> paymentData1 = Map.of("voucherCode", "ESHOP1234ABC5678");
        Map<String, String> paymentData2 = Map.of("bankName", "BCA", "referenceCode", "CODE-REF-DUMMY");

        Payment payment1 = new Payment("order-dummy", PaymentMethod.VOUCHER.getValue(), paymentData1);
        Payment payment2 = new Payment("order-dummy", PaymentMethod.BANK_TRANSFER.getValue(), paymentData2);

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Order order = orders.get(0);
        Payment payment = payments.get(0);

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), Map.of("voucherCode", "ESHOP1234ABC5678"));
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testSetStatusSuccess() {
        Order order = orders.get(0);
        Payment payment = payments.get(0);

        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        verify(orderRepository, times(1)).save(order);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderRepository, times(1)).save(order);
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(order).when(orderRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "NOT-AVAILABLE");
        });

        verify(orderRepository, times(0)).save(any(Order.class));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        verify(paymentRepository, times(1)).findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("id-nothing");
        assertNull(paymentService.getPayment("id-nothing"));
    }
}