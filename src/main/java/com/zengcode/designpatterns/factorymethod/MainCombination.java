package com.zengcode.designpatterns.factorymethod;

public class MainCombination {

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


    // Factory
    public static class PaymentFactory {
        public static PaymentStrategy getStrategy(String type) {
            switch (type) {
                case "CASH": return new CashPayment();
                case "PROMPTPAY": return new PromptPayPayment();
                case "CREDIT": return new CreditCardPayment();
                default: throw new IllegalArgumentException("Unknown type: " + type);
            }
        }
    }
    public static void main(String[] args) {
        PaymentStrategy strategy = PaymentFactory.getStrategy("CASH");
        strategy.pay(300);

        PaymentProcessor processor = new PaymentProcessor(PaymentFactory.getStrategy("CASH"));
        processor.process(300);

    }
}