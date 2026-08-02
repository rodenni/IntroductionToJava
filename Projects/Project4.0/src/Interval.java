/**
 * Interval.java
 * CMSC 215 - Programming Project 4
 * Author: Rodney Dennise
 * Date: August 2, 2026
 *
 * A generic, immutable class representing an interval defined by a start
 * and end value of any type that implements Comparable. Supports checking
 * whether a point lies within the interval, whether another interval is
 * completely contained within this one (subinterval), and whether another
 * interval overlaps this one.
 */
public class Interval<T extends Comparable<T>> {

    // The start and end of the interval; final because the class is immutable
    private final T start;
    private final T end;

    /**
     * Constructs an Interval with the given start and end values.
     *
     * @param start the start of the interval
     * @param end   the end of the interval
     */
    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start of the interval.
     *
     * @return the start value
     */
    public T getStart() {
        return start;
    }

    /**
     * Returns the end of the interval.
     *
     * @return the end value
     */
    public T getEnd() {
        return end;
    }

    /**
     * Determines whether the given point falls within this interval,
     * inclusive of the endpoints.
     *
     * @param point the value to check
     * @return true if point is within [start, end], false otherwise
     */
    public boolean within(T point) {
        return point.compareTo(start) >= 0 && point.compareTo(end) <= 0;
    }

    /**
     * Determines whether the given interval is completely contained
     * within this interval (i.e., this interval's bounds fully enclose it).
     *
     * @param other the interval to check
     * @return true if other is a subinterval of this interval
     */
    public boolean subinterval(Interval<T> other) {
        return other.start.compareTo(this.start) >= 0 && other.end.compareTo(this.end) <= 0;
    }

    /**
     * Determines whether the given interval overlaps this interval at all.
     *
     * @param other the interval to check
     * @return true if the intervals share any common point
     */
    public boolean overlaps(Interval<T> other) {
        return this.start.compareTo(other.end) <= 0 && other.start.compareTo(this.end) <= 0;
    }
}