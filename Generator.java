import java.util.Random;
public class Generator extends Grid{
  public String generator(){
  // simple Generator
  // bashes numbers until it works
  Random randgen = new Random();
  int tries = 0;
  Grid creation = new Grid();
  int nSize = size;
    while(size != 81){
    nSize = size;
    while(tries < 500 && size == nSize){
    creation.add(randgen.nextInt() % 10, Math.abs(randgen.nextInt() % 3), Math.abs(randgen.nextInt() % 3),Math.abs(randgen.nextInt() % 3),Math.abs(randgen.nextInt() % 3));
    if(size == nSize){
      tries ++;}}
    if(size == nSize){
      creation = new Grid();}}
    if(! creation.sum()){
      generator();}
  return creation.toString();}

  public boolean sum(Grid creation){
    int sum = 0;
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        for(int k = 0; k < 3; k++){
          for(int l = 0; l < 3; l++){
            sum += creation.get(i).get(j).get(k).get(l)}}}}
    if(sum != 45 * 9){
      return false;}
    return true;}
    public static void main(String[] args){
      System.out.println(generator());
    }

}
