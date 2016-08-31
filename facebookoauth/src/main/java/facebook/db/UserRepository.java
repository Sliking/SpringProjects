package facebook.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import facebook.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByEmail(String email);

}
