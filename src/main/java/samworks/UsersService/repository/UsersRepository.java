package samworks.UsersService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import samworks.UsersService.entity.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, Integer>{

}
