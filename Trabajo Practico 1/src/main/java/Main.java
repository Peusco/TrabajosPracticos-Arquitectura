import Dao.ClienteDao;
import Dao.FacturaDao;
import Dao.FacturaProductoDao;
import Dao.ProductoDao;
import Factory.DaoFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    private static ProductoDao productoDAO;
    private static ClienteDao ClienteDAO;
    private static FacturaDao FacturaDAO;
    private static FacturaProductoDao FacturaProductoDAO;
    public static void main(String[] args) throws SQLException, IOException {
        DaoFactory mysqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_JDBC);
        productoDAO = mysqlFactory.getProductoDao();
        ClienteDAO = mysqlFactory.getClienteDao();
        FacturaDAO = mysqlFactory.getFacturaDao();
        FacturaProductoDAO =mysqlFactory.getFacturaPDao();
        /*productoDAO.crearTabla();
        ClienteDAO.crearTabla();
        FacturaDAO.crearTabla();
        FacturaProductoDAO.crearTabla();*/

        @Deprecated
        CSVParser datosFacturasP = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/facturas-productos.csv"));
        @Deprecated
        CSVParser datosClietes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/clientes.csv"));
        @Deprecated
        CSVParser datosFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/facturas.csv"));
        @Deprecated
        CSVParser datosProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/productos.csv"));

        ClienteDAO.InsertarDatos(datosClietes);
    }
}
