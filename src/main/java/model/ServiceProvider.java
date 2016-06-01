package model;

/**
 * Created by Eigenaar on 20-5-2016.
 */
public class ServiceProvider {
    private static DeadlineService service = new DeadlineService();

    public static DeadlineService getDeadlineService(){
        if (service != null){
            return service;
        }
        else{
            return null;
        }
    }
}
