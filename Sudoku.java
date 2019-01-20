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
import java.util.Arrays;
import java.util.Random;

// terminal interface for Sudoku puzzle
public class Sudoku extends Generator{
	// a list of the x coordinates of points on the terminal
	//that have a _
	Grid solution;
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
	public static Grid menu(){
		// sets up a menu for the user
		// to choose difficulty
		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
		Grid puzzle = new Grid();
		boolean running = true;
		// running is false by inputting escape
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
			solution = generator2(newGrid());
			puzzle = puzzle(solution, 40);}
		if(key.getCharacter() == 'M'){
			running = false;
			terminal.clearScreen();
			terminal.exitPrivateMode();
			// after pressed will exit out of for loop
			solution = generator2(newGrid());
			puzzle = puzzle(solution, 30);
		if(key.getCharacter() == 'H'){
			running = false;
			terminal.clearScreen();
			terminal.exitPrivateMode();
			// after pressed will exit out of for loop
			solution = generator2(new Grid());
			puzzle = puzzle(solution, 20);}}}
			return puzzle;
	}
	public static void main(String[] args){
		//oldBlock is used to make necessary adjustments when moving between blocks
		int oldBlock = 0;
		Grid puzzle = menu();
		int row = 1;
		int col = 6;
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
			// move the cursor to the position row,col
			terminal.moveCursor(row,col);
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
			// print the puzzle on the terminal
			Key key = terminal.readInput();

			if (key != null)
			{
				// detect keystroke
				if (key.getKind() == Key.Kind.Escape || puzzle.size == 81) {
					// user wants out program ends
					// or user solved puzzle by inputting all numbers in
					// prints the solved puzzle
					terminal.exitPrivateMode();
					running = false;
					System.out.println(solution.toString());
				}
				if (key.getKind() == Key.Kind.ArrowLeft) {

					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					oldBlock = squareX;
					// oldBlock keeps track of your old squareX
					// x values below are boundaries of squareX
					if(row < 9){
						squareX = 0;
					}
					if(row > 7 && row < 19){
						squareX = 1;
					}
					if( row > 16){
						squareX = 2;
					}
					// sets squareX based on x value
					if(oldBlock != squareX){
						row -= 4;
						// the spaces are not uniform at the
						// boundaries of the block so they're special
					}
					else{
						// to the next slot
					row -= 3;}
				}

				if (key.getKind() == Key.Kind.ArrowRight) {
					terminal.moveCursor(row,col);
					terminal.putCharacter(' ');
					// oldBlock keeps track of your old squareX
					// x values below are boundaries of squareX
					oldBlock = squareX;
					if(row <= 7){
						squareX = 0;
					}
					if(row > 7 && row < 19){
						squareX = 1;
					}
					if(row > 16){
						squareX = 2;
					}
					if(oldBlock != squareX){
						// the spaces are not uniform at the
						// boundaries of the block so they're special
						row+=4;
					}
					else{
					row += 3;}
				}

				if (key.getKind() == Key.Kind.ArrowUp) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					// y values follow a pattern
					// that I used to keep track of squareY
					if(col % 5 == 0 && squareY > 0){
						squareY --;
					}
					col--;
				}

				if (key.getKind() == Key.Kind.ArrowDown) {
					terminal.moveCursor(row,col);
					terminal.putCharacter(' ');
					// y values follow a pattern
					// that I used to keep track of squareY
					if(col % 5 == 0 && squareY < 2){
						squareY ++;
					}
					col++;
				}
				//space moves it diagonally
				if(key.getCharacter() == 'B'){
					// if you press backspace
					// you will call the remove method
					// we know which block you're in
					// and we can find the x and y coordinates
					// by modding 3 the indices of the x,y values in their lists
					terminal.moveCursor(row, col);
					puzzle.remove(squareX, squareY, indexOf(xCords(),row) % 3, indexOf(yCords(), col) % 3);
					terminal.putCharacter(' ');
				}
				if(key.getCharacter() == 'h'){
					// if they want a hint, give them the number of the the slot they're in
					try{
					puzzle.add(solution.grid.get(squareY).get(squareX).getCell().get(indexOf(xCords(),row) % 3)
					.get(indexOf(yCords(), col)% 3), squareY, squareX, indexOf(xCords(), row) % 3, indexOf(xCords(), row)% 3);}
					catch(IndexOutOfBoundsException e){}
				}
				else
				{
					terminal.moveCursor(row, col);
					try{
						// if you submit something that's not a number, it will catch and do nothing
						// we know which block you're in
						// and we can find the x and y coordinates
						// by modding 3 the indices of the x,y values in their lists
						puzzle.add(Integer.parseInt("" + key.getCharacter()),
						squareX, squareY, indexOf(xCords(),row) % 3, indexOf(yCords(), col)% 3);
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
			putString(1,3, terminal, "(" + row + " , " + col + ")");
			putString(1, 4, terminal, "(" + squareX + ", " + squareY +")");
			putString(55, 0, terminal, "HOW TO PLAY");
			putString(55, 2, terminal, "The goal of the game is to fill in the grid");
			putString(55, 4, terminal, "in such a way that all of the rows, columns, and boxes");
			putString(55, 6, terminal, "contains some arrangement of the sequence [1,9]");
			putString(55, 8, terminal, "attempting to add a number that already appears in the same");
			putString(55, 10, terminal, "row, column, or box will not work");
			putString(55, 12, terminal, "if you get stuck press h for a hint");
			if(millis/1000 > lastSecond){
				lastSecond = millis / 1000;
				//one second has passed.
				//putString(1,3,terminal,"Seconds since start of program: "+lastSecond);
			}
		}
	}
}
