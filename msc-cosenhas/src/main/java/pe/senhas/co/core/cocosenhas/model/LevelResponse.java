package pe.senhas.co.core.cocosenhas.model;

public class LevelResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LevelResponse(String message) {
        super();
        this.message = message;
    }

    public LevelResponse() {
        super();
    }
}