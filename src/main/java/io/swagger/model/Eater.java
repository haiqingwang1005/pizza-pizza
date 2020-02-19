package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Eater {

  @JsonProperty("numberOfAdult")
  private Integer numberOfAdult;
  @JsonProperty("numberOfChild")
  private Integer numberOfChild;

  public Eater( Integer numberOfAdult, Integer numberOfChild) {
    this.numberOfAdult = numberOfAdult;
    this.numberOfChild = numberOfChild;
  }

  public Eater() {

  }

  public Eater numberOfAdult(Integer numberOfAdult) {
    this.numberOfAdult = numberOfAdult;
    return this;
  }

  @ApiModelProperty(value = "")
  public int getNumberOfAdult() {
    return numberOfAdult;
  }

  public void setNumberOfAdult(int numberOfAdult) {
    this.numberOfAdult = numberOfAdult;
  }

  public Eater numberOfChild(Integer numberOfChild) {
    this.numberOfAdult = numberOfChild;
    return this;
  }

  @ApiModelProperty(value = "")
  public int getNumberOfChild() {
    return numberOfChild;
  }

  public void setNumberOfChild(int numberOfChild) {
    this.numberOfChild = numberOfChild;
  }
}
