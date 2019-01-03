import java.util.ArrayList;
public class Blocks{
  public ArrayList<ArrayList<String>> cell = new ArrayList<ArrayList<String>>();
// cell is a single box in the sudoku puzzle
// it contains a 2D array list of Strings which are the numbers in the puzzle
  public Block(){
    // this creates a 3X3 arrayList
    // each row being a new arraylist
    for(int i = 0; i < 3; i++){
      cell.add(new ArrayList<String>());
      for(int j = 0; j < 3; j ++){
      cell.get(i).add(" _ ");}}}
  public ArrayList<ArrayList<String>> getCell(){
    // accessor method for grid class
    return cell;
  }
  public boolean contains(int num){
    // checks if the cell already has that number
    for(int i = 0; i < 3; i++){
        if(cell.get(i).contains(" " + num + " ")){
          return true;}}
    return false;}
