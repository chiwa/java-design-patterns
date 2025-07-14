package com.zengcode.designpatterns.singleton;

public class Main {

    public static class ClassicSingleton {
        private static ClassicSingleton instance;
        private ClassicSingleton() {} // ห้าม new จากข้างนอก
        public static ClassicSingleton getInstance() {
            if (instance == null) {
                instance = new ClassicSingleton();
            }
            return instance;
        }

        public void hell(String message) {
            System.out.println(message);
        }
    }

    public interface NotificationStrategy {
        void send(String msg);
    }

    public static class EmailNotification implements NotificationStrategy {
        public void send(String msg) {
            System.out.println("ส่ง Email: " + msg);
        }
    }

    public static class SMSNotification implements NotificationStrategy {
        public void send(String msg) {
            System.out.println(" ส่ง SMS: " + msg);
        }
    }

    public enum NotificationManager {
        INSTANCE;

        private NotificationStrategy strategy;

        public void setStrategy(NotificationStrategy s) {
            this.strategy = s;
        }

        public void notify(String msg) {
            strategy.send(msg);
        }
    }

    public static void main(String[] args) {
        ClassicSingleton.getInstance().hell("Hello");

        // กำหนด Strategy แบบ Email
        NotificationManager.INSTANCE.setStrategy(new EmailNotification());
        NotificationManager.INSTANCE.notify("Hello via Email!");

        // เปลี่ยนเป็น SMS แทน
        NotificationManager.INSTANCE.setStrategy(new SMSNotification());
        NotificationManager.INSTANCE.notify("Hello via SMS!");
    }
}