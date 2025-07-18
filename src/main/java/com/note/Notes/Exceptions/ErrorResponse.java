package com.note.Notes.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timeOfError;

    public ErrorResponse(String message,int status,LocalDateTime timeOfError)
    {
          this.message=message;
          this.status=status;
          this.timeOfError=timeOfError;
    }

}
