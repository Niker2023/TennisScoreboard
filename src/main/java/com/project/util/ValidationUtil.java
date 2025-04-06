package com.project.util;

import com.project.exception.ValidationException;

import java.util.regex.Pattern;

public class ValidationUtil {

    public static void checkName(String playerName) {

        Pattern patternName = Pattern.compile("^[A-Za-zА-Яа-яЁё\\s-.]+$");

        if (!patternName.matcher(playerName).matches()) {
            throw new ValidationException("""
                    Имя введено некорректно! <br>
                                    Допустимы для ввода: буквы, пробел, точка, дефис.<br>
                                    Максимальное длина имени 20 символов.""");
        }
    }

    public static int checkPage(String currentPage, int maxPage) {

        int currentPageNumber;

        Pattern patternPage = Pattern.compile("^\\d+$");

        if (currentPage == null || currentPage.isBlank() || !patternPage.matcher(currentPage).matches()) {
            currentPageNumber = 1;
        } else {
            currentPageNumber = Integer.parseInt(currentPage);
        }

        if (currentPageNumber > maxPage || currentPageNumber < 1) {
            throw new ValidationException("Страницы с данным номером не существует");
        }

        return currentPageNumber;
    }


    public static void checkUUID(String uuid) {

        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        if (uuid == null || uuid.isBlank() || !UUID_REGEX.matcher(uuid).matches()) {
            throw new ValidationException("UUID<br>" + uuid + "<br>введен некорректно!");
        }
    }
}
