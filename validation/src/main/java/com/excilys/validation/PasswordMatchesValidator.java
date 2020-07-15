package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dto.UserDto;

public class PasswordMatchesValidator 
  implements ConstraintValidator<CustomPasswordMatchesAnnotation, Object> { 
    
	 @Override
	    public void initialize(CustomPasswordMatchesAnnotation constraintAnnotation) {       
	    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){   
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());    
    }
	 
}