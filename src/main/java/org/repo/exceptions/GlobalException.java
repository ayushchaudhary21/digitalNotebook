package org.repo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {
    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    // for user not found class.
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e)
    {
          return buildResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    // for Notes not found.
    @ExceptionHandler(NotesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotesNotFound(NotesNotFoundException e)
    {
        return buildResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Duplicate User

    @ExceptionHandler(DuplicateUser.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUser(DuplicateUser e)
    {
        return buildResponse(e.getMessage(), HttpStatus.CONFLICT);
    }

    // handle Duplicate Email
    @ExceptionHandler(DuplicateEmail.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmail(DuplicateEmail e)
    {
        return buildResponse(e.getMessage(), HttpStatus.CONFLICT);
    }

    // handle Duplicate Notes

    @ExceptionHandler(DuplicateTitle.class)
    public ResponseEntity<ErrorResponse> handleDuplicateNote(DuplicateTitle e)
    {
        return buildResponse(e.getMessage(), HttpStatus.CONFLICT);
    }
    // Handle Internal Server Errors
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(InternalServerErrorException e) {
        log.error("Internal server error "+e.getMessage());
        return buildResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }




    // Helper to make custom pojo for builder response;
    private ResponseEntity<ErrorResponse>buildResponse(String message,HttpStatus status)
    {
         ErrorResponse errorResponse=new ErrorResponse(
             message,
                 status.value(),
                 LocalDateTime.now()
         );
         return new ResponseEntity<>(errorResponse,status);
    }
}
