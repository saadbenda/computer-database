package dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import validation.CustomPasswordMatchesAnnotation;
import validation.CustomValidEmailAnnotation;

@CustomPasswordMatchesAnnotation
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;
    
    @NotNull
    @NotEmpty
    private String lastName;
    
    @NotNull
    @NotEmpty
    private String password;
    
    private String matchingPassword;
    
    @NotNull
    @NotEmpty
    @CustomValidEmailAnnotation
    private String email;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", matchingPassword=" + matchingPassword + ", email=" + email + "]";
	}
    
   
}