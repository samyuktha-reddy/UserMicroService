package samworks.UsersService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samworks.UsersService.entity.AccountDetails;
@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Integer> {

	
	Optional<AccountDetails> findByXyz(Integer userId);
	

}
