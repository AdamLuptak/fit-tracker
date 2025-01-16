package io.codething.sporttracker.shared;

import java.util.UUID;

public class IdGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
