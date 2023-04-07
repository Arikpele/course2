package model;

import exception.WrongInputException;

import java.time.LocalDateTime;
public class OncelyTask extends Task{

    public OncelyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType,date);
    }



    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfMonth() == (requestedDate.getDayOfMonth());
    }

//    @Override
//    public boolean checkOcurrance(LocalDateTime requestedDate) {
//        return getFirstDate().getDayOfMonth()==(requestedDate.getDayOfMonth());
//    }
}
