package Dao;

import Factory.DaoMySqlFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDao implements TiendaDao{
    Connection conn;
    public void crearTabla () throws SQLException {
        this.conn = new DaoMySqlFactory().getConnection();
        String table= "CREATE TABLE Factura("+
                "idFactura INT,"+
                "idCliente INT," +
                "PRIMARY KEY(idFactura)," +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente));";
        conn.prepareStatement(table).execute();
        conn.commit();
        conn.close();
    }
    public void InsertarDatos (CSVParser datos) throws SQLException {
        this.conn = new DaoMySqlFactory().getConnection();
        for(CSVRecord row: datos) {
            int idFactura = Integer.parseInt(row.get("idFactura"));
            int idCliente = Integer.parseInt(row.get("idCliente"));
            String insert = "INSERT INTO Factura(idFactura,idCliente) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1,idFactura);
            ps.setInt(2, idCliente);
            ps.executeUpdate();
            ps.close();
            conn.commit();
            conn.close();
        }
    }
    @Override
    public void productoConMasRecaudacion() {

    }
}
