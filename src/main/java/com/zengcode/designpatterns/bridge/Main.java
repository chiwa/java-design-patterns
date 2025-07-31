package com.zengcode.designpatterns.bridge;

public class Main {
    // Implementation (ฝั่งของจริง)
    public interface Device {
        void turnOn();
        void turnOff();
        void setVolume(int volume);
    }

    public static class TV implements Device {
        public void turnOn() { System.out.println("TV เปิดแล้ว"); }
        public void turnOff() { System.out.println("TV ปิดแล้ว"); }
        public void setVolume(int volume) { System.out.println("TV Volume = " + volume); }
    }

    public static class Radio implements Device {
        public void turnOn() { System.out.println("Radio เปิดแล้ว"); }
        public void turnOff() { System.out.println("Radio ปิดแล้ว"); }
        public void setVolume(int volume) { System.out.println("Radio Volume = " + volume); }
    }

    // Abstraction (ฝั่งรีโมท)
    public static abstract class RemoteControl {
        protected Device device;
        public RemoteControl(Device device) { this.device = device; }
        public void turnOn() { device.turnOn(); }
        public void turnOff() { device.turnOff(); }
    }

    public static class BasicRemote extends RemoteControl {
        public BasicRemote(Device device) { super(device); }
    }

    public static class AdvancedRemote extends RemoteControl {
        public AdvancedRemote(Device device) { super(device); }
        public void mute() { device.setVolume(0); }
    }

    public static void main(String[] args) {
        Device tv = new TV();
        Device radio = new Radio();

        RemoteControl basicRemoteForTV = new BasicRemote(tv);
        RemoteControl advancedRemoteForRadio = new AdvancedRemote(radio);

        basicRemoteForTV.turnOn();
        advancedRemoteForRadio.turnOn();
        ((AdvancedRemote) advancedRemoteForRadio).mute();
        basicRemoteForTV.turnOff();
    }
}
