package io.swagger.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Withdrawal
 */
@Entity
@Validated
@NoArgsConstructor
@ToString
//@Table(name = "withdrawal")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
public class Withdrawal   {

  @Id
  @GeneratedValue
  private Long id;

  @JsonProperty("SenderIban")
  private String senderIban = null;

  @JsonProperty("Amount")
  private Double amount = null;

  @JsonProperty("TimeStamp")
  private Timestamp timeStamp = new Timestamp(new Date().getTime());


  public Withdrawal(String senderIban, Double amount) {
    this.senderIban = senderIban;
    this.amount = amount;
  }

  public Withdrawal senderIban(String senderIban) {
    this.senderIban = senderIban;
    return this;
  }

  /**
   * de iban van de gebruiker
   * @return from
  **/
  @ApiModelProperty(required = true, value = "de iban van de gebruiker")
  @NotNull

  public String getSenderIban() {
    return senderIban;
  }

  public void setSenderIban(String senderIban) {
    this.senderIban = senderIban;
  }

  public Withdrawal amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * het op te nemen bedrag
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "het op te nemen bedrag")
  @NotNull

  @Valid
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Withdrawal timeStamp(Timestamp timeStamp) {
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
    Withdrawal withdrawal = (Withdrawal) o;
    return Objects.equals(this.senderIban, withdrawal.senderIban) &&
        Objects.equals(this.amount, withdrawal.amount) &&
        Objects.equals(this.timeStamp, withdrawal.timeStamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(senderIban, amount, timeStamp);
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
