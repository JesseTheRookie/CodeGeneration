package io.swagger.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("Id")
  private Integer id = null;

  @JsonProperty("FromIban")
  private String fromIban = null;

  @JsonProperty("To")
  private String to = null;

  @JsonProperty("Amount")
  private Double amount = null;

  @JsonProperty("TimeStamp")
  //private Timestamp timeStamp = null;
  private Timestamp timeStamp = new Timestamp(new Date().getTime());

  @JsonProperty("PerformedBy")
  private Integer performedBy = null;

  public Transaction id(Integer id) {
    this.id = id;
    return this;
  }

  /*public Transaction(Integer id, String from, String to, BigDecimal amount, Timestamp timeStamp, Integer performedBy){
    this.id = id;
    this.from = from;
    this.to = to;
    this.amount = amount;
    this.timeStamp = timeStamp;
    this.performedBy = performedBy;
  }*/

  public Transaction(String fromIban, String to, Double amount, Integer performedBy){
    this.fromIban = fromIban;
    this.to = to;
    this.amount = amount;
    this.performedBy = performedBy;
  }
  public Transaction(){

  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getId() {
    return this.id;
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
   * @return from
  **/
  @ApiModelProperty(required = true, value = "the iban of the sending end")
  @NotNull

  public String getFromIban() {
    return fromIban;
  }

  public void setFrom(String from) {
    this.fromIban = fromIban;
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

  public Transaction amount(Double amount) {
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
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Transaction timeStamp(Timestamp timeStamp) {
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
  public Timestamp getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Timestamp timeStamp) {
    this.timeStamp = timeStamp;
  }

  public Transaction performedBy(Integer performedBy) {
    this.performedBy = performedBy;
    return this;
  }
  static public Timestamp parseStringToTimeStamp(String s){
    Timestamp timestamp = Timestamp.valueOf(s);
    return timestamp;
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
        Objects.equals(this.to, transaction.to) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.timeStamp, transaction.timeStamp) &&
        Objects.equals(this.performedBy, transaction.performedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fromIban, to, amount, timeStamp, performedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    from: ").append(toIndentedString(fromIban)).append("\n");
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
