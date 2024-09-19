package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.dao.UserRepository;
import com.filter.JwtToken;
import com.model.Item;
import com.service.*;
import com.model.Role;
class AuthenticateUser
{ 
	private String userName;
	private String password;
	private String role;
	public AuthenticateUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
class JwtResponse
{
	private String token;
	
	public JwtResponse(String token) 
	{
		this.token=token;
	}
	public String getToken() { 
		return token;
	}
}
@RestController
@CrossOrigin("*")
public class ItemServiceController {
	
	@Autowired
	ItemRepoService itemRepoService;
	
	@Autowired
	DaoAuthenticationProvider provider;
	
	@Autowired
	UserRepository userRepository;
	public ItemServiceController()
	{
		System.out.println("controller invoked");
	}
  
	
	@PostMapping("/api/auth")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticateUser user)
	{
		JwtToken jwtToken=new JwtToken();
		AuthenticationManager manager=new ProviderManager(provider);
		
		 Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
		
		 if(authentication.isAuthenticated())
		{
			 String username=user.getUserName();
			 String password=user.getPassword();
			 List<Role> roleList=userRepository.findByUsername(username).get().getRoles();
             ResponseEntity res=new ResponseEntity(HttpStatus.BAD_REQUEST);
			 for(Role r:roleList)
		     {
		    	 if(user.getRole().equals(r.getName()))
		    	 {
		    		 jwtToken.generateToken(username, password,user.getRole());
					  res= new ResponseEntity<JwtResponse>(new JwtResponse(jwtToken.getToken()),HttpStatus.ACCEPTED);
			
		    	 }
		    
		    	 return res;
		    	 
		     }
			 return res;
			 
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			

		}
		 	}
	
	@GetMapping("/getitems")
		
	public ResponseEntity<?> getAllItems()
	{
		return itemRepoService.getItemList();
	}
	@PostMapping("/additem")
	
	public ResponseEntity<?> addItem(@RequestBody Item item)
	{ 
		
		return itemRepoService.addItem(item);
	}
	@PutMapping("/updateitem")
	public ResponseEntity<?> updateItem(@RequestBody Item item)
	{ 
		return itemRepoService.updateItem(item);
	}
	
	@DeleteMapping("/deleteitem/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable  int id) 
	{ 
	     return itemRepoService.deleteItem(id);	
	}
	@GetMapping("/getinfo")
  public ResponseEntity<?> getInfo() throws Exception
  {
		throw new Exception("Exception raised");
  }

	@GetMapping("/findbyname/{name}")
	public ResponseEntity<Item> getByName(@PathVariable String name)
	{
		   return itemRepoService.findByName(name);
	}
	
	
}
