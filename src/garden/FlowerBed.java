package garden;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The FlowerBed class is using for a housing for the flowers to be displayed in.
 * There is only one Flower Bed for the program. Also, this is the hub for getting the
 * flowers in list form.
 */
public class FlowerBed {
    //every FlowerBed contains x number of flowers
    private List<Flower> flowers;

    private final Random rand = new Random();
    public FlowerBed(List<Flower> flowers){
        this.flowers = flowers;
    }

    public List<Flower> getFlowers(){
        return flowers;
    }
}
