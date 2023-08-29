package Factory;

import Dao.TiendaDao;

public abstract class DaoFactory {

    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public static final int JPA_HIBERNATE = 3;

    public abstract TiendaDao getClienteDao();
    public abstract TiendaDao getFacturaDao();
    public abstract TiendaDao getFacturaPDao();
    public abstract TiendaDao getProductoDao();


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
