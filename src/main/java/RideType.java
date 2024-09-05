/**
 * Enum representing different types of rides and their priorities.
 * @author Qiyuan Zhu
 */
enum RideType {
  EXPRESS(1),
  STANDARD(2),
  WAIT_AND_SAVE(3),
  ENVIRONMENTALLY_CONSCIOUS(4);

  private final int priority;

  RideType(int priority) {
    this.priority = priority;
  }

  /**
   * Returns the priority of the ride type.
   *
   * @return the priority
   */
  public int getPriority() {
    return priority;
  }
}
