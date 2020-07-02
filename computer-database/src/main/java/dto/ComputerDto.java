package dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;


public class ComputerDto {
	
	@NotNull
	@Size(min=1, max=20)
	private String id;
	@NotBlank
	@NotNull
	@Size(min=1, max=255)
	private String name;
	@Pattern(regexp = "?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))", message="date must follow this pattern yyyy-mm-dd")
	private String introduced;
	@Pattern(regexp = "?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))", message="date must follow this pattern yyyy-mm-dd")
	private String discontinued;
	private CompanyDto companyDto;
	
	public ComputerDto() {}

	private ComputerDto(ComputerDtoBuilder cdb) {
		this.id = cdb.id;
		this.name = cdb.name;
		this.introduced = cdb.introduced;
		this.discontinued = cdb.discontinued;
		this.companyDto = cdb.companyDto;

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public CompanyDto getCompanyDto() {
		return companyDto;
	}

	@Override
	public String toString() {
		return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyDto=" + companyDto + "]";
	}

	public static class ComputerDtoBuilder {
		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private CompanyDto companyDto;

		public ComputerDtoBuilder withId(String id) {
			this.id = id;
			return this;

		}

		public ComputerDtoBuilder withName(String name) {
			this.name = name;
			return this;

		}

		public ComputerDtoBuilder IntroducedIn(String introduced) {
			this.introduced = introduced;
			return this;

		}

		public ComputerDtoBuilder DiscontinuedIn(String discontinued) {
			this.discontinued = discontinued;
			return this;

		}

		public ComputerDtoBuilder withCompanyDto(CompanyDto companyDto) {
			this.companyDto = companyDto;
			return this;
		}

		public ComputerDto build() {
			return new ComputerDto(this);
		}

	}

}
