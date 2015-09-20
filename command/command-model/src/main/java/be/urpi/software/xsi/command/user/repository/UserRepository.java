package be.urpi.software.xsi.command.user.repository;

import be.urpi.software.xsi.command.user.aggregrate.UserAR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = UserRepository.NAME)
public interface UserRepository extends JpaRepository<UserAR, String> {
    String NAME = "userRepository";
}
