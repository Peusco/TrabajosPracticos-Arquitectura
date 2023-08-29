package org.example;

import Dao.TiendaDao;
import Factory.DaoFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DaoFactory df = DaoFactory.getDaoFactory(1);
        TiendaDao p = df.getProductoDao();
        p.productoConMasRecaudacion();
    }
}