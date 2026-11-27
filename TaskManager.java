import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private boolean showCompleted = true;

    public TaskManager(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }


    public boolean isShowingCompleted() {
        return showCompleted;
    }

    public void toggleShowCompleted() {
        showCompleted = !showCompleted;
    }

    public ArrayList<Task> getDisplayedTasks() {
        ArrayList<Task> displayed = new ArrayList<>();
        for(Task task : tasks) {
            if(showCompleted || !task.isCompleted()){
                displayed.add(task);
            }
        }
        sortByDueDate(displayed);
        return displayed;
    }

    public boolean markComplete(int number) {
        ArrayList<Task> displayed = getDisplayedTasks();
        if(number < 1 || number > displayed.size()){
            return false;
        }
        displayed.get(number - 1).setCompleted(true);
        return true;
    }

    public boolean deleteTask(int number){
        ArrayList<Task> displayed = getDisplayedTasks();
        if(number < 1 || number > displayed.size()){
            return false;
        }

        tasks.remove(displayed.get(number - 1));
        return true;
    }

    private void sortByDueDate(ArrayList<Task> list){
        for(int i = 0; i < list.size() - 1; i++){
            for(int j = 0; j < list.size() - 1; j++){
                Task current = list.get(j);
                Task next = list.get(j + 1);
                if (current.getDueDate().isAfter(next.getDueDate())) {
                    list.set(j, next);
                    list.set(j + 1, current);
                }
            }

        }
    }


}