package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eigenaar on 1-6-2016.
 */
public class UserDAO extends BaseDAO {

    private List<User> selectUsers(String query) {
        List<User> results = new ArrayList<User>();

        try (Connection con = super.getConnection()){

            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                String email = dbResultSet.getString("email");
                String password = dbResultSet.getString("password");
                String naam = dbResultSet.getString("naam");
                int isDocent = dbResultSet.getInt("isDocent");
                String tussenvoegsel = dbResultSet.getString("tussenvoegsel");
                Klas k = new Klas(dbResultSet.getString("klasCode"));

                User u = new User(password, email, naam, tussenvoegsel, isDocent, k);
                results.add(u);
            }

        } catch (SQLException sqle) { sqle.printStackTrace(); }
        return results;
    }


    public User save(User u) {
        try (Connection con = super.getConnection()) {

            String email = u.getEmail();
            String password = u.getPassword();
            String naam = u.getNaam();
            int isDocent = u.getIsDocent();
            String tussenvoegsel = u.getTussenvoegsel();
            Klas k = u.getK();

            Statement statement = con.createStatement();
            statement.executeQuery("INSERT INTO deadlines (password,email,naam,tussenvoegsel,isDocent, klasCode) VALUES" +
                    "(" + password + "," + email + "," + naam + "," + tussenvoegsel + "," + isDocent + "," + k + ")");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return u;
    }

    public User update(User u) {

        try(Connection con = super.getConnection()) {

            String ID = u.getUserID();
            String email = u.getEmail();
            String password = u.getPassword();
            String naam = u.getNaam();
            int isDocent = u.getIsDocent();
            String tussenvoegsel = u.getTussenvoegsel();
            Klas k = u.getK();

            Statement statement = con.createStatement();
            statement.executeQuery("UPDATE country SET email="+email+", password"+password+", naam="+naam+", isDocent"+isDocent+", tussenvoegsel="+tussenvoegsel+" WHERE ID="+ID+")");

        }catch (SQLException sqle) { sqle.printStackTrace(); }

        return u;
    }

    public User findByEmail(String em) {
        return selectUsers("select * from user where emailadres = "+ em + "").get(0);
    }
}
