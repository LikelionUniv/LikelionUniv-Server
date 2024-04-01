package likelion.univ.domain.user.repository;

import java.util.List;
import java.util.Optional;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByAuthInfoEmail(String email);

    Boolean existsByAuthInfoEmail(String email);

    @Query(value = "SELECT u FROM User u join fetch u.universityInfo.university where u.id = :id ")
    Optional<User> findByIdWithUniversity(Long id);

    List<User> findByAuthInfoRoleIn(List<Role> roles);

    List<User> findAllByIdIn(List<Long> ids);
}
