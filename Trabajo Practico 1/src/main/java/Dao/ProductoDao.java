package Dao;

import Entidades.Producto;
import Factory.DaoFactory;
import Factory.DaoMySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDao implements TiendaDao{
    public void productoConMasRecaudacion() {
        Producto producto = null;
        try {
            Connection conn = DaoMySqlFactory.getConnection();
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
