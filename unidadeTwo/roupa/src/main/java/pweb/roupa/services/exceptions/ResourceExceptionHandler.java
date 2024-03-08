package pweb.roupa.services.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pweb.roupa.controllers.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView recursoNaoEncontrado(ResourceNotFoundException e){
        ModelAndView mv = new ModelAndView("erro");
        mv.addObject("erro", e.getMessage());
        return mv;
    }
}
