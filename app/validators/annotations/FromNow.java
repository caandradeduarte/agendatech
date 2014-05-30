package validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import play.data.Form.Display;
import validators.FromNowValidator;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=FromNowValidator.class)
@Display(name="validations.fromNow")
public @interface FromNow {
	
	String message() default "error.fromNow";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };

}
