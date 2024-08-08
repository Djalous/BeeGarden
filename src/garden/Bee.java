package garden;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Random;
/**
 * The Bee represents a bee object. This class facilitates the instantiation of the
 * bee picture (type of Random Behavior or Patterned Behavior). The classes main funtion
 * is represented the bee and hold the fly method that moves the bee object across the GUI.
 */
public class Bee {
    private Point2D position;
    public double energyLevel;
    private BeeBehavior beeBehavior;
    private final int gardenWidth;
    private final int gardenHeight;
    private final VBox bee;
    private static final int THRESHOLD = 8;
    private Text label = new Text(""+energyLevel);

    private HBox labelHolder = new HBox();


    public Bee(Point2D position, VBox bee, int gardenWidth, int gardenHeight){
        this.position = position;
        this.gardenWidth = gardenWidth;
        this.gardenHeight = gardenHeight;
        Random random = new Random();
        int num = random.nextInt(2);
        this.bee = bee;

        ImageView beePic = null;
        switch (num) {
            case 0 -> {
                beeBehavior = new PatternedBehavior();
                beePic = new ImageView(new Image("garden_pics/pattern_bee.png"));
            }
            case 1 -> {
                beeBehavior = new RandomizedBehavior();
                beePic = new ImageView(new Image("garden_pics/random_bee.png"));
            }
        }

        labelHolder.getChildren().add(label);
        labelHolder.setPrefHeight(10);
        labelHolder.setPrefWidth(40);
        beePic.setFitWidth(40);
        beePic.setFitHeight(40);
        bee.getChildren().addAll(beePic, label);
    }

    public void fly(FlowerBed bed) {
        double stepSize = BeeBehavior.BEE_MOVE_DISTANCE / Math.sqrt(2);

        Point2D newPosition = beeBehavior.fly(position, bed, THRESHOLD, gardenWidth, gardenHeight);
        double xDistance = newPosition.getX() - position.getX();
        double yDistance = newPosition.getY() - position.getY();

        double newX;
        double newY;
        if (Math.abs(xDistance) <= stepSize) {
            newX = newPosition.getX();
        } else if (xDistance < 0) {
            newX = position.getX() - stepSize;
        } else {
            newX = position.getX() + stepSize;
        }

        if (Math.abs(yDistance) <= stepSize) {
            newY = newPosition.getY();
        } else if (yDistance < 0) {
            newY = newPosition.getY() - (stepSize/1.5);
        } else {
            newY = position.getY() + (stepSize/1.5);
        }

        double distanceFromBeeToFlower = position.distance(newPosition);
        position = new Point2D(newX, newY);
        if (position.getX() >= gardenWidth || position.getX() <= 0) {
            position = new Point2D(gardenWidth/2.0, position.getY());
        } else if (position.getY() >= gardenHeight || position.getY() <= 100) {
            position = new Point2D(position.getX(), gardenHeight-100);
        }

        if (distanceFromBeeToFlower <= THRESHOLD && beeBehavior.getTargetFlower() != null) {
            beeBehavior.getTargetFlower().changeEnergy(this);
            beeBehavior.setNullTarget();
        }
    }

    public Point2D getPosition() {
        return position;
    }
    public VBox getImage(){
        return bee;
    }
    public Text getLabel(){
        return label;
    }
}
