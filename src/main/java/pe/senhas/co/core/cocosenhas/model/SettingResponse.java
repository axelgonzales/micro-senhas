package pe.senhas.co.core.cocosenhas.model;

public class SettingResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SettingResponse(String message) {
        super();
        this.message = message;
    }

    public SettingResponse() {
        super();
    }
}