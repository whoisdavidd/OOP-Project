package Users;

public class Customer extends User {
    private String emailAddress;
    //Shld we have customerID? or just use username?
    //Add amount to customer (start with 1000 then we can update amt based on purchase of ticket?)
    public Customer(String username, String email, String password){
        super(username,password);
        this.emailAddress = email;
    }

    public String getEmail(){ 
        return this.emailAddress;
    }

    
}
