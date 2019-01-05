public class BlockTest extends Block{
  public static void main(String[] args){
    System.out.println("testing constructor");
    Block test1 = new Block();
    // testing constructor
    System.out.println(test1.toString());
    test1.add(3, 1, 1);
    System.out.println("testing add method center number should be a 3");
    System.out.println(test1.toString());
    test1.add(10, 2, 2);
    System.out.println(test1.contains(3));
    test1.add(3, 2,1);
    System.out.println("attempting to add 10 and 3, neither should work");
    test1.remove(1,1);
    System.out.println(test1.toString());
    System.out.println("removing the center number should be a blank grid");
    System.out.println(test1.toString());
  }
}
