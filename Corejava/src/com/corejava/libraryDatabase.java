package com.corejava;

import static java.lang.System.out; // required library routines to handle System.out requests

// import all the classes from the io package
import java.io.File;
import java.io.IOException;
import java.util.Scanner; //enable the scan function to be use in this class

public class libraryDatabase {

	public static void main() throws IOException // main function

	{
		Scanner scan = new Scanner(System.in); // get input from users

		File pubid = new File("publisher.txt"); // open the file
		File authorid = new File("author.txt");
		File isbnid = new File("ISBN.txt"); // open the file

		PubID[] publisherID = new PubID[size(pubid, "pub")]; // create the number of column in the array
		AuthorID[] author = new AuthorID[size(authorid, "aut")];
		ISBN[] isbnID = new ISBN[size(isbnid, "isbn")];

		publisherID = importPubIDFile(publisherID, pubid);
		author = importAuthorIDFile(author, authorid);
		isbnID = importISBNFile(isbnID, isbnid);

		// inialising all variables
		int option = 0;
		int searchOption = 0;

		boolean go = true;

		while (go = true) // runs while go is true
		{

			displayMenu(); // display main menu from the library
			option = getInt("Please enter your option number"); // get input from users

			switch (option) // uses to switch options
			{

			case 1:
				addInfo(publisherID, author, isbnID);

				break;
			/*
			 * case 2: editInfo("test"); break;
			 */

			case 3:

				searchInfo();
				searchOption = scan.nextInt();

				switch (searchOption) // uses to switch options
				{

				case 1:
					searchByIsbn(isbnID);
					break;

				case 2:
					searchByPub(publisherID);
					break;

				case 3:
					searchByAut(author);
					break;

				case 4:
					searchByBook(isbnID);
					break;

				case 5:
					displayMenu();
					break;

				}

				break;

			case 4:
				deleteInfo(publisherID, author, isbnID);
				break;

			case 0:
				endProgram("Thank you!");
				break;
			}
		}
	}

	public static void deleteInfo(PubID[] publisherID, AuthorID[] author, ISBN[] isbnID) throws IOException {
		Scanner scan = new Scanner(System.in); // get input from users
		int i;
		String isbnid;
		// String

		// show all book info
		out.println("\t\t\t\t\t\t\t\tAll books info");
		line(1);
		out.println("ISBN" + "\t\tAuthorID" + "\t\tPubID" + "\t\tDate" + "\tTitle");
		for (i = 0; i < isbnID.length; i++) {
			out.println(isbnID[i].isbn + "\t" + isbnID[i].authorID + "\t" + isbnID[i].pubID + "\t" + isbnID[i].date
					+ "\t" + isbnID[i].title);
		}
		blank(39);

		out.println("Please enter the ISBN number of the book you would like to delete"); // ask isbn id from user
		isbnid = scan.nextLine(); // scan the isbn id to be deleted

		// check if scan isbn id found in array
		for (i = 0; i < isbnID.length; i++) // setting limit to the loop
		{
			if (isbnid.equals(isbnID[i].isbn)) // setting condition to if statement
			{
				isbnID[i] = null;
				isbnID[i].authorID = null;
				isbnID[i].pubID = null;
				isbnID[i].date = null;
				isbnID[i].title = null;
			}
		}

		for (i = 0; i < isbnID.length; i++) {

		}

		scan.close();
	}

	public static void addInfo(PubID[] publisherID, AuthorID[] author, ISBN[] isbnID) throws IOException {
		Scanner scan = new Scanner(System.in); // get input from users
		int option = 0;
		String bookTitle, isbnid, pubID, publisher, pubAddress, authorID, authorName, authorBDay;
		int date;
		boolean goAut = true;
		boolean goPub = true;

		out.println("\t\t\t\t\t\t\t\tAll books info");
		line(1);
		out.println("ISBN" + "\t\tAuthorID" + "\t\tPubID" + "\t\tDate" + "\tTitle");
		for (int i = 0; i < publisherID.length; i++) {
			out.println(isbnID[i].isbn + "\t" + isbnID[i].authorID + "\t" + isbnID[i].pubID + "\t" + isbnID[i].date
					+ "\t" + isbnID[i].title);
		}
		blank(39);

		out.println("Please enter the ISBN number of the book");
		isbnid = scan.nextLine();
		// isbnID.isbn[push(isbnid)];

		out.println("Please enter the author ID ");
		authorID = scan.nextLine();

		for (int i = 0; i < author.length; i++) {
			if (authorID.equals(author[i].authorID)) {
				goAut = false;

			}
		}
		if (goAut == true) {
			out.println("Please enter author's name");
			authorName = scan.nextLine();
			out.println("Please enter author's birthday (format --> DD-MMM-YYYY )");
			authorBDay = scan.nextLine();
			// AuthorID [] author = new AuthorID [author.length + 1];
		}

		out.println("Please enter the publisher ID you would like to add");
		pubID = scan.nextLine();

		for (int i = 0; i < publisherID.length; i++) {
			if (pubID.equals(publisherID[i].pubID)) {
				goPub = false;

			}
		}
		if (goPub == true) {
			out.println("Please enter the name of the publisher");
			publisher = scan.nextLine();
			out.println("Please enter publisher's address ");
			pubAddress = scan.nextLine();
		}

		out.println("Please enter the book title you would like to add");
		bookTitle = scan.nextLine();

		out.println("Please enter the year of publication you would like to add (format --> xxxx )");
		date = scan.nextInt();

		scan.close();

	}

