package org.nahid.main;

import org.nahid.stock.*;
import org.nahid.domain.*;

public class Main{
    public static void main(String[] args){
	BookList stock = new FileStock();
	TextMenu menu = new TextMenu(stock);
	menu.start();
    }
}
