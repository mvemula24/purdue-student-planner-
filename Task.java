import java.time.LocalDate;

public class Task {
    private String title;
    private LocalDate dueDate;
    private boolean completion;

    public Task(String title, LocalDate dueDate, boolean completion){
        this.title = title;
        this.dueDate = dueDate;
        this.completion = completion;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completion;
    }

    public void setCompleted(boolean completion){
        this.completion = completion;
    }

    public String getType(){
        return "TASK";
    }

    public String output(){
        return getType() + "," + title.replace(",", " ") + "," + dueDate + "," + completion;
    }

    public String toString(){
        String box;
        if(completion){
            box = "[X]";
        } else {
            box = "[ ]";
        }

        return box + " " + getType() + ": " + title + " (due " + dueDate + ")";
    }


}