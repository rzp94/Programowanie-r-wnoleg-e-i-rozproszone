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
public class Synchronized {

    static public Integer liczba = 0;

    static public long calc() {
        long timerStart = System.nanoTime();

        Thread thread1;
        Thread thread2;

        thread1 = new Thread() {
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
        thread2 = new Thread() {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    liczba += 2;
                }
            }
        };

        try {
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
        } catch (Exception e) {
        }
        long timerEnd = System.nanoTime();
        return timerEnd - timerStart;
    }
}
