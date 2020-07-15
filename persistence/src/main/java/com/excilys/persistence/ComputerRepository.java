package persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Computer;
 
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
     
    /*
	@Query(value = "SELECT c FROM Customer c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'"
            + " OR c.address LIKE '%' || :keyword || '%'")
    public List<Computer> search(@Param("keyword") String keyword);
    */
    
    Computer save(Computer computer); 
    
    //List<Computer> findAll();

	//Computer findById(String id);

	//List<Computer> findByName();

	//List<Computer> findByNameContainingIgnoreCase(String computerName);
	
	
	
	
	
    
    
}