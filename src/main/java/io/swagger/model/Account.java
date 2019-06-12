package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
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
@Table(name = "accounts")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
public class Account   {
  @Id
  @JsonProperty("Iban")
  private String iban = null;
/*
  @JsonProperty("id")
  private Long id = null;
*/
  @JsonProperty("User")
  @ManyToOne(targetEntity = User.class)
  private User user;

  @JsonProperty("Name")
  private String name = null;

  @JsonProperty("Balance")
  private BigDecimal balance = null;

  /**
   * Gets or Sets accounttype
   */
  public enum AccounttypeEnum {
    CURRENT("current"),
    
    SAVINGS("savings");

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

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    ACTIVE("active"),
    
    FROZEN("frozen");

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
/*
  public Account(String iban, Long id, String name, BigDecimal balance, AccounttypeEnum type, StatusEnum status){
    this.iban = iban;
    this.id = id;
    this.name = name;
    this.balance = balance;
    this.accounttype = type;
    this.status = status;
  }*/


  public Account(String iban, Optional<User> user, String name, BigDecimal balance, AccounttypeEnum type, StatusEnum status){
    this.iban = iban;
    if(user.isPresent()){
      this.user = user.get();
    }
    this.name = name;
    this.balance = balance;
    this.accounttype = type;
    this.status = status;
  }

  /*
  public Account(String iban, User user, String name, BigDecimal balance, AccounttypeEnum type, StatusEnum status){
    this.iban = iban;
    this.user = user;
    this.name = name;
    this.balance = balance;
    this.accounttype = type;
    this.status = status;
  }*/

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
/*
  public Account id(Long id) {
    this.id = id;
    return this;
  }*/

  /**
   * Get userId
   * @return userId
  **/
//  @ApiModelProperty(required = true, value = "")
//  @NotNull
/*
  @Valid
  public Long getId() {
    return id;
  }

  public void setId(Long userId) {
    this.id = userId;
  }*/

  public Integer getUserId(){
    return this.user.getId();
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

  public Account balance(BigDecimal balance) {
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
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
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
