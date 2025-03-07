package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    Payment payment1;
    Payment payment2;
    Order order;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("a1b2c3d4-5678-90ef-ghij-klmnopqrstuv");
        product.setProductName("Sabun Cap Harimau");
        product.setProductQuantity(3);
        products.add(product);

        order = new Order("98765432-10fe-dcba-5678-ijklmnopqrst", products, 1708560000L, "Rizky Ramadhan");

        Map<String, String> voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP5678XYZ1234");

        Map<String, String> bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "Mandiri");
        bankTransferData.put("referenceCode", "REF987654321");

        payment1 = new Payment("payment-789", order, "VOUCHER", voucherData);
        payment2 = new Payment("payment-012", order, "BANK_TRANSFER", bankTransferData);
    }

    @Test
    void testSavePayment() {
        Payment savedPayment = paymentRepository.save(payment1);

        assertNotNull(savedPayment);
        assertEquals(payment1.getId(), savedPayment.getId());
        assertEquals(payment1.getMethod(), savedPayment.getMethod());
    }

    @Test
    void testFindByIdIfExists() {
        paymentRepository.save(payment1);

        Payment foundPayment = paymentRepository.findById(payment1.getId());

        assertNotNull(foundPayment);
        assertEquals(payment1.getId(), foundPayment.getId());
    }

    @Test
    void testFindByIdIfNotExists() {
        Payment foundPayment = paymentRepository.findById("unknown-id");

        assertNull(foundPayment);
    }

    @Test
    void testFindAllIfEmpty() {
        List<Payment> allPayments = paymentRepository.findAll();

        assertTrue(allPayments.isEmpty());
    }

    @Test
    void testFindAllIfNotEmpty() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(2, allPayments.size());
        assertTrue(allPayments.contains(payment1));
        assertTrue(allPayments.contains(payment2));
    }

    @Test
    void testSaveExistingPayment() {
        paymentRepository.save(payment1);

        payment1.setStatus("SUCCESS");
        Payment updatedPayment = paymentRepository.save(payment1);

        assertEquals("SUCCESS", updatedPayment.getStatus());

        Payment foundPayment = paymentRepository.findById(payment1.getId());
        assertEquals("SUCCESS", foundPayment.getStatus());
    }

    @Test
    void testFindByOrderId() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> paymentsForOrder = paymentRepository.findByOrderId(order.getId());

        assertEquals(2, paymentsForOrder.size());
        assertTrue(paymentsForOrder.contains(payment1));
        assertTrue(paymentsForOrder.contains(payment2));
    }

    @Test
    void testFindByOrderIdIfNotExists() {
        List<Payment> paymentsForOrder = paymentRepository.findByOrderId("non-existent-order-id");

        assertTrue(paymentsForOrder.isEmpty());
    }
}
