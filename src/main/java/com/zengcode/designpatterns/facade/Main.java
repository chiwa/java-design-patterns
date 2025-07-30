package com.zengcode.designpatterns.facade;

public class Main {

    public static class PaymentService {
        public void charge(String user, double amount) {
            System.out.println("เรียกเก็บเงินจาก " + user);
        }
    }

    public static class PointService {
        public void addPoints(String user, int points) {
            System.out.println("ให้แต้มสะสมแก่ " + user);
        }
    }

    public static class EmailService {
        public void sendReceipt(String user) {
            System.out.println("ส่งใบเสร็จให้ " + user);
        }
    }

    public static class CheckoutFacade {
        private final PaymentService paymentService = new PaymentService();
        private final PointService pointService = new PointService();
        private final EmailService emailService = new EmailService();

        public void checkout(String user, double amount) {
            paymentService.charge(user, amount);
            pointService.addPoints(user, (int) amount / 10);
            emailService.sendReceipt(user);
        }
    }

    public static void main(String[] args) {
        CheckoutFacade checkout = new CheckoutFacade();
        checkout.checkout("Chiwa", 500);
    }
}
