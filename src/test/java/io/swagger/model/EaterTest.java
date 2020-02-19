package io.swagger.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class EaterTest {

  private Eater eater;
  @Before
  public void setUp() throws Exception {
    eater = new Eater(4, 2);
  }

  @Test
  public void numberOfAdult() {
  }

  @Test
  public void getNumberOfAdult() {
    assertEquals(4, eater.getNumberOfAdult());
  }

  @Test
  public void setNumberOfAdult() {
    eater.setNumberOfAdult(5);
    assertEquals(5, eater.getNumberOfAdult());

  }

  @Test
  public void numberOfChild() {
  }

  @Test
  public void getNumberOfChild() {
    assertEquals(2,eater.getNumberOfChild() );
  }

  @Test
  public void setNumberOfChild() {
    eater.setNumberOfChild(3);
    assertEquals(3,eater.getNumberOfChild());
  }
}
