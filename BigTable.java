import java.io.*;
import java.util.*;

public class BigTable{
    static int n = 0; // number of cars 

    static int allVal[];

    static ArrayList<Integer> cars2 = new ArrayList<Integer>();

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


    public static void main(String[] args) throws IOException{
        // initialize("input1.txt");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        iter= Integer.parseInt(br.readLine());

        for (int id=0; id<iter; id++){
            br.readLine(); //empty line
            cars2.clear();
            boatLength= Integer.parseInt(br.readLine());
            L= boatLength*100;

            String val;
            // int j=0;
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
            
            

            visited=new boolean[(n+1)*(L+1)];

            currX = new int[cars.length];
            bestX = new int[cars.length];

            backTrackSolve(0, L);
            toString(bestX);

        }
        
       

        

        //tried soolving multiple interations
        // for (int i=0;i<iter;i++){
        //     init2(cars);
        // }

        

    }

    /*
    *   method to initialize the file and set the variables to their appropriate
    *   values
    */ 
    public static void initialize(String fileName) {
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
            

            iter = allVal[0];

            System.out.println(Arrays.toString(allVal));
            // cars = Arrays.copyOfRange(allVal, (allVal[i]=-1)+1, allVal[i]=0);
            cars = Arrays.copyOfRange(allVal, 3, allVal.length-1); // i realize this is wrong as it is hardcoded and does not help for multiple iterations

            //trying to solve for multiple iterations via different approach than using method init2()
            // for(int i=0; i<allVal.length;i++){
            //     if (allVal[i]==-1){
            //         i++;
            //         boatLength=allVal[i];
            //     }
            //     for(int j=0;j<allVal.length - i; j++){
            //         cars[j]= allVal[i];
            //         if(allVal[i]==0){
            //             break;
            //         }
            //     }

            // }
            n = cars.length;
            System.out.println(Arrays.toString(cars));

            boatLength = allVal[2]; // also realized this is wrong as it is hardcoded
            L = boatLength * 100;
            visited = new boolean[(n+1)*(L+1)]; //initialize the visited array
            

            currX = new int[cars.length];
            bestX = new int[cars.length];


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
        // currK=0;
        // currS=0;
        // total=0;
        // bestK=0;



    }

    /*
    * visites is a boolean function which returns true if the index of the 2 dimensional array was visited
    * false otherwise
    */
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

    /*
    * Method was supposed to be used for multiple iterations
    * could not solve...
    */

    // public static void init2(int[] array) {
    //         for(int i=1; i<iter;i++){
    //             boatLength = array[3 + currX.length];
    //             L = boatLength * 100;
    //             cars = Arrays.copyOfRange(array,4+cars.length, array[-1]);
    //             n = cars.length;

    //             currX = new int[cars.length];
    //             bestX = new int[cars.length];

    //             visited = new boolean[(n+1)*(L+1)]; //initialize the visited array
    //             backTrackSolve(0, L);
    //             toString(currX);
    //         }

    // }

}