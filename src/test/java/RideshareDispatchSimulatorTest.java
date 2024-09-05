import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RideshareDispatchSimulatorTest {

  @Test
  void testSimulatorWithBasicScenario() {
    RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(2);

    LocalDateTime now = LocalDateTime.now();
    RideRequest request1 = new RideRequest("Customer1", "A", "B", 10, now, RideType.EXPRESS);
    RideRequest request2 = new RideRequest("Customer2", "C", "D", 5, now.plusMinutes(1), RideType.STANDARD);

    simulator.addRideRequest(request1);
    simulator.addRideRequest(request2);

    simulator.finishRide(request1);
    simulator.finishRide(request2);

    assertEquals(0, simulator.getAverageWaitTime());
    assertEquals(1.0, simulator.getAverageRidesPerDriver());
  }

  @Test
  void testNoAvailableDrivers() {
    RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(0);

    LocalDateTime now = LocalDateTime.now();
    RideRequest request = new RideRequest("Customer1", "A", "B", 10, now, RideType.EXPRESS);

    simulator.addRideRequest(request);

    assertEquals(0, simulator.getTotalRides());
  }
}
