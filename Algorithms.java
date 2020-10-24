import java.util.*;

public class Algorithms{
  //indicates the algorithm type
  public boolean fifoAlgorithm;
  //indicates the max number of frames that the main memory can hold
  public int maxMainMemorySize;
  //simulates main memory
  public LinkedList<Page> mainMemory;
  //simulates page demands
  public ArrayList<Page> referenceString;
  //counts page defaults
  public int faults = 0;
  
  //stores data necassary to execute the algorithm
  public Algorithms(Object algorithm,Object frameSize, int[] referenceString){
    fifoAlgorithm = ((String)algorithm).equals("FIFO");
    maxMainMemorySize = Integer.parseInt((String)frameSize);
    mainMemory = new LinkedList<Page>();
    this.referenceString = pagify(referenceString);
  }
  
  //turns the reference string into Page objects
  public ArrayList<Page> pagify(int[] referenceString){
    ArrayList<Page> pages = new ArrayList<Page>(referenceString.length);
    for(int i = 0; i < referenceString.length; i++)
      pages.add(new Page(referenceString[i],1));
    return pages;
  }
  
  //executes the entire algorithm at once
  public int run(){
    while(referenceString.size() >0)
      demandPage();
    return faults;
  }
  
  //handles page demands
  public void demandPage(){
    Page page = referenceString.remove(0);
    int indexOfPage = mainMemory.indexOf(page);
    //if the page is already in memory, the reference bit is set and the method concludes
    if (indexOfPage > -1){
      mainMemory.get(indexOfPage).setReferenceBit();
    }
    //if there is space in memory, the page is added to memory and the fault counter is incremented
    else if(mainMemory.size()<maxMainMemorySize){
      mainMemory.add(page);
      faults++;
    }
    //if there isn't space in memory, a swap occurs and the fault counter is incremented
    else{
      replace(page);
      faults++;
    }
  }
  
  //multiplexes the replacement strategy based on used input
  public void replace(Page page){
    if(fifoAlgorithm)
      replaceFifo(page);
    else
      replaceClock(page);
  }
  
  //implements a FIFO replacement strategy
  public void replaceFifo(Page page){
    mainMemory.poll();
    mainMemory.add(page);
  }
  
  //implements a Clock replacement strategy
  public void replaceClock(Page page){
    Page removalCandidate = mainMemory.poll();
    while(removalCandidate.referenceBit==1){
      removalCandidate.clearReferenceBit();
      mainMemory.add(removalCandidate);
      removalCandidate = mainMemory.poll();
    }
    mainMemory.add(page);
  }
  
  
  
}