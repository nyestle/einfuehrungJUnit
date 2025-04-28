package com.schulung.test;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // ----------- ARRAY BEISPIEL -----------
        System.out.println("🔢 Array Beispiel:");

        int[] zahlenArray = { 1, 2, 3, 4, 5 };
        for (int i = 0; i < zahlenArray.length; i++) {
            System.out.println("Index " + i + ": " + zahlenArray[i]);
        }

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
