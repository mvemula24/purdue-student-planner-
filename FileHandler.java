import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandler {
    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while (line != null) {
                if (!line.isEmpty()) {
                    Task task = parseLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println("Could not read " + filename);
        }

        return tasks;
    }

    private Task parseLine(String line) {
        try {
            String[] parts = line.split(",");
            String type = parts[0];
            String title = parts[1];
            LocalDate dueDate = LocalDate.parse(parts[2]);
            boolean completed = Boolean.parseBoolean(parts[3]);

            if (type.equals("EXAM")) {
                return new Exam(title, dueDate, completed);
            }

            return new Assignment(title, dueDate, completed);

        } catch (Exception e) {
            System.out.println("Skipping unreadable line: " + line);
            return null;
        }
    }

    public void save(ArrayList<Task> tasks) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                out.println(task.output());
            }
        } catch (IOException e) {
            System.out.println("Could not save to " + filename);
        }
    }

}