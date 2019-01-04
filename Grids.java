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

  public boolean contains(int num,int indexA, int indexB, int x, int y){
    //if your block contains the number return true
    if(grid.get(indexA).get(indexB).contains(num)) {
      return true;}
    for(int i = 0; i < 3; i++) {
      for(int k = 0; k < 3; k++){
      // going through each block that has the number
      if((i != indexA && k != indexB) && grid.get(i).get(k).contains(num)){
        for(int j = 0; j < 3; j++){
          if(i == indexA && grid.get(i).get(k).getCell().get(j).get(x).equals(" " + num + " ")){
            return true;}
          if(k == indexB && grid.get(i).get(k).getCell().get(y).get(j).equals(" " + num + " ")){
            return true;}
          }}}}
            //if you got here the grid doesn't "contain" the number
    return false;}

    public boolean legal(int num, int indexA, int indexB, int x, int y){
      // checks to see if your input is a legal move
      if(num > 9 || num < 0 || contains(num, indexA, indexB, x, y)){
        return false;}
      return true;}

    public void add(int num, int indexA, int indexB, int x, int y){
      // calls on the super class' method of add to add to the block
      if(legal(num, indexA, indexB, x, y)){
        grid.get(indexA).get(indexB).add(num, x, y);
      size++;}
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
public boolean sum(){
  int sum = 0;
  for(int i = 0; i < 3; i++){
    for(int j = 0; j < 3; j++){
      for(int k = 0; k < 3; k++){
        for(int l = 0; l < 3; l++){
          sum += Integer.parseInt(grid.get(i).get(k).getCell().get(j).get(l));}
        if(sum != 45){
          return false;}}}}
  return true;
}
public String Solver(){
  Grid output = new Grid();
  Random randgen = new Random();
  while(size != 81){
    System.out.println(size);
    output.add(Math.abs(randgen.nextInt() % 10), Math.abs(randgen.nextInt() % 3),
    Math.abs(randgen.nextInt() % 3),Math.abs(randgen.nextInt() % 3), Math.abs(randgen.nextInt() % 3));
  }
    return output.toString();}

}
