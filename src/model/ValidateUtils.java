package model;

import exception.WrongInputException;

public class ValidateUtils {
    public static String checkString(String str) throws WrongInputException {
        if (str == null || str.isEmpty() || str.isBlank()) {
            throw new WrongInputException("Некорретный ввод");
        }
        else{
            return str;
        }
    }
}
