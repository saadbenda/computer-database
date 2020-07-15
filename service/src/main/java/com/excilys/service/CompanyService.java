package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Company;
import persistence.CompanyRepository;
import persistence.ComputerRepository;

@Service

public class CompanyService {
	@Autowired
	ComputerRepository computerRepo;

	@Autowired
	CompanyRepository companyRepo;

	public List<Company> findAll() {
	    	return companyRepo.findAll();
	}
	
	
}
