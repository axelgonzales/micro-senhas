package pe.senhas.co.core.cocosenhas.model;

public class ProfileResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProfileResponse(String message) {
        super();
        this.message = message;
    }

    public ProfileResponse() {
        super();
    }
}