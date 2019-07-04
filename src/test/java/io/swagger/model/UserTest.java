package io.swagger.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static io.swagger.model.User.RoleEnum.EMPLOYEE;
import static io.swagger.model.User.RoleEnum.USER;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp(){
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
    public void userNameShouldBeSetToDummy() {
        user.setUsername("Dummy");
        assertEquals("Dummy", user.getUsername());
    }

    @Test
    public void userPasswordShouldBeTest123(){
        assertEquals("test123", user.getPassword());
    }

    @Test
    public void userPassWordShouldBeSetToPass123() {
        user.setPassword("pass123");
        assertTrue(new BCryptPasswordEncoder().matches("pass123",user.getPassword()));
    }

    @Test
    public void userRoleShouldBeEmployee(){
        assertEquals(EMPLOYEE, user.getRole());
    }

    @Test
    public void userRoleShouldBeSetToUser() {
        user.setRole("USER");
        assertEquals(USER, user.getRole());
    }

    @Test
    public void userAuthoritiesShouldBeEmployee(){
        assertTrue(user.hasAuthority(EMPLOYEE));
    }

    @Test
    public void userAuthoritiesShouldNotBeUser(){
        assertFalse(user.hasAuthority(USER));
    }

}