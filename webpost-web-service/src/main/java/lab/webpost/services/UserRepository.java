package lab.webpost.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.webpost.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    //TODO: add necessary derived methods
}
