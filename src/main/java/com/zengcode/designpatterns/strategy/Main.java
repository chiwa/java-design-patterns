package com.zengcode.designpatterns.strategy;

public class Main {

    //Strategy
    public interface PaymentStrategy {
        void pay(double amount); //สัญญา หรือกติกาที่เราได้กล่าวถึงไว้
    }

    //Concrete Strategies
    public static class CashPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("จ่ายเงินสด: " + amount + " บาท, ลด 10%");
        }
    }

    public static class PromptPayPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("จ่ายผ่านพร้อมเพย์: " + amount + " บาท");
        }
    }

    public static class CreditCardPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("จ่ายผ่านพร้อมเพย์: " + amount + " บาท, ขาร์จเพิ่ม 3%");
        }
    }

    //Context
    public static class PaymentProcessor {
        private PaymentStrategy strategy;

        public PaymentProcessor(PaymentStrategy strategy) {
            this.strategy = strategy;
        }
        public void setStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }
        public void process(double amount) {
            strategy.pay(amount);
        }
    }


    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor(new CashPayment());
        processor.process(200.0);

        // เปลี่ยน strategy ระหว่าง runtime
        processor.setStrategy(new PromptPayPayment());
        processor.process(200.0);

        processor.setStrategy(new CreditCardPayment());
        processor.process(200.0);

        PaymentStrategy qrPay = amount -> System.out.println("จ่ายด้วย QR: " + amount);
        processor = new PaymentProcessor(qrPay);
        processor.process(300);
    }
}