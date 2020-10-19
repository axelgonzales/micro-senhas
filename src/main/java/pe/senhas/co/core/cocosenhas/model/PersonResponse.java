package pe.senhas.co.core.cocosenhas.model;

public class PersonResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonResponse(String message) {
        super();
        this.message = message;
    }

    public PersonResponse() {
        super();
    }
}