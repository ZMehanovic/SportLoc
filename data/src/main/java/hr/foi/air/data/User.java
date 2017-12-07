package hr.foi.air.data;

/**
 * Created by Gabriel on 14.11.2017..
 */

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String gender;
    private String password;
    private String dob;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoginData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setRegistrationData(String firstName, String lastName, String userName, String email, String gender, String password, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.dob = dob;
    }
}
