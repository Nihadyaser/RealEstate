public class User {
    private String username;
    private String password;
    private String phoneNumber;

    boolean isAgent;
    public User(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String toString (){
        return "Username: " + username + " Password: " + password + " Phone: " + phoneNumber;

    }
    public boolean isAgent() {
        return isAgent;
    }
}
