package com.ecommerce.auth_service.exception;

import com.ecommerce.auth_service.dto.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //    Handler resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException exception){
        logger.info("Exception Handler Invoke !!");
        ApiResponseMessage responseMessage= ApiResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
        return  new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
    }

    // MethodArgumentsNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Map<String,Object>>  handleMethodArgumentsNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        Map<String,Object> response=new HashMap<>();
        allErrors.forEach(objectError -> {
            String message=objectError.getDefaultMessage();
            String field=((FieldError) objectError).getField();
            response.put(field,message);
        });
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<ApiResponseMessage> handleBadApiRequest(BadApiRequestException exception){
        logger.info("Bad Api Request !!");
        ApiResponseMessage responseMessage=ApiResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
        return  new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
    }
}
