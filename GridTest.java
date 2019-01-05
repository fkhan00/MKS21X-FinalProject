public class GridTest extends Grid{
  public static void main(String[] args){
  Grid test1 = new Grid();
  System.out.println("testing constructor should print a blank puzzle");
  System.out.println(test1.toString());
  test1.add(1, 1, 1, 1, 1);
  System.out.println(test1.contains(1,1,1,1,1));
  System.out.println("inserting the number one to the center of the puzzle");
  System.out.println(test1.toString());
  test1.add(1, 2, 1, 1, 1);
  System.out.println(test1.contains(1,1,1,1,1));
  System.out.println("trying to add 1 to the same column as the center should not work");
  System.out.println(test1.toString());
  test1.remove(1,1,1,1);
  System.out.println(test1.toString());
  System.out.println("testing remove method center number should disappear");
  /// need to work on contains method
  }
}
