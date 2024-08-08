package garden;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * The GardenController houses a lot of the main functions to populate the GUI
 * with Bees and Flowers (as well as actual objects).
 */
public class GardenController {

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX
    private final List<Bee> bees = new ArrayList<>();
    private FlowerBed flowerBed;
    private static final int WINDOW_WIDTH = 745;
    private static final int WINDOW_HEIGHT = 745;
    private final Scanner sc = new Scanner(System.in);
    private final Random rand = new Random();

    private final Pane bedPane = new Pane();
    private final List<Flower> flowers = new ArrayList<>();
    private final VBox legend = new VBox();
    private double ratioOfBeesFlower = 0;


    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setPrefWidth(WINDOW_WIDTH);
        theGarden.setPrefHeight(WINDOW_HEIGHT);
        bedPane.setPrefHeight(WINDOW_HEIGHT);
        bedPane.setPrefWidth(WINDOW_WIDTH);
        theGarden.getChildren().add(bedPane);

        addLegendNode("Pattern Bee", "garden_pics/pattern_bee.png");
        addLegendNode("Random Bee", "garden_pics/random_bee.png");
        addLegendNode("Draining Flower", "garden_pics/daisy.png");
        addLegendNode("Giving Flower", "garden_pics/nightshade.png");
        legend.setPrefHeight(100);
        theGarden.getChildren().add(legend);

        //user gets to pick how many flowers and bees they want
        System.out.println("How many flowers do you want in this application?");
        int numFlowers = Integer.parseInt(sc.nextLine());
        System.out.println("How many bees do you want in this application");
        int numBees = Integer.parseInt(sc.nextLine());
        //is randomizing a position for the flower bed instead of the individual flowers
        //need to make the method in the FlowerBed class so that it can regenerate the points
        //right now it is the same
        populateFlowers(numFlowers);
        addInBees(numBees);
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project// scale bee to be a reasonable size
        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
        //setting energy level based on ratio
        ratioOfBeesFlower = (double)bees.size()/flowers.size();
        pickEnergyLevel(ratioOfBeesFlower);
    }

    private void pickEnergyLevel(double ratioOfBeesFlower) {
        if (ratioOfBeesFlower <= .25) { //more flowers
            double startingEnergy = 3*(flowers.size()-1);
            for (Bee bee: bees){
                bee.energyLevel = startingEnergy;
                bee.getLabel().setText(""+startingEnergy);
            }
        } else if(ratioOfBeesFlower >= .25 && ratioOfBeesFlower <= .5){
            double startingEnergy = flowers.size();
            for (Bee bee: bees){
                bee.energyLevel = startingEnergy;
                bee.getLabel().setText(""+startingEnergy);
            }
        } else{ //less flowers
            double startingEnergy = flowers.size()-1;
            for (Bee bee: bees){
                bee.energyLevel = startingEnergy;
                bee.getLabel().setText(""+startingEnergy);
            }
        }
    }

    private void addInBees(int numBees) {
        VBox currentBeeImage;
        for (int j = 0; j < numBees; j++){
            Point2D position = randomizePoint();
            currentBeeImage = new VBox();
            currentBeeImage.setPrefHeight(40);
            currentBeeImage.setPrefWidth(40);
            Bee newBee = new Bee(position, currentBeeImage,
                    WINDOW_WIDTH, WINDOW_HEIGHT);
            bees.add(newBee);
            theGarden.getChildren().add(currentBeeImage);
            displayBee(position.getX(), position.getY(), currentBeeImage);
        }
    }

    // display the bee at the (beeXLocation, beeYLocation), ensuring the bee does not leave the garden
    private void displayBee(double beeXLocation, double beeYLocation, VBox beeImageBox) {
        beeImageBox.setLayoutX(beeXLocation);
        beeImageBox.setLayoutY(beeYLocation);
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        List<Bee> deadBees = new ArrayList<>();
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            for (Bee bee : bees){
                bee.fly(flowerBed);
                if (bee.energyLevel <= 0){
                    deadBees.add(bee);
                } else {
                    displayBee(bee.getPosition().getX()
                            , bee.getPosition().getY(), bee.getImage());
                }
            }
            deadBees.parallelStream().forEach(bees::remove);
        }
    }
    private void populateFlowers(int numberFlowers){
        VBox currentFlowerImage;
        for (int i = 0 ; i < numberFlowers; i++){
            currentFlowerImage = new VBox();
            currentFlowerImage.setPrefHeight(40);
            currentFlowerImage.setPrefWidth(40);
            Point2D randomPoint = randomizePoint();
            Flower newFlower = new Flower(rand.nextInt(2), currentFlowerImage, randomPoint);
            flowers.add(newFlower);
            bedPane.getChildren().add(currentFlowerImage);
            currentFlowerImage.setLayoutY(randomPoint.getY());
            currentFlowerImage.setLayoutX(randomPoint.getX());
        }
        flowerBed = new FlowerBed(flowers);
    }

    private Point2D randomizePoint(){
        int randYPosition = rand.nextInt(WINDOW_HEIGHT-215) + 170;
        int randXPosition = rand.nextInt(WINDOW_WIDTH-70) + 70;
        return new Point2D(randXPosition, randYPosition);
    }

    private void addLegendNode(String text, String iconFile) {
        Label label = new Label(text);
        ImageView view = new ImageView(iconFile);
        view.setFitHeight(40);
        view.setPreserveRatio(true);
        label.setGraphic(view);
        legend.getChildren().add(label);
    }
}
