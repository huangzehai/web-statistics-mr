package util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class RandomDate {
    private final int minDay;
    private final int maxDay;
    private final Random random;

    public RandomDate(LocalDate minDate, LocalDate maxDate) {
        minDay = (int) minDate.toEpochDay();
        maxDay = (int) maxDate.toEpochDay();
        this.random = new Random();
    }

    public Date nextDate() {
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate localDate = LocalDate.ofEpochDay(randomDay);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "RandomDate{" +
                "minDay=" + minDay +
                ", maxDay=" + maxDay +
                ", random=" + random +
                '}';
    }
}
