package com.evereats.fooder.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FreightTaxValidator implements ConstraintValidator<FreightTax, Number> {

    private int minimumValue;

    @Override
    public void initialize(FreightTax constraintAnnotation) {
        this.minimumValue = constraintAnnotation.minimumValue();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.intValue() >= this.minimumValue;
    }
}
