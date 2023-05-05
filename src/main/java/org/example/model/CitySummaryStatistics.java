package org.example.model;

import org.example.domain.City;

import java.util.Comparator;
import java.util.function.Consumer;

public class CitySummaryStatistics implements Consumer<City> {

      private Comparator<City> comparator;

      private City min;

      private City max;

      private long count;

      public CitySummaryStatistics() {
      }

      public CitySummaryStatistics(Comparator<City> comparator) {
            this.comparator = comparator;
      }

      @Override
      public void accept(City city) {
            count++;
            min = min != null && comparator.compare(min, city)<= 0 ? min : city;
            max = max != null && comparator.compare(max, city)>= 0 ? max : city;
      }

      public void combine(CitySummaryStatistics statistic) {
            this.min = comparator.compare(this.min, statistic.min) <= 0 ? this.min : statistic.min;
            this.max = comparator.compare(this.max, statistic.max) >= 0 ? this.max : statistic.max;
      }

      public City getMin() {
            return min;
      }

      public void setMin(City min) {
            this.min = min;
      }

      public City getMax() {
            return max;
      }

      public void setMax(City max) {
            this.max = max;
      }

      public Long getCount() {
            return count;
      }

      public void setCount(Long count) {
            this.count = count;
      }

      @Override
      public String toString() {
            return "CitySummaryStatistic{" +
                        "min=" + min +
                        ", max=" + max +
                        ", count=" + count +
                        '}';
      }
}
