package com.meetingsummary.meeting_summary_agent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meetingsummary.meeting_summary_agent.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleNotFound (ResourceNotFoundException  ex){
    	return new ResponseEntity<ErrorResponse>(
    			new ErrorResponse(
    					404,
    					ex.getMessage()
    					),
    			HttpStatus.NOT_FOUND
    			);
    }
    
    
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFile(InvalidFileException ex){
    	
    	return new ResponseEntity<>(new ErrorResponse(400,ex.getMessage()
    			),
    			HttpStatus.BAD_REQUEST
    			);
    }
    
    
    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateDataException ex){
    	
    	
    	return new ResponseEntity<>(new ErrorResponse(409,ex.getMessage()
    			),
    			HttpStatus.CONFLICT
    			);
    }
    
   
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){
    	
    	String message=
    			ex.getBindingResult()
    			.getFieldErrors()
    			.get(0)
    			.getDefaultMessage();
    	
    	return new ResponseEntity<>(new ErrorResponse(400,message
    			),
    			HttpStatus.BAD_REQUEST
    			);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>handleGlobal(Exception ex){
    	
    	return new ResponseEntity<>(new ErrorResponse(
    			500,
    			ex.getMessage()
    			),
    			HttpStatus.INTERNAL_SERVER_ERROR);
    }




}