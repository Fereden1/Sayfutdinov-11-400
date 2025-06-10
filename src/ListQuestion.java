import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListQuestions implements Serializable {
    public List<QuestionResponse> qrs;

    public ListQuestions() {
        this.qrs = new ArrayList<>();
    }

    public void add(QuestionResponse qr){
        qrs.add(qr);
    }
}