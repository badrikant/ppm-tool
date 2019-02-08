package io.tool.full.stack.ppmtoolfullstack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author badrikant.soni on 22/01/19
 * @ControllerAdvice is an annotation that allows you to write global code that can be applied to controllers.
 * In our use-case, we are using this for exception handling. @ControllerAdvice applies to all classes that use the @Controller annotation (which as you know extends to classes using @RestController)
 */

// Spring advices all the controllers in the system that at point of time if an exception arises
// come here and find the match handler.
// In nut shall, its a global exception handler for all the service.
@ControllerAdvice
// As this acts like a controller so its annotated as rest controller
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request) {
        //WebRequest is a Generic interface. The main purpose of WebRequest is to be used by web requests interceptors i.e. a class annotated with @ControllerAdvice.
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request) {

        ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
