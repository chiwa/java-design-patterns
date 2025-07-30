package com.zengcode.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // Component: สัญญาเดียวกันหมด
    public interface MenuComponent {
        void print();
    }

    // Leaf: รายการเดี่ยว
    public static class MenuItem implements MenuComponent {
        private final String name;

        public MenuItem(String name) {
            this.name = name;
        }

        public void print() {
            System.out.println("- " + name);
        }
    }

    // Composite: มีลูกข้างใน
    public static class Menu implements MenuComponent {
        private final String name;
        private final List<MenuComponent> children = new ArrayList<>();

        public Menu(String name) {
            this.name = name;
        }

        public Menu add(MenuComponent child) {
            children.add(child);
            return this; // chain ได้
        }

        public void print() {
            System.out.println(name + ":");
            for (MenuComponent c : children) {
                c.print();
            }
        }
    }

    public static void main(String[] args) {
        MenuComponent root = new Menu("Main Menu")
                .add(new MenuItem("Home"))
                .add(new Menu("Products")
                        .add(new MenuItem("Laptop"))
                        .add(new MenuItem("Phone")))
                .add(new Menu("About")
                        .add(new MenuItem("Team"))
                        .add(new MenuItem("Contact")));
        root.print();
    }
}
