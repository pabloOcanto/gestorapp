package ar.com.gestor.stats.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.gestor.stats.model.ErrorMessage;
@ControllerAdvice
public class HandlerException  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NotFoundException.class})
    public ResponseEntity<?> handlerCustomExceptions(Exception ex) {
    	    	
        return new ResponseEntity<>(
        		new ErrorMessage(400,ex.getLocalizedMessage(),new Date()),
        		new HttpHeaders(),HttpStatus.NOT_FOUND 		
        		);
        }
    
    @ExceptionHandler
    public ResponseEntity<?> handlerExceptions(Exception ex) {
    	    	
        return new ResponseEntity<>(
        		new ErrorMessage(500,ex.getLocalizedMessage(),new Date()),
        		new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR 		
        		);
        }
}
