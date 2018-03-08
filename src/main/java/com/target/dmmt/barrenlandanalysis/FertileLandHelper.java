
/*
 *
 * Created by srujan vancha on 8/6/17.
 *
 */

package com.target.dmmt.barrenlandanalysis;

import java.util.*;
import java.util.logging.Logger;

public class FertileLandHelper {

    private final static Logger LOGGER = Logger.getLogger("");

    // Set as 1 all nodes inside a barren rectangle
    public static void colorBarrenFarm(Farm myFarm) {

        ListIterator<Integer[]> iterator = myFarm.getAllBarrenLands().listIterator();
        int farmLand[][] = myFarm.getFarmLand();
        while(iterator.hasNext()){

            Integer[] barrenblock = iterator.next();
            for(int i = barrenblock[0]; i <= barrenblock[2]; i++)
                for(int j = barrenblock[1]; j <= barrenblock[3]; j++)
                    farmLand[i][j] = 1;
        }
    }

    // Zero Matrix
    public static void clearColoMatrix(Farm myFarm) {
        int farmLand[][] = myFarm.getFarmLand();
        for(int i = 0; i < myFarm.X_MAX; i++)
            for(int j = 0; j < myFarm.Y_MAX; j++)
                farmLand[i][j] = 0;
    }

    // Add node to the queue to be be inspected
    public static void addQueue(int i, int j, Farm myFarm){
        int farmLand[][] = myFarm.getFarmLand();
        LinkedList<Integer[]> queue = myFarm.getQueue();

        if(farmLand[i][j] == 0){
            queue.add(new Integer[] {i, j});
        }
    }

    /***
     * Main work is done here. This is a type of BFS for disconnected components.
     * We will traverse through the "Graph" giving the same color to all connected components.
     * We will keep track of how many nodes each component has, and this will be the final area.
     *
     * @param myFarm input is the farm
     */
    public static void getFertileLands(Farm myFarm){
        // better explanation of i, j, and the BFS algorithm
        int land = 1;
        int i = 0;
        int j = 0;
        int farmLand[][] = myFarm.getFarmLand();
        LinkedList<Integer[]> queue = myFarm.getQueue();
        HashMap<Integer, Integer> areasMap = myFarm.getAreasMap();

        while(i < myFarm.X_MAX && j < myFarm.Y_MAX){

            if(queue.isEmpty()) {
                Integer node[] = {i, j};

                // If node[i][j] has not been visited add to queue
                // As the queue was empty, this is a new fertile land

                if(farmLand[i][j] == 0) {
                    land++;
                    areasMap.put(land, 0);
                    queue.add(node);
                }

                // Make sure we pass through all the Land

                if(i == (myFarm.X_MAX -1)) {
                    i = 0;
                    j++;
                }
                else
                    i++;
            }

            if(!queue.isEmpty()) {
                Integer node[] = queue.pop();

                int x = node[0];
                int y = node[1];

        // To add fertile neighbours into the queue

                if(farmLand[x][y] == 0){
                    if(x > 0)
                        addQueue(x-1, y,myFarm);
                    if(x < (myFarm.X_MAX - 1))
                        addQueue(x+1, y,myFarm);
                    if(y > 0)
                        addQueue(x, y-1,myFarm);
                    if(y < (myFarm.Y_MAX - 1))
                        addQueue(x, y+1,myFarm);

                    farmLand[x][y] = land;
                    areasMap.put(land, (areasMap.get(land) + 1));
                }
            }
        }
    }

    public static String printOutput(Farm myFarm){
        HashMap<Integer, Integer> areasMap = myFarm.getAreasMap();
        int[] result = new int[areasMap.values().size()];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : areasMap.entrySet()){
            result[i] = entry.getValue();
            i++;
        }

        Arrays.sort(result);
        return (Arrays.toString(result)).replaceAll("\\[|\\]|,", "");

    }
}
