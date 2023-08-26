package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("src/clientes.csv"));
        for(CSVRecord row: parser) {
            System.out.println("ID: " + Integer.parseInt(row.get("idCliente")));
            System.out.println("Nombre: " + row.get("nombre"));
            System.out.println("email: " + row.get("email"));
        }
    }
}