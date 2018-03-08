/*
 *
 * Created by srujan vancha on 8/6/17.
 *
 */

package com.target.dmmt.barrenlandanalysis;
import java.util.logging.Logger;

public class BarrenLandProcessor {
    static Logger logger = Logger.getLogger("BarrenLandProcessor");

    public static void main(String[] args) {
        Farm myFarm = new Farm();

        try {
            InputLoader.readFromSTDIN(myFarm);
        }catch (Exception e){
            System.out.print(e.getMessage());
            System.exit(1);
        }

        logger.info("Arranging Barran land with array values 1 inititially");
        FertileLandHelper.clearColoMatrix(myFarm); //Barran -1 , Fertile - 0

        logger.info("Arranging Barran land with array values 0 with given coordinates");
        FertileLandHelper.colorBarrenFarm(myFarm);

        logger.info("Get fertile land out of barran land ");
        FertileLandHelper.getFertileLands(myFarm);
        System.out.print(FertileLandHelper.printOutput(myFarm));


    }

}
