package Dao;

import Factory.DaoMySqlFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaProductoDao implements TiendaDao{
    Connection conn;
    public void crearTabla () throws SQLException {
        this.conn = new DaoMySqlFactory().getConnection();
        String table= "CREATE TABLE Factura_Producto("+
                "idFactura INT,"+
                "idProducto INT," +
                "cantidad INT);";
        conn.prepareStatement(table).execute();
        conn.commit();
        conn.close();
    }
    public void InsertarDatos (CSVParser datos) throws SQLException {
        this.conn = new DaoMySqlFactory().getConnection();
        for(CSVRecord row: datos) {
            int idFactura = Integer.parseInt(row.get("idFactura"));
            int idProducto = Integer.parseInt(row.get("idProducto"));
            int cantidad = Integer.parseInt(row.get("cantidad"));
            String insert = "INSERT INTO Factura_Producto(idFactura,idProducto,cantidad) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1,idFactura);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
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
