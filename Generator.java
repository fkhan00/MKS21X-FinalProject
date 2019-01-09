import java.util.Random;
public class Generator extends Grid{
  public static String generator(){
  // simple Generator
  // bashes numbers until it works
  Random randgen = new Random();
  int tries = 0;
  Grid creation = new Grid();
  int nSize = size;
    while(! filled(creation)){
    nSize = size;
    creation.add(randgen.nextInt() % 10, Math.abs(randgen.nextInt() % 3), Math.abs(randgen.nextInt() % 3),
    Math.abs(randgen.nextInt() % 3),Math.abs(randgen.nextInt() % 3));}
    System.out.println(creation.toString());
     if(! sum(creation)){
      generator();}
  return creation.toString();}
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
            sum += Integer.parseInt(creation.grid.get(i).get(j).getCell().get(k).get(l).substring(1,2));}}}}
    if(sum != 45 * 9){
      return false;}
    return true;}
    public static void main(String[] args){
      System.out.println(generator());
    }

}
