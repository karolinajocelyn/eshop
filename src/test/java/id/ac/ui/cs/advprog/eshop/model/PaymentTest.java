package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTest {
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("a1b2c3d4-5678-90ef-ghij-klmnopqrstuv");
        product1.setProductName("Sabun Cap Harimau");
        product1.setProductQuantity(3);
        products.add(product1);

        this.order = new Order("98765432-10fe-dcba-5678-ijklmnopqrst", products, 1708560000L, "Rizky Ramadhan");

        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentWithRequiredFields() {
        Payment payment = new Payment("payment-789", this.order, "VOUCHER", this.paymentData);

        assertEquals("payment-789", payment.getId());
        assertEquals(this.order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
        assertEquals("WAITING", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithCustomStatus() {
        Payment payment = new Payment("payment-789", this.order, "VOUCHER", this.paymentData, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentStatus() {
        Payment payment = new Payment("payment-789", this.order, "VOUCHER", this.paymentData);
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testVoucherPaymentValidation_ValidVoucher() {
        this.paymentData.put("voucherCode", "ESHOP5678XYZ1234");

        Payment payment = new Payment("payment-789", this.order, "VOUCHER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testVoucherPaymentValidation_InvalidLength() {
        this.paymentData.put("voucherCode", "ESHOP98765");

        Payment payment = new Payment("payment-789", this.order, "VOUCHER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherPaymentValidation_InvalidPrefix() {
        this.paymentData.put("voucherCode", "SHOP98765432WXYZ");

        Payment payment = new Payment("payment-789", this.order, "VOUCHER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherPaymentValidation_NotEnoughDigits() {
        this.paymentData.put("voucherCode", "ESHOPABCDEFGHIJKLMNOP");

        Payment payment = new Payment("payment-789", this.order, "VOUCHER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferPaymentValidation_Valid() {
        this.paymentData.put("bankName", "Mandiri");
        this.paymentData.put("referenceCode", "REF987654321");

        Payment payment = new Payment("payment-789", this.order, "BANK_TRANSFER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testBankTransferPaymentValidationMissingBankName() {
        this.paymentData.put("referenceCode", "REF987654321");

        Payment payment = new Payment("payment-789", this.order, "BANK_TRANSFER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferPaymentValidationEmptyReferenceCode() {
        this.paymentData.put("bankName", "Mandiri");
        this.paymentData.put("referenceCode", "");

        Payment payment = new Payment("payment-789", this.order, "BANK_TRANSFER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferPaymentValidationNullValues() {
        this.paymentData.put("bankName", null);
        this.paymentData.put("referenceCode", null);

        Payment payment = new Payment("payment-789", this.order, "BANK_TRANSFER", this.paymentData);
        payment.validateAndSetStatus();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testUnsupportedPaymentMethod() {
        Payment payment = new Payment("payment-789", this.order, "CASH", this.paymentData);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.validateAndSetStatus();
        });

        assertTrue(exception.getMessage().contains("Unsupported payment method"));
    }
}
