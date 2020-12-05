import java.io.*;
import java.util.*;

public class BigTable{
    static int n = 0; // number of cars 

    static int iter; // number of iterations in the code

    static int[] cars; // array for the cars in the file and stores their length in cm

    static int boatLength = 0; // length of boat in meters

    static int[] bestX; // array to show the best solution

    static int[] currX; // array to show the current solution

    static int bestK = -1; // integer to show the best number of cars that can fit on the boat

    static int L;  // boat lenght in cm to match unit of car length.

    static int currS; // space left on left side of the boat

    static int newS; // stores updated value of s

    static boolean[] visited; // stores the visited values


    public static void main(String[] args) {
        // will have to code for scanner to execute the functions
        initialize(args[0]);
        
        // used the commented lines to test the program
        // System.out.println(n);
        // System.out.println(iter);
        // System.out.println("Length of boat in cm " + L);

        //System.out.println(Arrays.toString(cars));

        backTrackSolve(0, L);
        System.out.println(bestK);

        // System.out.println(Arrays.toString(currX));

        for (int i=0; i<bestK;i++){
            if (currX[i]== 1){
                System.out.println("port");
            }
            else{
                System.out.println("starboard");
            }
        }
        

    }

    /*
    *   method to initialize the file and set the variables to their appropriate
    *   values
    */ 
    public static void initialize(String fileName) {
        int allVal[];
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            while (br.readLine() != null) {
                counter++;
            }
            // System.out.println(counter); line was used to verify code

            String line; // creating a vairable to use for the readLine function from BufferedReader
            allVal = new int[counter]; // array of all the integer values of the file
            cars = new int[allVal.length-3];

            BufferedReader br2 = new BufferedReader(new FileReader(fileName));
            for (int i = 0; i < counter; i++) {
                line = br2.readLine();
                if (line.isEmpty()) {
                    allVal[i] = -1;
                } else if (line != null) {
                    allVal[i] = Integer.parseInt(line);
                }
            }
            br2.close();

            iter = allVal[0];

            // System.out.println(Arrays.toString(allVal));

            cars = Arrays.copyOfRange(allVal, 3, allVal.length-1);

            n = cars.length;
            
            boatLength = allVal[2];
            L = boatLength * 100;

            currX = new int[cars.length];
            bestX = new int[cars.length];

            visited = new boolean[(n+1)*(L+1)]; //initialize the visited array

        }
        catch ( FileNotFoundException e ) {
            System.out.println("File not found");
        } catch ( IOException e) {
            System.out.println("Input exception");
        }



    }
    /*
    * Backtracking method takes two integers as arguments to compute the ferri loading problem and to populate
    * the port and Starboard of the ferri.
    */

    public static void backTrackSolve(int currK, int currS){
        int total =0;
        for(int i=0; i< currK;i++){
            total+=cars[i];
        }
        if (currK>bestK){
            bestK = currK;
            for(int i=0; i<bestK;i++){
            bestX[i] = currX[i];
            }
        }
        if(currK<n){
            if(currS>=cars[currK] && !visited[(currK+1)*(currS-cars[currK])]){
                currX[currK]=1;
                // currS = currS - cars[currK];
                newS = currS - cars[currK];
                backTrackSolve(currK+1, newS);
                visited[(currK+1)*newS]= true;
            }
            if((2*L - currS - total)>= cars[currK] && !visited[(currK+1)*(currS)]){
                currX[currK]=0;
                // currS = currS - cars[currK];
                // spaceR = spaceR - cars[currK];
                backTrackSolve(currK+1, currS);
                visited[(currK+1)*currS]= true;
            }

        }


    }

    /*
    * visites is a boolean function which returns true if the index of the 2 dimensional array was visited
    * false otherwise
    */
    public static boolean visited(Integer k, Integer s){
        //the index of array needs to be changed into the array for x to for the spots 0 and 1.
        if (visited[k*s]= true){
            return true;
        }
        return false;
    }

}