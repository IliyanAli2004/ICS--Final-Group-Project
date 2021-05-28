/*
* Name: Iliyan , Dean, Toby, Abdullah
* Assignemt: Final Assignment- Accounting doccuments
*
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    
    //Arrays for assets accounts and their respective values
    String [] assetAccountNames= {"Cash", "A/R- Morgan", "Furniture","Autombile", "Land"};
    int [] assetAccountValues= {1000, 5000, 7000, 40000, 100000};

    //Arays for liablities accounts and their respective values
    String [] liabilityAccountNames= {"A/P- Mike", "Bank Loan", "Mortgage"};
    int [] liabilityAccountValues= {100, 5000, 50000};

    //Calling method to get total assets
    int totalAssets = calculateTotalAsset(assetAccountValues);

    //Calling method to get total liability
    int totalLiability = calculateTotalLiability(liabilityAccountValues);
  } 

  public static int calculateTotalAsset (int [] arr){
    //Creating total int to add all the array values inside this variable
    int total=0;

    //Creating For loop to itereate around the array and add values from the array to the total integer
    for (int i=0; i<arr.length; i++){
      total+= arr[i];
    }
    return total; //returns total to the main method
  }

  public static int calculateTotalLiability (int [] arr){
     //Creating total int to add all the array values inside this variable
    int totalL=0;

    //Creating For loop to itereate around the array and add values from the array to the total integer
    for (int i=0; i<arr.length; i++){
      totalL+= arr[i];
    }
    return totalL; //returns total to the main method
  }
}
