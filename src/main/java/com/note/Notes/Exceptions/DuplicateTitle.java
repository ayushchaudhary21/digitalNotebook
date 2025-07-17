package com.note.Notes.Exceptions;

public class DuplicateTitle extends RuntimeException{
    public DuplicateTitle(String message)
    {
         super(message);
    }
}
