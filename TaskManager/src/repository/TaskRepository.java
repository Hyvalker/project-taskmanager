package repository;

import model.Priority;
import model.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public static void saveTask(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt", true))) {
            writer.write(task.getId() + ";" +
                    task.getTitle() + ";" +
                    task.getDescription() + ";" +
                    task.getDeadline() + ";" +
                    task.isDone() + ";" +
                    task.getPriority() + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefa." + e.getMessage());
        }
    }

    public static void saveAll(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt", false))) {
            for (Task task : tasks) {
                writer.write(task.getId() + ";" +
                        task.getTitle() + ";" +
                        task.getDescription() + ";" +
                        task.getDeadline() + ";" +
                        task.isDone() + ";" +
                        task.getPriority() + "\n");
            }
            System.out.println("Todas as tarefas foram salvas com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas." + e.getMessage());
        }
    }

    public static List<Task> readTask() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 6) {
                    int id = Integer.parseInt(fields[0]);
                    String title = fields[1];
                    String description = fields[2];
                    LocalDate deadline = LocalDate.parse(fields[3]);
                    boolean isDone = Boolean.parseBoolean(fields[4]);
                    Priority priority = Priority.valueOf(fields[5]);
                    Task task = new Task(id, title, description, deadline, isDone, priority);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo " + e.getMessage());
        }
        return tasks;
    }
}
