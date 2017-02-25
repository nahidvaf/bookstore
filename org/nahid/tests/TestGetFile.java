package org.nahid.tests;
import org.nahid.admin.GetFile;
import java.io.File;
import java.io.IOException;

public class TestGetFile{
    private static final String FILE_URL = "http://www.contribe.se/bookstoredata/bookstoredata.txt";
    public static void main(String[] args){
	System.out.println("Running test for getting the file.");
	System.out.print("Creating the client ...");
	GetFile client = new GetFile();
	try{
	    client.getFile(FILE_URL);
	}catch(IOException ioe){
	    ioe.printStackTrace();
	    assert false: "Exception getting file: " + ioe.getMessage();
	}
	System.out.println("Done!");
	System.out.print("Checking that the file exists...");
	File bookFile = new File(GetFile.INVENTORY_FILE);
	assert bookFile.exists() : "File Does not exist.";
	System.out.println("Done!");
	System.out.print("Checking that the file is not empty...");
	assert bookFile.length() != 0 : "File Exists but is empty.";
	System.out.println("Done!");
	System.out.println("All tests passed!");
    }


}
