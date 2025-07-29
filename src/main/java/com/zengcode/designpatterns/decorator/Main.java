package com.zengcode.designpatterns.decorator;



// ใช้งาน
public class Main {

    public interface Notifier {
        void send(String message);
    }

    // ของจริงที่ใช้ส่งข้อความจริง
    public static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("ส่ง Email: " + message + " สำเร็จแล้วน๊า");
        }
    }

    // Decorator ที่เพิ่ม log ก่อนส่ง
    public static class LoggingNotifierDecorator implements Notifier {
        private final Notifier wrapped;

        public LoggingNotifierDecorator(Notifier wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void send(String message) {
            System.out.println("[LOG] กำลังจะส่งข้อความ: " + message);
            wrapped.send(message); // ส่งของจริง
        }
    }
    public static void main(String[] args) {
        Notifier email = new EmailNotifier();
        Notifier loggedEmail = new LoggingNotifierDecorator(email);
        loggedEmail.send("สวัสดีจาก Decorator!");
    }
}
