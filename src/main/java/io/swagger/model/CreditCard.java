package io.swagger.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.CreditCardsRepository;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * CreditCard
 */
@Data
@JsonDeserialize(builder = CreditCard.CreditCardBuilder.class)
@Builder(builderClassName = "CreditCardBuilder", toBuilder = true)
public class CreditCard   {
  static public void initialize(CreditCardsRepository creditCardsRepository) {
    if (creditCardsRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default credit cards!");

    List<CreditCard> defaults = new ArrayList<>();
    defaults.add(CreditCard.builder().creditCardType("American Express").cardNumber("378282246310005").build());
    defaults.add(CreditCard.builder().creditCardType("American Express").cardNumber("371449635398431").build());
    defaults.add(CreditCard.builder().creditCardType("American Express Corporate").cardNumber("378734493671000").build());
    defaults.add(CreditCard.builder().creditCardType("Australian BankCard").cardNumber("5610591081018250").build());
    defaults.add(CreditCard.builder().creditCardType("Diners Club").cardNumber("30569309025904").build());
    defaults.add(CreditCard.builder().creditCardType("Diners Club").cardNumber("38520000023237").build());
    defaults.add(CreditCard.builder().creditCardType("Discover").cardNumber("6011111111111117").build());
    defaults.add(CreditCard.builder().creditCardType("Discover").cardNumber("6011000990139424").build());
    defaults.add(CreditCard.builder().creditCardType("JCB").cardNumber("3530111333300000").build());
    defaults.add(CreditCard.builder().creditCardType("JCB").cardNumber("3566002020360505").build());
    defaults.add(CreditCard.builder().creditCardType("MasterCard").cardNumber("5555555555554444").build());
    defaults.add(CreditCard.builder().creditCardType("MasterCard").cardNumber("5105105105105100").build());
    defaults.add(CreditCard.builder().creditCardType("Visa").cardNumber("4111111111111111").build());
    defaults.add(CreditCard.builder().creditCardType("Visa").cardNumber("4012888888881881").build());
    defaults.add(CreditCard.builder().creditCardType("Visa").cardNumber("4222222222222").build());
    defaults.add(CreditCard.builder().creditCardType("Dankort(PBS)").cardNumber("76009244561").build());
    defaults.add(CreditCard.builder().creditCardType("Dankort(PBS)").cardNumber("5019717010103742").build());
    defaults.add(CreditCard.builder().creditCardType("Switch/Solo(Paymentech)").cardNumber("6331101999990016").build());

    creditCardsRepository.insert(defaults);
  }

  @JsonProperty("creditCardType")
  private String creditCardType;

  @JsonProperty("nameOnCard")
  private String nameOnCard;

  @JsonProperty("cardNumber")
  private String cardNumber;

  @JsonProperty("expireYear")
  private Integer expireYear;

  @JsonProperty("expireMonth")
  private Integer expireMonth;


  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  public String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class CreditCardBuilder {
  }
}
