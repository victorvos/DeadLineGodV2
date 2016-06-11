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
                String beoordeling = dbResultSet.getString("beoordeling");
                Date datum = dbResultSet.getDate("datum");
                String klasCode = dbResultSet.getString("klasCode");

                Klas k = new Klas(klasCode);

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


    public boolean addDeadline(Deadline d) {
        boolean ad = false;
        try (Connection con = super.getConnection()) {
            String URI = "", beschrijving = "";
            String beoordeling = "";

            String naam = d.getNaam();
            if(!d.getURI().equals(null)){
                URI = d.getURI();
            }
            if(!d.getBeschrijving().equals(null)){
                beschrijving = d.getBeschrijving();
            }
            if(d.getBeoordeling()!= null){
                beoordeling = d.getBeoordeling();
            }
            String klasCode = d.getK().getKlasCode();
            Date datum = d.getDatum();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO deadline (naam,beschrijving,URI,beoordeling,datum,klasCode) VALUES(?,?,?,?,?,?)");
            pstmt.setString(1, naam);
            pstmt.setString(2, beschrijving);
            pstmt.setString(3, URI);
            pstmt.setString(4, beoordeling);
            pstmt.setDate(5, datum);
            pstmt.setString(6, klasCode);

            pstmt.executeUpdate();
            ad = true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            ad = false;
        }
        return ad;
    }

    public Deadline updateDeadline(Deadline d) {

        try(Connection con = super.getConnection()) {

            String URI = "", beschrijving = "";
            String beoordeling = "";

            String naam = d.getNaam();
            if(!d.getURI().equals(null)){
                URI = d.getURI();
            }
            if(!d.getBeschrijving().equals(null)){
                beschrijving = d.getBeschrijving();
            }
            if(d.getBeoordeling()!=null){
                beoordeling = d.getBeoordeling();
            }
            String k = d.getK().getKlasCode();
            Date datum = d.getDatum();
            int ID = d.getID();

            PreparedStatement pstmt = con.prepareStatement("UPDATE deadline SET naam= ?, beschrijving = ?, URI= ?, beoordeling = ?, datum = ? WHERE ID = ?)");

            pstmt.setString(1, naam);
            pstmt.setString(2, beschrijving);
            pstmt.setString(3, URI);
            pstmt.setString(4, beoordeling);
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

//    public List<Deadline> findAll(){
//        return selectDeadlines("select * from deadline");
//    }

//    public List<Deadline> get10LargestPopulations() {
//        return selectDeadlines("select * from deadlines order by population desc limit 10");
//    }

    public boolean checkEmptyDeadlines(Klas k){
        int numberOfDeadlinedWithKlas = selectDeadlines("SELECT * FROM deadline WHERE klasCode='"+k.getKlasCode()+"'").size();
        return numberOfDeadlinedWithKlas != 0;
    }

    public List<Deadline> getDeadlinesThisWeekPerKlas(Klas k) {
        if (checkEmptyDeadlines(k)){
            return selectDeadlines("SELECT * FROM deadline WHERE datum BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW() and klasCode='"+k.getKlasCode()+"' ORDER BY datum ASC");
        }
        else {
            return selectDeadlines("SELECT * from deadline");
        }
    }

    public List<Deadline> getDeadlinesThisMonthPerKlas(Klas k) {
        if (checkEmptyDeadlines(k)){
            return selectDeadlines("SELECT * FROM deadline WHERE datum BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW() and klasCode='"+k.getKlasCode()+"' ORDER BY datum ASC");
        }
        else {
            return selectDeadlines("SELECT * from deadline");
        }
    }

    public Deadline findByID(Integer ID) {
        return selectDeadlines("select * from deadline where ID = "+ ID + "").get(0);
    }

    public int findID(Deadline d) {
        int numberOfDeadlinesWithID = selectDeadlines("SELECT * FROM deadline WHERE naam='"+d.getNaam()+"' and beschrijving='"+d.getBeschrijving()+"' and klasCode='"+d.getK().getKlasCode()+"'").size();
        if(numberOfDeadlinesWithID != 0){
            Deadline x = selectDeadlines("SELECT * FROM deadline WHERE naam='"+d.getNaam()+"' and beschrijving='"+d.getBeschrijving()+"' and klasCode='"+d.getK().getKlasCode()+"'").get(0);
            int ID = x.getID();
            return ID;
        } else {
            return 0;
        }
    }

//    public int getID(String email) {
//        return Integer.parseInt(String.valueOf(selectDeadlines("select ID from deadline where email = "+ email + "").get(0)));
//    }

}
