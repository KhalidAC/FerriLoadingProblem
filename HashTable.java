import java.io.*;
import java.util.*;

/*
*Class to implement the Backtracking using a hashTable
*
*/

public class HashTable{
    static int n = 0; // number of cars 

    static int iter; // number of iterations in the code

    static int[] cars; // array for the cars in the file and stores their length in cm

    static ArrayList<Integer> cars2 = new ArrayList<Integer>(); // gets the values inputed from the file

    static int boatLength = 0; // length of boat in meters

    static int[] bestX; // array to show the best solution

    static int[] currX; // array to show the current solution

    static int bestK = -1; // integer to show the best number of cars that can fit on the boat

    static int L;  // boat lenght in cm to match unit of car length.

    static int currS; // space left on left side of the boat

    static int newS; // stores updated value of s

    public static HashMap<Integer, Integer> hashT = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        iter= Integer.parseInt(br.readLine());

        for (int id=0; id<iter; id++){
            br.readLine(); //empty line
            cars2.clear();
            boatLength= Integer.parseInt(br.readLine());
            L= boatLength*100;

            String val;
            int j=0;
            boolean whl = true;
            val=br.readLine();
            while(whl){
                if(val.equals("0")){
                    whl = false;
                }
                cars2.add(Integer.parseInt(val));
                val=br.readLine();
            }
            // br.close();

            n=cars2.size();
            cars= new int[n];

            for(int i=0;i<cars2.size();i++){
                if(cars2.get(i)==0){
                    break;
                }
                cars[i]=cars2.get(i);
            }
            
            

            // visited=new boolean[(n+1)*(L+1)];

            currX = new int[cars.length];
            bestX = new int[cars.length];

            backTrackSolve(0, L);
            toString(bestX);

        }
        
        // used the commented lines to test the program
        // System.out.println(n);
        // System.out.println(iter);
        // System.out.println("Length of boat in cm " + L);

        //System.out.println(Arrays.toString(cars));


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
            if(currS>=cars[currK] && (hashT.get(currK+1) == null)){
                currX[currK]=1;
                // currS = currS - cars[currK];
                newS = currS - cars[currK];
                backTrackSolve(currK+1, newS);
                hashT.put((currK+1),newS);
            }
            if((2*L - currS - total)>= cars[currK] && (hashT.get(currK+1) == null)){
                currX[currK]=0;
                // currS = currS - cars[currK];
                // spaceR = spaceR - cars[currK];
                backTrackSolve(currK+1, currS);
                hashT.put((currK+1), currS);
            }

        }


    }

    public static void toString(int[] array){
        System.out.println(bestK);
        for (int i=0; i<bestK;i++){
            if (array[i]== 1){
                System.out.println("port");
            }
            else{
                System.out.println("starboard");
            }
        }
        System.out.println("");
    }


}

// public class HashTable{

//     public void hashValue(int K, int V){

//     }


// }