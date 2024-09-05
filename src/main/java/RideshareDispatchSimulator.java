import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.temporal.ChronoUnit;

/**
 * Simulator for managing ride-share dispatch operations.
 * Handles ride requests and assigns available drivers based on priority and time.
 * @author Qiyuan Zhu
 */
public class RideshareDispatchSimulator {
  private static final int MIN_DISTANCE = 5; // Minimum distance for a ride in miles
  private static final int MAX_DISTANCE = 25; // Maximum distance for a ride in miles
  private static final double AVERAGE_SPEED = 60.0; // Average speed in miles per hour
  private static final int LOCATION_VARIANTS = 10; // Number of different locations simulated

  private final PriorityQueue<RideRequest> rideQueue;
  private final PriorityQueue<Event> eventQueue;
  private final List<Driver> drivers;
  private double totalWaitTime;
  private int totalRides;

  /**
   * Constructs a RideshareDispatchSimulator with the specified number of drivers.
   *
   * @param numberOfDrivers the number of drivers available in the simulation
   */
  public RideshareDispatchSimulator(int numberOfDrivers) {
    //https://www.geeksforgeeks.org/comparator-interface-java/?ref=outind comparator reference
    this.rideQueue = new PriorityQueue<>(Comparator.comparingInt(RideRequest::getPriority).thenComparing(
        RideRequest::getRequestTime));
    this.eventQueue = new PriorityQueue<>(Comparator.comparing(e -> e.eventTime));
    this.drivers = new ArrayList<>();

    for (int i = 0; i < numberOfDrivers; i++) {
      drivers.add(new Driver());
    }
  }

  /**
   * Runs the simulation for a specified number of rides.
   *
   * @param numberOfRides the number of ride requests to simulate
   */
  public void runSimulation(int numberOfRides) {
    for (int i = 0; i < numberOfRides; i++) {
      RideRequest request = generateRandomRideRequest(i);
      eventQueue.add(new RideRequestedEvent(request.getRequestTime(), request));
    }

    while (!eventQueue.isEmpty()) {
      processNextEvent();
    }

    calculateStats();
  }

  /**
   * Generates a random ride request.
   *
   * @param index the index to differentiate customer IDs
   * @return a new randomly generated RideRequest
   */
  private RideRequest generateRandomRideRequest(int index) {
    String customerId = "Customer" + index;
    String startLocation = "Location" + (index % LOCATION_VARIANTS);
    String destination = "Destination" + (index % LOCATION_VARIANTS);
    // https://www.geeksforgeeks.org/java-math-random-method-examples/ random reference
    double distance = MIN_DISTANCE + Math.random() * (MAX_DISTANCE - MIN_DISTANCE); // Random distance between 5 and 25 miles
    LocalDateTime requestTime = LocalDateTime.now().plusMinutes(index); // Increment request time by index minutes
    RideType type = RideType.values()[(int) (Math.random() * RideType.values().length)];
    return new RideRequest(customerId, startLocation, destination, distance, requestTime, type);
  }

  /**
   * Adds a ride request to the queue.
   *
   * @param request the ride request to be added
   */
  public void addRideRequest(RideRequest request) {
    rideQueue.add(request);
  }

  /**
   * Marks a ride as finished and processes the next ride in the queue.
   *
   * @param ride the ride that has finished
   */
  public void finishRide(RideRequest ride) {
    for (Driver driver : drivers) {
      if (!driver.isAvailable()) {
        driver.finishRide();
        break;
      }
    }

    if (!rideQueue.isEmpty()) {
      RideRequest nextRequest = rideQueue.poll();
      // https://www.geeksforgeeks.org/chronounit-values-method-in-java-with-examples/ ChronoUnit method
      long waitTime = ChronoUnit.MINUTES.between(nextRequest.getRequestTime(), ride.getRequestTime());
      totalWaitTime += waitTime;
      processRideRequest(nextRequest);
    }
  }

  /**
   * Processes a ride request by assigning it to an available driver.
   *
   * @param request the ride request to be processed
   */
  private void processRideRequest(RideRequest request) {
    for (Driver driver : drivers) {
      if (driver.isAvailable()) {
        driver.takeRide();
        LocalDateTime finishTime = request.getRequestTime().plusMinutes((long) (request.getDistance() / AVERAGE_SPEED * 60));
        eventQueue.add(new RideFinishedEvent(finishTime, request));
        totalRides++;
        return;
      }
    }
  }

  /**
   * Processes the next event in the event queue.
   */
  public void processNextEvent() {
    Event event = eventQueue.poll();
    if (event != null) {
      event.processEvent(this);
    }
  }

  /**
   * Calculates and prints the average statistics after the simulation.
   */
  private void calculateStats() {
    double averageWaitTime = totalRides == 0 ? 0 : totalWaitTime / totalRides;
    double averageRidesPerDriver = drivers.isEmpty() ? 0 : (double) totalRides / drivers.size();
    System.out.println("Average wait time: " + averageWaitTime + " minutes");
    System.out.println("Average number of rides per driver: " + averageRidesPerDriver);
  }

  /**
   * Returns the average wait time for a ride.
   *
   * @return the average wait time
   */
  public double getAverageWaitTime() {
    return totalRides == 0 ? 0 : totalWaitTime / totalRides;
  }

  /**
   * Returns the average number of rides a driver has handled.
   *
   * @return the average number of rides per driver
   */
  public double getAverageRidesPerDriver() {
    return drivers.isEmpty() ? 0 : (double) totalRides / drivers.size();
  }

  /**
   * Returns the total number of rides completed.
   *
   * @return the total number of rides
   */
  public int getTotalRides() {
    return totalRides;
  }

  /**
   * Main method to run the simulation.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(20);
    System.out.println("Simulation with 25 rides:");
    simulator.runSimulation(25);

    System.out.println("\nSimulation with 100 rides:");
    simulator.runSimulation(100);

    System.out.println("\nSimulation with 250 rides:");
    simulator.runSimulation(250);
  }
}
