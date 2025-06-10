import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        createQuestions();
        ListQuestions lst = loadQuestions();
        Scanner sc = new Scanner(System.in);
        ListBadQuestions lstBadQuestions = loadBadQuestions();
        while (true) {
            System.out.println("Запрос:");
            String query = sc.nextLine().trim().toLowerCase();
            if (query.equals("остановка")) {
                break;
            }
            List<QuestionResponse> q = lst.qrs;
            List<QuestionResponse> ans = q.stream().
                    filter(qr -> qr.question.toLowerCase().contains(query)
                            || query.contains(qr.question.toLowerCase())).toList();
            if (!ans.isEmpty()){
                System.out.println(ans.get(0).response);
            } else {
                System.out.println("Неудачный запрос, запоминаю!");
                lstBadQuestions.add(new BadQuestion(query, new Date()));
            }
        }
        saveBadQuestions(lstBadQuestions);
    }

    public static void saveBadQuestions(ListBadQuestions lst) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("BadQuestions.txt"))) {
            obj.writeObject(lst);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ListBadQuestions loadBadQuestions() {
        ListBadQuestions lst = new ListBadQuestions();
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("BadQuestions.txt"))) {
            lst = (ListBadQuestions) obj.readObject();
        } catch (EOFException | FileNotFoundException e) {
            return lst;
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public static ListQuestions loadQuestions() {
        ListQuestions listQuestions = new ListQuestions();
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("questions.txt"))) {
            listQuestions = (ListQuestions)obj.readObject();
        } catch (FileNotFoundException e) {
            createQuestions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listQuestions;
    }

    public static void createQuestions() {
        ListQuestions listQuestions = new ListQuestions();
        listQuestions.add(new QuestionResponse("Какая погода?", "как всегда, отличная"));
        listQuestions.add(new QuestionResponse("Как дела?", "Устал"));
        listQuestions.add(new QuestionResponse("Как учеба?", "Закрываю долги"));
        listQuestions.add(new QuestionResponse("Когда экзамен?", "Уже совсем скоро"));
        listQuestions.add(new QuestionResponse("Когда каникулы?", "В августе"));
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("questions.txt"))) {
            obj.writeObject(listQuestions);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {f
            throw new RuntimeException(e);
        }
    }
}