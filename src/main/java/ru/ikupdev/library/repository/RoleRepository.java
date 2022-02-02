package ru.ikupdev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.Role;

import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
