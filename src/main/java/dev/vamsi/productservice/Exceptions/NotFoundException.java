package dev.vamsi.productservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Something is Not found")
public class NotFoundException extends Exception{
    public NotFoundException(String message)
    {
        super(message);
    }
}
