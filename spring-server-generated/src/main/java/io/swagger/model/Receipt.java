package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.Order;
import io.swagger.repository.ReceiptRepository;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * Receipt
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-12T01:52:56.484Z[GMT]")
public class Receipt   {
  static public void initialize(ReceiptRepository receiptRepository) {

  }
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("date")
  private LocalDate date = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("order")
  private Order order = null;

  @JsonProperty("totalAmount")
  private Double totalAmount = null;

  @JsonProperty("discount")
  private Double discount = null;

  @JsonProperty("netAmount")
  private Double netAmount = null;

  @JsonProperty("totalReceived")
  private Double totalReceived = null;

  @JsonProperty("change")
  private Double change = null;

  @JsonProperty("paymentType")
  private PaymentType paymentType = null;

  @JsonProperty("paymentLast4Digit")
  private String paymentLast4Digit = null;



  public Receipt id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Receipt date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")
  
    public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Receipt address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")
  
    public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Receipt order(Order order) {
    this.order = order;
    return this;
  }

  /**
   * Get order
   * @return order
  **/
  @ApiModelProperty(value = "")
  
    public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Receipt totalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

  /**
   * Get totalAmount
   * @return totalAmount
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Receipt discount(Double discount) {
    this.discount = discount;
    return this;
  }

  /**
   * Get discount
   * @return discount
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Receipt netAmount(Double netAmount) {
    this.netAmount = netAmount;
    return this;
  }

  /**
   * Get netAmount
   * @return netAmount
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Double getNetAmount() {
    return netAmount;
  }

  public void setNetAmount(Double netAmount) {
    this.netAmount = netAmount;
  }

  public Receipt totalReceived(Double totalReceived) {
    this.totalReceived = totalReceived;
    return this;
  }

  /**
   * Get totalReceived
   * @return totalReceived
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Double getTotalReceived() {
    return totalReceived;
  }

  public void setTotalReceived(Double totalReceived) {
    this.totalReceived = totalReceived;
  }

  public Receipt change(Double change) {
    this.change = change;
    return this;
  }

  /**
   * Get change
   * @return change
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Double getChange() {
    return change;
  }

  public void setChange(Double change) {
    this.change = change;
  }

  public Receipt paymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
    return this;
  }

  /**
   * Get paymentType
   * @return paymentType
  **/
  @ApiModelProperty(value = "")
  
    public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public Receipt paymentLast4Digit(String paymentLast4Digit) {
    this.paymentLast4Digit = paymentLast4Digit;
    return this;
  }

  /**
   * Get paymentLast4Digit
   * @return paymentLast4Digit
  **/
  @ApiModelProperty(value = "")
  
    public String getPaymentLast4Digit() {
    return paymentLast4Digit;
  }

  public void setPaymentLast4Digit(String paymentLast4Digit) {
    this.paymentLast4Digit = paymentLast4Digit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Receipt receipt = (Receipt) o;
    return Objects.equals(this.id, receipt.id) &&
        Objects.equals(this.date, receipt.date) &&
        Objects.equals(this.address, receipt.address) &&
        Objects.equals(this.order, receipt.order) &&
        Objects.equals(this.totalAmount, receipt.totalAmount) &&
        Objects.equals(this.discount, receipt.discount) &&
        Objects.equals(this.netAmount, receipt.netAmount) &&
        Objects.equals(this.totalReceived, receipt.totalReceived) &&
        Objects.equals(this.change, receipt.change) &&
        Objects.equals(this.paymentType, receipt.paymentType) &&
        Objects.equals(this.paymentLast4Digit, receipt.paymentLast4Digit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, address, order, totalAmount, discount, netAmount, totalReceived, change, paymentType, paymentLast4Digit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Receipt {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    totalAmount: ").append(toIndentedString(totalAmount)).append("\n");
    sb.append("    discount: ").append(toIndentedString(discount)).append("\n");
    sb.append("    netAmount: ").append(toIndentedString(netAmount)).append("\n");
    sb.append("    totalReceived: ").append(toIndentedString(totalReceived)).append("\n");
    sb.append("    change: ").append(toIndentedString(change)).append("\n");
    sb.append("    paymentType: ").append(toIndentedString(paymentType)).append("\n");
    sb.append("    paymentLast4Digit: ").append(toIndentedString(paymentLast4Digit)).append("\n");
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