	public static void searchInfo() throws IOException {
		Scanner scan = new Scanner(System.in); // get input from users
		// int searchOption = 0;

		out.println("Which option would you like to take?"); // output option 1 program's title

		out.println("Option 1:\tSearch by ISBN"); // output option 1 program's title
		out.println("Option 2:\tSearch by Publisher"); // output option 2 program's title
		out.println("Option 3:\tSearch by Author"); // output option 3 program's title
		out.println("Option 4:\tSearch by Book");
		out.println("\nOption 0:\tBack To Main Menu");
		blank(41);
		// int searchOption = scan.nextInt();

		// return searchOption;
	}

	public static void searchByBook(ISBN[] isbnID) throws IOException {
		Scanner scan = new Scanner(System.in);
		int i = 0;
		boolean go = true;

		blank(35);
		out.println("Please enter the book title you would like to search ");
		String targetValue = scan.nextLine();

		while (go = true) {
			while (i < isbnID.length) {

				if (targetValue.equals(isbnID[i].title)) {
					out.println("\t\t\t\t\t\t\t\t\t\tResult ");
					line(1);
					out.println("ISBN ID\t\t:\t" + isbnID[i].isbn);
					out.println("Author ID\t:\t" + isbnID[i].authorID);
					out.println("Publisher ID\t:\t" + isbnID[i].pubID);
					out.println("Date\t\t:\t" + isbnID[i].date);
					out.println("Book Title\t:\t" + isbnID[i].title);
					blank(39);
					delay();
					go = false;

				}

				i++;
			}
		}
	}

	public static void searchByIsbn(ISBN[] isbnID) throws IOException {
		Scanner scan = new Scanner(System.in);
		int i = 0;
		int option = 0;
		boolean go = true;

		blank(35);
		out.println("Please enter IBSN ID you would like to search ( format : x-xxxxx-xxx-x )  ");
		String targetValue = scan.nextLine();

		while (go = true) {
			while (i < isbnID.length) {

				if (targetValue.equals(isbnID[i].isbn)) {
					out.println("\t\t\t\t\t\t\t\t\t\tResult ");
					line(1);
					out.println("ISBN ID\t\t:\t" + isbnID[i].isbn);
					out.println("Author ID\t:\t" + isbnID[i].authorID);
					out.println("Publisher ID\t:\t" + isbnID[i].pubID);
					out.println("Date\t\t:\t" + isbnID[i].date);
					out.println("Book Title\t:\t" + isbnID[i].title);
					blank(39);
					delay();
					go = false;

				}

				i++;
			}
		}

	}

	public static void searchByPub(PubID[] publisherID) throws IOException {
		Scanner scan = new Scanner(System.in);
		int i = 0;
		boolean go = true;

		blank(40);
		out.println("Please enter the publisher ID you would like to search");
		String targetValue = scan.nextLine();

		while (go = true) {
			while (i < publisherID.length) {
				if (targetValue.equals(publisherID[i].pubID)) {
					out.println("\t\t\t\t\t\t\t\t\t\tResult ");
					line(1);
					out.println("Publisher ID\t\t:\t" + publisherID[i].pubID);
					out.println("Publisher Name\t\t:\t" + publisherID[i].publisher);
					out.println("Publisher Address\t:\t" + publisherID[i].pubAddress);
					blank(41);
					delay();
					go = false;
				}

				i++;
			}
		}

	}

	public static void searchByAut(AuthorID[] author) throws IOException {
		Scanner scan = new Scanner(System.in);
		boolean go = true;

		blank(35);
		out.println("Please enter the author ID you would like to search ");
		String targetValue = scan.next();

		while (go = true) {
			for (int i = 0; i < author.length; i++) {

				if (targetValue.equals(author[i].authorID)) {
					out.println("\t\t\t\t\t\t\t\t\t\tResult ");
					line(1);
					out.println("Author ID\t\t:\t" + author[i].authorID);
					out.println("Author Name\t\t:\t" + author[i].authorName);
					out.println("Author Birthday\t\t:\t" + author[i].authorBday);
					blank(41);
					delay();
					go = false;
				}

				// i++;
			}
		}

	}

