
public class Page{
  //represents the reference bit
  public int referenceBit;
  //represents the page number
  public int pageNumber;
  
  //stores the data necassary for Page creation
  public Page(int pageNumber, int referenceBit){
    this.pageNumber = pageNumber;
    this.referenceBit = referenceBit;
  }
  
  //two pages are considered equal if they have the same page number
  @Override
  public boolean equals(Object anotherPage){
    return pageNumber==((Page)anotherPage).pageNumber;
  }
  
  //a page can be represented by its number
  @Override
  public String toString(){
    return pageNumber+" ";
  }
  
  //sets reference bit to one
  public void setReferenceBit(){
    referenceBit=1;
  }
  
  //clears reference bit to zero
  public void clearReferenceBit(){
    referenceBit=0;
  }
}