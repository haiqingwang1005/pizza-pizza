package io.swagger.model;

public class Payment {
  private double amount;
  private PaymentType paymentType;

  public Payment(double amount, PaymentType paymentType) {
    this.amount = amount;
    this.paymentType = paymentType;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }
}
