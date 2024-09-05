import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

  @Test
  void testDriverInitialState() {
    Driver driver = new Driver();
    assertTrue(driver.isAvailable());
    assertEquals(0, driver.getCompletedRides());
  }

  @Test
  void testDriverTakeAndFinishRide() {
    Driver driver = new Driver();
    driver.takeRide();
    assertFalse(driver.isAvailable());
    assertEquals(1, driver.getCompletedRides());

    driver.finishRide();
    assertTrue(driver.isAvailable());
  }
}
