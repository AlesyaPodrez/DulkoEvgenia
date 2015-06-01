package com.springapp.mvc.validation;

import com.springapp.mvc.domain.Dish;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Евгения on 25.05.2015.
 */
@Component
public class DishValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dName", "dtName", "Поле обязательно для ввода.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dComposition", "dComposition", "Поле обязательно для ввода.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dPrice", "dPrice", "Поле обязательно для ввода.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dMass", "dMass", "Поле обязательно для ввода.");
    }

}
