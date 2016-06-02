package model;



import java.util.ArrayList;

/**
 * Created by Victor on 17-5-2016.
 */
public class DeadlineService {
    private ArrayList<User> userList = new ArrayList<User>();
    ArrayList<Deadline> deadLines = new ArrayList<Deadline>();
    private Deadline d;

//    public void addDeadlineForKlas(String naam, String beschrijving, String URI, String datum, int beoordeling, Klas k){
//        if(!naam.equals(null)||!naam.equals("") && !datum.equals(null)||!datum.equals("")){
//            Deadline dl = new Deadline(naam, beschrijving, URI, datum, k);
//            k.addDeadLine(dl);
//        }
//        else{
//            throw new IllegalArgumentException("naam en datum moeten worden ingevuld!");
//        }
//    }
//
//    public boolean registerUser(String password, String email, String naam, String tussenvoegsel, int isDocent, Klas k) {
//        boolean geregistreerd = false;
//        User user = new User(password, email, naam, tussenvoegsel, isDocent, k);
//        if (userList.isEmpty()) {
//            userList.add(user);
//            geregistreerd = true;
//        }
//        else{
//            for (User u : userList) {
//                if (u.getEmail().equals(email)) {
//                    geregistreerd = false;
//                } else {
//                    geregistreerd = true;
//                }
//            }
//            if (geregistreerd){
//                userList.add(user);
//            }
//        }
//        return geregistreerd;
//    }
//
//
//    public User logingUser(String em, String pw){
//        User x = null;
//        for (User u : userList){
//            if(u.getEmail().equals(em) && u.getPassword().equals(pw)){
//                x = u;
//            }
//        }
//        return x;
    }
}
