package model;

import java.time.LocalDate;

public class Task {
    /*
    This class sets all the parameters of a Task, along with its getters, setters and constructor.
    Also, the toString method is overridden o it displays a more readable output.
    */

    //Task parameters.
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean isDone;
    private Priority priority;

    //Task constructor.
    public Task(int id, String title, String description, LocalDate deadline, boolean isDone, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.priority = priority;
    }

    //Getters and Setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    //Override of the toString method
    @Override
    public String toString() {
        return "ID: " + id +
                "\nTítulo: " + title +
                "\nDescrição: " + description +
                "\nData limite para entrega: " + deadline +
                "\nEstá concluída: " + (isDone ? "Sim" : "Não") +
                "\nNível de prioridade: " + priority + "\n";
    }
}
