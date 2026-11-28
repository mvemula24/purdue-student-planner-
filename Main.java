import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static TaskManager manager;

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("tasks.csv");
        
        manager = new TaskManager(fileHandler.load());

        boolean running = true;
        while (running) {
            printMenu();

            String choice = ask("select number: ");

            if (choice.equals("1")) {
                addTask(false);
            } else if (choice.equals("2")) {
                addTask(true);
            } else if (choice.equals("3")) {
                viewTasks();
            } else if (choice.equals("4")) {
                markTaskComplete();
            } else if (choice.equals("5")) {
                deleteTask();
            } else if (choice.equals("6")) {
                toggleCompleted();
            } else if (choice.equals("7")) {
                fileHandler.save(manager.getAllTasks());
                System.out.println("all saved, bye!");
                running = false;
            } else {
                System.out.println("invalid choice");
            }
        }
    }

    private static void printMenu() {
        IO.println();
        IO.println("Welcome to the Student Planner");
        IO.println("1 - add assignment");
        IO.println("2 - add exam");
        IO.println("3 - view all tasks");
        IO.println("4 - update completed task");
        IO.println("5 - delete task");
        IO.println("6 - hide/show completed tasks");
        IO.println("7 - save and quit");
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        try {
            String line = reader.readLine();
            if (line == null) {
                return "";
            }
            return line;
        } catch (IOException e) {
            return "";
        }
    }

    private static void addTask(boolean isExam) {
        String title = ask("Name: ");
        if (title.isEmpty()) {
            System.out.println("name can't be empty");
            return;
        }
        String dateText = ask("Due date (YYYY-MM-DD): ");
        try {
            LocalDate dueDate = LocalDate.parse(dateText);
            if (isExam) {
                manager.addTask(new Exam(title, dueDate, false));
            } else {
                manager.addTask(new Assignment(title, dueDate, false));
            }
            System.out.println("Added!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Use YYYY-MM-DD (ex: 2026-12-15)");
        }
    }

    private static void viewTasks() {
        ArrayList<Task> displayed = manager.getDisplayedTasks();
        if (!manager.isShowingCompleted()) {
            System.out.println("(completed tasks are hidden)");
        }
        if (displayed.isEmpty()) {
            System.out.println("no tasks yet");
        }
        for (int i = 0; i < displayed.size(); i++) {
            System.out.println((i + 1) + ". " + displayed.get(i));
        }
    }

    private static void markTaskComplete() {
        String numberText = ask("task number: ");
        try {
            int number = Integer.parseInt(numberText);
            if (manager.markComplete(number)) {
                System.out.println("marked complete!");
            } else {
                System.out.println("no task with that number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
        }
    }


    private static void deleteTask() {
        String numberText = ask("task number to delete: ");
        try {
            int number = Integer.parseInt(numberText);
            if (manager.deleteTask(number)) {
                System.out.println("Deleted.");
            } else {
                System.out.println("no task with that number");
            }
        } catch (NumberFormatException e) {
            System.out.println("please enter a number");
        }
    }

    private static void toggleCompleted() {
        manager.toggleShowCompleted();
        if (manager.isShowingCompleted()) {
            System.out.println("now showing completed tasks");
        } else {
            System.out.println("now hiding completed tasks");
        }
    }

}

