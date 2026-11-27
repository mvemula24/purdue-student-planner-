import java.time.LocalDate;

public class Exam extends Task {
    public Exam(String title, LocalDate dueDate, boolean completed){
        super(title, dueDate, completion);
    }

    public String output(){
        return "EXAM";
    }
}