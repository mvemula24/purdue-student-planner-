import java.time.LocalDate;

public class Assignment extends Task {
    public Assignment(String title, LocalDate dueDate, boolean completion){
        super(title, dueDate, completion);
    }

    public String output(){
        return "ASSIGNMENT";
    }
}