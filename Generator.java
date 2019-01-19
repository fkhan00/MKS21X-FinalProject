import java.util.Random;
import java.util.ArrayList;
public class Generator extends Grid{

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

      public static Grid puzzle(Grid creation, int difficulty){
        Random randgen = new Random();
        String original = creation.toString();
        int tries = 0;
        int old = 0;
        int squareX = 0;
        int squareY = 0;
        int x = 0;
        int y = 0;
        String edited = "";
        while(creation.size != 50){
          edited = creation.toString();
          squareX = Math.abs(randgen.nextInt() % 3);
          squareY = Math.abs(randgen.nextInt() % 3);
          x = Math.abs(randgen.nextInt() % 3);
          y = Math.abs(randgen.nextInt() % 3);
          try{
          old = Integer.parseInt(creation.grid.get(squareX).get(squareY).getCell().get(x).get(y));}
          catch(NumberFormatException e){
            old = 0;
          }
          creation.remove(squareX, squareY, x, y);
          while(tries < 50){
            if(!generator2(creation).toString().equals(original)){
              if(old != 0){
              creation.add(old, squareX, squareY, x, y);}
              break;}
            tries ++;}}
        return creation;
      }
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
          ary.remove(ary.indexOf(submission));}
      }
      return output;
    }


      public static Grid generator2(Grid creation){
        // stores permutation of [1,9]
        ArrayList<Integer> arrangement = new ArrayList<Integer>();
        int counter = 0;
        for(int bR = 0; i < 3; i++){
          for(int bC = 0; j < 3; j++){
            // create arrangement which will be stored in block
            arrangement = permutation();
            for(int r = 0; k < 3; k++){
              for(int c = 0; l < 3; l++){
                counter = 0;
                System.out.println(creation.toString());
                while(! creation.add(arrangement.get(counter), bR, bC, r, c)){
                  // until you find a number you can put in
                  // go through the arrangment
                  counter++;}
                  // remove that number because it wont't work this is for efficiency
                arrangement.remove(counter);}}}}
        return creation;}

      public static void main(String[] args){
        System.out.println(generator2(new Grid()).toString());
      }
  }
