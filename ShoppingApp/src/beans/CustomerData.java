package beans;

public class CustomerData {
  private Integer startIndex;
  private Integer endIndex;
  private String sortBy;
  
  public CustomerData() {}
  
  public String getSortBy() { return sortBy; }
  
  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }
  
  public Integer getStartIndex() { return startIndex; }
  
  public void setStartIndex(Integer startIndex) {
    this.startIndex = startIndex;
  }
  
  public Integer getEndIndex() { return endIndex; }
  
  public void setEndIndex(Integer endIndex) {
    this.endIndex = endIndex;
  }
}
