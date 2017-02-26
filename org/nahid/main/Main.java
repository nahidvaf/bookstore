package org.nahid.main;

import org.nahid.stock.*;
import org.nahid.domain.*;
/**
 * @author Nahid Vafaie
 * The main application. Creates a BookList and a menu,
 * and starts the text-based menu.
 *
 * Requires no arguments.
 */
public class Main{
    public static void main(String[] args){
	BookList stock = new FileStock();
	TextMenu menu = new TextMenu(stock);
	menu.start();
    }
}
