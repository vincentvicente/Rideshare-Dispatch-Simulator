import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a ride request from a customer.
 * @author Qiyuan Zhu
 */
class RideRequest {
  private final String customerId;
  private final String startLocation;
  private final String destination;
  private final double distance;
  private final LocalDateTime requestTime;
  private final RideType type;
  private final int priority;

  /**
   * Constructs a new RideRequest.
   *
   * @param customerId   the ID of the customer
   * @param startLocation the starting location of the ride
   * @param destination  the destination of the ride
   * @param distance     the anticipated distance of the ride
   * @param requestTime  the time the ride was requested
   * @param type         the type of the ride
   */
  public RideRequest(String customerId, String startLocation, String destination, double distance, LocalDateTime requestTime, RideType type) {
    this.customerId = customerId;
    this.startLocation = startLocation;
    this.destination = destination;
    this.distance = distance;
    this.requestTime = requestTime;
    this.type = type;
    this.priority = type.getPriority();
  }

  /**
   * Returns the customer ID.
   *
   * @return the customer ID
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * Returns the starting location of the ride.
   *
   * @return the starting location
   */
  public String getStartLocation() {
    return startLocation;
  }

  /**
   * Returns the destination of the ride.
   *
   * @return the destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Returns the anticipated distance of the ride.
   *
   * @return the distance
   */
  public double getDistance() {
    return distance;
  }

  /**
   * Returns the request time of the ride.
   *
   * @return the request time
   */
  public LocalDateTime getRequestTime() {
    return requestTime;
  }

  /**
   * Returns the type of the ride.
   *
   * @return the ride type
   */
  public RideType getType() {
    return type;
  }

  /**
   * Returns the priority of the ride request.
   *
   * @return the priority
   */
  public int getPriority() {
    return priority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RideRequest)) return false;
    RideRequest that = (RideRequest) o;
    return Double.compare(that.distance, distance) == 0 &&
        priority == that.priority &&
        Objects.equals(customerId, that.customerId) &&
        Objects.equals(startLocation, that.startLocation) &&
        Objects.equals(destination, that.destination) &&
        Objects.equals(requestTime, that.requestTime) &&
        type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, startLocation, destination, distance, requestTime, type, priority);
  }

}
