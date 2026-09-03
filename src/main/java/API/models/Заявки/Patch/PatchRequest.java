package API.models.Заявки.Patch;

public class PatchRequest {

    private String title;

    public PatchRequest(String title) {

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
