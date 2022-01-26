package com.company;

public class Deadlock {
    static class Friend{
        private final String name;
        public Friend(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public synchronized void greet(Friend friend){
            System.out.format("%s: %s " + "has greeted to me!%n", this.name, friend.getName());
            friend.greetBack(this);
        }
        public synchronized void greetBack(Friend friend){
            System.out.format("%s: %s " + "has greeted back to me!%n", this.name, friend.getName());
        }
    }
    public static void main(String[] args) {
	    final Friend A = new Friend("Arkadii");
        final Friend B = new Friend("Bober");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1");
                A.greet(B);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2");
                B.greet(A);
            }
        }).start();
    }
}
