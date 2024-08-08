package garden;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Random;
/**
 * This GivingBehavior class adds energy levels to whatever bee interacted with it.
 * Also, it houses the type of flower picture associated with a "Giving Flower"
 */
public class GivingBehavior extends FlowerBehavior{
    private Random random = new Random();
    //adds random of 1-4
    //the starts to add starting from 1 and incrementing to 5
    private static int givingFlowerStatus = 1;
    private ImageView flowerPicHolder;

    public GivingBehavior(VBox flower) {
        flowerPicHolder = new ImageView(new Image("garden_pics/nightshade.png"));
        flowerPicHolder.setFitWidth(40);
        flowerPicHolder.setFitHeight(40);
        flower.getChildren().add(flowerPicHolder);
    }

    @Override
    public void changeEnergy(Bee bee) {
        switch (givingFlowerStatus) {
            case 1 -> {
                bee.energyLevel += random.nextDouble(1, 2);
                givingFlowerStatus += 1;
            }
            case 2 -> {
                bee.energyLevel += random.nextDouble(2, 3);
                givingFlowerStatus += 1;
            }
            case 3 -> {
                bee.energyLevel += random.nextDouble(3, 4);
                givingFlowerStatus = 1;
            }
        }
        bee.getLabel().setText(""+bee.energyLevel);
    }
}
