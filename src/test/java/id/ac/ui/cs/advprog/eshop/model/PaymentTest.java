package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp(){
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertEquals("id-dummy", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData, "SUCCESS");
        assertEquals("id-dummy", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        Payment payment = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData, PaymentStatus.REJECTED.getValue());
        assertEquals("id-dummy", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData, "INVALID");
        });
    }

    @Test
    void testSetPaymentToDataEmpty() {
        Payment payment = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        this.paymentData.clear();
        assertThrows (IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testSetPaymentDataToStatusSuccess() {
        Payment payment = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment.setPaymentData(this.paymentData);
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentMethodVoucher() {
        Payment payment = new Payment("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertEquals("id-dummy", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentMethodBankTransfer() {
        Payment payment = new Payment("id-dummy", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        assertEquals("id-dummy", payment.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

}