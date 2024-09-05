import java.time.LocalDateTime;

/**
 * Event representing the completion of a ride.
 * @author Qiyuan Zhu
 */
class RideFinishedEvent extends Event {
  private final RideRequest ride;

  /**
   * Constructs a RideFinishedEvent.
   *
   * @param eventTime the time the event occurs
   * @param ride      the ride that has finished
   */
  public RideFinishedEvent(LocalDateTime eventTime, RideRequest ride) {
    super(eventTime);
    this.ride = ride;
  }

  @Override
  public void processEvent(RideshareDispatchSimulator simulator) {
    simulator.finishRide(ride);
  }
}
