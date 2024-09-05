import java.time.LocalDateTime;

/**
 * Event representing a new ride request.
 * @author Qiyuan Zhu
 */
class RideRequestedEvent extends Event {
  private final RideRequest request;

  /**
   * Constructs a RideRequestedEvent.
   *
   * @param eventTime the time the event occurs
   * @param request   the ride request
   */
  public RideRequestedEvent(LocalDateTime eventTime, RideRequest request) {
    super(eventTime);
    this.request = request;
  }

  @Override
  public void processEvent(RideshareDispatchSimulator simulator) {
    simulator.addRideRequest(request);
  }
}
