package com.note.Notes.Exceptions;

public class DuplicateEmail extends RuntimeException{
    public DuplicateEmail(String message)
    {
          super(message);
    }
}
