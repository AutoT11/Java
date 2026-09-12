package API.reqresIN.models.register;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {

    private int id;
    private String token;
    private String context;

    public int getId() {return id;}
    public String getToken() {return token;}


}
