package io.rebase.fitnessapp.activity.domain.model;

public record Distance(Double value, DistanceUnit unit) {
  public static Distance of(Double value) {
    return new Distance(value, DistanceUnit.KILOMETERS);
  }

  public enum DistanceUnit {
    MILES,
    KILOMETERS;
  }

  public double toKilometers() {
    return switch (unit) {
      case MILES -> value * 1.60934;
      case KILOMETERS -> value;
    };
  }

  public double toMiles() {
    return switch (unit) {
      case MILES -> value;
      case KILOMETERS -> value / 1.60934;
    };
  }
}
