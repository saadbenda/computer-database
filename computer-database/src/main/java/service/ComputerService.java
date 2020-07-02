package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Computer;

@Service
@Transactional
public class ComputerService {
	@Autowired CustomerRepository repo;
    
    public void save(Computer customer) {
        repo.save(customer);
    }
     
    public List<Customer> listAll() {
        return (List<Customer>) repo.findAll();
    }
     
    public Customer get(Long id) {
        return repo.findById(id).get();
    }
     
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public List<Customer> search(String keyword) {
        return repo.search(keyword);
    }
}
