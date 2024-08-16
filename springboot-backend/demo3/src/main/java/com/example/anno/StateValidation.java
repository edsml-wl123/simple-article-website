package com.example.anno;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null)return false;

        return s.equalsIgnoreCase("draft") || s.equalsIgnoreCase("published");
    }
}
