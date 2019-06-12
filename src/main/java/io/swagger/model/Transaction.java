package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Entity
@Validated
@Table(name = "transactions")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
public class Transaction   {
  @Id
  @GeneratedValue
  @JsonProperty("Id")
  private Integer id = null;

  @JsonProperty("From")
  private String from = null;

  @JsonProperty("To")
  private String to = null;

  @JsonProperty("Amount")
  private BigDecimal amount = null;

  @JsonProperty("TimeStamp")
  private OffsetDateTime timeStamp = null;

  @JsonProperty("PerformedBy")
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
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Transaction from(String from) {
    this.from = from;
    return this;
  }

  /**
   * the iban of the sending end
   * @return from
  **/
  @ApiModelProperty(required = true, value = "the iban of the sending end")
  @NotNull

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Transaction to(String to) {
    this.to = to;
    return this;
  }

  /**
   * the iban of the receiving end
   * @return to
  **/
  @ApiModelProperty(required = true, value = "the iban of the receiving end")
  @NotNull

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
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
        Objects.equals(this.from, transaction.from) &&
        Objects.equals(this.to, transaction.to) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.timeStamp, transaction.timeStamp) &&
        Objects.equals(this.performedBy, transaction.performedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, from, to, amount, timeStamp, performedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
