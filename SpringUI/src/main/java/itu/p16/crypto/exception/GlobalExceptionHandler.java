package itu.p16.crypto.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoUserLoggedException.class)
    public String handleNoUserLogged(NoUserLoggedException e) {
        return new Redirection("/?msg=" + e.getMessage()).getUrl();
    }


}
