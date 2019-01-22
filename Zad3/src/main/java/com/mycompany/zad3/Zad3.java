/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zad3;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Kuba
 */
public class Zad3 {

    public static void main(String[] args) {
        final String url = "https://shares.telegraph.co.uk/indices/summary/index/MCX";

        try {
            final Document document = Jsoup.connect(url).get();

            for (Element row : document.select(
                    "table.tablesorter.full tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")) {
                    continue;
                } else {
                    final String epic
                            = row.select("td:nth-of-type(1)").text();
                    final String name
                            = row.select("td:nth-of-type(2)").text();
                    final String price
                            = row.select("td.right:nth-of-type(3)").text();

                    System.out.println(epic + " || " + name + " || " + price);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
