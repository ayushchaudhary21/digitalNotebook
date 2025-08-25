package org.repo.exceptions;

public class DuplicateTitle extends RuntimeException{
    public DuplicateTitle(String message)
    {
         super(message);
    }
}
