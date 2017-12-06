package com.corejava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainClass 
{
public static void main(String[] args) throws IOException {
	Scanner strInput = new Scanner(System.in);
    String choice,cont = "y";        
    
    while( cont.equalsIgnoreCase("y") ) {        	
    	   System.out.println("\t\t  Information System\n\n");
    
        System.out.println("1 ===> Add New  Record ");
        System.out.println("2 ===> View All  Record ");	
        System.out.println("3 ===> Delete  Record ");
        System.out.println("4 ===> Search Specific Record ");
        System.out.println("5 ===> Update Specific Record ");	        
        System.out.print("\n\n");
        System.out.println("Enter your choice: ");
        choice = strInput.nextLine();
        
        if( choice.equals("1") ) {
        		AddRecord();
        } else if( choice.equals("2") ) {
        		ViewAllRecord();
        } else if( choice.equals("3") ) {
        		DeleteRecordByID();
        }	else if( choice.equals("4") ) {
        		SearchRecordbyID();
        }	
	        	
        System.out.println("Do you want to continue? Y/N");
        cont = strInput.nextLine();
       	
    }
}
    
    public static void AddRecord() throws IOException {
		
		BufferedWriter bw = new BufferedWriter( new FileWriter("src/au.txt",true) );
		Scanner strInput = new Scanner(System.in);
		AuthorID aid= new AuthorID();
		System.out.print("Enter the Author  ID: ");
		String auid=strInput.nextLine();
		aid.setAuthorID(auid);
		System.out.print("Enter the Author Name: ");
		aid.setAuthorName( strInput.nextLine());
		System.out.print("Enter the Author DOB ");
		aid.setAuthorBday( strInput.nextLine());;		
		bw.write(aid.getAuthorID()+","+aid.getAuthorName()+","+aid.getAuthorBday());
		bw.flush();
		bw.newLine();
		bw.close();	
		BufferedWriter bw1 = new BufferedWriter( new FileWriter("src/pub.txt",true) );
		//Scanner strInput = new Scanner(System.in);
		PubID pid= new PubID();
		System.out.print("Enter the Publisher  ID: ");
		String puid=strInput.nextLine();
		pid.setPubID(puid);
		System.out.print("Enter the Publisher Name: ");
		pid.setPublisher(strInput.nextLine());
		System.out.println("Enter the Publisher address");
		pid.setPubAddress(strInput.nextLine());
		
		bw1.write(pid.getPubID()+","+pid.getPublisher()+","+pid.getPubAddress());
		bw1.flush();
		bw1.newLine();
		bw1.close();
		BufferedWriter bw2 = new BufferedWriter( new FileWriter("src/isbn.txt",true) );
		//Scanner strInput = new Scanner(System.in);
		ISBN is=new ISBN();
		System.out.print("Enter the ISBN  ID: ");
		is.setIsbn(strInput.nextLine());
		System.out.print("Enter the PUB ID: ");
		is.setPubID(pid.getPubID());
		System.out.println("Enter the Auth ID");
		is.setAuthorID(aid.getAuthorID());
		System.out.println("date of the publications ");
		is.setDate(strInput.nextLine());
		System.out.println("Title of the Book");
		is.setTitle(strInput.nextLine());
		
		bw2.write(is.getIsbn()+","+is.getAuthorID()+","+is.getPubID()+","+is.getDate()+","+is.getTitle());
		bw2.flush();
		bw2.newLine();
		bw2.close();
	
}
    
    public static void ViewAllRecord() throws IOException {
    	BufferedReader br = new BufferedReader( new FileReader("src/au.txt") );
    	String record;
    	System.out.println("-------------------------------------------------------------------------------------------");
    		System.out.println("authorid\t\tauthorname\t\tauthorbday");	
    	while( ( record = br.readLine() ) != null ) {
    			
    		StringTokenizer st = new StringTokenizer(record,",");
    			
    		System.out.println(st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t\t"+st.nextToken());
    	}
    	System.out.println("-------------------------------------------------------------------------------------------");
    	System.out.println();
    	br.close();    		
    		
    
    BufferedReader br1 = new BufferedReader( new FileReader("src/pub.txt") );
	String record1;
	System.out.println("-------------------------------------------------------------------------------------------");
	
		System.out.println("pubid\t\tpublisher\t\tpubAddress");	
	while( ( record1 = br1.readLine() ) != null ) {
			
		StringTokenizer st = new StringTokenizer(record1,",");
			
		System.out.println(st.nextToken()+"\t\t"+st.nextToken()+"\t\t\t"+st.nextToken());
	}
	System.out.println("-------------------------------------------------------------------------------------------");
	System.out.println();
	br1.close(); 

    BufferedReader br2 = new BufferedReader( new FileReader("src/isbn.txt") );
	String record2;
	System.out.println("-------------------------------------------------------------------------------------------");
	
		System.out.println("isbno\t\tauthId\t\tpubid\t\tdate\t\ttitle");	
	while( ( record2 = br2.readLine() ) != null ) {
			
		StringTokenizer st = new StringTokenizer(record2,",");
			
		System.out.println(st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken());
	}
	System.out.println("-------------------------------------------------------------------------------------------");
	
	br2.close(); 
		
}
    
    public static void DeleteRecordByID() throws IOException {
    	
		Scanner strInput =  new Scanner(System.in);
		String ID, record;
		File tempDB = new File("src/autemp.txt");
		File db = new File("src/au.txt");
		if(!tempDB.exists())
		{
		tempDB.createNewFile();	
		}
		BufferedReader br = new BufferedReader( new FileReader( db ) );
		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
		System.out.println("\t\t Delete Employee Record\n");
		
		System.out.println("Enter the Author ID");
		ID =  strInput.nextLine();
		while( ( record = br.readLine() ) != null ) {
			if( record.contains(ID) ) 
				continue;
			bw.write(record);
			bw.flush();
			bw.newLine();
		}
		br.close();
		bw.close();
		
		db.delete();
		tempDB.renameTo(db);
		String  record1;
		File tempDB1 = new File("src/isbntemp.txt");
		File db1 = new File("src/isbn.txt");
		if(!tempDB1.exists())
		{
		tempDB1.createNewFile();	
		}
		BufferedReader br1 = new BufferedReader( new FileReader( db1 ) );
		BufferedWriter bw1 = new BufferedWriter( new FileWriter( tempDB1 ) );
		while( ( record1 = br1.readLine() ) != null ) {
			if( record1.contains(ID) ) 
				continue;
			bw1.write(record1);
			bw1.flush();
			bw1.newLine();
		}
		br1.close();
		bw1.close();
		db1.delete();
		tempDB1.renameTo(db1);

}
    public static void SearchRecordbyID() throws IOException {
		String ID,record;
		Scanner strInput = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader( new FileReader("src/au.txt") );
		
		System.out.println("\t\t Search author  Record\n");
	
		
		System.out.println("Enter the author ID: ");
		ID = strInput.nextLine();
		
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("authorid\t\tauthorname\t\tauthorbday");	
	
		
		while( ( record = br.readLine() ) != null ) {
			
			StringTokenizer st = new StringTokenizer(record,",");
			if( record.contains(ID) ) {
				System.out.println(st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken());
			}
		}
		System.out.println("-------------------------------------------------------------------------------------------");
		br.close();
		
		BufferedReader br1 = new BufferedReader( new FileReader("src/isbn.txt") );
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("isbno\t\tauthId\t\tpubid\t\tdate\t\ttitle");
	
		
		while( ( record = br1.readLine() ) != null ) {
			
			StringTokenizer st = new StringTokenizer(record,",");
			if( record.contains(ID) ) {
				System.out.println(st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken());
			}
		}
		System.out.println("-------------------------------------------------------------------------------------------");
		br1.close();
		
		
}
    public static void updateRecordbyID() throws IOException {
		String newName, newAge, newAddr, record, ID,record2;
		
		File db = new File("naldrix_db.txt");
		File tempDB = new File("naldrix_db_temp.txt");
		
		BufferedReader br = new BufferedReader( new FileReader(db) );
		BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );
		    		
		Scanner strInput = new Scanner(System.in);
		
		System.out.println("\t\t Update Employee Record\n\n");   
	/****/		
		System.out.println("Enter the Employee ID: ");
    		ID = strInput.nextLine();	    		
    		System.out.println(" ------------------------------------------------------------- ");
    		System.out.println("|	ID		Name 				Age			Address 		  |");
    		System.out.println(" ------------------------------------------------------------- ");
    		
    		while( ( record = br.readLine() ) != null ) {
    			
    			StringTokenizer st = new StringTokenizer(record,",");
    			if( record.contains(ID) ) {
    				System.out.println("|	"+st.nextToken()+"	"+st.nextToken()+" 		"+st.nextToken()+"			"+st.nextToken()+"      |");
    			}
    			
    		}	    		
    		System.out.println("|	                                            	          |");
    		System.out.println(" ------------------------------------------------------------- ");
    		
    	br.close();
	/****/    	   
		System.out.println("Enter the new Name: ");
		newName = strInput.nextLine();    		
		System.out.println("Enter the new Age: ");
		newAge = strInput.nextLine();  
		System.out.println("Enter the new Address: ");
		newAddr = strInput.nextLine();  
		
		BufferedReader br2 = new BufferedReader( new FileReader(db) );
			
		while( (record2 = br2.readLine() ) != null ) {    			
			if(record2.contains(ID)) {
				bw.write(ID+","+newName+","+newAge+","+newAddr);
			} else {
			
				bw.write(record2);	
			}    			
			bw.flush();
			bw.newLine();
		}
		
		bw.close();
		br2.close();    		
		db.delete();    		
		boolean success = tempDB.renameTo(db);    		
		System.out.println(success);    		
		
}
    public static  PubID [] importPubIDFile(PubID [] publisherID, File file)       throws IOException
    {
        Scanner scan = new Scanner(file);       //prepare for input
        
        
           //create array
        for ( int i = 0; i < publisherID.length ; i++ )      //setting limit 
            publisherID[i] = new PubID();
            
           // out.println(publisherID.length);
       
        for ( int i = 0; i < publisherID.length ; i++ )      //setting limit 
        {
         
          publisherID[i].pubID = scan.nextLine();
          publisherID[i].publisher = scan.nextLine();
          publisherID[i].pubAddress = scan.nextLine();
         
        }
           
    
      
        scan.close();  
       
        
        
        return publisherID;
    }
    
    public static  AuthorID [] importAuthorIDFile( File file)       throws IOException
    {
        
    	BufferedReader reader = new BufferedReader(new FileReader(file));
    	int lines = 0;
    	while (reader.readLine() != null) {
    	    lines++;
    	}
    	
    	AuthorID author[]=new AuthorID[lines];
    	
        return author;
    }
    
  public static  ISBN [] importISBNFile(ISBN [] isbnID, File file)       throws IOException
    {
        Scanner scan = new Scanner(file);       //prepare for input
        
        
           //create array
        for ( int i = 0; i < isbnID.length ; i++ )      //setting limit 
            isbnID[i] = new ISBN();
            
           // out.println(publisherID.length);
       
        for ( int i = 0; i < isbnID.length ; i++ )      //setting limit 
        {
         
          isbnID[i].isbn = scan.nextLine();
          isbnID[i].authorID = scan.nextLine();
          isbnID[i].pubID = scan.nextLine();
          isbnID[i].date = scan.nextLine();
          isbnID[i].title = scan.nextLine();
         
        }
         

        scan.close();  
       
        
        
        return isbnID;
    }  

}
