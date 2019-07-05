package io.swagger.repository;


import io.swagger.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUsername(String username);
    User findUserById(Integer id);

    @Query("select u from User u where u.username =?1")
    public User getUserByUsername(String username);

    @Query("select u from User u where u.id =?1")
    public User getUserById(Integer id);

}
