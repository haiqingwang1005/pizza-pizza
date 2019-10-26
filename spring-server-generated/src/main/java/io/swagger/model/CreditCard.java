package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreditCard
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-25T17:23:10.744Z[GMT]")
public class CreditCard   {
  @JsonProperty("creditCardType")
  private String creditCardType = null;

  @JsonProperty("nameOnCard")
  private String nameOnCard = null;

  @JsonProperty("cardNumber")
  private String cardNumber = null;

  @JsonProperty("expireYear")
  private Integer expireYear = null;

  @JsonProperty("expireMonth")
  private Integer expireMonth = null;

  public CreditCard creditCardType(String creditCardType) {
    this.creditCardType = creditCardType;
    return this;
  }

  /**
   * Get creditCardType
   * @return creditCardType
  **/
  @ApiModelProperty(value = "")

  public String getCreditCardType() {
    return creditCardType;
  }

  public void setCreditCardType(String creditCardType) {
    this.creditCardType = creditCardType;
  }

  public CreditCard nameOnCard(String nameOnCard) {
    this.nameOnCard = nameOnCard;
    return this;
  }

  /**
   * Get nameOnCard
   * @return nameOnCard
  **/
  @ApiModelProperty(value = "")

  public String getNameOnCard() {
    return nameOnCard;
  }

  public void setNameOnCard(String nameOnCard) {
    this.nameOnCard = nameOnCard;
  }

  public CreditCard cardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

  /**
   * Get cardNumber
   * @return cardNumber
  **/
  @ApiModelProperty(value = "")

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public CreditCard expireYear(Integer expireYear) {
    this.expireYear = expireYear;
    return this;
  }

  /**
   * Get expireYear
   * @return expireYear
  **/
  @ApiModelProperty(value = "")

  public Integer getExpireYear() {
    return expireYear;
  }

  public void setExpireYear(Integer expireYear) {
    this.expireYear = expireYear;
  }

  public CreditCard expireMonth(Integer expireMonth) {
    this.expireMonth = expireMonth;
    return this;
  }

  /**
   * Get expireMonth
   * @return expireMonth
  **/
  @ApiModelProperty(value = "")

  public Integer getExpireMonth() {
    return expireMonth;
  }

  public void setExpireMonth(Integer expireMonth) {
    this.expireMonth = expireMonth;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreditCard creditCard = (CreditCard) o;
    return Objects.equals(this.creditCardType, creditCard.creditCardType) &&
        Objects.equals(this.nameOnCard, creditCard.nameOnCard) &&
        Objects.equals(this.cardNumber, creditCard.cardNumber) &&
        Objects.equals(this.expireYear, creditCard.expireYear) &&
        Objects.equals(this.expireMonth, creditCard.expireMonth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditCardType, nameOnCard, cardNumber, expireYear, expireMonth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreditCard {\n");
    
    sb.append("    creditCardType: ").append(toIndentedString(creditCardType)).append("\n");
    sb.append("    nameOnCard: ").append(toIndentedString(nameOnCard)).append("\n");
    sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n");
    sb.append("    expireYear: ").append(toIndentedString(expireYear)).append("\n");
    sb.append("    expireMonth: ").append(toIndentedString(expireMonth)).append("\n");
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
