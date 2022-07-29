package funny.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import funny.movies.models.Role;
import funny.movies.models.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<funny.movies.models.Role> findByName(RoleName roleName);
}
