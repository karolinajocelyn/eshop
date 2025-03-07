package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentVoucherTest {
    private Map<String, String> paymentData;

    @BeforeEach
    public void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    public void testSetPaymentDataWithEmptyData() {
        PaymentVoucher paymentVoucher = new PaymentVoucher("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> paymentVoucher.setPaymentData(this.paymentData));
    }

    @Test
    void testSetPaymentDataWithValidCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        PaymentVoucher payment = new PaymentVoucher("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidTooShortCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC567");
        PaymentVoucher payment = new PaymentVoucher("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidNo8NumcharCode() {
        this.paymentData.put("voucherCode", "ESHOPOKEGASS");
        PaymentVoucher payment = new PaymentVoucher("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidNoEshopCode() {
        this.paymentData.put("voucherCode", "AAAAA1234ABC5678");
        PaymentVoucher payment = new PaymentVoucher("id-dummy", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}