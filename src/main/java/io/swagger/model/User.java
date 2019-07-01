package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * User
 */
@Entity
@Validated
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
public class User   {
    @Id
    @SequenceGenerator(name = "user_seq", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @JsonProperty("Id")
    private Integer id; // needs to be converted to Long

    @JsonProperty("Name")
    private String username = null; // needs to be username

    @JsonIgnore
    private String password = null;


    /**
     * Gets or Sets role
     */
    public enum RoleEnum {
        USER("USER"),

        EMPLOYEE("EMPLOYEE"),

        USER_EMPLOYEE("USER_EMPLOYEE");

        private String value;

        RoleEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static RoleEnum fromValue(String text) {
            for (RoleEnum b : RoleEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }
    @JsonProperty("Role")
    private RoleEnum role = null;


    public User id(Integer id) {
        this.id = id;
        return this;
    }

    public User(Integer id, String username, String password, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = RoleEnum.valueOf(role);
    }

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = RoleEnum.valueOf(role);;
    }

    public User(){}

    /**
     * Get id
     * @return id
     **/
    @ApiModelProperty(required = true, value = "")

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Get username
     * @return username
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Get password
     * @return password
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("Password")
    public void setPassword(String password) {
        this.password = password;
    }

    public User role(RoleEnum role) {
        this.role = role;
        return this;
    }

    /**
     * Get role
     * @return role
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.username, user.username) &&
                Objects.equals(this.password, user.password) &&
                Objects.equals(this.role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    role: ").append(toIndentedString(role)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
