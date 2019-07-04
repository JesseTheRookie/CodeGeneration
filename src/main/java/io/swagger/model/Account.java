package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-04T17:47:19.062Z[GMT]")
public class Account   {
  @JsonProperty("iban")
  private String iban = null;

  @JsonProperty("user")
  @Valid
  private List<User> user = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  /**
   * The type of the account; current or savings
   */
  public enum TypeEnum {
    CURRENT("current"),
    
    SAVINGS("savings");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  /**
   * The status of the account; frozen or active
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
  @JsonProperty("status")
  private StatusEnum status = null;

  public Account iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * The Iban of the account
   * @return iban
  **/
  @ApiModelProperty(required = true, value = "The Iban of the account")
  @NotNull

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public Account user(List<User> user) {
    this.user = user;
    return this;
  }

  public Account addUserItem(User userItem) {
    if (this.user == null) {
      this.user = new ArrayList<User>();
    }
    this.user.add(userItem);
    return this;
  }

  /**
   * The user who owns the account
   * @return user
  **/
  @ApiModelProperty(value = "The user who owns the account")
  @Valid
  public List<User> getUser() {
    return user;
  }

  public void setUser(List<User> user) {
    this.user = user;
  }

  public Account name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the user
   * @return name
  **/
  @ApiModelProperty(required = true, value = "The name of the user")
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
   * The ammount of money in the account
   * @return balance
  **/
  @ApiModelProperty(required = true, value = "The ammount of money in the account")
  @NotNull

  @Valid
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Account type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the account; current or savings
   * @return type
  **/
  @ApiModelProperty(required = true, value = "The type of the account; current or savings")
  @NotNull

  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Account status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * The status of the account; frozen or active
   * @return status
  **/
  @ApiModelProperty(required = true, value = "The status of the account; frozen or active")
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
        Objects.equals(this.type, account.type) &&
        Objects.equals(this.status, account.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, user, name, balance, type, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
