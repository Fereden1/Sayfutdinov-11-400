import java.io.Serializable;
import java.util.Date;

public class BadQuestion implements Serializable {
    String question;
    Date dateQuestion;

    public BadQuestion(String question, Date dateQuestion) {
        this.question = question;
        this.dateQuestion = dateQuestion;
    }
}