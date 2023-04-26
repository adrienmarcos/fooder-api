package com.evereats.fooder.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ZeroIncludesDescriptionValidator.class })
public @interface ZeroIncludesDescription {

    String message() default "Required description is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldValue();

    String fieldDescription();

    String requiredDescription();
}
