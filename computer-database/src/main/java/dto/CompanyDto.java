package dto;

public class CompanyDto {
	private String id;
	private String name;
	
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
