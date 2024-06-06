package org.softaria.ams.platform;

import java.util.Objects;

public class Validator<T> {

    private Validator() {
    }

    public static <T> void requireNonNull(T validated, String nameOfBeingChecked) {
        Objects.requireNonNull(validated, nameOfBeingChecked + " must be not null");
    }
}
