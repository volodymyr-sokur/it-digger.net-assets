package net.it_digger;

// The Double-Exit Mystery
public class Mystery_1 {
    public static void main(String[] args) {
        synchronized(Mystery_1.class) {
            System.out.println("Inside");
        }
    }
}