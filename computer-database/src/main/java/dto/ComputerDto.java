package dto;

public class ComputerDto {
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDto companyDto;

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
