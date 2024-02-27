package Users;

public class User {
    private String username;
    private String password;

    public User(String username, String pw){
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
