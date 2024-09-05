import java.time.LocalDateTime;

/**
 * Abstract class representing an event in the ride-share simulation.
 * @author Qiyuan Zhu
 */
abstract class Event {
  protected final LocalDateTime eventTime;

  /**
   * Constructs an Event with the specified event time.
   *
   * @param eventTime the time at which the event occurs
   */
  public Event(LocalDateTime eventTime) {
    this.eventTime = eventTime;
  }

  /**
   * Processes the event within the specified simulator context.
   *
   * @param simulator the simulator context in which to process the event
   */
  public abstract void processEvent(RideshareDispatchSimulator simulator);
}
