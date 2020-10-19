package pe.senhas.co.core.cocosenhas.model;

public class ParameterResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ParameterResponse(String message) {
        super();
        this.message = message;
    }

    public ParameterResponse() {
        super();
    }
}