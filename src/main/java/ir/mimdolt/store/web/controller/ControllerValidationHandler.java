package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.web.dto.ExceptionDTO;
import ir.mimdolt.store.web.dto.MessageDTO;
import ir.mimdolt.store.web.dto.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.Locale;


@RestController
@ControllerAdvice(annotations = RestController.class)
public class ControllerValidationHandler {
    @Autowired
    private MessageSource msgSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return processFieldError(error);
    }

    private MessageDTO processFieldError(FieldError error) {
        MessageDTO message = null;
        if (error != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
//      String msg = msgSource.getMessage(error.getDefaultMessage(), null, null);
            message = new MessageDTO(error.getField(), error.getDefaultMessage(), MessageType.ERROR);
        }
        return message;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDTO processBusinessException(BusinessException bException) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ExceptionDTO exception = null;
        getClassName((bException.getStackTrace()[0]).getClassName().toString());
        if (bException != null) {
            exception = new ExceptionDTO(bException.getClassName() + "." + bException.getType(), bException.getMessage(), bException.getStackTrace().toString());
        }
        return exception;
    }

    private String getClassName(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(name);
        java.lang.reflect.Type t = null;
        t = c.getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        String str = (pt.getActualTypeArguments()[2].toString());
        String splits[] = str.split("\\.");
        System.out.println(splits[splits.length-1]);
        return null;

    }


}
