import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;


public class FindPopRelation {
   private ArrayList<Double> listOfHealths;
   private ArrayList<Integer> listOfPopulations;
   private String csvPath;
   public FindPopRelation(String csvFilePath) {
       csvPath = csvFilePath;
       listOfHealths = new ArrayList<>();
       listOfHealths = initializeListOfHealths();
       listOfPopulations = new ArrayList<>();


   }
   public void removeItem()  {
       listOfHealths.remove(findHealthiest());


   }
   public ArrayList<Double> initializeListOfHealths() {
       String line = "";
       try {
           BufferedReader br = new BufferedReader(new FileReader(csvPath));
           while((line = br.readLine()) != null) {
               //splits each row (city) into multiple strings
               String[] values = line.split(",");
               /*checks if values[x] is not a String, if true, it catches and does nothing
               if each value[x] is not a string, it will add the healths to the arrayList
                */
               try {
                   listOfHealths.add(Double.parseDouble(values[3]) +
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
       return listOfHealths;
   }
   public int findHealthiest() {


       int cityNum = -1;
       //lowest value means healthiest with given data
       double lowest = Integer.MAX_VALUE;
       for(int i = 0; i < listOfHealths.size(); i++) {
           if(listOfHealths.get(i) < lowest) {
               cityNum = i;
               lowest = listOfHealths.get(i);
           }
       }
       return cityNum; //this is the index of the healhtiest city in listOfHealhts
   }
   public int findPopOfHealthiest() {
       int indexOfCity = findHealthiest();
       String line = "";
       try {
           BufferedReader br = new BufferedReader(new FileReader(csvPath));
           while((line = br.readLine()) != null) {
               //splits each row (city) into multiple strings
               String[] values = line.split(",");
               /*checks if values[x] is not a String, if true, it catches and does nothing
               if each value[x] is not a string, it will add the healths to the arrayList
                */
               try {
                   listOfPopulations.add(Integer.parseInt(values[2]));
               } catch (Exception e){
               }
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       int popOfHealthiest = listOfPopulations.get(findHealthiest());
       listOfPopulations.remove(findHealthiest());
       return popOfHealthiest;
   }
}
