
/*
 *
 * Created by srujan vancha on 8/6/17.
 *
 */

package com.target.dmmt.barrenlandanalysis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.lang.ArrayIndexOutOfBoundsException;



public class InputLoader {
    static Logger logger = Logger.getLogger("InputLoader");

    public static Integer tryParse(String text) throws Exception {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Barren boundary coordinates are not integers \n");
        }
    }

    public static void readInput(String input, Farm myfarm) throws Exception{

        logger.info("Reading Inputs at readInput()");
        LinkedList<Integer[]> allBarrenLands = new LinkedList<Integer[]>();
        String[] parts = input.split(",");

        for(String s : parts){
            s = s.replace("\"", "");
            s = s.replaceAll("“|”", "");
            s = s.replaceAll("\\{|\\}", "");
            s = s.replaceAll("^ ", "");
            if(!s.isEmpty()){
                String[] coord = s.split(" ");
                if(coord.length<4){
                    // when 4 co-ordinates are not provided throw Exception
                    throw new ArrayIndexOutOfBoundsException("");
                }
                if( tryParse(coord[0]) >= 0 && tryParse(coord[1]) >= 0 &&
                        tryParse(coord[2]) >= 0  && tryParse(coord[3]) >= 0 &&
                        tryParse(coord[0]) < myfarm.X_MAX && tryParse(coord[2]) < myfarm.X_MAX &&
                        tryParse(coord[1]) < myfarm.Y_MAX && tryParse(coord[3]) < myfarm.Y_MAX) {
                    Integer[] temp = {tryParse(coord[0]), tryParse(coord[1]),
                            tryParse(coord[2]), tryParse(coord[3])};
                    allBarrenLands.add(temp);
                } else{
                    // when co-ordinates are out of the Max X and Y boundary
                    throw new Exception("Barren Land OutOfBound \n");
                }
            }
        }
    myfarm.setAllBarrenLands(allBarrenLands);
    }


    public static void readFromSTDIN(Farm myFarm) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER THE CO-ORDINATE INPUTS AS BELOW GIVEN FORMAT FOR BARREN LANDS");
        System.out.println("{\" X11 Y11 X21 Y21\",\" X12 Y12 X22 Y22\", ......... } ");
        String s = br.readLine();
        readInput(s,myFarm);
    }

}
