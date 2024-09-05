/**
 * Represents a driver in the rideshare system.
 * @author Qiyuan Zhu
 */
class Driver {
  private boolean available;
  private int completedRides;

  /**
   * Constructs a new Driver.
   */
  public Driver() {
    this.available = true;
    this.completedRides = 0;
  }

  /**
   * Checks if the driver is available.
   *
   * @return true if the driver is available, false otherwise
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * Marks the driver as taking a ride and increments the completed rides count.
   */
  public void takeRide() {
    available = false;
    completedRides++;
  }

  /**
   * Marks the driver as available after completing a ride.
   */
  public void finishRide() {
    available = true;
  }

  /**
   * Returns the number of completed rides by the driver.
   *
   * @return the number of completed rides
   */
  public int getCompletedRides() {
    return completedRides;
  }
}
