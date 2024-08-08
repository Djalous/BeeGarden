package garden;

import javafx.geometry.Point2D;

import java.util.Random;

/**
 * The RandomizedBehavior class describes the bee that randomly selects a flower
 * and moves to it while not picking the same flower when it relocates.
 */
public class RandomizedBehavior extends BeeBehavior {
    private Flower targetFlower;

    @Override
    public Point2D fly(Point2D point2D, FlowerBed bed, int threshold, int... gardenDimensions) {
        if (targetFlower == null) {
            Random rand = new Random();
            int targetValue = rand.nextInt(bed.getFlowers().size());
            targetFlower = bed.getFlowers().get(targetValue);
        }

        return targetFlower.getPoint2D();
    }

    @Override
    Flower getTargetFlower() {
        return targetFlower;
    }

    @Override
    void setNullTarget() {
        targetFlower = null;
    }
}
