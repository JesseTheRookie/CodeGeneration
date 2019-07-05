package io.swagger.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.api.SecurityController;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("Id")
    private Integer id = null;

    //optional
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("FromIban")
    private String fromIban = null;

    //optional
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ToIban")
    private String toIban = null;

    @JsonProperty("Amount")
    private Double amount = null;

    @JsonProperty("Type")
    private TransactionType type = null;

    @JsonProperty("TimeStamp")
    private Timestamp timeStamp = new Timestamp(new Date().getTime());

    @JsonProperty("PerformedBy")
    private Integer performedBy = null;

    @Transient
    @Autowired
    private SecurityController securityController;

    /**
     * Gets or Sets transaction type
     */
    public enum TransactionType {
        TRANSACTION("transaction"),
        DEPOSIT("deposit"),
        WITHDRAWAL("withdrawal");

        private String value;

        TransactionType(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static TransactionType fromValue(String text) {
            for (TransactionType b : TransactionType.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    //constructor for transactions
    public Transaction(String fromIban, String toIban, Double amount, String type) {
        this.fromIban = fromIban;
        this.toIban = toIban;
        this.amount = amount;
        this.type = TransactionType.valueOf(type);
        this.performedBy = securityController.currentUserId();
    }

    //constructor for deposits/withdrawals
    public Transaction(String Iban, Double amount, String type) {
        this.amount = amount;
        this.type = TransactionType.valueOf(type);
        this.performedBy = securityController.currentUserId();

        if (this.type.equals(TransactionType.DEPOSIT)) {
            this.toIban = Iban;
        } else {
            this.fromIban = Iban;
        }
    }

    //no args constructor
    public Transaction() {
    }

    @ApiModelProperty(required = true, value = "")
    public Integer getId() {
        return this.id;
    }

    public TransactionType getType() {
        return this.type;
    }


    public String getFromIban() {
        return fromIban;
    }

    public String getToIban() {
        return toIban;
    }

    @ApiModelProperty(required = true, value = "the amount toIban transfer")
    @NotNull
    @Valid
    public Double getAmount() {
        return amount;
    }

    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    @ApiModelProperty(required = true, value = "userID of the user who creates the transaction")
    @NotNull
    public Integer getPerformedBy() {
        return performedBy;
    }


    //setters
    private void setId(Integer id) {
        this.id = id;
    }

    public void setFromIban(String fromIban) {
        this.fromIban = fromIban;
    }

    public void setToIban(String toIban) {
        this.toIban = toIban;
    }

    public void setAmount(Double amount) {
        if(amount < 0){
            throw new IllegalArgumentException("Amount is below zero");
        } else{
            this.amount = amount;
        }
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setPerformedBy(Integer performedBy) {
        this.performedBy = performedBy;
    }

    public Transaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Transaction timeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public Transaction performedBy(Integer performedBy) {
        this.performedBy = performedBy;
        return this;
    }

    static public Timestamp parseStringToTimeStamp(String s) {
        Timestamp timestamp = Timestamp.valueOf(s);
        return timestamp;
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
                Objects.equals(this.timeStamp, transaction.timeStamp) &&
                Objects.equals(this.performedBy, transaction.performedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromIban, toIban, amount, timeStamp, performedBy);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Transaction {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        if (fromIban != null) {
            sb.append("    fromIban: ").append(toIndentedString(fromIban)).append("\n");
        }
        if (toIban != null) {
            sb.append("    toIban: ").append(toIndentedString(toIban)).append("\n");
        }
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    type: ").append(toIndentedString(getType())).append("\n");
        sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
        sb.append("    performedBy: ").append(toIndentedString(performedBy)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object toIban string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

///// TESTING

    //constructor for test transactions
    public Transaction(String fromIban, String toIban, Double amount, String type, int performedBy) {
        this.fromIban = fromIban;
        this.toIban = toIban;
        this.amount = amount;
        this.type = TransactionType.valueOf(type);
        this.performedBy = performedBy;
    }

    //constructor for test deposits/withdrawals
    public Transaction(String Iban, Double amount, String type, int performedBy) {
        this.amount = amount;
        this.type = TransactionType.valueOf(type);
        this.performedBy = performedBy;

        if (this.type.equals(TransactionType.DEPOSIT)) {
            this.toIban = Iban;
        } else {
            this.fromIban = Iban;
        }
    }
}
