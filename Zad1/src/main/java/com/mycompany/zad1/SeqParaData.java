/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zad1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;



/**
 *
 * @author Kuba
 */
public class SeqParaData {
    
    
    public static void main(String[] args) throws Exception {
	
        int i;
        float timeSeq0 = System.nanoTime();
        for ( i = 1; i < 11; i++) {
            ImageIO.write(ImageIO.read(new URL("http://www.drapiezniki.pl/Photos/tygrys.jpg")), "jpg", new File("C:\\SeqParaPhotos\\0" + i + ".jpg"));
            System.out.println("Sekwencyjne pobieranie pliku" );
        }    
	
        float timeSeq1 = System.nanoTime();
        float timeSeq2 = timeSeq1-timeSeq0;
        System.out.println("Pobieranie sekwencyjne zajeło: "+timeSeq2+"ms\n");
        
        float timePara0 = System.nanoTime();
        List<Thread> threadsList = new ArrayList<Thread>();
        
        for ( i = 0; i < 10; i++){
            
            final int endingI = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        ImageIO.write(ImageIO.read(new URL("http://www.drapiezniki.pl/Photos/tygrys.jpg")), "jpg", new File("C:\\SeqParaPhotos\\" + endingI + ".jpg"));
                        System.out.println("Wspolbiezne pobieranie pliku");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadsList.add(thread);
            thread.start();
            
            
        }
        for(Thread thread: threadsList){
            thread.join();
        }
        float timePara1 = System.nanoTime();
        float timePara2 = timePara1 -  timePara0;
        System.out.println("Pobieranie wspolbiezne zajeło: "+timePara2+"ms\n");
        
        float difference = timeSeq2 - timePara2;
        System.out.println("Rożnica między pobieraniem sekwencyjnym a wspolbieznym wyniosla:  "+difference+"ms");
        
        
        
           
    }
	


	
    
}
