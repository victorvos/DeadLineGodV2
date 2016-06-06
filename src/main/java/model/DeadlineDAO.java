package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 19-5-2016.
 */

public class DeadlineDAO extends BaseDAO {

    private List<Deadline> selectDeadlines(String query) {
        List<Deadline> deadlineList = new ArrayList<Deadline>();

        try (Connection con = super.getConnection()){

            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int ID = dbResultSet.getInt("ID");
                String naam = dbResultSet.getString("naam");
                String beschrijving = dbResultSet.getString("beschrijving");
                String URI = dbResultSet.getString("URI");
                int beoordeling = dbResultSet.getInt("beoordeling");
                Date datum = dbResultSet.getDate("datum");
                Klas k = new Klas(dbResultSet.getString("klasCode"));

                Deadline deadline = new Deadline(naam, datum, k);
                deadline.setBeoordeling(beoordeling);
                deadline.setID(ID);
                deadline.setBeschrijving(beschrijving);
                deadline.setURI(URI);

                deadlineList.add(deadline);
            }

        } catch (SQLException sqle) { sqle.printStackTrace(); }
        return deadlineList;
    }


    public Deadline addDeadline(Deadline d) {
        try (Connection con = super.getConnection()) {
            String URI = "", beschrijving = "";
            int beoordeling = 0;

            String naam = d.getNaam();
            if(!d.getURI().equals(null)){
                URI = d.getURI();
            }
            if(!d.getBeschrijving().equals(null)){
                beschrijving = d.getBeschrijving();
            }
            if(d.getBeoordeling()!=0){
                beoordeling = d.getBeoordeling();
            }
            String k = d.getK().getKlasCode();
            Date datum = d.getDatum();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO deadline (naam,beschrijving,URI,beoordeling,datum,klas) VALUES(?,?,?,?,?,?,?)");
            pstmt.setString(1, naam);
            pstmt.setString(2, beschrijving);
            pstmt.setString(3, URI);
            pstmt.setString(4, naam);
            pstmt.setInt(5, beoordeling);
            pstmt.setDate(6, datum);
            pstmt.setString(7, k);

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return d;
    }

    public Deadline updateDeadline(Deadline d) {

        try(Connection con = super.getConnection()) {

            String URI = "", beschrijving = "";
            int beoordeling = 0;

            String naam = d.getNaam();
            if(!d.getURI().equals(null)){
                URI = d.getURI();
            }
            if(!d.getBeschrijving().equals(null)){
                beschrijving = d.getBeschrijving();
            }
            if(d.getBeoordeling()!=0){
                beoordeling = d.getBeoordeling();
            }
            String k = d.getK().getKlasCode();
            Date datum = d.getDatum();
            int ID = d.getID();

            PreparedStatement pstmt = con.prepareStatement("UPDATE deadline SET naam= ?, beschrijving = ?, URI= ?, beoordeling = ?, datum = ? WHERE ID = ?)");

            pstmt.setString(1, naam);
            pstmt.setString(2, beschrijving);
            pstmt.setString(3, URI);
            pstmt.setInt(4, beoordeling);
            pstmt.setDate(5, datum);
            pstmt.setInt(6, ID);

            pstmt.executeUpdate();
        }catch (SQLException sqle) { sqle.printStackTrace(); }

        return d;

    }

    public boolean delete(Deadline d) {

        try (Connection con = super.getConnection()) {
            int ID = d.getID();

            PreparedStatement pstmt = con.prepareStatement("DELETE FROM deadline WHERE ID= ?");

            pstmt.setInt(1, ID);
            if (pstmt.execute()) {
                pstmt.executeUpdate();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    public List<Deadline> findAll(){
        return selectDeadlines("select * from deadline");
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

//    public Deadline findByID(String ID) {
//        return selectDeadlines("select * from deadline where ID = "+ ID + "").get(0);
//    }
}
