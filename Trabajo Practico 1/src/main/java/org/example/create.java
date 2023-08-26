package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class create {
    public static void main(String[] args) throws IOException {
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String uri="jdbc:mysql://localhost:3306/trabajo_practico_1";
        try {
            Connection conn = DriverManager.getConnection(uri,"root","");
            conn.setAutoCommit(false);
           /* createTableFacturasP(conn);
            createTableFacturas(conn);
            createTableClients(conn);
            createTableProductos(conn);*/
            alter_table_factura(conn);
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void alter_table_factura(Connection conn) throws SQLException {

        Statement smt = conn.createStatement();
        smt.executeUpdate("ALTER TABLE Factura ADD CONSTRAINT fk_facutra_cliente" +
                "FOREIGN KEY (idCliente)," +
                "REFERENCES (Cliente (idCliente))," +
                ";");
        conn.commit();
    }

    private static void createTableClients(Connection conn) throws SQLException {
        String table= "CREATE TABLE Cliente("+
                "idCliente INT,"+
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)," +
                "PRIMARY KEY(idCliente));";
        conn.prepareStatement(table).execute();
        conn.commit();
    }

    private static void createTableFacturas(Connection conn) throws SQLException {
        String table= "CREATE TABLE Factura("+
                "idFactura INT,"+
                "idCliente INT," +
                "PRIMARY KEY(idFactura));";
        conn.prepareStatement(table).execute();
        conn.commit();
    }

    private static void createTableFacturasP(Connection conn) throws SQLException {
        String table= "CREATE TABLE Factura_Producto("+
                "idFactura INT,"+
                "idProducto INT," +
                "cantidad INT);";
        conn.prepareStatement(table).execute();
        conn.commit();
    }

    private static void createTableProductos(Connection conn) throws SQLException {
        String table= "CREATE TABLE Producto("+
                "idProducto INT,"+
                "nombre VARCHAR(45)," +
                "valor FLOAT,"+
                "PRIMARY KEY(idProducto));";
        conn.prepareStatement(table).execute();
        conn.commit();
    }

}
