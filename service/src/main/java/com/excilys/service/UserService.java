package service;

import java.util.Arrays;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import exceptions.EmailExistsException;
import model.User;
import persistence.UserRepository;


@Service
@Transactional

public class UserService implements UserDetailsManager{
	
//	@Autowired
//	User user;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	 public PasswordEncoder passwordEncoder2() {
	        return new BCryptPasswordEncoder();
	    }

	public User saveUser(@Valid User userl) {
	    //System.out.println("user in service : "+user);
	    User user = new User();
	    user.setEmail("saad@saad.com");
	    user.setRole("ADMIN");
	    user.setFirstName("saad");
	    user.setLastName("saad");
	    user.setPassword(new BCryptPasswordEncoder().encode("password"));
	    //user.setRoles(Arrays.asList();
	    return userRepo.save(user);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optUser = userRepo.findByEmail(username);
		UserBuilder userBuilder = null;
		if(optUser.isPresent()) {
			User user  = optUser.get();
			userBuilder = org.springframework.security.core.userdetails.User
					.withUsername(user.getEmail())
					.password(user.getPassword())
					.roles(user.getRole());
			System.out.println(user);
			
		}else {
			throw new EmailExistsException();
		}
		
		return userBuilder.build();
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
