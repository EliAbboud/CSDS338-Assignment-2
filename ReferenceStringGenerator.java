import java.util.*;

public class ReferenceStringGenerator{
  
  //multiplexes the reference string creation based on user input
  public static int[] referenceString(Object size, Object bound,Object distribution){
    int intSize = Integer.parseInt((String)size);
    int intBound = Integer.parseInt((String)bound);
    switch((String)distribution){
      case "Part A":
        return partA(intSize, intBound);
      case "Part B":
        return partB(intSize, intBound);
      default:
        return partC(intSize, intBound);
    }
  }
  
  //creates a reference string based on a uniform probability distribution
  public static int[] partA(int size, int bound){
    Random random = new Random();
    random.setSeed(100);
    int[] array = random.ints(size, 0,bound+1).toArray();
    System.out.println(Arrays.toString(array));
    return array;
  }
  
  //creates a reference string based on an exponential probability distribution (albeit a weak one)
  public static int[] partB(int size, int bound){
    Random random = new Random();
    double lambda = -.1;
    random.setSeed(100);
    int[] array = new int[size];
    for(int i = 0; i < array.length; i++){
      int randomInt = (int)(Math.log(1-random.nextDouble())/lambda);
      while(randomInt > bound)
        randomInt = (int)(Math.log(1-random.nextDouble())/lambda);
      array[i] = randomInt;
    }
    System.out.println(Arrays.toString(array));
    return array;
  }
  
  //creates a reference string based on an exponential probability distribution and then biases all values between 3 & 10 inclusive
  public static int[] partC(int size, int bound){
    int[] array = partB(size, bound);
    Random random = new Random();
    random.setSeed(150);
    for(int i = 0; i < array.length; i++){
      if(Math.round(random.nextDouble())==0)
         array[i]= random.ints(4,10).iterator().next();
    }
    System.out.println(Arrays.toString(array));
    return array;
  }
  
  
}