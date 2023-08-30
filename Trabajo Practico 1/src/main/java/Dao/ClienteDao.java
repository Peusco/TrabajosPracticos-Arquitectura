package Dao;

import Factory.DaoMySqlFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao implements TiendaDao{
    Connection conn ;
    public void InsertarDatos (CSVParser datos) throws SQLException {
        this.conn = new DaoMySqlFactory().getConnection();
        for(CSVRecord row: datos) {
            int idCliente = Integer.parseInt(row.get("idCliente"));
            String nombre = row.get("nombre");
            String email = row.get("email");
            String insert = "INSERT INTO Cliente(idCliente,nombre,email) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1,idCliente);
            ps.setString(2, nombre);
            ps.setString(3,email);
            ps.executeUpdate();
            ps.close();
            conn.commit();
            conn.close();
        }
    }

    public void crearTabla () throws SQLException{
        this.conn = new DaoMySqlFactory().getConnection();
        String table= "CREATE TABLE Cliente("+
                "idCliente INT,"+
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)," +
                "PRIMARY KEY(idCliente));";
        conn.prepareStatement(table).execute();
        conn.commit();
        conn.close();
    }

    @Override
    public void productoConMasRecaudacion() {

    }

}
