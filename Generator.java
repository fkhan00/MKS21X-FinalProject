import java.util.Random;
public class Generator extends Grid{
    public static Grid generator(Grid creation){
    // simple Generator
    // bashes numbers until it works
    Random randgen = new Random();
    int tries = 0;
    int nSize = creation.size;

      while(! filled(creation)){
      nSize = creation.size;
      tries = 0;
      while(tries < 5000 && nSize == creation.size){
      creation.add(randgen.nextInt() % 10, Math.abs(randgen.nextInt() % 3), Math.abs(randgen.nextInt() % 3),
      Math.abs(randgen.nextInt() % 3),Math.abs(randgen.nextInt() % 3));
      tries ++;}
        }
       if(! sum(creation)){
        System.out.println(creation.toString());
        return generator(new Grid());}
    return creation;}

    public static boolean filled(Grid creation){
      for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
          for(int k = 0; k < 3; k++){
            for(int l = 0; l < 3; l++){
              if(creation.grid.get(i).get(j).getCell().get(k).get(l).equals(" _ ")){
                return false;}}}}}
      return true;
    }
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
        while(creation.size != difficulty){
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
            if(!generator(creation).toString().equals(original)){
              if(old != 0){
              creation.add(old, squareX, squareY, x, y);}
              break;}
            tries ++;}}
        return creation;
      }
      public static void main(String[] args){
        System.out.println(puzzle(generator(new Grid()), 20).toString());
      }
  }
