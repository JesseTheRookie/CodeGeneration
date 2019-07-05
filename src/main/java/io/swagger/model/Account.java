package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Entity
@Validated
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"iban"})})
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
public class Account   {
    @Id
    @JsonProperty("Iban")
    private String iban = null;

    @JsonProperty("User")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JsonProperty("Name")
    private String name = null;

    @JsonProperty("Balance")
    private Double balance = null;


    /**
     * Gets or Sets accounttype
     */
    public enum AccounttypeEnum {
        CURRENT("CURRENT"),

        SAVINGS("SAVINGS");

        private String value;

        AccounttypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static AccounttypeEnum fromValue(String text) {
            for (AccounttypeEnum b : AccounttypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }
    @JsonProperty("Accounttype")
    private AccounttypeEnum accounttype = null;

    @JsonIgnore
    public String typeValue = null;

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        ACTIVE("ACTIVE"),

        FROZEN("FROZEN");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }
    @JsonProperty("Status")
    private StatusEnum status = null;

    public Account iban(String iban) {
        this.iban = iban;
        return this;
    }

    public Account (User user) {
        this.user = user;
    }


    public Account(String iban, Optional<User> user, String name, Double balance, String accounttype, String status){
        this.iban = iban;
        if(user.isPresent()){
            this.user = user.get();
        }
        this.name = name;
        this.balance = balance;
        this.accounttype = AccounttypeEnum.valueOf(accounttype);
        this.status = StatusEnum.valueOf(status);
        this.typeValue = accounttype;
    }

    public Account(String iban, User user, String name, Double balance, String accounttype, String status){
        this.iban = iban;
        this.user = user;
        this.name = name;
        this.balance = balance;
        this.accounttype = AccounttypeEnum.valueOf(accounttype);
        this.status = StatusEnum.valueOf(status);
        this.typeValue = accounttype;
    }

    public Account(){}

    /**
     * Get iban
     * @return iban
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @JsonIgnore
    /**
     * Get userId
     * @return userId
     **/

    public Integer getUserId(){ //wat doet dit hier?
        return this.user.getId();
    }

    public User getUser(){
        return this.user;
    }


    public Account name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     * @return name
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account balance(Double balance) {
        this.balance = balance;
        return this;
    }

    /**
     * Get balance
     * @return balance
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account accounttype(AccounttypeEnum accounttype) {
        this.accounttype = accounttype;
        return this;
    }

    /**
     * Get accounttype
     * @return accounttype
     **/
    @ApiModelProperty(value = "")

    public AccounttypeEnum getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(AccounttypeEnum accounttype) {
        this.accounttype = accounttype;
    }

    public Account status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     * @return status
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(this.iban, account.iban) &&
                Objects.equals(this.user, account.user) &&
                Objects.equals(this.name, account.name) &&
                Objects.equals(this.balance, account.balance) &&
                Objects.equals(this.accounttype, account.accounttype) &&
                Objects.equals(this.status, account.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, user, name, balance, accounttype, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");
        sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
        sb.append("    user: ").append(toIndentedString(user)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("    accounttype: ").append(toIndentedString(accounttype)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
