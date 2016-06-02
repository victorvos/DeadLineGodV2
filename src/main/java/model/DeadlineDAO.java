package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 19-5-2016.
 */
public class DeadlineDAO extends BaseDAO {
    private Deadline deadline;
    private ArrayList<Deadline> daedlineList = new ArrayList<Deadline>();

    private List<Deadline> selectDeadlines(String query) {
        List<Deadline> results = new ArrayList<Deadline>();

        try (Connection con = super.getConnection()){

            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int ID = dbResultSet.getInt("ID");
                String naam = dbResultSet.getString("naam");
                String beschrijving = dbResultSet.getString("beschrijving");
                String URI = dbResultSet.getString("URI");
                int beoordeling = dbResultSet.getInt("beoordeling");
                String datum = String.valueOf(dbResultSet.getDate("datum"));
                Klas k = new Klas(dbResultSet.getString("klasCode"));

                Deadline deadline = new Deadline(naam, beschrijving, URI, datum, ID, beoordeling, k);
                results.add(deadline);
            }

        } catch (SQLException sqle) { sqle.printStackTrace(); }
        return results;
    }


    public Deadline addDeadline(Deadline d) {
        try (Connection con = super.getConnection()) {

            int ID = d.getID();
            String naam = d.getNaam();
            String beschrijving = d.getBeschrijving();
            String URI = d.getURI();
            int beoordeling = d.getBeoordeling();
            String datum = d.getDatum();

            Statement statement = con.createStatement();
            statement.executeQuery("INSERT INTO deadlines (ID,naam,beschrijving,URI,beoordeling, datum) VALUES" +
                    "(" + ID + "," + naam + "," + beschrijving + "," + URI + "," + beoordeling + "," + datum + ")");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return d;
    }

    public List<Deadline> findAll(){
        return selectDeadlines("select * from deadlines");
    }

//    public List<Deadline> get10LargestPopulations() {
//        return selectDeadlines("select * from deadlines order by population desc limit 10");
//    }

    public List<Deadline> getDeadlinesThisWeekPerKlas(Klas k) {
        return selectDeadlines("select * from deadline where datum BETWEEN TRUNC(sysdate, 'DAY') and TRUNC(sysdate+6, 'DAY')-1  from dual and KlasCode = "+k+")");
    }

    public List<Deadline> getDeadlinesThisMonthPerKlas(Klas k) {
        return selectDeadlines("select * from deadline where datum BETWEEN TRUNC(sysdate, 'MONTH') and TRUNC(sysdate+30, 'MONTH')-1  from dual and KlasCode = "+k+")");
    }

    public Deadline findByID(String ID) {
        return selectDeadlines("select * from deadline where ID = "+ ID + "").get(0);
    }

    public Deadline update(Deadline d) {

        try(Connection con = super.getConnection()) {

            int ID = d.getID();
            String naam = d.getNaam();
            String beschrijving = d.getBeschrijving();
            String URI = d.getURI();
            int beoordeling = d.getBeoordeling();
            String datum = d.getDatum();

            Statement statement = con.createStatement();
            statement.executeQuery("UPDATE country SET naam="+naam+", beschrijving"+beschrijving+", URI="+URI+", beoordeling="+beoordeling+", datum="+datum+" WHERE ID="+ID+")");

        }catch (SQLException sqle) { sqle.printStackTrace(); }

        return d;

    }

    public boolean delete(Deadline d) {

        try(Connection con = super.getConnection()) {

            Statement stmt = con.createStatement();
            if(stmt.executeQuery("DELETE FROM country WHERE ID=" + d.getID()) != null) {
                return true;
            }

        }catch (SQLException sqle) { sqle.printStackTrace(); }
        return false;

    }



}
