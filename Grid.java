import java.util.ArrayList;
import java.util.Random;
public class Grid extends Block{
  public ArrayList<ArrayList<Block>> grid = new ArrayList<ArrayList<Block>>();
  public int size;
  // grid is an 2D ArrayList of Blocks
  // it is the sudoku puzzle
  public Grid(){
    // initialzies 3 ArrayList Block objects and adds them to Grid
    // then initializes 3 Block objects for each arraylist
    size = 0;
    for(int i = 0; i < 3; i++){
      grid.add(new ArrayList<Block>());
      for(int j = 0; j < 3; j++){
        grid.get(i).add(new Block());}}}

  public boolean containsRow(int num, int indexA, int indexB, int x, int y){
    for(int i = 0; i < 3; i++) {
      for(int k = 0; k < 3; k++){
        //keep horizontal sections indexA and x constant
        // iterate across the row using i and k
          if( grid.get(indexA).get(i).getCell().get(x).get(k).equals(" " + num + " ")){
            return true;}}}
    return false;
  }

  public boolean containsColumn(int num, int indexA, int indexB, int x, int y){
    for(int i = 0; i < 3; i++) {
      for(int k = 0; k < 3; k++){
      // keep vertical sections indexB and y constant
      // iterate down the column using i and k
          if( grid.get(i).get(indexB).getCell().get(k).get(y).equals(" " + num + " ")){
            return true;}}}
    return false;}

  public boolean contains(int num,int indexA, int indexB, int x, int y){
    //if your block contains the number in row,column, or block return true
    return grid.get(indexA).get(indexB).contains(num) || containsRow(num, indexA, indexB, x, y) || containsColumn(num, indexA, indexB, x, y);}

    public boolean legal(int num, int indexA, int indexB, int x, int y){
      // checks to see if your input is a legal move
      // legal defined as an integer within [1,9]
      // contains returns false and you're adding into an empty space
      return grid.get(indexA).get(indexB).getCell().get(x).get(y).equals(" _ ") && num <= 9 && num > 0 && !contains(num, indexA, indexB, x, y);}

    public boolean add(int num, int indexA, int indexB, int x, int y){
      // calls on the super class' method of add to add to the block
      // if legal move you may add and will increase size by 1
      if(legal(num, indexA, indexB, x, y)){
        grid.get(indexA).get(indexB).add(num, x, y);
        size++;
        return true;
      }
      return false;
    }
    public void remove(int indexA, int indexB, int x, int y){
      // calls on the super class' method of remove to the block
      grid.get(indexA).get(indexB).remove(x, y);
      size--;}
    public String toString(){
      // toString built from scratch
      // 4 level nested for loop to make necessary adjustments to sudoku board
  String output = "";
  output += " ______________________________\n";
  for(int i = 0; i < 3; i++){
    for(int j = 0; j < 3; j++){
      for(int k = 0; k < 3; k++){
        for(int l = 0; l < 3; l++){
          output += grid.get(i).get(k).getCell().get(j).get(l);
        }
        output += "|";
      }
      output += "\n";
    }
    output += "|\n ______________________________\n";
  }
  return output;
}

}
