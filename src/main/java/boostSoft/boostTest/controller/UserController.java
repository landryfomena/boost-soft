package boostSoft.boostTest.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boostSoft.boostTest.data.User;
import boostSoft.boostTest.service.api.UserServiceApi;


@RestController
@RequestMapping(path = "/auth")
public class UserController {

	@Autowired UserServiceApi userServiceApi;
	
	@PostMapping("/register")
	public HttpEntity<? extends Object> createUser(@RequestBody User user) {
		return userServiceApi.create(user);
	}
	
	@GetMapping("/select")
	public HttpEntity<? extends Object> findByUserName(@RequestParam String username){
		return userServiceApi.findByUserName(username);
	}
	
	@DeleteMapping("/delete")
	public HttpEntity<? extends Object> delete(@RequestParam String name){
		return userServiceApi.delete(name);
	}
	
	@PutMapping("/update")
	public HttpEntity<? extends Object> update(@RequestBody User user){
		return userServiceApi.update(user);
	}
	
	@PutMapping("/name")
	public HttpEntity<? extends Object> updateName(@RequestParam int userId,@RequestParam String name){
		return userServiceApi.updateName(userId, name);
	}
	
	@PutMapping("/subname")
	public HttpEntity<? extends Object> updateSubName(@RequestParam int userId,@RequestParam String subname){
		return userServiceApi.updateSubName(userId, subname);
	}
	
	@PutMapping("/username")
	public HttpEntity<? extends Object> updateUserName(@RequestParam int userId,@RequestParam String username){
		return userServiceApi.updateUserName(userId, username);
	}
	
	@PutMapping("/password")
	public HttpEntity<? extends Object> updatePassWord(@RequestParam int userId,@RequestParam String password){
		return userServiceApi.updatePassWord(userId, password);
	}
	
	@PutMapping("/mail")
	public HttpEntity<? extends Object> updateMail(@RequestParam int userId,@RequestParam String mail){
		return userServiceApi.updateMail(userId, mail);
	}
	
	@PutMapping("/phonenumber")
	public HttpEntity<? extends Object> updatePhoneNumber(@RequestParam int userId,@RequestParam String phonenumber){
		return userServiceApi.updatePhoneNumber(userId, phonenumber);
	}
	
	@PostMapping("/validate")
	public HttpEntity<? extends Object> validateUser(@RequestParam int validateMessage,@RequestParam String username){
		return userServiceApi.validateUser(validateMessage, username);
	}
	
	@GetMapping("/users")
	public HttpEntity<? extends Object> findAll(){
		return userServiceApi.findAll();
	}
	
	@PostMapping("/login")
	public HttpEntity<? extends Object> login(Principal principal){
		return userServiceApi.login(principal);
	}
	
	@PostMapping("/logout")
	public HttpEntity<? extends Object> logout(Principal principal){
		return userServiceApi.logout(principal);
	}
	
	@GetMapping("/name")
	public HttpEntity<? extends Object> findByName(@RequestParam String name){
		return userServiceApi.findByName(name);
	}
	
	@PostMapping("/verifyaccount")
	public HttpEntity<? extends Object> verifyAccount(@RequestParam String username){
		return userServiceApi.verifyAccount(username);
	}
	
	@PostMapping("/resetpassword")
	public HttpEntity<? extends Object> resetPasswordUser(@RequestParam String password,@RequestParam int validateMessage,@RequestParam String username){
		return userServiceApi.resetPasswordUser(password, validateMessage, username);
	}
	
	@PostMapping("/blocked")
	public HttpEntity<? extends Object> blockedUser(@RequestParam String username){
		return userServiceApi.blockedUser(username);
	}
	
	@PostMapping("/suspend")
	public HttpEntity<? extends Object> suspendedUser(@RequestParam String username){
		return userServiceApi.suspendedUser(username);
	}
}
