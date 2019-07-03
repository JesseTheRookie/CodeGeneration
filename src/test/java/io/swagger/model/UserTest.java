package io.swagger.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.yaml.snakeyaml.constructor.ConstructorException;

import java.util.Collection;
import java.util.List;

import static io.swagger.model.User.RoleEnum.EMPLOYEE;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void init(){
        user = new User(1,"TheoTester", "test123", "EMPLOYEE");
    }


    @Test
    public void userShouldNotBeNull(){
        assertNotNull(user);
    }

    @Test
    public void userNameShouldBeTheoTester(){
        assertEquals("TheoTester", user.getUsername());
    }

    @Test
    public void userPasswordShouldBeTest123(){
        assertEquals("test123", user.getPassword());
    }

    @Test
    public void userRoleShouldBeEmployee(){
        assertEquals(EMPLOYEE, user.getRole());
    }

}