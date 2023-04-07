package model;

import exception.WrongInputException;

import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, TaskType taskType, LocalDateTime LocalDateTime) throws WrongInputException {
        super(title, description, taskType, LocalDateTime);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return (getFirstDate().getDayOfMonth()==requestedDate.getDayOfMonth()&&getFirstDate().getMonth()==requestedDate.getMonth());
    }
}
