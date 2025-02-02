package itu.p16.crypto.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoUserLoggedException.class)
    public String handleNoUserLogged(NoUserLoggedException e) {
        return new Redirection("/?msg=" + e.getMessage()).getUrl();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("page/error"); // Vue d'erreur
        modelAndView.addObject("errorMessage", "Une erreur s'est produite lors dutraitement de votre requête.");
        modelAndView.addObject("errorDetails", ex.getMessage());
        return modelAndView;
    }


}
