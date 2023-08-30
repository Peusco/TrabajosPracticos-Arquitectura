package Dao;

import Entidades.Producto;
import Factory.DaoFactory;
import Factory.DaoMySqlFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDao implements TiendaDao{
    Connection conn;
    public void crearTabla () throws SQLException {
        this.conn = new DaoMySqlFactory().getConnection();
        String table= "CREATE TABLE Producto("+
                "idProducto INT,"+
                "nombre VARCHAR(45)," +
                "valor FLOAT,"+
                "PRIMARY KEY(idProducto));";
        conn.prepareStatement(table).execute();
        conn.commit();
        conn.close();
    }
    public void InsertarDatos (CSVParser datos) throws SQLException {
        this.conn = new DaoMySqlFactory().getConnection();
        for(CSVRecord row: datos) {
            int idProducto = Integer.parseInt(row.get("idProducto"));
            String nombre = row.get("nombre");
            Float valor = Float.parseFloat((row.get("valor")));
            String insert = "INSERT INTO Producto(idProducto,nombre,valor) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1,idProducto);
            ps.setString(2, nombre);
            ps.setFloat(2, valor);
            ps.executeUpdate();
            ps.close();
            conn.commit();
            conn.close();
        }
    }
    public void productoConMasRecaudacion() {
        Producto producto = null;
        try {
            this.conn = DaoMySqlFactory.getConnection();
            String select = "select (p,* , sum(p.valor * fp.cantidad) as total) ; "
                    + "FROM producto p JOIN factura_producto fp ON (p.idProducto = fp.idProducto) "
                    + "WHERE (p.idProducto = fp.idProducto) "
                    + "GROUP BY (idProducto) "
                    + "ORDER BY total DESC; ";;
            System.out.println("prueba");
            PreparedStatement ps = conn.prepareStatement(select);
            System.out.println("funco");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getInt(3) + "," + rs.getFloat(4));
            }
        }catch (Exception e){
            System.out.println(e);
        }



    }
}
