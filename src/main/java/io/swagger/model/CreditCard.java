package io.swagger.model;

import io.swagger.repository.CreditCardsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CreditCard
 */
@Validated
public class CreditCard   {
  static public void initialize(CreditCardsRepository creditCardsRepository) {
    if (creditCardsRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default credit cards!");

    List<CreditCard> defaults = new ArrayList<>();
    defaults.add(new CreditCard().creditCardType("American Express").cardNumber("378282246310005"));
    defaults.add(new CreditCard().creditCardType("American Express").cardNumber("371449635398431"));
    defaults.add(new CreditCard().creditCardType("American Express Corporate").cardNumber("378734493671000"));
    defaults.add(new CreditCard().creditCardType("Australian BankCard").cardNumber("5610591081018250"));
    defaults.add(new CreditCard().creditCardType("Diners Club").cardNumber("30569309025904"));
    defaults.add(new CreditCard().creditCardType("Diners Club").cardNumber("38520000023237"));
    defaults.add(new CreditCard().creditCardType("Discover").cardNumber("6011111111111117"));
    defaults.add(new CreditCard().creditCardType("Discover").cardNumber("6011000990139424"));
    defaults.add(new CreditCard().creditCardType("JCB").cardNumber("3530111333300000"));
    defaults.add(new CreditCard().creditCardType("JCB").cardNumber("3566002020360505"));
    defaults.add(new CreditCard().creditCardType("MasterCard").cardNumber("5555555555554444"));
    defaults.add(new CreditCard().creditCardType("MasterCard").cardNumber("5105105105105100"));
    defaults.add(new CreditCard().creditCardType("Visa").cardNumber("4111111111111111"));
    defaults.add(new CreditCard().creditCardType("Visa").cardNumber("4012888888881881"));
    defaults.add(new CreditCard().creditCardType("Visa").cardNumber("4222222222222"));
    defaults.add(new CreditCard().creditCardType("Dankort(PBS)").cardNumber("76009244561"));
    defaults.add(new CreditCard().creditCardType("Dankort(PBS)").cardNumber("5019717010103742"));
    defaults.add(new CreditCard().creditCardType("Switch/Solo(Paymentech)").cardNumber("6331101999990016"));

    creditCardsRepository.insert(defaults);
  }

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

  public CreditCard() {

  }

  public CreditCard(String creditCardType, String nameOnCard, String cardNumber,
      Integer expireYear, Integer expireMonth) {
    this.creditCardType = creditCardType;
    this.nameOnCard = nameOnCard;
    this.cardNumber = cardNumber;
    this.expireYear = expireYear;
    this.expireMonth = expireMonth;
  }

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
