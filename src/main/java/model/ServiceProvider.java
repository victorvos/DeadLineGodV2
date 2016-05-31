package model;

/**
 * Created by Eigenaar on 20-5-2016.
 */
public class ServiceProvider {
    private static BlogService service = new BlogService();

    public static BlogService getBlogService(){
        if (service != null){
            return service;
        }
        else{
            return null;
        }
    }
}
