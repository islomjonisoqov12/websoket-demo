package uz.pdp.websocketdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.websocketdemo.model.Message;
import uz.pdp.websocketdemo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(nativeQuery = true,
            value = """
                    select id
                    from users
                    where username = :username""")
    UUID getUserIdByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query(value = " select cast(id as varchar) from users ",nativeQuery = true)
    List<String> getContacts(String username);
}
