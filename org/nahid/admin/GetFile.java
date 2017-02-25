package org.nahid.admin;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.file.*;
/**
 * Small class inspired by code from 
 * http://virt08.itu.chalmers.se/mediawiki/images/7/70/Yrgo_-_Getting_started_with_Winstone.pdf
 * Used with the  permission of the author.
 */
public class GetFile{
    private static final String SEP = File.separator;
    public static final String INVENTORY_FILE = "org"+SEP+"nahid"+SEP+"resources"+SEP+"bookstoredata.txt";
    public static void main(String[] args){
	GetFile client = new GetFile();
	try{
	    client.getFile(args[0]);
	}catch(IOException ioe){ioe.printStackTrace();}
    }
    public void getFile(String addr) throws IOException {
	URL url = new URL(addr);
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.setRequestMethod("GET");
	Files.copy(connection.getInputStream(), Paths.get(INVENTORY_FILE), StandardCopyOption.REPLACE_EXISTING);
	connection.disconnect();
    }
}
