package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentBankTransferTest {
    Map<String, String> paymentData;

    @BeforeEach
    public void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testSetPaymentData() {
        this.paymentData.put("bankName", "BCA");
        this.paymentData.put("referenceCode", "2387452194");

        PaymentBankTransfer payment = new PaymentBankTransfer("id-dummy", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataEmptyBankName() {
        this.paymentData.put("bankName", "");
        this.paymentData.put("referenceCode", "2387452194");

        PaymentBankTransfer payment = new PaymentBankTransfer("id-dummy", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataEmptyReferenceCode() {
        this.paymentData.put("bankName", "Mandiri");
        this.paymentData.put("referenceCode", "");

        PaymentBankTransfer payment = new PaymentBankTransfer("id-dummy", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataEmptyPaymentData() {
        PaymentBankTransfer payment = new PaymentBankTransfer("id-dummy", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}