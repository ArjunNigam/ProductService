package com.arjunnigam.productservice.controlleradvices;

import com.arjunnigam.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // This annotation marks the class as Controller Advice. Now whenever response is coming from whichever controller,
                  // before getting tothe Client, it will go via all the Controller Advices
public class ProductServiceExceptionHandler {

    @ExceptionHandler(RuntimeException.class)// If exception of type RuntimeException is coming, then this method gets executed.
    // Even if we have 100 Controllers, if any controller throws runtime exception, this method gets triggered automatically
    public ResponseEntity<String> handlerRuntimeException(RuntimeException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex)
    {
        return new ResponseEntity<>(ex.getProductId() + " is an invalid product ID." + " Please provide a valid productId.", HttpStatus.NOT_FOUND);
    }
}
