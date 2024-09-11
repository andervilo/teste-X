package com.x.loan.api.validator;

import com.x.loan.domain.enumeration.IdentificadorType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Constraint(validatedBy = IdentificadorTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIdentificadorType {

    String message() default "Tipo de Identificador inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class IdentificadorTypeValidator implements ConstraintValidator<ValidIdentificadorType, IdentificadorType> {

    @Override
    public boolean isValid(IdentificadorType value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return Arrays.asList(IdentificadorType.values()).contains(value);
    }
}

