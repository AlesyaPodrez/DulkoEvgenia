package com.springapp.mvc.validation;

import com.springapp.mvc.domain.DishType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by Евгения on 25.05.2015.
 */
@Component
public class DishTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return DishType.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dtName", "dtName", "Поле обязательно для ввода.");
    }

}
