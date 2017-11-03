package entities;

import javax.persistence.GeneratedValue;

@javax.persistence.Entity
@javax.persistence.Table(name="Customer")
public class CustomersEntity {
  @javax.persistence.Id
  @org.hibernate.annotations.GenericGenerator(name="kaugen", strategy="increment")
  @GeneratedValue(generator="kaugen")
  private Integer id;
  private String firstName;
  private String lastName;
  private String city;
  private String country;
  private String phone;
  private String username;
  private String password;
  
  public CustomersEntity() {}
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getCountry() {
    return country;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
}
