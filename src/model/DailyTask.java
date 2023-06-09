package model;

import exception.WrongInputException;

import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }
@Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return true;
    }
}
