package pe.senhas.co.core.cocosenhas.model;

public class QualificationResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QualificationResponse(String message) {
        super();
        this.message = message;
    }

    public QualificationResponse() {
        super();
    }
}