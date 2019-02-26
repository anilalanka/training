import java.util.*;
public class SocialNetwork {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Social network name you want to login to, Facebook/Twitter/Google/LinkedIn:");
        do{
            String social_network_name = sc.next().toLowerCase();
            Product socialNetwork = SocialNetworkFactory.createInstance(social_network_name);
            socialNetwork.authenticate(sc);
            System.out.println("Enter Social network name you want to login to, Facebook/Twitter/Google/LinkedIn:");
        }while(sc.hasNext());
    }
}
class SocialNetworkFactory{
    static Product createInstance(String social_network_name){
        if(social_network_name.equals("facebook"))
            return new facebook();
        if(social_network_name.equals("twitter"))
            return new twitter();
        if(social_network_name.equals("google"))
            return new google();
        if(social_network_name.equals("linkedin"))
            return new linkedin();
    return null;
    }
}
class Product{
    private String social_network = getClass().getSimpleName();
    void welcome() {
        System.out.println("Welcome to "+social_network);
    }
    private static Map< String, HashMap<String, String> > map = new HashMap<>();

    static {
        map.put("facebook",new HashMap<>());
        register("facebook","anil","password");

        map.put("twitter",new HashMap<>());
        register("twitter","anil","password");

        map.put("google",new HashMap<>());
        register("google","anil","password");

        map.put("linkedin",new HashMap<>());
        register("linkedin","anil","password");
    }
    void authenticate(Scanner sc){
        System.out.println("Press 'y' to register as a new user.\nTo login, press any other key");
        if(sc.next().toLowerCase().charAt(0) == 'y'){
            System.out.println("User registration\nEnter username:");
            String username  = sc.next();
            System.out.println("Enter password:");
            String password = sc.next();
            register(social_network, username, password);
        }
        System.out.println("User Authentication \nEnter Username:");
        String username = sc.next();
        System.out.println("Enter Password:");
        String password = sc.next();
        login(social_network, username,password);
    }
    private static void register(String social_network, String username, String password){
        map.get(social_network).put(username,password);
    }
    private void login(String social_network, String username, String password){

        if(!Product.map.get(social_network).containsKey(username)){
            System.out.println("Username not found. Please try again");
            return;
        }
        if(Product.map.get(social_network).get(username).equals(password)){
            System.out.println("Hi "+username+", You are now successfully logged in to "+social_network);
        }
        else{
            System.out.println("Invalid password. Please try again");

        }
    }
}
class facebook extends Product{
    facebook(){
        welcome();
    }

}
class twitter extends Product{
    twitter(){
        welcome();
    }
}
class google extends Product{
    google(){
        welcome();
    }
}
class linkedin extends Product{
    linkedin(){
        welcome();
    }
}