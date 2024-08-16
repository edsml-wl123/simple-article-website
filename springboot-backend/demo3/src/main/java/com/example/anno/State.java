package com.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "state can only be draft or submitted";

    //自定义validation注解必须有groups, payload两个参数
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
