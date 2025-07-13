package com.zengcode.designpatterns.factorymethod;

public class Main {

    // ใช้ Enum แทน String เพื่อหลีกเลี่ยงพิมพ์ผิด
    public enum PaymentType {
        CASH, PROMPTPAY, CREDIT
    }

    // Strategy Interface
    public interface PaymentStrategy {
        void pay(double amount);
    }

    // Concrete Strategies
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
            System.out.println("จ่ายผ่านบัตรเครดิต: " + amount + " บาท, ชาร์จเพิ่ม 3%");
        }
    }

    // Context ที่ใช้ Strategy
    public static class PaymentProcessor {
        private PaymentStrategy strategy;

        public void setStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void process(double amount) {
            if (strategy == null) {
                throw new IllegalStateException("ยังไม่ได้กำหนดกลยุทธ์การจ่ายเงิน");
            }
            strategy.pay(amount);
        }
    }

    // Factory Method ที่ใช้ Enum แทน String
    public static class PaymentFactory {
        public static PaymentStrategy getStrategy(PaymentType type) {
            switch (type) {
                case CASH:
                    return new CashPayment();
                case PROMPTPAY:
                    return new PromptPayPayment();
                case CREDIT:
                    return new CreditCardPayment();
                default:
                    throw new IllegalArgumentException("ไม่รู้จักประเภทการจ่ายเงิน: " + type);
            }
        }
    }

    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.setStrategy(PaymentFactory.getStrategy(PaymentType.CASH));
        processor.process(300);

        processor.setStrategy(PaymentFactory.getStrategy(PaymentType.PROMPTPAY));
        processor.process(500);

        processor.setStrategy(PaymentFactory.getStrategy(PaymentType.CREDIT));
        processor.process(1000);

        // ใช้ Lambda Strategy
        processor.setStrategy(amount -> System.out.println("จ่ายด้วย QR Code: " + amount + " บาท"));
        processor.process(700);
    }
}