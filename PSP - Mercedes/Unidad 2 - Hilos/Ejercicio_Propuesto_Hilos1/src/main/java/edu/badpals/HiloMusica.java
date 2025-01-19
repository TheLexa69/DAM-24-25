package edu.badpals;


public class HiloMusica extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Reproduciendo música...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

