package pe.senhas.co.core.cocosenhas.model;

public class VideoResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VideoResponse(String message) {
        super();
        this.message = message;
    }

    public VideoResponse() {
        super();
    }
}