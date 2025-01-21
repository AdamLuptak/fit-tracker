package io.rebase.fitnessapp.activity.domain.model;

import java.util.Set;

public class SupportedActivityType {
  public static String RUNNING = "RUNNING";
  public static String SWIMING = "SWIMING";
  public static String WALKING = "WALKING";
  public static Set<String> SUPPORTED_ACTIVITY_TYPES = Set.of(RUNNING, SWIMING, WALKING);
}
