package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Withdrawal
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
public class Withdrawal   {
  @JsonProperty("From")
  private String from = null;

  @JsonProperty("Amount")
  private BigDecimal amount = null;

  @JsonProperty("TimeStamp")
  private OffsetDateTime timeStamp = null;

  public Withdrawal from(String from) {
    this.from = from;
    return this;
  }

  /**
   * de iban van de gebruiker
   * @return from
  **/
  @ApiModelProperty(required = true, value = "de iban van de gebruiker")
  @NotNull

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Withdrawal amount(BigDecimal amount) {
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
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Withdrawal timeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Get timeStamp
   * @return timeStamp
  **/
  @ApiModelProperty(value = "")

  @Valid
  public OffsetDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(OffsetDateTime timeStamp) {
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
    return Objects.equals(this.from, withdrawal.from) &&
        Objects.equals(this.amount, withdrawal.amount) &&
        Objects.equals(this.timeStamp, withdrawal.timeStamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, amount, timeStamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Withdrawal {\n");
    
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
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
