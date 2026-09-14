package API.reqresIN.models.register;

public class RegisterRequest {

    public String email;
    public String password;

    public RegisterRequest(String email, String password) {

        this.email = email;
        this.password = password;

    }

    public String getEmail () {return email;}
    public String getPassword () {return password;}

}
