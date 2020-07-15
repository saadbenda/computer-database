package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "computer")
public class Computer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	
	public Computer() {}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	
	

	public static class ComputerBuilder {
		private long id=0L;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;
		
		
		public ComputerBuilder withId(long id) {
			this.id=id;
			return this;
			
		}
		
		public ComputerBuilder withName(String name) {
			this.name=name;
			return this;
			
		}
		public ComputerBuilder IntroducedIn(LocalDate introduced2) {
			this.introduced=introduced2;
			return this;
			
		}
		public ComputerBuilder DiscontinuedIn(LocalDate discontinued) {
			this.discontinued=discontinued;
			return this;
			
		}
		public ComputerBuilder withCompany(Company company) {
			this.company=company;
			return this;	
		}
		
		
		public Computer build() {
			Computer computer = new Computer(this);
			return computer;	
		}
	}	
	private Computer(ComputerBuilder cb) {
		this.id=cb.id;
		this.company=cb.company;
		this.name=cb.name;
		this.introduced=cb.introduced;
		this.discontinued=cb.discontinued;
	}
	@Override
	public String toString() {
		return "id "+this.id+" name "+this.name+" introduced "+this.introduced+" discontinued "+this.discontinued+" company name "/*+this.company.toString()*/;
	}
	
}
