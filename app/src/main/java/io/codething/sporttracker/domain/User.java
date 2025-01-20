package io.codething.sporttracker.domain;


public record User(String id, Gender gender, String name, int age, double weight, double height) {
    public enum Gender {MALE, FEMALE}
}