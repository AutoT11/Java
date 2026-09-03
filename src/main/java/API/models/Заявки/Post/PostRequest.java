package API.models.Заявки.Post;

public class PostRequest {

    private int userId;
    private String title;
    private String body;

    public PostRequest (int userId, String title, String body) {

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
