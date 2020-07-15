package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "company")
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name")
	private String name;
	
	public Company() {
		
	}
	
	
	public Company(CompanyBuilder cb) {
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
	
	public void setName(String name) {
		this.name=name;
	}
	public void setId(long id) {
		this.id=id;
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
