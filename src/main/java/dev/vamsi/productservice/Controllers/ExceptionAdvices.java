package dev.vamsi.productservice.Controllers;

import dev.vamsi.productservice.DTOs.Errorresponsedto;
import dev.vamsi.productservice.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Errorresponsedto> HandleNotFoundException(Exception exception){
        Errorresponsedto errorresponsedto = new Errorresponsedto();
        errorresponsedto.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorresponsedto, HttpStatus.NOT_FOUND);
    }
}
