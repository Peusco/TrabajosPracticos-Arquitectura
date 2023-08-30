package Factory;

import Dao.*;

public abstract class DaoFactory {

    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public static final int JPA_HIBERNATE = 3;

    public abstract ClienteDao getClienteDao();
    public abstract FacturaDao getFacturaDao();
    public abstract FacturaProductoDao getFacturaPDao();
    public abstract ProductoDao getProductoDao();


    public static DaoFactory getDaoFactory(int f){
        switch (f){
            case MYSQL_JDBC -> {
                return new DaoMySqlFactory();
            }
            case DERBY_JDBC -> {
                return null;
            }
            case JPA_HIBERNATE -> {
                return null;
            }
        }
        return null;
    }

}
