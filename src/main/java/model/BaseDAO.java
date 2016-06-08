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
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/MySQLDS");

            result = ds.getConnection();            // CHECK DS.CEONNECTION (not working)!!
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
