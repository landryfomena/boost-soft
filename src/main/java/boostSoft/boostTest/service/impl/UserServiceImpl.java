package boostSoft.boostTest.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;


import boostSoft.boostTest.data.User;
import boostSoft.boostTest.data.UserStatus;
import boostSoft.boostTest.mail.EmailServiceImpl;
import boostSoft.boostTest.repository.UserRepository;
import boostSoft.boostTest.service.api.UserServiceApi;

@Service
public class UserServiceImpl implements UserServiceApi {

	@Autowired
	UserRepository userRepository;
	@Autowired
	SimpleMailMessage template;
	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Override
	public HttpEntity<? extends Object> create(User user) {
		try {

			Random r = new Random();
			user.setValidate(false);
			user.setUserStatus(UserStatus.AWAITINGVALIDATION.getStatut());
			user.setPassWord(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassWord()));
			user.setValidateMessage(r.nextInt(10000 - 1000));
			User currentUser = userRepository.save(user);

			String text = String.format(template.getText());
			emailServiceImpl.sendSimpleMessage("boost.test.v1@gmail.com", user.getMail(), "Welcome", text);

			/*
			 * Twilio.init(ACd1667c4efdd5e5790fe7c3254046776b,
			 * 7b31db8ba18a32eaff173d42b673e960);
			 */
			/*Message message = Message.creator(new PhoneNumber(user.getPhoneNumber()), new PhoneNumber("+12052935591"),
					"your validation code is" + user.getValidateMessage()).create();*/

			return new ResponseEntity<User>(currentUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> delete(String username) {
		try {
			User currentUser = userRepository.findByUserName(username);
			currentUser.setUserStatus(UserStatus.DELETED.getStatut());
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> findByUserName(String username) {
		try {
			User currentUser = userRepository.findByUserName(username);
			return new ResponseEntity<User>(currentUser, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> update(User user) {
		try {
			User currentUser = userRepository.findById(user.getUserId()).get();
			currentUser.setName(user.getName());
			currentUser.setSubName(user.getSubName());
			currentUser.setUserName(user.getUserName());
			currentUser
					.setPassWord(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassWord()));
			currentUser.setMail(user.getMail());
			currentUser.setPhoneNumber(user.getPhoneNumber());
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> updateName(int userId, String name) {
		try {
			User currentUser = userRepository.findById(userId).get();
			currentUser.setName(name);
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> updateSubName(int userId, String subname) {
		try {
			User currentUser = userRepository.findById(userId).get();
			currentUser.setSubName(subname);
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> updateUserName(int userId, String username) {
		try {
			User currentUser = userRepository.findById(userId).get();
			currentUser.setUserName(username);
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> updatePassWord(int userId, String password) {
		try {
			User currentUser = userRepository.findById(userId).get();
			currentUser.setPassWord(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password));
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> updateMail(int userId, String mail) {
		try {
			User currentUser = userRepository.findById(userId).get();
			currentUser.setMail(mail);
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> updatePhoneNumber(int userId, String phonenumber) {
		try {
			User currentUser = userRepository.findById(userId).get();
			currentUser.setPhoneNumber(phonenumber);
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> validateUser(int validateMessage, String username) {
		try {
			User currentUser = userRepository.findByUserName(username);
			if (currentUser.getValidateMessage().equals(validateMessage)) {
				currentUser.setValidate(true);
				currentUser.setUserStatus(UserStatus.VALID.getStatut());
				userRepository.saveAndFlush(currentUser);
			}
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> login(Principal principal) {
		try {
			User currentUser = userRepository.findByUserName(principal.getName());
			
			if (currentUser.getValidate()
					&& !(currentUser.getUserStatus().equalsIgnoreCase(UserStatus.BLOCKED.getStatut()))) {
				
				currentUser.setUserStatus(UserStatus.SIGNEDIN.getStatut());
				userRepository.saveAndFlush(currentUser);
				return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
				
			} else {
				
				if (!currentUser.getValidate()) {
					return new ResponseEntity<String>("please validate your account first ", HttpStatus.UNAUTHORIZED);
				}
				// l'utilisateur est bloque donc acces interdit
				else {

					return new ResponseEntity<String>(
							"you are not allowed to acces this system contact Boost Soft for more details",
							HttpStatus.FORBIDDEN);
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> findAll() {
		try {
			List<User> currentUsers = userRepository.findAll();
			return new ResponseEntity<List<User>>(currentUsers, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> logout(Principal principal) {
		try {
			User currentUser = userRepository.findByUserName(principal.getName());
			currentUser.setUserStatus(UserStatus.SIGNEDOUT.getStatut());
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> findByName(String name) {
		try {
			List<User> currentUsers = userRepository.findByName(name);
			return new ResponseEntity<List<User>>(currentUsers, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public HttpEntity<? extends Object> verifyAccount(String username) {
		// TODO Auto-generated method stub
		try {
			User currentUser = userRepository.findByUserName(username);
			Random r = new Random();
			currentUser.setValidateMessage(r.nextInt(10000 - 100));
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public HttpEntity<? extends Object> resetPasswordUser(String password, int validateMessage, String username) {
		// TODO Auto-generated method stub
		try {
			User currentUser = userRepository.findByUserName(username);
			if (currentUser.getValidateMessage() == validateMessage) {
				currentUser
						.setPassWord(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password));
				userRepository.saveAndFlush(currentUser);
				return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("Wrong code ", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public HttpEntity<? extends Object> blockedUser(String username) {
		try {
			User currentUser = userRepository.findByUserName(username);
			currentUser.setUserStatus(UserStatus.BLOCKED.getStatut());
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> suspendedUser(String username) {
		try {
			User currentUser = userRepository.findByUserName(username);
			currentUser.setUserStatus(UserStatus.SUSPENDED.getStatut());
			userRepository.saveAndFlush(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
