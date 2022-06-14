package uz.pdp.websocketdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.websocketdemo.model.Role;
import uz.pdp.websocketdemo.model.User;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {


}
