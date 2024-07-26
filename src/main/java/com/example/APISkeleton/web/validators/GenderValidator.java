package com.example.APISkeleton.web.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, Character> {

    @Override
    public void initialize(ValidGender constraintAnnotation) {
    }

    @Override
    public boolean isValid(Character gender, ConstraintValidatorContext context) {
        return gender != null && (gender.equals('H') || gender.equals('M'));
    }
}
