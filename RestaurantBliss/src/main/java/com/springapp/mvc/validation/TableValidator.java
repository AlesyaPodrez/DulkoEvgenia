package com.springapp.mvc.validation;

import com.springapp.mvc.domain.MyTable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Евгения on 25.05.2015.
 */
@Component
public class TableValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return MyTable.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tNumOfSeats", "required.tNumOfSeats", "Поле обязательно для ввода.");
    }
}
