package fftc.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String EXIT = "-stop";

    public static void main(String args[]) {

        PersonExtractor personExtractor = new PersonExtractor().invoke();
        if (personExtractor.wasError()) {
            System.out.println("Error");
            return;
        }
        processCommand(personExtractor);
    }

    private static void processCommand(PersonExtractor personExtractor) {
        System.out.println("Enter '-f' '-l' '-a'...");
        List<Person> persons = personExtractor.getPerson();
        try (Scanner sc = new Scanner(System.in);) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (isStopTheApp(line)) {
                    break;
                }
                if (line.equals("-f")) {
                    Collections.sort(persons, new FirstNameComparator());
                } else if (line.equals("-l")) {
                    Collections.sort(persons, new LastNameComparator());
                } else if (line.equals("-a")) {
                    Collections.sort(persons, new AgeComparator());
                }
                printList(persons);
                System.out.println("Enter '-f' '-l' '-a' ");
            }
        }
    }

    private static void printList(List<Person> persons) {
        persons.forEach(System.out::println);
    }

    private static boolean isStopTheApp(String line) {
        return line.equals(EXIT);
    }
}