package model;


import exception.WrongInputException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String title;
    private String description;
    //        private final model.TaskType taskType;
    private final TaskType taskType;
    private final LocalDateTime firstDate;
    private static Integer counter = 1;
    private final Integer id;
    private boolean archived;


    public Task(String title, String description, TaskType taskType, LocalDateTime LocalDateTime) throws WrongInputException {
        this.title = ValidateUtils.checkString(title);
        this.description = ValidateUtils.checkString(description);
        this.taskType = taskType;
        this.firstDate = LocalDateTime;
        this.archived = false;
        id = counter++;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public Integer getId() {
        return id;
    }

    public static void setCounter(Integer counter) {
        Task.counter = counter;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract boolean checkOccurrence(LocalDateTime LocalDateTime);

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, taskType, firstDate, archived ,id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", firstDate=" + firstDate +
                ", id=" + id +
                '}';
    }
}

