/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zad2;

/**
 *
 * @author Kuba
 */
public class Unsynchronized {

    static public Integer liczba = 0;

    static public long calc() {
        long timerStart = System.nanoTime();

        Thread thread1 = new Thread() {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    if (i % 3 == 0) {
                        liczba %= 2;
                    } else {
                        liczba *= 5;
                    }
                }
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                for (int i = 0; i < 100000; i++) {

                    liczba += 2;
                }
            }
        };

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

        } catch (Exception e) {
        }
        long timerEnd = System.nanoTime();
        return timerEnd - timerStart;
    }
}
