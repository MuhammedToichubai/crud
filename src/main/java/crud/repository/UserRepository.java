package crud.repository;

import crud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.NoSuchElementException;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> getByEmail(String email);

    default User findByEmail(String email){
       return getByEmail(email).orElseThrow(() -> new NoSuchElementException("User with email: " + " not exists"));
    }

    boolean existsByEmail(String email);

}