	public static int size(File file, String id) throws IOException {

		Scanner input = new Scanner(file); // prepare for input
		int x = 1;

		String pub = "pub";
		String aut = "aut";
		String isbn = "isbn";

		if (id.equals(pub) || id.equals(aut)) {
			// count number of array and inout string into array
			while (input.hasNextLine()) {
				String theLine = input.nextLine(); // consume a line
				x++; // count each lines consumed

			}
			input.close();
			x = (x / 3);
			// targetValue.equals(publisherID)
		} else if (id.equals(isbn)) {
			while (input.hasNextLine()) {
				String theLine = input.nextLine(); // consume a line
				x++; // count each lines consumed

			}
			input.close();
			x = (x / 5);
		}

		return x;

	}

	public static PubID[] importPubIDFile(PubID[] publisherID, File file) throws IOException {
		Scanner scan = new Scanner(file); // prepare for input

		// create array
		for (int i = 0; i < publisherID.length; i++) // setting limit
			publisherID[i] = new PubID();

		// out.println(publisherID.length);

		for (int i = 0; i < publisherID.length; i++) // setting limit
		{

			publisherID[i].pubID = scan.nextLine();
			publisherID[i].publisher = scan.nextLine();
			publisherID[i].pubAddress = scan.nextLine();

		}

		scan.close();

		return publisherID;
	}

	public static AuthorID[] importAuthorIDFile(AuthorID[] author, File file) throws IOException {
		Scanner scan = new Scanner(file); // prepare for input

		// create array
		for (int i = 0; i < author.length; i++) // setting limit
			author[i] = new AuthorID();

		// out.println(publisherID.length);

		for (int i = 0; i < author.length; i++) // setting limit
		{

			author[i].authorID = scan.nextLine();
			author[i].authorName = scan.nextLine();
			author[i].authorBday = scan.nextLine();

		}

		scan.close();

		return author;
	}

	public static ISBN[] importISBNFile(ISBN[] isbnID, File file) throws IOException {
		Scanner scan = new Scanner(file); // prepare for input

		// create array
		for (int i = 0; i < isbnID.length; i++) // setting limit
			isbnID[i] = new ISBN();

		// out.println(publisherID.length);

		for (int i = 0; i < isbnID.length; i++) // setting limit
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

	public static void title() // title function, use to outprint the title of the program
	{
		blank(50); // output 50 blank lines in the program
		out.println("\t\t\t\t\t\t\t\tWelcome to Library Database Management System"); // title of the function
		line(1); // output a line in the program
	}

	public static void blank(int tab) // blank function, use to blank lines in the program
	{
		// defining variable as interger
		int i;
		for (i = 1; i <= tab; i++) // setting limit for i
			out.println(); // output blank for one line each time it loops
	}

	public static void line(int lines) // line function, use to draw a line in the program
	{
		// defining variables as interger
		int count;
		int bar;
		for (count = 0; count < lines; count++) // setting limit for count
		{
			for (bar = 0; bar < 155; bar++) // setting limit for bar
			{
				out.print("="); // output = each time it loops
			}
			out.println(); // change to the next line each time it loops
		}
	}

	public static void endProgram(String display) {

		blank(45);
		line(1);
		out.println(display);
		System.exit(0);
	}

	public static void displayMenu() // display menu function, use to display the menu of the programs
	{
		title(); // output the title of the program from the title function
		out.println("Main Menu For The Programs");
		line(1); // output a line in the program
		out.println("Option 1:\tAdding info to the database"); // output option 1 program's title
		out.println("Option 2:\tEditing info in the database"); // output option 2 program's title
		out.println("Option 3:\tSearch info in the database"); // output option 3 program's title
		out.println("Option 4:\tDelete info in the database"); // output option 4 program's title
		out.println("Option 0:\tExit program"); // output option 4 program's title

		blank(37); // output 37 blank lines in the program
	}

	public static int getInt(String display) // getInt function, use to get scan the input of the user
	{
		Scanner scan = new Scanner(System.in); // used to get input from user
		out.println(display); // output the main menu to prompt the user to enter a number
		int option = scan.nextInt(); // scan the number as an integer
		scan.close(); // closing scanner
		return option; // return the option value to the main function
	}

	public static void delay() throws IOException // delay function, use to delay the program in order to output the
													// rsult of the program
	{
		Scanner scan = new Scanner(System.in); // used to get input from user
		out.println("Press ENTER to continue"); // promt the user to press enter to continue the program
		String delayed = scan.nextLine(); // scan the input as a string
		main();
		scan.close();

	}

}
