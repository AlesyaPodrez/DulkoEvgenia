package com.springapp.mvc.validation;

/**
 * Created by Евгения on 25.05.2015.
 */

import com.springapp.mvc.domain.MyTable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Евгения on 25.05.2015.
 */
@Component
public class ClientValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MyTable.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cSurname", "required.cSurname", "Поле обязательно для ввода.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cName", "required.cName", "Поле обязательно для ввода.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cPhone", "required.cPhone", "Поле обязательно для ввода.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cAdress", "required.cAdress", "Поле обязательно для ввода.");
    }
}
