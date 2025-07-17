package com.note.Notes.Exceptions;

public class DuplicateUser extends RuntimeException{
    public DuplicateUser(String message)
    {
        super(message);
    }
}
