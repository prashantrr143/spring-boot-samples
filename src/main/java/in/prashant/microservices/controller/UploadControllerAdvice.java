package in.prashant.microservices.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
public class UploadControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { MaxUploadSizeExceededException.class, IllegalArgumentException.class,
			IllegalStateException.class, Exception.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		System.out.println("Maximum Size Limit Exceeded error raised in system");
		String bodyOfResponse = "This should be application specific";
		return new ResponseEntity<Object>("Ye kya file upload kar di" , HttpStatus.BAD_REQUEST);
	//	return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
