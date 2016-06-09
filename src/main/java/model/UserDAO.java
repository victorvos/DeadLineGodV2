package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eigenaar on 1-6-2016.
 */
public class UserDAO extends BaseDAO {

    private List<User> selectUsers(String query) {
        List<User> userList = new ArrayList<User>();

        try (Connection con = super.getConnection()){

            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                String email = dbResultSet.getString("email");
                String password = dbResultSet.getString("password");
                String naam = dbResultSet.getString("naam");
                int isDocent = dbResultSet.getInt("isDocent");
                String achternaam = dbResultSet.getString("achternaam");
                String tussenvoegsel = dbResultSet.getString("tussenvoegsel");
                Klas k = new Klas(dbResultSet.getString("klasCode"));

                User u = new User(password, email, naam, achternaam, isDocent, k);
                if(!tussenvoegsel.equals(null)){
                    u.setTussenvoegsel(tussenvoegsel);
                }
                userList.add(u);
            }

        } catch (SQLException sqle) { sqle.printStackTrace(); }
        return userList;
    }


    public boolean findByEmail(String email) {
        int numberOfUsersWithEmail = selectUsers("SELECT * FROM user WHERE email='"+email+"'").size();
        return numberOfUsersWithEmail != 0;
    }


    public boolean registerUser(User u) {
        boolean ru = false;
        try (Connection con = super.getConnection()) {

            String email = u.getEmail();
            String password = u.getPassword();
            String naam = u.getNaam();
            int isDocent = u.getIsDocent();
            String tussenvoegsel = u.getTussenvoegsel();
            String k = u.getK().getKlasCode();
            String achternaam = u.getAchternaam();

            if (!findByEmail(email)) {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO user (password,email,naam,achternaam,tussenvoegsel,isDocent, klasCode) VALUES(?,?,?,?,?,?,?)");
                pstmt.setString(1, password);
                pstmt.setString(2, email);
                pstmt.setString(3, naam);
                pstmt.setString(4, achternaam);
                pstmt.setString(5, tussenvoegsel);
                pstmt.setInt(6, isDocent);
                pstmt.setString(7, k);

                pstmt.executeUpdate();
                ru = true;
            }
            else{
                ru = false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return ru;
    }

    public boolean login(User u){
        boolean login = false;

        String email = u.getEmail();

        if(findByEmail(email)){
            User user = selectUsers("SELECT * FROM user WHERE email='"+email+"'").get(0);
            String password = user.getPassword();

            if (password.equals(u.getPassword())) {
                login = true;
            } else {
                login = false;
            }
        }
        return login;
    }

    public User getUser(String email){
        if(findByEmail(email)){
            User user = selectUsers("SELECT * FROM user WHERE email='"+email+"'").get(0);
            return user;
        }
        else{
            return null;
        }
    }

    public User updateUser(User u) {

        try(Connection con = super.getConnection()) {

            String ID = u.getUserID();
            String email = u.getEmail();
            String password = u.getPassword();
            String naam = u.getNaam();
            int isDocent = u.getIsDocent();
            String tussenvoegsel = u.getTussenvoegsel();

            PreparedStatement pstmt = con.prepareStatement("UPDATE user SET email= ?, password = ?, naam = ?, isDocent = ?, tussenvoegsel= ? WHERE ID = ?");

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, naam);
            pstmt.setInt(4, isDocent);
            pstmt.setString(5, tussenvoegsel);
            pstmt.setString(6, ID);

            pstmt.executeUpdate();

        }catch (SQLException sqle) { sqle.printStackTrace(); }

        return u;
    }
}
