package dao;

import beans.SignUpData;
import entities.CustomersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SignUp_LoginDAO
{
  public SignUp_LoginDAO() {}
  
  public String signUp(SignUpData signUpData)
  {
    String message = "Something went wrong.Please try again later";
    final SessionFactory factory;
    try
    {
    	
    	
    	factory = ((AnnotationConfiguration) new AnnotationConfiguration().
                configure()).addAnnotatedClass(CustomersEntity.class).
                buildSessionFactory();
         System.out.println("SIGGGGNNNNNN UPPPPPP DAOOOOOOOO");
        
      CustomersEntity customerEntity = new CustomersEntity();
      customerEntity.setId(Integer.valueOf(1000));
      customerEntity.setFirstName(signUpData.getFirstName());
      customerEntity.setLastName(signUpData.getLastName());
      customerEntity.setCity(signUpData.getCity());
      customerEntity.setPhone(signUpData.getPhone());
      customerEntity.setCountry(signUpData.getCountry());
      customerEntity.setPassword(signUpData.getPassword());
      customerEntity.setUsername(signUpData.getFirstName() + "_" + signUpData.getLastName());
      
//      SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
      Session session = factory.openSession();
      session.beginTransaction();
      Integer id = (Integer)session.save(customerEntity);
      session.getTransaction().commit();
      session.close();
      
      if (id != null) {
        message = "Sign Up Successfull \n Username is firtsName_LastName";
      }
    } catch (Exception e) {
    	System.out.println("ERRRROOORRRR");
      e.printStackTrace();
    }
    return message;
  }
}
