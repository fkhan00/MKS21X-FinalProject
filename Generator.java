import java.util.Random;
import java.util.ArrayList;

public class Generator extends Grid{
  public static Grid generator2(Grid creation){
    // stores permutation of [1,9]
    Grid old = creation;
    ArrayList<Integer> arrangement = new ArrayList<Integer>();
    int counter = 0;
    int tries = 0;
    for(int bR = 0; bR < 3; bR++){
      for(int bC = 0; bC < 3; bC++){
        System.out.println(creation.toString());
        for(int r = 0; r < 3; r++){
          arrangement = permutation();
          tries = 0;
          // create arrangement which will be stored in block
          for(int c = 0; c < 3; c++){
            counter = 0;
            while(creation.grid.get(bR).get(bC).getCell().get(r).get(c).equals(" _ ") || !creation.add(arrangement.get(counter), bR, bC, r, c) && counter != 9){
              // until you find a number you can put in
              // go through the arrangment
              //if you're stuck start the block over
              // if it happens repeatedly restart
              if(tries == 5){
                bR = 0;
                bC = 0;
                r = 0;
                c = 0;
                counter = 0;
                creation = old;}
              if(counter == 9){
                r = 0;
                c = 0;
                creation.grid.get(bR).set(bC, old.grid.get(bR).get(bC));
                tries ++;}
              counter++;}
              // remove that number because it wont't work this is for efficiency
              arrangement.remove(counter);}}}}
    return creation;}

  public static ArrayList<Integer> permutation(){
    //creates a random arragnement of [1,9]
    Random randgen = new Random();
    ArrayList<Integer> ary = new ArrayList<Integer>();
    for(int i = 1; i < 10; i++){
      // store ordered arrangement
      ary.add(i);}
      ArrayList<Integer> output = new ArrayList<Integer>();
      while(ary.size() > 0 ){
      // while there is still something to add
      int submission = Math.abs((randgen.nextInt() % 10));
      // random number being added
      if(ary.contains(submission)){
        // must be in ary so must be a new number
        output.add(submission);
        // add to end of output and remove from ary
        ary.remove(ary.indexOf(submission));}}
      return output;}

  //checks if deletion still yields unique solution
  public static boolean unique(Grid creation){
    int counter = 0;
    //checks fifty times if consecutive calls of generator yield the same solution
    while(counter < 50){
      if(! generator2(creation).toString().equals(generator2(creation).toString())){
        return false;}}
    return true;}

//puzzle removes elements to make the puzzle
    public static Grid puzzle(Grid creation, int difficulty){
      Random randgen = new Random();
      Grid old = creation;
      // holds previous pre-trial grid
      while(creation.size != difficulty){
        old = creation;
        // remove a random number from the grid
        creation.remove(randgen.nextInt() % 3, randgen.nextInt() % 3, randgen.nextInt() % 3, randgen.nextInt() % 3);
        if(! unique(creation)){
          //check if grid is still unique
          //if not set creation back to previous
          creation = old;}}
        //once size is achieved return puzzle
      return creation;}

      public static void main(String[] args){
        System.out.println(generator2(new Grid()).toString());
      }
  }
