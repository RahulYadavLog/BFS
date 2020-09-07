package com.example.pdfview;

public class PdfGuardModel {

    String guard,rate,amount;

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PdfGuardModel(String guard, String rate, String amount) {
        this.guard = guard;
        this.rate = rate;
        this.amount = amount;
    }
}
