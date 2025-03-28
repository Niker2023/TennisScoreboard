package com.project.util;

import com.project.exception.ValidationException;

import java.util.regex.Pattern;

public class ValidationUtil {

    public void checkName(String playerName) {

        Pattern patternName = Pattern.compile("^[A-Za-zА-Яа-яЁё\\s-.]+$");

        if (!patternName.matcher(playerName).matches()) {
            throw new ValidationException("""
                    Имя введено некорректно! <br>
                                    Допустимы для ввода: буквы, пробел, точка, дефис.<br>
                                    Максимальное длина имени 20 символов.""");
        }
    }

    public int checkPage(String currentPage) {

        int currentPageNumber;

        Pattern patternPage = Pattern.compile("^\\d+$");

        if (currentPage == null || currentPage.isBlank() || !patternPage.matcher(currentPage).matches()
                || Integer.parseInt(currentPage) < 2) {
            currentPageNumber = 1;
        } else {
            currentPageNumber = Integer.parseInt(currentPage);
        }

        return currentPageNumber;
    }
}
