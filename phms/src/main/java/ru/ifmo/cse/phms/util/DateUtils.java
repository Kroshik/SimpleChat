package ru.ifmo.cse.phms.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class DateUtils {
    public static Date nowMinusDays(long d) {
        Instant now = Instant.now(); //current date
        Instant before = now.minus(Duration.ofDays(d));
        return Date.from(before);
    }

    public static Date nowMinusHours(long h) {
        Instant now = Instant.now(); //current date
        Instant before = now.minus(Duration.ofHours(h));
        return Date.from(before);
    }

    public static Date datePlusHours(Date date, long h) {
        Instant now = Instant.from(date.toInstant()); //current date
        Instant after = now.plus(Duration.ofHours(h));
        return Date.from(after);
    }
}
