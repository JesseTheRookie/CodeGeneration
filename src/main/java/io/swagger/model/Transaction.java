package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-04T17:47:19.062Z[GMT]")
public class Transaction   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("fromIban")
  private String fromIban = null;

  @JsonProperty("toIban")
  private String toIban = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  /**
   * the type of transaction
   */
  public enum TypeEnum {
    TRANSACTION("transaction"),
    
    DEPOSIT("deposit"),
    
    WITHDRAWAL("withdrawal");

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

  @JsonProperty("timeStamp")
  private OffsetDateTime timeStamp = null;

  @JsonProperty("performedBy")
  private Integer performedBy = null;

  public Transaction id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Transaction fromIban(String fromIban) {
    this.fromIban = fromIban;
    return this;
  }

  /**
   * the iban of the sending end
   * @return fromIban
  **/
  @ApiModelProperty(required = true, value = "the iban of the sending end")
  @NotNull

  public String getFromIban() {
    return fromIban;
  }

  public void setFromIban(String fromIban) {
    this.fromIban = fromIban;
  }

  public Transaction toIban(String toIban) {
    this.toIban = toIban;
    return this;
  }

  /**
   * the iban of the receiving end
   * @return toIban
  **/
  @ApiModelProperty(required = true, value = "the iban of the receiving end")
  @NotNull

  public String getToIban() {
    return toIban;
  }

  public void setToIban(String toIban) {
    this.toIban = toIban;
  }

  public Transaction amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * the amount to transfer
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "the amount to transfer")
  @NotNull

  @Valid
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Transaction type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * the type of transaction
   * @return type
  **/
  @ApiModelProperty(required = true, value = "the type of transaction")
  @NotNull

  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Transaction timeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Get timeStamp
   * @return timeStamp
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public OffsetDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public Transaction performedBy(Integer performedBy) {
    this.performedBy = performedBy;
    return this;
  }

  /**
   * userID of the user who creates the transaction
   * @return performedBy
  **/
  @ApiModelProperty(required = true, value = "userID of the user who creates the transaction")
  @NotNull

  public Integer getPerformedBy() {
    return performedBy;
  }

  public void setPerformedBy(Integer performedBy) {
    this.performedBy = performedBy;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.fromIban, transaction.fromIban) &&
        Objects.equals(this.toIban, transaction.toIban) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.type, transaction.type) &&
        Objects.equals(this.timeStamp, transaction.timeStamp) &&
        Objects.equals(this.performedBy, transaction.performedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fromIban, toIban, amount, type, timeStamp, performedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fromIban: ").append(toIndentedString(fromIban)).append("\n");
    sb.append("    toIban: ").append(toIndentedString(toIban)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    performedBy: ").append(toIndentedString(performedBy)).append("\n");
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
