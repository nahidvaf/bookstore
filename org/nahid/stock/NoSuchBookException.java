package org.nahid.stock;
public class NoSuchBookException extends Exception{
    public NoSuchBookException(String msg){
	super(msg);
    }
}
