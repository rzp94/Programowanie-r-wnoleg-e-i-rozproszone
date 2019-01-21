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
public class Main {
    public static void main(String[] args)
	{
            long difference;
            Synchronized.calc();
            Unsynchronized.calc();
            System.out.println("Zsynchronizowana liczba wyniosła "+Synchronized.liczba);
            System.out.println("Obliczanie zsynchronizowane zajelo: "+Synchronized.calc()+" ms\n");
            System.out.println("Niezsynchronizowana liczba wyniosła: "+Unsynchronized.liczba);
            System.out.println("Obliczanie niezsynchronizowane zajelo: "+Unsynchronized.calc()+" ms\n");
            if(Unsynchronized.calc()>Synchronized.calc())
            {
                difference = Unsynchronized.calc() - Synchronized.calc();
            }
            else
            {
                difference = Synchronized.calc() - Unsynchronized.calc();
            }
            System.out.println("Różnica wyniosła: "+difference+"ms\n");
           
		
	}
}
