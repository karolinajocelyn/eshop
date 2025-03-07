package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    Map<String, String> paymentData;

    @BeforeEach
    public void setUp() {
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();

        Payment payment1 = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), paymentData);
        payments.add(payment1);

        Payment payment2 = new Payment("id-dummy-2", PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        payments.add(payment2);
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        Payment result = paymentRepository.save(newPayment);

        Payment findPayment = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findPayment.getId());

        assertEquals(payment.getMethod(), findPayment.getMethod());
        assertEquals(payment.getStatus(), findPayment.getStatus());

        assertSame(payment.getPaymentData(), findPayment.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findPayment = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findPayment.getId());

        assertEquals(payments.get(1).getMethod(), findPayment.getMethod());
        assertEquals(payments.get(1).getStatus(), findPayment.getStatus());

        assertSame(payments.get(1).getPaymentData(), findPayment.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("id-never-created");
        assertNull(findResult);
    }
}