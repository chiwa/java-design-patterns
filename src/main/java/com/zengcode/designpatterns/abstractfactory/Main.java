package com.zengcode.designpatterns.abstractfactory;

public class Main {
    // Abstract Products
    public interface PaymentStrategy {
        void pay(double amount);
    }
    public interface TaxStrategy {
        double calculateTax(double amount);
    }
    // Concrete Products for Thai
    public static class PromptPayPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("จ่ายผ่านพร้อมเพย์: " + amount + " บาท");
        }
    }
    public static class ThaiTax implements TaxStrategy {
        public double calculateTax(double amount) {
            return amount * 0.07;
        }
    }
    // Concrete Products for US
    public static class PayPalPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("Pay with PayPal: $" + amount);
        }
    }
    public static class UsTax implements TaxStrategy {
        public double calculateTax(double amount) {
            return amount * 0.10;
        }
    }
    // Abstract Factory
    public interface PaymentFactory {
        PaymentStrategy createPayment();
        TaxStrategy createTax();
    }
    // Concrete Factories
    public static class ThaiPaymentFactory implements PaymentFactory {
        public PaymentStrategy createPayment() {
            return new PromptPayPayment();
        }
        public TaxStrategy createTax() {
            return new ThaiTax();
        }
    }
    public static class UsPaymentFactory implements PaymentFactory {
        public PaymentStrategy createPayment() {
            return new PayPalPayment();
        }
        public TaxStrategy createTax() {
            return new UsTax();
        }
    }
    // Client Code
    public static class PaymentProcessor {
        private final PaymentStrategy payment;
        private final TaxStrategy tax;
        public PaymentProcessor(PaymentFactory factory) {
            this.payment = factory.createPayment();
            this.tax = factory.createTax();
        }
        public void checkout(double amount) {
            double taxAmount = tax.calculateTax(amount);
            System.out.println("Tax: " + taxAmount);
            payment.pay(amount + taxAmount);
        }
    }
    public static void main(String[] args) {
        PaymentFactory thaiFactory = new ThaiPaymentFactory();
        PaymentProcessor thaiProcessor = new PaymentProcessor(thaiFactory);
        thaiProcessor.checkout(1000);
        System.out.println("\n----\n");
        PaymentFactory usFactory = new UsPaymentFactory();
        PaymentProcessor usProcessor = new PaymentProcessor(usFactory);
        usProcessor.checkout(1000);
    }
}
