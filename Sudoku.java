//API : http://mabe02.github.io/lanterna/apidocs/2.1/
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


public class Sudoku extends Grid{
	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}
	public Grid readPuzzle(String file) throws FileNotFoundException{
		// converts info given in file to Sudoku puzzle
		File input = new File(file);
		Grid output = new Grid();
		int counter = 0;
		Scanner parse = new Scanner(input);
		while(parse.hasNextLine()){
			for(int i = 0; i < parse.nextLine().length(); i++){
				output.add(parse.nextInt(), counter, counter / 3, counter, i);}
			counter ++;}
		return output;}

	public static void main(String[] args) {
		Grid puzzle = new Grid();
		int old = 0;
		//Grid puzzle = readPuzzle(args[0]);
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
					puzzle.remove(squareX, squareY, (x-1) % 3, (y-6) % 3);
					terminal.putCharacter(' ');
				}
				else
				{
					terminal.moveCursor(x, y);
					try{
						// if you submit something that's not a number, it will catch and do nothing
						puzzle.add(Integer.parseInt("" + key.getCharacter()), squareY, squareX,(y-6) % 3, (x-1) % 3);
						// need to work on coordinates for x and y
						terminal.putCharacter(key.getCharacter());}
					catch(NumberFormatException e){}
				}
				putString(1,4,terminal,"["+key.getCharacter() +"]");
				putString(1,1,terminal,key+"        ");//to clear leftover letters pad withspaces
			}

			//DO EVEN WHEN NO KEY PRESSED:
			long tEnd = System.currentTimeMillis();
			long millis = tEnd - tStart;
			putString(1,2,terminal,"Milliseconds since start of program: "+millis);
			putString(1,3, terminal, "(" + x + " , " + y + ")");
			putString(1, 4, terminal, "(" + squareX + ", " + squareY +")");
			if(millis/1000 > lastSecond){
				lastSecond = millis / 1000;
				//one second has passed.
				putString(1,3,terminal,"Seconds since start of program: "+lastSecond);
			}
		}
	}
}