package garden;
import javafx.geometry.Point2D;
import javafx.scene.layout.VBox;
/**
 * The Flower class focuses on changing the energy level of the bee using the flower behavior.
 * The type of behavior depends on what flowers were picked and produced.
 */
public class Flower {
    private final Point2D point2D;
    private final FlowerBehavior flowerBehavior;
    //the String parameter will be used in FlowerBed to randomize
    public Flower(int randBehavior, VBox flower, Point2D point2D){//default can be the giving
        this.point2D = point2D;
        flowerBehavior = randBehavior == 0
                ? new GivingBehavior(flower) : new DrainingBehavior(flower);
    }
    public void changeEnergy(Bee bee){
        flowerBehavior.changeEnergy(bee);
    }

    public Point2D getPoint2D() {
        return point2D;
    }
}
