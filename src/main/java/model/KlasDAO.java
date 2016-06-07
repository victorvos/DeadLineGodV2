package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eigenaar on 2-6-2016.
 */
public class KlasDAO extends BaseDAO{
    private List<Klas> selectKlassen(String query) {
        List<Klas> klasList = new ArrayList<Klas>();

        try (Connection con = super.getConnection()){

            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                Klas k = new Klas(dbResultSet.getString("klasCode"));
                klasList.add(k);
            }

        } catch (SQLException sqle) { sqle.printStackTrace(); }
        return klasList;
    }

    public List<Klas> findAll(){
        return selectKlassen("select klasCode from klas");
    }
}
