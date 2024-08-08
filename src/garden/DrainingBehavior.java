package garden;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Random;

/**
 * The DrainingBehavior classes extends the FlowerBehavior class. When the bee interacts
 * with the flower, the energy level is adjusted. In this class, the energy level
 * will subtract from a value picked from the method's choosing. The type of picture the
 * flower displays is also included in this class.
 */
public class DrainingBehavior extends FlowerBehavior{
    private Random random = new Random();
    private ImageView flowerPicHolder;

    public DrainingBehavior(VBox flower){
        flowerPicHolder = new ImageView(new Image("garden_pics/daisy.png"));
        flowerPicHolder.setFitWidth(40);
        flowerPicHolder.setFitHeight(40);
        flower.getChildren().add(flowerPicHolder);
    }

    //everytime the changeEnergy method i called the drain behavior needs to change
    @Override
    public void changeEnergy(Bee bee) {
        bee.energyLevel -= random.nextDouble(1,4);
        bee.getLabel().setText(""+bee.energyLevel);
    }
}
