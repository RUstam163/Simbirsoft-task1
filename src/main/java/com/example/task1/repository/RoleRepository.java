package com.example.task1.repository;

        import com.example.task1.model.Role;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleById(Long id);
}
