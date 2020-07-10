package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.User;
import persistence.UserRepository;



@Service
public class UserService {
	
	@Autowired
	User user;
	
	@Autowired
	UserRepository userRepo;

	public User addUser(User user) {
		if (emailExist(accountDto.getEmail())) {
	        throw new EmailExistsException(
	          "There is an account with that email adress:" + accountDto.getEmail());
	    }
		
		
	   
	    user.setFirstName(accountDto.getFirstName());
	    user.setLastName(accountDto.getLastName());
	    
	    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
	    
	    user.setEmail(accountDto.getEmail());
	    user.setRole(new Role(Integer.valueOf(1), user));
	    return userRepo.save(user);
		
	}

}
