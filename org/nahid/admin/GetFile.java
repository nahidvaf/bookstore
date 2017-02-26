package org.nahid.admin;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.file.*;
/**
 * Small class inspired by code from 
 * http://virt08.itu.chalmers.se/mediawiki/images/7/70/Yrgo_-_Getting_started_with_Winstone.pdf
 * Used with permission of the author.
 *
 * This class can be run as a stand-alone application
 * whenever we want to re-download the inventory file from Contribe.
 * The argument to the main method/application should be the URL to the file.
 *
 * It is also possible to create an instance of this class and call
 * the instance method getFile(String) with the argument of the URL for
 * where to fetch the file, as the argument.
 */
public class GetFile{
    private static final String SEP = File.separator;
    /**
     * The String containing the location for where to store the file.
     */
    public static final String INVENTORY_FILE = "org"+SEP+"nahid"+SEP+"resources"+SEP+"bookstoredata.txt";
    public static void main(String[] args){
	GetFile client = new GetFile();
	try{
	    client.getFile(args[0]);
	}catch(IOException ioe){ioe.printStackTrace();}
    }
    /**
     * Fetches the file with the inventory, from the URL given as argument.
     * @param addr The URL as a String
     * @throws IOException if the file cannot be fetched or stored.
     */
    public void getFile(String addr) throws IOException {
	URL url = new URL(addr);
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.setRequestMethod("GET");
	Files.copy(connection.getInputStream(), Paths.get(INVENTORY_FILE), StandardCopyOption.REPLACE_EXISTING);
	connection.disconnect();
    }
}
