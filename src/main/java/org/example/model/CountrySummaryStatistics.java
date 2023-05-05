package org.example.model;

import org.example.domain.Country;

import java.util.Comparator;
import java.util.function.Consumer;

public class CountrySummaryStatistics implements Consumer<Country> {

    private Comparator<Country> comparator;
    private Country min;
    private Country max;
    private long count;

    public CountrySummaryStatistics() {
    }

    public CountrySummaryStatistics(Comparator<Country> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void accept(Country country) {
        count++;
        min = min != null && comparator.compare(min, country) <= 0 ? min : country;
        max = max != null && comparator.compare(max, country) >= 0 ? max : country;
    }

    public void combine(CountrySummaryStatistics other) {
        this.min = comparator.compare(this.min, other.min) <= 0 ? this.min : other.min;
        this.max = comparator.compare(this.max, other.max) >= 0 ? this.max : other.max;
    }

    public Country getMin() {
        return min;
    }

    public void setMin(Country min) {
        this.min = min;
    }

    public Country getMax() {
        return max;
    }

    public void setMax(Country max) {
        this.max = max;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountrySummaryStatistics{" +
                "min=" + getMin() +
                ", max=" + getMax() +
                ", count=" + getCount() +
                '}';
    }
}
