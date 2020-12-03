import java.io.*;
import java.util.*;

public class BigTable{
    static int M = 0; // number of cars 

    static int iter; // number of iterations in the code

    static int[] cars; // array for the cars in the file and stores their length in cm

    static int boatLength = 0; // length of boat in meters

    static int[] bestX; // array to show the best solution

    static int[] currX; // array to show the current solution

    static int bestK; // integer to show the best number of cars that can fit on the boat

    static int[] visArr; // array to store visited values.

    static int L;  // boat lenght in cm to match unit of car length.

    static int currS; // space left on left side of the boat

    static int newS; // stores updated value of s

    static boolean[][] visited; // stores the visited values

    public static void main(String[] args) {
        // will have to code for scanner to execute the functions
        initialize(args[0]);
        

        System.out.println(M);
        System.out.println(iter);
        System.out.println("Length of boat in cm " + L);

        System.out.println(Arrays.toString(cars));

        // backTrackSolve(0, L);

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

            System.out.println(Arrays.toString(allVal));
            // M = allVal.length - 2; CANT HARDCODE

            cars = Arrays.copyOfRange(allVal, 3, allVal.length-1);

            M = cars.length;
            
            boatLength = allVal[2];
            L = boatLength * 100;

            currX = new int[cars.length];
            bestX = new int[cars.length];

            visited = new boolean[M+1][L+1];

        }
        catch ( FileNotFoundException e ) {
            System.out.println("File not found");
        } catch ( IOException e) {
            System.out.println("Input exception");
        }



    }

    public static void backTrackSolve(int currK, int currS){
        if (currK>bestK){
            bestK = currK;
            for(int i=0; i<cars.length;i++){
            bestX[i] = currX[i];
            }
        }
        // if(currK<M){
        //     if(add to left && visited(currK, currS-cars[currK])==false){
        //         currX[currK]=1;
        //         newS = currS - cars[currK];
        //         backTrackSolve(currK+1, newS);
        //         visited[currK+1][currS]= true;
        //     }
        //     if(space on right && visited(currK, currS-cars[currK])==false){
        //         currX[currK]=1;
        //         newS = currS - cars[currK];
        //         backTrackSolve(currK+1, newS);
        //         visited[currK+1][currS]= true;
        //     }
        // }


    }



    public static boolean visited(Integer k, Integer s){
        //the index of array needs to be changed into the array for x to for the spots 0 and 1.
        if (visited[k][s]= true){
            return true;
        }
        return false;
    }

    // public static boolean backtracking(Integer i){
    //     for (i=0; i<n; i++){
    //         if (index[i] != -1){
    //             return false; //means that cell was visited
    //         }
    //         return true;
    //     }
    // }
    

}