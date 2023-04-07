package model;

import exception.WrongInputException;

import java.time.LocalDateTime;

public class WeekyTask extends Task{
    public WeekyTask(String title, String description, TaskType taskType, LocalDateTime LocalDateTime) throws WrongInputException {
        super(title, description, taskType, LocalDateTime);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek()==(requestedDate.getDayOfWeek());
    }
}
