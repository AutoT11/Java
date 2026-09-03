package API.models.Заявки.Put;

public class PutRequest {

    private int userId;
    private String title;
    private String body;

    public PutRequest(int userId, String title, String body) {

        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId()  {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}
