package com.junyi.validation;

import com.junyi.ioc.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.Locale;

import static com.junyi.validation.ErrorsMessageDemo.createMessageSource;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: {@link Validator} 示例
 */
public class ValidatorDemo {
    public static void main(String[] args) {
        UserValidator userValidator = new UserValidator();
        User user = new User();
        Errors errors = new BeanPropertyBindingResult(user, "user");
        if (userValidator.supports(user.getClass())) {
            userValidator.validate(user, errors);
        }
        MessageSource messageSource = createMessageSource();
        for(ObjectError error: errors.getAllErrors()) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            String name = user.getName();
            // ...
        }
    }
}
