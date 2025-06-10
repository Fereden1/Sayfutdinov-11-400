import java.io.Serializable;

public class QuestionResponse implements Serializable {
    public String question;
    public String response;

    public QuestionResponse(String question, String response) {
        this.question = question;
        this.response = response;
    }
}