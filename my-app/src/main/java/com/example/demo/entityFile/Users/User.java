package Users;

public class User {
    private String username;
    private String password;

<<<<<<< Updated upstream
    public User(String username, String pw){
=======

    @OneToOne(mappedBy = "user")
    private Customer customer;

    public User() {
        
    }
    public User(String username, String password) {
        super();
>>>>>>> Stashed changes
        this.username = username;
        this.password = pw;
    }

    public String getUsername(){ 
        return this.username;
    }

    public String getPassword(){ // potential security risk, watch privacy and necessity 
        return this.password;
    }
    
}
