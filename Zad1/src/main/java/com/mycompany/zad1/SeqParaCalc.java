/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zad1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kuba
 */
public class SeqParaCalc {
    
    public static void main(String[] args) throws Exception {   
        
        long fibonSeq = 1;
        long fibonSeq1 = 1;
        long fibonSeq2 = fibonSeq + fibonSeq1;  
        
        System.out.println("Ciag Fibonacciego sekwencyjnie");
        long timeSeq0 = System.nanoTime();
        for(int i =0; i<50; i++)
        {
            System.out.println("kn+2 dla kn = "+ fibonSeq + " i kn+1 = " + fibonSeq1 + " wynosi "  +fibonSeq2);
            fibonSeq=fibonSeq1;
            fibonSeq1=fibonSeq2;
            fibonSeq2 = fibonSeq + fibonSeq1;
        }
        long timeSeq1 = System.nanoTime();
        long timeSeq2 = timeSeq1-timeSeq0;
        System.out.println("Sekwencyjne obliczanie ciagu Fibonacciego zajelo: "+timeSeq2+"ms");
        
        
        
        final F f=new F();
        f.fibonPara = 1;
        f.fibonPara1 = 1;
        f.fibonPara2 = f.fibonPara + f.fibonPara1;
        System.out.println("\n Ciag Fibonacciego wspolbieznie");
        long timePara0 = System.nanoTime();
        List<Thread> threadsList = new ArrayList<Thread>();
        for (int i =0; i<50; i++){
            
            Thread thread = new Thread() {
                @Override
                public void run() {
                      System.out.println("kn+2 dla kn = "+ f.fibonPara + " i kn+1 = " + f.fibonPara1 + " wynosi "  +f.fibonPara2);
                      f.fibonPara=f.fibonPara1;
                      f.fibonPara1=f.fibonPara2;
                      f.fibonPara2 = f.fibonPara + f.fibonPara1;  
                }
            };
            threadsList.add(thread);
            thread.start();
        }
        for(Thread thread: threadsList){
            thread.join();
        }
        long timePara1 = System.nanoTime();
        long timePara2 = timePara1-timePara0;
        System.out.println("Wspolbiezne obliczanie ciagu Fibonacciego zajelo: "+timePara2+"ms");
        long difference = timePara2 - timeSeq2;
        System.out.println("Rożnica między obliczaniem wspolbieznym a sekwencyjnym wyniosla:  "+difference+"ms");
        
    
}

}
class F
{
    public long fibonPara;
    public long fibonPara1;
    public long fibonPara2;
    
}