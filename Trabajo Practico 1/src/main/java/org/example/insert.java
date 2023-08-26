package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insert {
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

            CSVParser parser2 = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/facturas.csv"));
            for(CSVRecord row: parser2) {
                addFactura(conn, Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")));
            }

           CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/clientes.csv"));
            for(CSVRecord row: parser) {
                addCliente(conn, Integer.parseInt(row.get("idCliente")),row.get("nombre"),row.get("email"));
            }

            CSVParser parse3 = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/facturas-productos.csv"));
            for(CSVRecord row: parse3) {
                addFacturaP(conn, Integer.parseInt(row.get("idFactura")),Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad")));
            }

            CSVParser parse4 = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/productos.csv"));
            for(CSVRecord row: parse4) {
                addProducto(conn, Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor")));
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    private static void addProducto(Connection conn, int idProducto, String nombre, Float valor) throws SQLException {
        String insert = "INSERT INTO Producto(idProducto,nombre,valor) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1,idProducto);
        ps.setString(2, nombre);
        ps.setFloat(3,valor);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    private static void addFacturaP(Connection conn, int idFactura, int idProducto, int cantidad) throws SQLException {
        String insert = "INSERT INTO Factura_Producto(idFactura,idProducto,cantidad) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1,idFactura);
        ps.setInt(2, idProducto);
        ps.setInt(3,cantidad);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    private static void addFactura(Connection conn, int idFactura, int idCliente) throws SQLException {
        String insert = "INSERT INTO Factura(idFactura,idCliente) VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1,idFactura);
        ps.setInt(2, idCliente);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    private static void addCliente(Connection conn, int id, String nombre, String email) throws SQLException {
        String insert = "INSERT INTO Cliente(idCliente,nombre,email) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1,id);
        ps.setString(2, nombre);
        ps.setString(3,email);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }
}
