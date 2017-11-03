package beans;

public class SignUpData { private String firstName;
  private String lastName;
  private String city;
  private String country;
  private String phone;
  private String password;
  
  public SignUpData() {}
  
  public String getFirstName() { return firstName; }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() { return lastName; }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getCity() { return city; }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getCountry() { return country; }
  
  public void setCountry(String country) {
    this.country = country;
  }
  
  public String getPhone() { return phone; }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getPassword() { return password; }
  
  public void setPassword(String password) {
    this.password = password;
  }
}
