package Factory;

import Dao.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoMySqlFactory extends DaoFactory{
    public static final String DRIVER= "com.mysql.cj.jdbc.Driver";
    public static final String URI = "jdbc:mysql://localhost:3306/trabajo_practico_1";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection conn = DriverManager.getConnection(URI,"root","");
        conn.setAutoCommit(false);
        return conn;
    }

    @Override
    public ClienteDao getClienteDao() {
        return new ClienteDao();
    }

    @Override
    public FacturaDao getFacturaDao() {
        return new FacturaDao();
    }

    @Override
    public FacturaProductoDao getFacturaPDao() {
        return new FacturaProductoDao();
    }

    @Override
    public ProductoDao getProductoDao() {
        return new ProductoDao();
    }
}
