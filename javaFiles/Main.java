import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Main {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       System.out.println("Write the path to your csv file: ");
       String csvFilePath = sc.nextLine();
       ArrayList<Double> addedFields = organizeByHealth(csvFilePath);
       int cityNum = -1;
       //lowest value means healthiest with given data
       double lowest = Integer.MAX_VALUE;
       for(int i = 0; i < addedFields.size(); i++) {
           if(addedFields.get(i) < lowest) {
               cityNum = i;
               lowest = addedFields.get(i);
           }
       }


       System.out.println("The city with the best health (lowest values when the columns D through J are added) is: " +
               getCityBasedOnIndex(csvFilePath, cityNum));
       FindPopRelation var = new FindPopRelation(csvFilePath);
       int firstPop = var.findPopOfHealthiest();
       var.removeItem();
       int secondPop = var.findPopOfHealthiest();
       var.removeItem();
       int thirdPop = var.findPopOfHealthiest();
       var.removeItem();
       int fourthPop = var.findPopOfHealthiest();
       var.removeItem();
       int fifthPop = var.findPopOfHealthiest();


       int[] popsOfHealthiest = new int[5];
       popsOfHealthiest[0] = firstPop;
       popsOfHealthiest[1] = secondPop;
       popsOfHealthiest[2] = thirdPop;
       popsOfHealthiest[3] = fourthPop;
       popsOfHealthiest[4] = fifthPop;
       System.out.println("this is the population of the healthiest city " + firstPop);
       System.out.println("this is the population of the second healthiest city " + secondPop);
       System.out.println("this is the population of the third healthiest city " + thirdPop);
       System.out.println("this is the population of the fourth healthiest city " + fourthPop);
       System.out.println("this is the population of the fifth healthiest city " + fifthPop);


       int allInRangeTester = 0;
       for(int i = 0; i < popsOfHealthiest.length; i++) {
           for(int j = 0; j < popsOfHealthiest.length; j++) {
               if(popsOfHealthiest[i] >= popsOfHealthiest[j] - 100000 && popsOfHealthiest[i] <= popsOfHealthiest[j] + 100000)
                   allInRangeTester++;
           }
       }
       //if allInRnageTester is 25, this means that every value was wihtin 100k of one another
       if(allInRangeTester == 25) {
           System.out.println("Given the spread, there IS a correlation between health and population");
       } else {
           System.out.println("Given the spread, there is NOT a correlation between health and population");
       }
   }
   /* imports the csv file, adds the relevant fields together, and organizes in an arrayList
    */
   public static ArrayList<Double> organizeByHealth(String csvFilePath) {


       String line = "";
       ArrayList<Double> listofHealths = new ArrayList<Double>();


       try {
           BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
           while((line = br.readLine()) != null) {
               //splits each row (city) into multiple strings
               String[] values = line.split(",");
               /*checks if values[x] is not a String, if true, it catches and does nothing
               if each value[x] is not a string, it will add the healths to the arrayList
                */
               try {
                   listofHealths.add(Double.parseDouble(values[3]) +
                           Double.parseDouble(values[4]) +
                           Double.parseDouble(values[5]) +
                           Double.parseDouble(values[6]) +
                           Double.parseDouble(values[7]) +
                           Double.parseDouble(values[8]) +
                           Double.parseDouble(values[9]));
               } catch (Exception e){
               }
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return listofHealths;
   }
   public static String getCityBasedOnIndex(String csvFilePath, int index) {
       String line = "";
       ArrayList<String> cities = new ArrayList<>();
       try {
           BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
           while ((line = br.readLine()) != null) {
               String[] values = line.split(",");
               cities.add(values[1]);
           }
       } catch (Exception e) {


       }
       return cities.get(index + 1);
   }


}