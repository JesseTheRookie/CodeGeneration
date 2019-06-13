package io.swagger.model;

import java.sql.Timestamp;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;


import lombok.NoArgsConstructor;
import lombok.ToString;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Deposit
 */
@Entity
@Validated
@NoArgsConstructor
@ToString
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
public class Deposit   {

  @Id
  @GeneratedValue
  private Long id;

  @JsonProperty("To")
  private String to = null;

  @JsonProperty("Amount")
  private BigDecimal amount = null;

  @JsonProperty("TimeStamp")
  private Timestamp timeStamp = new Timestamp(new Date().getTime());

  public Deposit to(String to) {
    this.to = to;
    return this;
  }

  public Deposit(String to, BigDecimal amount) {
    this.to = to;
    this.amount = amount;
  }

  /**
   * de iban van de gebruiker
   * @return to
  **/
  @ApiModelProperty(required = true, value = "de iban van de gebruiker")
  @NotNull

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Deposit amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * het te storten bedrag
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "het te storten bedrag")
  @NotNull

  @Valid
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Deposit timeStamp(Timestamp timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Get timeStamp
   * @return timeStamp
  **/
  @ApiModelProperty(value = "")

  @Valid
  public Timestamp getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Timestamp timeStamp) {
    this.timeStamp = timeStamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Deposit deposit = (Deposit) o;
    return Objects.equals(this.to, deposit.to) &&
        Objects.equals(this.amount, deposit.amount) &&
        Objects.equals(this.timeStamp, deposit.timeStamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(to, amount, timeStamp);
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
