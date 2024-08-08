package garden;

import javafx.geometry.Point2D;
/**
 * The BeeBehavior class acts as a interface for the general BeeBehavior. Each BeeBehavior
 * contains the elements of the constant distance the bee moves (everytime it moves).
 * Some of the other methods are relating to setting a target flower (which would be most
 * common within the RandomBehavior).
 */
public abstract class BeeBehavior {
    public static final int BEE_MOVE_DISTANCE = 10;
    abstract Point2D fly(Point2D point2D, FlowerBed bed, int threshold, int... gardenDimensions);
    abstract Flower getTargetFlower();
    abstract void setNullTarget();
}
