package persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	@Override
	User save(User user);
	@Override
	Optional<User> findById(Long id);
	@Override
	boolean existsById(Long id);
	@Override
	Optional<User> findByEmail(String email);
	
	
	
	/*
	@Override
	public <S extends Company> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public Iterable<Company> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Company> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Company entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Company> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	*/



}
