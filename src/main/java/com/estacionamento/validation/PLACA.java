package com.estacionamento.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Constraint(validatedBy = {})
@Pattern(regexp = "([a-zA-Z]{3}-\\d{4})?")
public @interface PLACA {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{com.estacionamento.constraints.PLACA.message}";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
