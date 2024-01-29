public class Customer extends User {
    private String emailAddress;

    public Customer(String username, String email, String password){
        super(username,password);
        this.emailAddress = email;
    }

    public String getEmail(){ 
        return this.emailAddress;
    }

    
}
