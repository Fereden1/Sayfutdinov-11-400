import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListBadQuestions implements Serializable {
    public List<BadQuestion> qrs;

    public ListBadQuestions() {
        this.qrs = new ArrayList<>();
    }

    public void add(BadQuestion bq){
        qrs.add(bq);
    }
}