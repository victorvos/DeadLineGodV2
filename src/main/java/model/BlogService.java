package model;



import java.util.ArrayList;

/**
 * Created by Victor on 17-5-2016.
 */
public class BlogService {
    private ArrayList<User> userList = new ArrayList<User>();

    public void addBlogPostForUser(String sub, String txt, User u){
        if(!sub.equals(null)||!sub.equals("") && !txt.equals(null)||!txt.equals("")){
            BlogPost bp = new BlogPost(sub, txt);
            u.addBlogPost(bp);
        }
        else{
            throw new IllegalArgumentException("onderwerp en text moeten worden ingevuld");
        }
    }

    public ArrayList<BlogPost> getAllPosts(){
        ArrayList<BlogPost> allPosts = new ArrayList<BlogPost>();
        for(User user : userList ){
            allPosts.addAll(user.getMyPosts());
        }
        return allPosts;
    }

    public boolean registerUser(String uNm, String pw, String em, String rN) {
        boolean geregistreerd = false;
        User user = new User(uNm, pw, em, rN);
        if (userList.isEmpty()) {
            userList.add(user);
            geregistreerd = true;
        }
        else{
            for (User u : userList) {
                if (u.getUsername().equals(uNm)) {
                    geregistreerd = false;
                } else {
                    geregistreerd = true;
                }
            }
            if (geregistreerd){
                userList.add(user);
            }
        }
        return geregistreerd;
    }


    public User logingUser(String uNm, String pw){
        User x = null;
        for (User u : userList){
            if(u.getUsername().equals(uNm) && u.getPassword().equals(pw)){
                x = u;
            }
        }
        return x;
    }
}
