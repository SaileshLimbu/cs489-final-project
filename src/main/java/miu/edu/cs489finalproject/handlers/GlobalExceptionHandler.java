package miu.edu.cs489finalproject.handlers;

import io.jsonwebtoken.ExpiredJwtException;
import miu.edu.cs489finalproject.data.models.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(error.getDefaultMessage(), error.getDefaultMessage());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UsernameNotFoundException ex){
        return new ResponseEntity<>("Username not found.", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> expiredJWTException(ExpiredJwtException ex){
        return new ResponseEntity<>("Jwt Expired.", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseWrapper<String>> handleNoSuchElementException(NoSuchElementException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper("error", ex.getMessage(), null));
    }
}
