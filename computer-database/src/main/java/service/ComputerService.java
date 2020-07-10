package service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Company;
import model.Computer;
import persistence.CompanyRepository;
import persistence.ComputerRepository;

@Service
//@Transactional
public class ComputerService {
	@Autowired 
	ComputerRepository computerRepo;
	
	@Autowired 
	CompanyRepository companyRepo;
	
    
    public Computer save(Computer customer) {
    	return computerRepo.save(customer);
    }
     
    
   
}
