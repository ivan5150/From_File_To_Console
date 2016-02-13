package fftc.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;


public class PersonExtractor {
    public static final String FILE_PATH = "names.txt";
    public static final String CHARSET_NAME = "UTF-8";
    private boolean error;
    private List<Person> persons;

    private List<Person> readPersons() throws IOException {
        List<Person> persons = new ArrayList<>();

        try (FileInputStream fileIStream = new FileInputStream(FILE_PATH);
             InputStreamReader inputStreamReader = new InputStreamReader(fileIStream, CHARSET_NAME);
             BufferedReader reader = new BufferedReader(inputStreamReader);) {
            String line;
            while ((line = reader.readLine()) != null) {
                persons.add(convertToPerson(line));
            }
        }
        return persons;
    }

    private Person convertToPerson(String line) {
        String[] personData = line.split(" ");
        return new Person(personData[0], personData[1], Integer.parseInt(personData[2]));
    }

    boolean wasError() {
        return error;
    }

    public List<Person> getPerson() {
        return persons;
    }

    public PersonExtractor invoke() {
        try {
            persons = readPersons();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found " + e.getMessage());
            error = true;
            return this;
        } catch (IOException e) {
            System.out.println("Access file error " + e.getMessage());
            error = true;
            return this;
        }
        error = false;
        return this;
    }
}
