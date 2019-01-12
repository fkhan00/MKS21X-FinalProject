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


public class Sudoku extends Grid{
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
	public static int indexOf(int[] ary, int num){
		for(int i = 0; i < ary.length; i++){
			if(ary[i] == num){
				return i;}}
		return -1;}
	// putString() is from Mr.K's terminaldemo
	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}
	public static Grid menu() throws FileNotFoundException{
		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
		Grid puzzle = new Grid();
		boolean running = true;
		while(running == true){
		putString(1,0, terminal, "WELCOME TO PSUEDOSUDOKHAN");
		putString(1, 3, terminal, "Choose your difficulty");
		putString(1,4, terminal, "E for easy, M for Medium, H, for Hard");
		Key key = terminal.readInput();
		if(key != null){
		if (key.getKind() == Key.Kind.Escape) {
			terminal.exitPrivateMode();
			running = false;}
		if (key.getCharacter() == 'E') {
			terminal.clearScreen();
			running = false;
			terminal.exitPrivateMode();
			Random randgen = new Random();
			puzzle = readPuzzle("sE" + (Math.abs(randgen.nextInt() % 3)+ 1) + ".txt");}
		if(key.getCharacter() == 'M'){
			running = false;
			terminal.clearScreen();
			terminal.exitPrivateMode();
			Random randgen = new Random();
			puzzle = readPuzzle("sM" + (Math.abs(randgen.nextInt() % 3) + 1) + ".txt");}
		if(key.getCharacter() == 'H'){
			running = false;
			terminal.clearScreen();
			terminal.exitPrivateMode();
			Random randgen = new Random();
			puzzle = readPuzzle("sH" + (Math.abs(randgen.nextInt() % 3) + 1) + ".txt");}}}
			return puzzle;
	}
	public static String converter(Scanner input){
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
		int counter = 0;
		Scanner parse = new Scanner(input);
		String text = converter(parse);
		int index = 0;
		while(counter < 9){
			for(int i = 0; i < 16; i++){
				try{
				if(i % 3 == 2){
				output.add(Integer.parseInt(text.substring(index, index + 1)),counter / 3, i / 6, counter % 3, 1);}
				if(i % 3 == 1){
				output.add(Integer.parseInt(text.substring(index, index + 1)),counter / 3, i / 6, counter % 3, 2);}
				if(i % 3 == 0){
				output.add(Integer.parseInt(text.substring(index, index + 1)),counter / 3, i / 6, counter % 3, 0);}}
				catch(NumberFormatException e){}
				index ++;}
				System.out.println(output.toString());
			counter ++;}
		return output;}

	public static void main(String[] args) throws FileNotFoundException{
		int old = 0;
		//Grid puzzle = menu();
		Grid puzzle = readPuzzle("SM3.txt");
		puzzle.add(4, 2, 0, 0, 1);
		int x = 1;
		int y = 6;
		int squareX = 0;
		int squareY = 0;
		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;
		long tStart = System.currentTimeMillis();
		long lastSecond = 0;

		while(running){

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
			Key key = terminal.readInput();

			if (key != null)
			{

				if (key.getKind() == Key.Kind.Escape) {

					terminal.exitPrivateMode();
					running = false;
				}

				if (key.getKind() == Key.Kind.ArrowLeft) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					old = squareX;
					if(x < 9){
						squareX = 0;
					}
					if(x > 7 && x < 19){
						squareX = 1;
					}
					if(x > 16){
						squareX = 2;
					}
					if(old != squareX){
						x -= 4;
					}
					else{
					x -= 3;}
				}

				if (key.getKind() == Key.Kind.ArrowRight) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
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
						x+=4;
					}
					else{
					x += 3;}
				}

				if (key.getKind() == Key.Kind.ArrowUp) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					old = squareY;
					if(y % 5 == 0 && squareY > 0){
						squareY --;
					}
					y--;
				}

				if (key.getKind() == Key.Kind.ArrowDown) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					if(y % 5 == 0 && squareY < 2){
						squareY ++;
					}
					y++;
				}
				//space moves it diagonally
				if (key.getCharacter() == ' ') {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y++;
					x+=3;
				}
				if(key.getCharacter() == 'B'){
					terminal.moveCursor(x,y);
					puzzle.remove(squareX, squareY, indexOf(xCords(),x) % 3, indexOf(yCords(), y) % 3);
					terminal.putCharacter(' ');
				}
				else
				{
					terminal.moveCursor(x, y);
					try{
						// if you submit something that's not a number, it will catch and do nothing
						puzzle.add(Integer.parseInt("" + key.getCharacter()),
						squareY, squareX, indexOf(xCords(),x) % 3, indexOf(yCords(), y)% 3);
						// need to work on coordinates for x and y
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
			//putString(1,2,terminal,"Milliseconds since start of program: "+millis);
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
				//putString(1,3,terminal,"Seconds since start of program: "+lastSecond);
			}
		}
	}
}
