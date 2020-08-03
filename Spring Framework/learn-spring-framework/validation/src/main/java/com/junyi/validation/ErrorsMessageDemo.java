package com.junyi.validation;

import com.junyi.ioc.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: 错误文案示例
 */
public class ErrorsMessageDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setName("junyi");

        Errors errors = new BeanPropertyBindingResult(user, "user");
        errors.reject("name.properties.not.null");
        errors.rejectValue("name", "name.required");


        List<ObjectError> globalError = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        // allErrors包括了 globalError和fieldErrors
        List<ObjectError> allErrors = errors.getAllErrors();

        MessageSource messageSource = createMessageSource();
        for(ObjectError error: allErrors) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    public static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("name.required", Locale.getDefault(), "the name is empty");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id is empty");
        messageSource.addMessage("name.properties.not.null", Locale.getDefault(), "all properties are not null");
        return messageSource;
    }
}
