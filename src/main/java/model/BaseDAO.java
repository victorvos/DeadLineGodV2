package model;
/**
 * Created by Victor on 19-5-2016.
 */
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {
    protected final Connection getConnection() {
        Connection result = null;
        try {
            InitialContext ic = new InitialContext();
            String Database = "deadlinegod";
            String User = "admin2Vy5KAK";
            String Password = "ZhGS4GsCucP4";
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/MySQLDS");

            result = ds.getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
