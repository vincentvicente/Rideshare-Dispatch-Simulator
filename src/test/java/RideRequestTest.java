import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RideRequestTest {

  @Test
  void testRideRequestCreation() {
    LocalDateTime requestTime = LocalDateTime.now();
    RideRequest request = new RideRequest("Customer1", "LocationA", "LocationB", 10.0, requestTime, RideType.EXPRESS);

    assertEquals("Customer1", request.getCustomerId());
    assertEquals("LocationA", request.getStartLocation());
    assertEquals("LocationB", request.getDestination());
    assertEquals(10.0, request.getDistance(), 0.001);
    assertEquals(requestTime, request.getRequestTime());
    assertEquals(RideType.EXPRESS, request.getType());
    assertEquals(1, request.getPriority());
  }

  @Test
  void testEqualsAndHashCode() {
    LocalDateTime requestTime = LocalDateTime.now();
    RideRequest request1 = new RideRequest("Customer1", "LocationA", "LocationB", 10.0, requestTime, RideType.EXPRESS);
    RideRequest request2 = new RideRequest("Customer1", "LocationA", "LocationB", 10.0, requestTime, RideType.EXPRESS);
    RideRequest request3 = new RideRequest("Customer2", "LocationC", "LocationD", 15.0, requestTime.plusMinutes(1), RideType.STANDARD);

    assertEquals(request1, request2);
    assertNotEquals(request1, request3);
    assertEquals(request1.hashCode(), request2.hashCode());
  }
}
