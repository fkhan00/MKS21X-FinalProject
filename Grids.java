import java.util.ArrayList;
import java.util.Random;
public class Grid extends Blocks{
  public ArrayList<ArrayList<Block>> grid = new ArrayList<ArrayList<Block>>();
  public int size;
  // grid is an 2D ArrayList of Blocks
  // it is the sudoku puzzle
  public Grids(){
    // initialzies 3 ArrayList Block objects and adds them to Grid
    // then initializes 3 Block objects for each arraylist
    size = 0;
    for(int i = 0; i < 3; i++){
      grid.add(new ArrayList<Block>());
      for(int j = 0; j < 3; j++){
        grid.get(i).add(new Blocks());}}}
      }
