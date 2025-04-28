package com.schulung.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {


        TreeSet<String> städte = new TreeSet<>();
        städte.add("Berlin");
        städte.add("Hamburg");
        städte.add("München");

        // Einfache Iteration
        for (String stadt : städte) {
            System.out.println(stadt);
        }

        TreeSet<Integer> zahlen = new TreeSet<>();
        zahlen.add(10);
        zahlen.add(5);
        zahlen.add(20);

        Iterator<Integer> iterator = zahlen.iterator();
        while (iterator.hasNext()) {
            Integer zahl = iterator.next();
            System.out.println(zahl);
            // Mit iterator.remove() könnte man Elemente während der Iteration entfernen
        }

        TreeMap<String, Integer> personenAlter = new TreeMap<>();
        personenAlter.put("Hans", 25);
        personenAlter.put("Anna", 30);
        personenAlter.put("Lisa", 22);

        // Iteration über alle Einträge
        for (Map.Entry<String, Integer> eintrag : personenAlter.entrySet()) {
            System.out.println(eintrag.getKey() + " ist " + eintrag.getValue() + " Jahre alt");
        }



        // ----------- ARRAY BEISPIEL -----------
        System.out.println("🔢 Array Beispiel:");

        int[] zahlenArray = { 1, 2, 3, 4, 5 };
        for (int i = 0; i < zahlenArray.length; i++) {
            System.out.println("Index " + i + ": " + zahlenArray[i]);
        }

        int[][] zahlenArray2 = {{ 10, 11, 12, 13, 14 },
                                { 20, 21, 22, 4, 5 }};

        System.out.println(zahlenArray2[0][2]);

        // ----------- ARRAYLIST BEISPIEL -----------
        System.out.println("\n📦 ArrayList Beispiel:");

        ArrayList<String> namenListe = new ArrayList<>();
        namenListe.add("Anna");
        namenListe.add("Ben");
        namenListe.add("Clara");

        for (String name : namenListe) {
            System.out.println("Name: " + name);
        }

        namenListe.add("David");
        System.out.println("Nach dem Hinzufügen von David: " + namenListe);

        // ----------- BINÄRER BAUM BEISPIEL -----------
        System.out.println("\n🌳 Einfacher Binärbaum Beispiel:");

        BaumKnoten wurzel = new BaumKnoten(10);
        wurzel.links = new BaumKnoten(5);
        wurzel.rechts = new BaumKnoten(15);
        wurzel.links.links = new BaumKnoten(2);
        wurzel.links.rechts = new BaumKnoten(7);

        System.out.println("Inorder-Ausgabe des Baumes:");
        inorderAusgabe(wurzel);
    }

    // Hilfsklasse für Baumknoten
    static class BaumKnoten {
        int wert;
        BaumKnoten links;
        BaumKnoten rechts;

        BaumKnoten(int wert) {
            this.wert = wert;
        }
    }

    // Rekursive Methode zur Ausgabe (Inorder Traversierung)
    static void inorderAusgabe(BaumKnoten knoten) {
        if (knoten != null) {
            inorderAusgabe(knoten.links);
            System.out.println("Knoten: " + knoten.wert);
            inorderAusgabe(knoten.rechts);
        }
    }
}
