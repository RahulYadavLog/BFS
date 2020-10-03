package com.example.pdfview;

public class PdfGuardModel {

    String guardType, rate,Amount,guard;

    public PdfGuardModel(String guardType, String rate, String amount, String guard) {
        this.guardType = guardType;
        this.rate = rate;
        Amount = amount;
        this.guard = guard;
    }

    public String getGuardType() {
        return guardType;
    }

    public void setGuardType(String guardType) {
        this.guardType = guardType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }
}
