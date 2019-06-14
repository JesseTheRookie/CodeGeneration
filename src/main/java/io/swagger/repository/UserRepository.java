package io.swagger.repository;


import io.swagger.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.name =?1")
    public User getUserByName(String name);

    @Query("select u from User u where u.id =?1")
    public User getUserById(Integer id);

}
