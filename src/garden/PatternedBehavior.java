package garden;

import javafx.geometry.Point2D;

/**
 * The PatternedBehavior is a bee behavior in which there is a method
 * to which the bee moves around the screen. Also, has some methods to set
 * the target flower (which is this case is more for seeing which flower is the closest to
 * the bee)
 */
public class PatternedBehavior extends BeeBehavior {
    public Flower targetFlower;
    private int direction;

    public PatternedBehavior() {
        direction = 0;
    }
    @Override
    public Point2D fly(Point2D position, FlowerBed bed, int threshold, int... gardenDimensions) {
        if (targetFlower != null) {
            return targetFlower.getPoint2D();
        }

        for (Flower flower : bed.getFlowers()) {
            double distanceFromBeeToFlower = position.distance(flower.getPoint2D());
            if (distanceFromBeeToFlower <= BEE_MOVE_DISTANCE && distanceFromBeeToFlower > threshold) {
                targetFlower = flower;
                return targetFlower.getPoint2D();
            }
        }

        Point2D point2D = null;
        if (targetFlower == null) {
            int moveDistance = BEE_MOVE_DISTANCE*5;

            switch (direction) {
                // Up
                case 0 -> {
                    direction += 1;
                    point2D = new Point2D(position.getX(), position.getY() - moveDistance);
                }
                // Right
                case 1 -> {
                    direction += 1;
                    point2D = new Point2D(position.getX() + moveDistance, position.getY());
                }
                // Down
                case 2 -> {
                    direction += 1;
                    point2D = new Point2D(position.getX(), position.getY() + moveDistance);
                }
                // Left
                case 3 -> {
                    direction = 0;
                    point2D = new Point2D(position.getX() - moveDistance, position.getY());
                }
            }
        }
        return point2D;
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
