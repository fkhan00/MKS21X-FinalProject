import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
//import com.jdwb.twitterapi;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

// terminal interface for Sudoku puzzle
public class Sudoku extends Grid{
	// a list of the x coordinates of points on the terminal
	//that have a _
	public static int[] xCords(){
		int[] ary = new int[9];
		ary[0] = 1;
		ary[1] = 4;
		ary[2] = 7;
		ary[3] = 11;
		ary[4] = 14;
		ary[5] = 17;
		ary[6] = 21;
		ary[7] = 24;
		ary[8] = 27;
		return ary;
	}
	// a list of the y coordinates of points on the terminal
	//that have a _
	public static int[] yCords(){
		int[] ary = new int[9];
		ary[0] = 6;
		ary[1] = 7;
		ary[2] = 8;
		ary[3] = 11;
		ary[4] = 12;
		ary[5] = 13;
		ary[6] = 16;
		ary[7] = 17;
		ary[8] = 18;
		return ary;
	}
	static String solution = "";
	// custom array method to get indexOf item
	public static int indexOf(int[] ary, int num){
		for(int i = 0; i < ary.length; i++){
			if(ary[i] == num){
				return i;}}
		return -1;}
	// putString() is from Mr.K's terminaldemo
	// inserts String at position (r,c) into terminal
	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}
	public static boolean sum(Grid creation){
		 int sum = 0;
		 for(int i = 0; i < 3; i++){
			 for(int j = 0; j < 3; j++){
				 for(int k = 0; k < 3; k++){
					 for(int l = 0; l < 3; l++){
						 try{
						 sum += Integer.parseInt(creation.grid.get(i).get(j).getCell().get(k).get(l).substring(1,2));}
						 catch(NumberFormatException e)
						 {return false;}
					 }}}}
		 if(sum != 45 * 9){
			 return false;}
		 return true;}
	public static Grid menu() throws FileNotFoundException{
		// sets up a menu for the user
		// to choose difficulty
		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
		Grid puzzle = new Grid();
		boolean running = true;
		// running is false by inputting escape or solving puzzle
		while(running == true){
		putString(1,0, terminal, "WELCOME TO PSUEDOSUDOKHAN");
		putString(1, 3, terminal, "Choose your difficulty");
		putString(1,4, terminal, "E for easy, M for Medium, H, for Hard");
		// instructions on using menu
		Key key = terminal.readInput();
		if(key != null){ //if something is pressed
		if (key.getKind() == Key.Kind.Escape) {
			terminal.exitPrivateMode();
			//exits out of program
			running = false;}
		if (key.getCharacter() == 'E') {
			terminal.clearScreen();
			running = false;
			// after pressed will exit out of for loop
			terminal.exitPrivateMode();
			Random randgen = new Random();
			// randomly picks a sudoku file from a pool of easy sudoku files
			// then uses readPuzzle to convert the file to the puzzle
			solution = "sE" + (Math.abs(randgen.nextInt() % 3)+ 1) + ".txt";
			puzzle = readPuzzle(solution);}
		if(key.getCharacter() == 'M'){
			running = false;
			terminal.clearScreen();
			terminal.exitPrivateMode();
			Random randgen = new Random();
			solution = "sM" + (Math.abs(randgen.nextInt() % 3)+ 1) + ".txt";
			puzzle = readPuzzle(solution);}
		if(key.getCharacter() == 'H'){
			running = false;
			terminal.clearScreen();
			terminal.exitPrivateMode();
			Random randgen = new Random();
			solution = "sH" + (Math.abs(randgen.nextInt() % 3)+ 1) + ".txt";
			puzzle = readPuzzle(solution);}}}
			return puzzle;
	}

	public static String converter(Scanner input){
		// converts file into string
		String output = "";
		while(input.hasNextLine()){
			output += input.nextLine();
		}
		return output;
	}
	public static Grid readPuzzle(String file) throws FileNotFoundException{
		// converts info given in file to Sudoku puzzle
		File input = new File(file);
		Grid output = new Grid();
		Scanner parse = new Scanner(input);
		String text = converter(parse);
		// convert file to string
		int index = 0;
		for(int counter = 0; counter < 9; counter++){
			// counter keeps track of line number
			for(int i = 0; i < 9; i++){
				// i position on a line
					// get the number
          // counter/3 gives horizontal section of lists
          // i / 3 gives vertical section
					// i % 3 gives row
					// counter % 3 gives column
				output.add(Integer.parseInt(text.substring(index, index + 1)),
        counter / 3, i / 3, i % 3, counter % 3);
        index ++;
      }}
		return output;}

	public static void main(String[] args) throws FileNotFoundException{
		String name = "";
		try{
		 name = args[0];}
		catch(IndexOutOfBoundsException e){
			System.out.println("please give your first name");
		}
		int old = 0;
		//Grid puzzle = menu();
		Grid puzzle = menu();
		int x = 1;
		int y = 6;
		// keeps track of which block you're in
		int squareX = 0;
		int squareY = 0;
		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;
		long tStart = System.currentTimeMillis();
		long lastSecond = 0;
		// running is false when escape is pressed
		// or puzzle is solved
		while(running){
			// move the cursor to the position x,y
			terminal.moveCursor(x,y);
			terminal.applyBackgroundColor(Terminal.Color.WHITE);
			terminal.applyForegroundColor(Terminal.Color.BLACK);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
			//terminal.putCharacter('\u00a4');
			terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);
			// this part was used in Mr.K's terminaldemo
			putString(0, 5, terminal, puzzle.toString());
			// print the puzzle on the toString
			Key key = terminal.readInput();

			if (key != null)
			{
				// detect keystroke
				if (key.getKind() == Key.Kind.Escape) {
					// user wants out program ends
					// or user solved puzzle by inputting all numbers in
					terminal.exitPrivateMode();
					running = false;
				}

				if (key.getKind() == Key.Kind.ArrowLeft) {

					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					old = squareX;
					// old keeps track of your old squareX
					// x values below are boundaries of squareX
					if(x < 9){
						squareX = 0;
					}
					if(x > 7 && x < 19){
						squareX = 1;
					}
					if(x > 16){
						squareX = 2;
					}
					// sets squareX based on x value
					if(old != squareX){
						x -= 4;
						// the spaces are not uniform at the
						// boundaries of the block so they're special
					}
					else{
						// to the next slot
					x -= 3;}
				}

				if (key.getKind() == Key.Kind.ArrowRight) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					// old keeps track of your old squareX
					// x values below are boundaries of squareX
					old = squareX;
					if(x <= 7){
						squareX = 0;
					}
					if(x > 7 && x < 19){
						squareX = 1;
					}
					if(x > 16){
						squareX = 2;
					}
					if(old != squareX){
						// the spaces are not uniform at the
						// boundaries of the block so they're special
						x+=4;
					}
					else{
					x += 3;}
				}

				if (key.getKind() == Key.Kind.ArrowUp) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					old = squareY;
					// y values follow a pattern
					// that I used to keep track of squareY
					if(y % 5 == 0 && squareY > 0){
						squareY --;
					}
					y--;
				}

				if (key.getKind() == Key.Kind.ArrowDown) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					// y values follow a pattern
					// that I used to keep track of squareY
					if(y % 5 == 0 && squareY < 2){
						squareY ++;
					}
					y++;
				}
				//space moves it diagonally
				if(key.getCharacter() == 'B'){
					// if you press backspace
					// you will call the remove method
					// we know which block you're in
					// and we can find the x and y coordinates
					// by modding 3 the indices of the x,y values in their lists
					terminal.moveCursor(x,y);
					puzzle.remove(squareX, squareY, indexOf(xCords(),x) % 3, indexOf(yCords(), y) % 3);
					terminal.putCharacter(' ');
				}
				else
				{
					terminal.moveCursor(x, y);
					try{
						// if you submit something that's not a number, it will catch and do nothing
						// we know which block you're in
						// and we can find the x and y coordinates
						// by modding 3 the indices of the x,y values in their lists
						puzzle.add(Integer.parseInt("" + key.getCharacter()),
						squareY, squareX, indexOf(xCords(),x) % 3, indexOf(yCords(), y)% 3);
						terminal.putCharacter(key.getCharacter());}
					catch(NumberFormatException e){}
					catch(IndexOutOfBoundsException e){}
				}
				putString(1,4,terminal,"["+key.getCharacter() +"]");
				putString(1,1,terminal,key+"        ");//to clear leftover letters pad withspaces
			}

			//DO EVEN WHEN NO KEY PRESSED:
			long tEnd = System.currentTimeMillis();
			long millis = tEnd - tStart;
			// basic instructions for the user
			putString(0, 0, terminal, "PSUEDOSUDOKHAN PRESENTS:\n SUDOKU");
			putString(1,3, terminal, "(" + x + " , " + y + ")");
			putString(1, 4, terminal, "(" + squareX + ", " + squareY +")");
			putString(55, 0, terminal, "HOW TO PLAY");
			putString(55, 2, terminal, "The goal of the game is to fill in the grid");
			putString(55, 4, terminal, "in such a way that all of the rows, columns, and boxes");
			putString(55, 6, terminal, "contains some arrangement of the sequence [1,9]");
			putString(55, 8, terminal, "attempting to add a number that already appears in the same");
			putString(55, 10, terminal, "row, column, or box will not work");
			if(millis/1000 > lastSecond){
				lastSecond = millis / 1000;
				//one second has passed.
				putString(0,26,terminal,"Seconds since start of program: "+lastSecond);
			}
		}
		System.out.println("You got: " + puzzle.compareTo(readPuzzle(solution.substring(0,3) + "S.txt")) + " wrong.");
		System.out.println(readPuzzle(solution).toString());
		System.out.println("Congragulations " + name + ". " + "You completed the puzzle in " + lastSecond / 60 + " minutes");
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("ojX2Sf6pcthABwDTvEq0aR1cQ", "bKduV5lO5V0iUf02orkSZApnlLfuyi0s7Mt1HntO3MrxaJ4E1J");
		twitter.setOAuthAccessToken(new AccessToken("1087149025302859776-IBPDjbiWlWPDP5A3FYoBOwli4GYm9n",
		"zbShyjiKGKYZsPVt9ACN53S7WXrRsl8npb7d6D1GvHjm7"));

		try{
			Status status = twitter.updateStatus(name + " completed a sudoku puzzle in " + " in "
			+ lastSecond / 60 + " minutes");
			}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}
