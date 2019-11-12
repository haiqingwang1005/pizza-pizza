package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

public class StoreLocationTest {
  private StoreLocation storeLocation;
  private Address address;
  private GeoLocation geoLocation;

  @Before
  public void setUp() {
    address = new Address();
    address.address1("a1").address2("a2").city("city").state("state").zip("123");

    geoLocation = new GeoLocation();
    geoLocation.latitude(BigDecimal.ONE);
    geoLocation.longtitude(BigDecimal.TEN);

    storeLocation = new StoreLocation();
    storeLocation.name("name").address(address).geoLocation(geoLocation);
  }

  @Test
  public void testStoreLocation() {
    StoreLocation storeLocation1 = new StoreLocation();
    storeLocation1.setAddress(address);
    storeLocation1.setName("name");
    storeLocation1.setGeoLocation(geoLocation);

    assertEquals(storeLocation1, storeLocation1);
    assertEquals(storeLocation1, storeLocation);
    assertNotEquals(storeLocation1, null);
    assertNotEquals(storeLocation1, address);

    assertEquals("name", storeLocation1.getName());
    assertEquals(address, storeLocation1.getAddress());
    assertEquals(geoLocation, storeLocation1.getGeoLocation());
  }

  @Test
  public void testAddress() {
    Address address1 = new Address();
    address1.setAddress1("a1");
    address1.setAddress2("a2");
    address1.setCity("city");
    address1.setState("state");
    address1.setZip("123");

    assertEquals(address1, address1);
    assertEquals(address, address1);
    assertNotEquals(address1, null);
    assertNotEquals(address1, geoLocation);

    assertEquals("a1", address1.getAddress1());
    assertEquals("a2", address1.getAddress2());
    assertEquals("city", address1.getCity());
    assertEquals("state", address1.getState());
    assertEquals("123", address1.getZip());
  }

  @Test
  public void testGeoLocation() {
    GeoLocation geoLocation1 = new GeoLocation();
    geoLocation1.setLatitude(BigDecimal.ONE);
    geoLocation1.setLongtitude(BigDecimal.TEN);

    assertEquals(geoLocation1, geoLocation1);
    assertEquals(geoLocation, geoLocation1);
    assertNotEquals(geoLocation1, null);
    assertNotEquals(geoLocation1, new Object());

    assertEquals(BigDecimal.ONE, geoLocation1.getLatitude());
    assertEquals(BigDecimal.TEN, geoLocation1.getLongtitude());
  }

  @Test
  public void testHash() {
    assertNotNull(geoLocation.hashCode());
    assertNotNull(address.hashCode());
    assertNotNull(storeLocation.hashCode());
  }

  @Test
  public void testString() {
    assertEquals("class GeoLocation {\n"
        + "    longtitude: 10\n"
        + "    latitude: 1\n"
        + "}", geoLocation.toString());
    assertEquals("class Address {\n"
        + "    address1: a1\n"
        + "    address2: a2\n"
        + "    city: city\n"
        + "    zip: 123\n"
        + "    state: state\n"
        + "}", address.toString());
    assertEquals("class StoreLocation {\n"
        + "    name: name\n"
        + "    geoLocation: class GeoLocation {\n"
        + "        longtitude: 10\n"
        + "        latitude: 1\n"
        + "    }\n"
        + "    address: class Address {\n"
        + "        address1: a1\n"
        + "        address2: a2\n"
        + "        city: city\n"
        + "        zip: 123\n"
        + "        state: state\n"
        + "    }\n"
        + "}", storeLocation.toString());

  }
}
