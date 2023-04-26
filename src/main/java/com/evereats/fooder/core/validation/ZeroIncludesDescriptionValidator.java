package com.evereats.fooder.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ZeroIncludesDescriptionValidator implements ConstraintValidator<ZeroIncludesDescription, Object> {

    private String fieldValue;
    private String fieldDescription;
    private String requiredDescription;

    @Override
    public void initialize(ZeroIncludesDescription constraintAnnotation) {
        this.fieldValue = constraintAnnotation.fieldValue();
        this.fieldDescription = constraintAnnotation.fieldDescription();
        this.requiredDescription = constraintAnnotation.requiredDescription();
    }

    @Override
    public boolean isValid(Object validationObject, ConstraintValidatorContext context) {
        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(validationObject.getClass(), fieldValue)
                    .getReadMethod().invoke(validationObject);
            String description = (String) BeanUtils.getPropertyDescriptor(validationObject.getClass(), fieldDescription)
                    .getReadMethod().invoke(validationObject);
            if (value != null && value.compareTo(BigDecimal.ZERO) > 0) return true;
            if (value != null && value.compareTo(BigDecimal.ZERO) == 0 && description != null) {
               return description.toLowerCase().contains(this.requiredDescription.toLowerCase());
            }
            return false;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
