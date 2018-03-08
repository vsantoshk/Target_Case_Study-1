/*
 *
 * Created by srujan vancha on 8/6/17.
 *
 */

package com.target.dmmt.barrenlandanalysis;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.LinkedList;

@Getter
@Setter
public class Farm {
    //  Assign x and y coordinates values given in problem statement
    public final static int X_MAX = 400;
    public final static int Y_MAX = 600;
    // Create Linked List to store barren land coordinates
    LinkedList<Integer[]> allBarrenLands = new LinkedList<>();
    // create queue to hold coordinates while iterating to find fertile land
    LinkedList<Integer[]> queue = new LinkedList<>(); // what does queue do?
    // create HashMap names areasMap to find area of fertile land againist barren land
    HashMap<Integer, Integer> areasMap = new HashMap<>();
    // creating farm as array
    int farmLand[][] = new int[X_MAX][Y_MAX];
}
