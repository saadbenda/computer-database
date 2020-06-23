package model;

public class Company {
	private long id;
	private String name;
	
	
	private Company(CompanyBuilder cb) {
		this.id=cb.idC;
		this.name=cb.nameC;
	}
	@Override
	public String toString() {
		return "  "+this.id+"  "+this.name;
	}
	public String getName() {
		return this.name;
	}
	public long getId() {
		return id;
	}
	
	
	public static class CompanyBuilder {
		private long idC;
		private String nameC;
		
		
		public CompanyBuilder withId(long id) {
			this.idC=id;
			return this;
		}
		public CompanyBuilder withName(String name) {
			this.nameC=name;
			return this;
		}
		
		public Company build() {
			return new Company(this);
		}
	}
}
