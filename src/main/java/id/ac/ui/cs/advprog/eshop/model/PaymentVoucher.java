package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentVoucher extends Payment{
    public PaymentVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentVoucher(String id, String method, Map<String, String> paymentData, String status) {
        super(id, method, paymentData, status);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode.isEmpty() || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            long numcharCount = voucherCode.chars().filter(Character::isDigit).count();
            this.status = (numcharCount == 8) ? PaymentStatus.SUCCESS.getValue() : PaymentStatus.REJECTED.getValue();
        }

        this.paymentData = paymentData;
    }
}