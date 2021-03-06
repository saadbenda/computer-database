package dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class CompanyDto {
	@NotNull
	@Size(min=1, max=20)
	private String id;
	
	@NotNull
	@Size(min=1, max=255)
	private String name;
	
	public CompanyDto() {}
	
	public String getId() {
		return this.id;
	}

	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", name=" + name + "]";
	}
	
	private CompanyDto (CompanyDtoBuilder cdb) {
		this.id=cdb.id;
		this.name=cdb.name;
	}
	
	
	public static class CompanyDtoBuilder {
		private String id;
		private String name;
		
		public CompanyDtoBuilder withName(String name) {
			this.name=name;
			return this;
		}
		
		public CompanyDtoBuilder withId(String id) {
			this.id=id;
			return this;
		}
		
		public CompanyDto build() {
			return new CompanyDto(this);  
		}

		
		
		
	}
}
