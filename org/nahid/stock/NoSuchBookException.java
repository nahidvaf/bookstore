package org.nahid.stock;
/**
 * Represents the case that a Book could not be found
 * in the inventory.
 */
public class NoSuchBookException extends Exception{
    public NoSuchBookException(String msg){
	super(msg);
    }
}
