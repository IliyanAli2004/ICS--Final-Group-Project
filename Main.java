/*
* Name: Iliyan , Dean, Toby, Abdullah
* Assignemt: Final Assignment- Accounting doccuments
*
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.regex.Pattern;

class Main {
  static String companyName;
	static String date;

	static String[] assetNames = new String[0];
	static double[] assetBalances = new double[0];
	static int assetCount = 0;
	static String assetStart = "Assets";

	static String[] liabilityNames = new String[0];
	static double[] liabilityBalances = new double[0];
	static int liabilityCount = 0;
	static String liabilityStart = "Liabilities";

	static String[] capitalNames = new String[0];
	static double[] capitalBalances = new double[0];
	static int capitalCount = 0;
	static String capitalStart = "Owner's Equity Capital";

	static String[] drawingNames = new String[0];
	static double[] drawingBalances = new double[0];
	static int drawingCount = 0;
	static String drawingStart = "Owner's Equity Drawings";

	static String[] incomeNames = new String[0];
	static double[] incomeBalances = new double[0];
	static int incomeCount = 0;
	static String incomeStart = "Owner's Equity Income";

	static String[] expenseNames = new String[0];
	static double[] expenseBalances = new double[0];
	static int expenseCount = 0;
	static String expenseStart = "Owner's Equity Expense";
	
	static int currentIndex = 0;
	
	public static void main(String[] args) throws IOException {
		readInformation();
		fixTotalCounts();
		initializeArrays();
		readAccountBalances();
	}
  public static void readAccountBalances() {
		try {
			File dataFile = new File("ExampleUserFile.csv");
			Scanner scanner = new Scanner(dataFile);
			String currentBalance = "";
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				currentBalance = checkCurrentBalance(currentBalance, line);
				readAccountRow(currentBalance, line);
				currentIndex++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void readInformation() {
		try {
			File dataFile = new File("ExampleUserFile.csv");
			Scanner scanner = new Scanner(dataFile);
			int lineNumber = 1;
			String currentBalance = "";
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				readCompanyNameAndDate(lineNumber, line);
				currentBalance = checkCurrentBalance(currentBalance, line);
				countAccountRows(currentBalance, line);
				lineNumber++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void readAccountRow(String currentBalance, String line) {
		if(!empty(line) && currentIndex > -1) {
			if(assetStart.equals(currentBalance)) {
				assetNames[currentIndex] = readName(line);
				assetBalances[currentIndex] = readBalance(line);
			} else if(liabilityStart.equals(currentBalance)) {
				liabilityNames[currentIndex] = readName(line);
				liabilityBalances[currentIndex] = readBalance(line);
			} else if(capitalStart.equals(currentBalance)) {
				capitalNames[currentIndex] = readName(line);
				capitalBalances[currentIndex] = readBalance(line);
			} else if(drawingStart.equals(currentBalance)) {
				drawingNames[currentIndex] = readName(line);
				drawingBalances[currentIndex] = readBalance(line);
			} else if(expenseStart.equals(currentBalance)) {
				expenseNames[currentIndex] = readName(line);
				expenseBalances[currentIndex] = readBalance(line);
			} else if(incomeStart.equals(currentBalance)) {
				incomeNames[currentIndex] = readName(line);
				incomeBalances[currentIndex] = readBalance(line);
			}
		}
		
	}

	public static double readBalance(String line) {
		return Double.parseDouble(line.split(Pattern.quote(","))[1]);
	}

	public static String readName(String line) {
		return line.split(Pattern.quote(","))[0];
	}

	public static void initializeArrays() {
		assetNames = new String[assetCount];
		assetBalances = new double[assetCount];

		liabilityNames = new String[liabilityCount];
		liabilityBalances = new double[liabilityCount];

		capitalNames = new String[capitalCount];
		capitalBalances = new double[capitalCount];

		drawingNames = new String[drawingCount];
		drawingBalances = new double[drawingCount];

		incomeNames = new String[incomeCount];
		incomeBalances = new double[incomeCount];

		expenseNames = new String[expenseCount];
		expenseBalances = new double[expenseCount];

		
	}

	public static void fixTotalCounts() {
		assetCount--;
		liabilityCount--;
		capitalCount--;
		drawingCount--;
		expenseCount--;
		incomeCount--;
	}

	public static void countAccountRows(String currentBalance, String line) {
		if(!empty(line)) {
			if(assetStart.equals(currentBalance)) {
				assetCount++;
			} else if(liabilityStart.equals(currentBalance)) {
				liabilityCount++;
			} else if(capitalStart.equals(currentBalance)) {
				capitalCount++;
			} else if(drawingStart.equals(currentBalance)) {
				drawingCount++;
			} else if(expenseStart.equals(currentBalance)) {
				expenseCount++;
			} else if(incomeStart.equals(currentBalance)) {
				incomeCount++;
			}
		}
	}

	public static String checkCurrentBalance(String currentBalance, String line) {
		
		if(trim(line).equals(assetStart)) {
			currentBalance = assetStart;
			currentIndex = -1;
		} else if(trim(line).equals(liabilityStart)) {
			currentBalance = liabilityStart;
			currentIndex = -1;
		} else if(trim(line).equals(capitalStart)) {
			currentBalance = capitalStart;
			currentIndex = -1;
		} else if(trim(line).equals(drawingStart)) {
			currentBalance = drawingStart;
			currentIndex = -1;
		} else if(trim(line).equals(expenseStart)) {
			currentBalance = expenseStart;
			currentIndex = -1;
		} else if(trim(line).equals(incomeStart)) {
			currentBalance = incomeStart;
			currentIndex = -1;
		}
		return currentBalance;
	}
	public static double netIncome(double[] expenseBalances, double[] incomeBalances){
        
	//sets the sum of the expenses and the sum of the revenue to 0
        double sumExpense = 0;
        double sumRevenue = 0;
        double netIncome = 0;
		//adds up all of the expense arrays in a for loop
        for (int i = 0; i < 7; i++) {
            sumExpense = sumExpense + expenseBalances[i];
        }
		//adds up all the revenue arrays in a for loop
        for (int i = 0; i < 7; i++) {
            sumRevenue = sumRevenue + incomeBalances[i];
        }
		//subtracts the two sums to create the net income
        netIncome = sumRevenue - sumExpense;
		//returns the net income to the main
        return netIncome;
    	}


	public static boolean empty(String line) {
		return trim(line).equals("");
	}

	public static String trim(String line) {
		return line.replaceAll(Pattern.quote(","), "");
	}
}
 /* public static void main(String[] args) {
    
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
    
    //Calling method to get company name info
    String[] statementInfo = getStatementInfo();

    //Getting information from the array returned above. This infor mation is regarding the company name and date which is important to add on the balance sheet and income statements.
    String companyName = statementInfo[0];
    String date = statementInfo[2];
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
  public static String[] getStatementInfo () {

    //Creating a completly empty string to store the value of the company name in this string. Setting to size 3. This size is always three as there is never more than 3 peices of information for header.
    String[] companyInfo= new String [3]; 

    try {
      //Creating new file and putting the path of the csv file with info of company name
      File file= new File("ExampleUserFile.csv");
      
      //Initializing scanner which will scan the csv file
      Scanner reader = new Scanner(file);

      //Storing the value of the company name, date into the string array using simple for loop
      for(int i=0; i < 3; i++){
        companyInfo[i] = reader.nextLine();
      }

    }

    catch (Exception e) {
      e.printStackTrace();
    }
    //returning the string with company info (name and date) to the main method
    return companyInfo;
  }
}
