
/**
 * TripCost.java
 * Author: Rodney Dennise
 * Date: July 21, 2026
 *
 * Immutable class representing the cost breakdown of a road trip.
 * All values passed into the constructor are expected to already be
 * expressed in Imperial units (miles, dollars per gallon, miles per
 * gallon). Unit conversion from Metric input is handled by the caller
 * (Project3) before values reach this class -- TripCost itself has no
 * knowledge of units, it just computes.
 */
public final class TripCost {

    // Core trip inputs, all Imperial after conversion by the caller
    private final double distanceMiles;
    private final double gasMileageMpg;
    private final double gasolineCostPerGallon;
    private final int numberOfDays;
    private final double hotelCostPerDay;
    private final double foodCostPerDay;
    private final double attractionsCost;

    // Pre-computed at construction time since the class is immutable
    private final double totalTripCost;

    /**
     * Constructs an immutable TripCost and computes the total cost.
     *
     * @param distanceMiles           total distance of the trip, in miles
     * @param gasMileageMpg           vehicle fuel economy, in miles per gallon
     * @param gasolineCostPerGallon   price of gasoline, in dollars per gallon
     * @param numberOfDays            length of the trip, in days
     * @param hotelCostPerDay         hotel cost per day, in dollars
     * @param foodCostPerDay          food cost per day, in dollars
     * @param attractionsCost         flat cost of attractions/activities, in dollars
     */
    public TripCost(double distanceMiles, double gasMileageMpg, double gasolineCostPerGallon,
                     int numberOfDays, double hotelCostPerDay, double foodCostPerDay,
                     double attractionsCost) {
        this.distanceMiles = distanceMiles;
        this.gasMileageMpg = gasMileageMpg;
        this.gasolineCostPerGallon = gasolineCostPerGallon;
        this.numberOfDays = numberOfDays;
        this.hotelCostPerDay = hotelCostPerDay;
        this.foodCostPerDay = foodCostPerDay;
        this.attractionsCost = attractionsCost;
        this.totalTripCost = computeTotalTripCost();
    }

    /**
     * Computes the total trip cost using the two required formulas:
     *   gasolineCost = (distance / gasMileage) * gasolineCostPerGallon
     *   totalTripCost = gasolineCost + (hotelCost + foodCost) * days + attractions
     *
     * @return the total trip cost in dollars
     */
    private double computeTotalTripCost() {
        double gasolineCost = (distanceMiles / gasMileageMpg) * gasolineCostPerGallon;
        return gasolineCost + (hotelCostPerDay + foodCostPerDay) * numberOfDays + attractionsCost;
    }

    /** @return the pre-computed total trip cost, in dollars */
    public double getTotalTripCost() {
        return totalTripCost;
    }

    // Standard accessors -- included so the immutable object can still
    // report the values it was built from, e.g. for testing or display.

    public double getDistanceMiles() {
        return distanceMiles;
    }

    public double getGasMileageMpg() {
        return gasMileageMpg;
    }

    public double getGasolineCostPerGallon() {
        return gasolineCostPerGallon;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public double getHotelCostPerDay() {
        return hotelCostPerDay;
    }

    public double getFoodCostPerDay() {
        return foodCostPerDay;
    }

    public double getAttractionsCost() {
        return attractionsCost;
    }
}
