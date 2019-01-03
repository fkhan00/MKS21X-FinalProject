import java.util.ArrayList;
public class Block{
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

  public boolean legal(int num){
    // makes sure user doesn't break the rules
    // by putting a number outside of the range [1,9]
    // or putting a number that the cell already contains
    if(num > 9 || num <= 0){
       return false;}
    if(cell.contains(num)){
      return false;}
    return true;}

  public void add(int num, int x, int y){
    //checks to see if it is legal
    // at arraylist y and index x, it sets that value to num
    if(legal(num)){
      cell.get(y).set(x, " " + num + " ");}}

  public void remove(int x, int y){
    // just sets the String at position (x,y) to default
    cell.get(y).set(x, " _ ");}

  public String toString(){
    String output = "";
    output += "\n___________";
    // creates the initial bar
    for(int i = 0; i < 3; i++){
      // at the end of each row put a |
      // to show its the edge of the box
      output += "\n|";
      for(int j = 0; j < 3; j++){
        output += cell.get(i).get(j);
      }
      //move to the next line and repeat
    output += "|\n";
  }
     output += "___________";
    return output;}
  }
