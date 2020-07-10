package validation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import dto.UserDto;
import persistence.UserRepository;

public class validationUser {

	@Autowired
	UserRepository userRepo;
	
	public void emailExists(@Valid UserDto user) {
		userRepo.findByEmail(user.getEmail());
	}

}
