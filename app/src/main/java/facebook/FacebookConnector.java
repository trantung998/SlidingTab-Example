package facebook;

/**
 * Created by Tran on 6/10/2015.
 */
public class FacebookConnector {
    private static FacebookConnector instance =  new FacebookConnector();

    private FacebookConnector(){

    }

    public static FacebookConnector getInstance() {
        return instance;
    }


}
