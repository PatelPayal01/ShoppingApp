package service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import Factory.Factory;
import beans.SignUpData;
import dao.SignUp_LoginDAO;

@Path("/loginNsignup")
public class SignUp_LoginService {
	
	@POST
	@Path("/signup")
	public String signUp(SignUpData signUpData){
		String message = "";
		try{
			System.out.println(signUpData.getCity());
			 SignUp_LoginDAO signUp_LoginDAO =Factory.getSignUp_LoginDAO();
			 message = signUp_LoginDAO.signUp(signUpData);
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	
}
