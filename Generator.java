import java.util.Random;
public class Generator extends Grid{
    public static Grid generator(Grid creation){
    Grid old = creation;
    // simple Generator
    // bashes numbers until it works
    Random randgen = new Random();
    int tries = 0;
    String efficiency = "";
    int nSize = creation.size;
    int attempts = 0;
    while(! sum(creation)){
      creation = old;
      // optimize by reducing minimum size to 78
      // this is minimum number that always gives unique solution
      while(creation.size != 5000 && attempts < 5000){
      nSize = creation.size;
      tries = 0;
      //fix add methods the no need for sum(creation)
      // efficiency will increase rapidly
      //since you're not starting from beginning
      while(tries < 5000 && nSize == creation.size){
      creation.add(randgen.nextInt() % 10, Math.abs(randgen.nextInt() % 3), Math.abs(randgen.nextInt() % 3),
      Math.abs(randgen.nextInt() % 3),Math.abs(randgen.nextInt() % 3));
      tries ++;}
      attempts ++;
    }
    attempts = 0;
    efficiency += "*";
    System.out.println(efficiency.length());
    }
    return creation;}

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
            if(!generator(creation).toString().equals(original)){
              if(old != 0){
              creation.add(old, squareX, squareY, x, y);}
              break;}
            tries ++;}}
        return creation;
      }
      public static void main(String[] args){
        System.out.println(generator(new Grid()).toString());
      }
  }
