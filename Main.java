/*
* Name: Iliyan , Dean, Toby, Abdullah
* Assignemt: Final Assignment- Accounting documents
*
*/
//File imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.regex.Pattern;

// Javafx imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
    
    // Stage the windows and scenes for GUI
    Stage window;
    Scene scene, scene2, scene3;
    static File userFile;
	
	//Store all asset account names and balanaces/information
	static String[] assetNames = new String[0];
	static double[] assetBalances = new double[0];
	static int assetCount = 0;
	static String assetStart = "Assets";
	
	//Store all liabilities account names and balances/information 
	static String[] liabilityNames = new String[0];
	static double[] liabilityBalances = new double[0];
	static int liabilityCount = 0;
	static String liabilityStart = "Liabilities";
	
	//Store all capital account names and balances/information
	static String[] capitalNames = new String[0];
	static double[] capitalBalances = new double[0];
	static int capitalCount = 0;
	static String capitalStart = "Owner's Equity Capital";
	
	//Store the drawings account name and balance/information 
	static String[] drawingNames = new String[0];
	static double[] drawingBalances = new double[0];
	static int drawingCount = 0;
	static String drawingStart = "Owner's Equity Drawings";
	
	//Store the income account name and balances/information
	static String[] incomeNames = new String[0];
	static double[] incomeBalances = new double[0];
	static int incomeCount = 0;
	static String incomeStart = "Owner's Equity Income";
	
	//Store all expenses account names and balances/information 
	static String[] expenseNames = new String[0];
	static double[] expenseBalances = new double[0];
	static int expenseCount = 0;
	static String expenseStart = "Owner's Equity Expense";
	
	//Current index of the account line we are reading since in all of the arrays, first index is 0
	static int currentIndex = 0;
	
	//@param args
	//@throws IOException
	public static void main(String[] args) throws IOException {
		// Launches GUI Application
        launch(args);
        
	}

        //Method that reads the "ExampleUserFile.csv" file line by line and from each line, read the names and amounts of each asset, liability, capital, drawings, income and expenses
	//@param File dataFile is the users file that they chose in the file chooser ("ExampleUserFile.csv")
	public static void readAccountBalances(File dataFile) {
		try {
			//Create scanner
			Scanner scanner = new Scanner(dataFile);
			//Variable for current balance can be assets, liabilities, capital, drawings, income and expenses
			String currentBalance = "";
			//Loop line by line
			while (scanner.hasNextLine()) {
				//Read a line
				String line = scanner.nextLine();
				//Read current balance type assets, liabilities, capital, drawings, income and expenses
				currentBalance = checkCurrentBalance(currentBalance, line);
				//Read name and amount of the corresponding account
				readAccountRow(currentBalance, line);
				currentIndex++;
			}
			scanner.close();
			//Give an error if file not found
		} catch (FileNotFoundException e) {
		       System.out.println("An error occurred.");
		       e.printStackTrace();
		}
	}
	//Reads the "ExampleUserFile.csv" file line by line and extracts company name and date of balances and also reads the number of assets, liabilities, capital, drawings, income and expenses
	//@param File dataFile is the users file that they chose in the file chooser ("ExampleUserFile.csv")
	public static void readInformation(File dataFile) {
		try {
			//Create scanner
			Scanner scanner = new Scanner(dataFile);
			//Start with line number 1
			int lineNumber = 1;
			//Current balance can be assets, liabilities, capital, drawings, income and expenses
			String currentBalance = "";
			//Loop line by line
			while (scanner.hasNextLine()) {
				//Read a line from the csv file
				String line = scanner.nextLine();
				//Current balance can be assets, liabilities, capital, drawings, income and expenses
				currentBalance = checkCurrentBalance(currentBalance, line);
				//Count number of rows for each balance
				countAccountRows(currentBalance, line);
				//Next line
				lineNumber++;
			}
			scanner.close();
			//Give an error if file not found
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
        //Reads and stores names and amount of assets, liabilities, capital, drawings, income and expenses in corresponding arrays
	//@param currentBalance current assets, liabilities, capital, drawings income and expenses
	//@param line from csv file
	public static void readAccountRow(String currentBalance, String line) {
		//Check if line is empty or current index is not -1,  currentIndex -1 means that we have not started reading assets, liabilities, capital, drawings, income or expenses so its reading any line before accounts headings
		if(!empty(line) && currentIndex > -1) {
			//Depending upon assets, liabilities, capital, drawings, income and expenses, read the name and amounts
			if(assetStart.equals(currentBalance)) {
				assetNames[currentIndex] = readName(line);
				assetBalances[currentIndex] = readBalance(line);
			} 
			else if(liabilityStart.equals(currentBalance)) {
				liabilityNames[currentIndex] = readName(line);
				liabilityBalances[currentIndex] = readBalance(line);
			} 
			else if(capitalStart.equals(currentBalance)) {
				capitalNames[currentIndex] = readName(line);
				capitalBalances[currentIndex] = readBalance(line);
			} 
			else if(drawingStart.equals(currentBalance)) {
				drawingNames[currentIndex] = readName(line);
				drawingBalances[currentIndex] = readBalance(line);
			} 
			else if(expenseStart.equals(currentBalance)) {
				expenseNames[currentIndex] = readName(line);
				expenseBalances[currentIndex] = readBalance(line);
			} 
			else if(incomeStart.equals(currentBalance)) {
				incomeNames[currentIndex] = readName(line);
				incomeBalances[currentIndex] = readBalance(line);
			}
		}
	}
        //Reads the balances of accounts
	//@param line from input csv file
	//@return amount/balance of account
	public static double readBalance(String line) {
		return Double.parseDouble(line.split(Pattern.quote(","))[1]);
	}
	//Reads the names of accounts
	//@param line from input csv file
	//@return Name of account
	public static String readName(String line) {
		return line.split(Pattern.quote(","))[0];
	}
	
	//Initializes the arrays to store names and amounts of the accounts related to assets, liabilities, capital, drawings, income and expenses
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
	
	//Reduce each count by 1 since there is a empty line after each account type group of assets, liabilities, capital, drawings, income and expenses
	public static void fixTotalCounts() {
		assetCount--;
		liabilityCount--;
		capitalCount--;
		drawingCount--;
		expenseCount--;
		incomeCount--;
	}
	
	//Depending upon currentBalance adds to the count of assets, liabilities, capital, drawings, income and expenses 
	//@param currentBalance is current account
	//@param line is from input csv file
	public static void countAccountRows(String currentBalance, String line) {
		if(!empty(line)) {
			if(assetStart.equals(currentBalance)) {
				assetCount++;
			} 
			else if(liabilityStart.equals(currentBalance)) {
				liabilityCount++;
			} 
			else if(capitalStart.equals(currentBalance)) {
				capitalCount++;
			} 
			else if(drawingStart.equals(currentBalance)) {
				drawingCount++;
			} 
			else if(expenseStart.equals(currentBalance)) {
				expenseCount++;
			} 
			else if(incomeStart.equals(currentBalance)) {
				incomeCount++;
			}
		}
	}
	
	//Checks the provided line and determines the type of account
	//@param currentBalance currently selected balance can be assets, liabilities, capital, drawings, income and expenses
	//@param line is current line
	//@return assets, liabilities, capital, drawings, income and expenses
	public static String checkCurrentBalance(String currentBalance, String line) {
		//Check if a new account type has started and then return that, otherwise return the existing account type
		if(trim(line).equals(assetStart)) {
			currentBalance = assetStart;
			//At the beginning of new balance move the current index to heading. Actual account balance line minus 1 
			currentIndex = -1;
		} 
		else if(trim(line).equals(liabilityStart)) {
			currentBalance = liabilityStart;
			//At the beginning of new balance move the current index to heading. Actual account balance line minus 1
			currentIndex = -1;
		} 
		else if(trim(line).equals(capitalStart)) {
			currentBalance = capitalStart;
			//At the beginning of new balance move the current index to heading. Actual account balance line minus 1
			currentIndex = -1;
		} 
		else if(trim(line).equals(drawingStart)) {
			currentBalance = drawingStart;
		        //At the beginning of new balance move the current index to heading. Actual account balance line minus 1
			currentIndex = -1;
		} 
		else if(trim(line).equals(expenseStart)) {
			currentBalance = expenseStart;
			//At the beginning of new balance move the current index to heading. Actual account balance line minus 1
			currentIndex = -1;
		} 
		else if(trim(line).equals(incomeStart)) {
			currentBalance = incomeStart;
			//At the beginning of new balance move the current index to heading. Actual account balance line minus 1
			currentIndex = -1;
		}
		//Return the account type
		return currentBalance;
	}
	public static double netIncome(double[] expenseBalances, double[] incomeBalances){
        
	//sets the sum of the expenses and the sum of the revenue to 0
        double sumExpense = 0;
        double sumRevenue = 0;
        double netIncome = 0;
		//adds up all of the expense arrays in a for loop
        for (int i = 0; i < expenseBalances.length; i++) {
            sumExpense = sumExpense + expenseBalances[i];
        }
		//adds up all the revenue arrays in a for loop
        for (int i = 0; i < incomeBalances.length; i++) {
            sumRevenue = sumRevenue + incomeBalances[i];
        }
		//subtracts the two sums to create the net income
        netIncome = sumRevenue - sumExpense;
		//returns the net income to the main
        return netIncome;
    	}
	/*
  	*Name: calculateTotalAsset
  	*Description: Takes input of an array which contains all the values of the assets. Adds all the values and gets the total Assets value which is used in the balance sheet.
  	*@return: totalA- DataType double- This variable contains the total amount of assets which is important for further calculations as well as presenting on the GUI
  	*/
	public static double calculateTotalAsset (double [] arr){
		//Creating total int to add all the array values inside this variable
		double totalA=0;
		//Creating For loop to itereate around the array and add values from the array to the total integer
		for (int i=0; i<arr.length; i++){
			totalA+= arr[i];
		}
		return totalA; //returns total to the main method
	}
	 /*
  	*Name: calculateTotalLiability
  	*Description: Takes input of an array which contains all the values of the liability. Adds all the values and gets the total Liability value which is used in the balance sheet.
  	*@return: totalL- DataType double- This variable contains the total amount of Liability which is important for further calculations as well as presenting on the GUI. This value is also used to get the value of OwnersEquity and Liability part in the balance sheet.
 	*/
	public static double calculateTotalLiability (double [] arr){
		//Creating total int to add all the array values inside this variable
		double totalL=0;
		//Creating For loop to itereate around the array and add values from the array to the total integer
		for (int i=0; i<arr.length; i++){
			totalL+= arr[i];
		}
		return totalL; //returns total to the main method
	}
	/*
  	*Name: getStatementInfo
  	*Description: Reads the csv file with a scanner object. The scanner reads the file for information regarding date and company name and stores those lines into an array. That array is then returned.
  	*@return: comapanyInfo- DataType double[]- contains the values of companyName and Date of the statement. This is divded later into spereate name and date using index value. But a sting array is returned. 
  	*/
	public static String[] getStatementInfo (File userFile) {
		//Creating a completly empty string to store the value of the company name in this string. Setting to size 3. This size is always three as there is never more than 3 peices of information for header.
		String[] companyInfo= new String [3]; 
		try {
			//Creating new file and putting the path of the csv file with info of company name
			File file= userFile;
		
			//Initializing scanner which will scan the csv file
			Scanner reader = new Scanner(file);
			//Storing the value of the company name, date into the string array using simple for loop
			for(int i=0; i < 3; i++){
				companyInfo[i] = reader.nextLine();
			}
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//returning the string with company info (name and date) to the main method
		return companyInfo;
	}
	/*
  	*Name: getOwnersEquity
  	*Description: Gets all of the Starting values, Drawings values and Income values and uses the a formula to calculate the final balance.
  	*@param: arrDrawings- DataType double []- This array contains the value of the amount of cash the person made in total.  This is important for us to calculate the final Balance.
  	*@param: arrStartingBalance- DataType double []- This array contains the starting balance value which is then used to get the ending balaance using the formula
  	*@param: Income - DataType: double- This variable contains the amount of income generated in the period. Income is also important for getting the final ending balance.
  	*@return: fBalance- DataType  double- This is also a variable not an array since only one value. This variable contains the ending balance/ final balance of the period. After putting everything into the equation this is what we get and its being returned. 
  	*/
	public static double getOwnersEquity (double[] arrDrawings, double[] arrStartingBalance, double Income){
		//Creating all variables to use later in this method
		double totalDrawings=0;
		double startingBalance=0;
		double increaseInCapital=0;
		double fBalance=0;
		
		//Simple for loop to get total amount of drawings into a variable.
		for(int i=0; i< arrDrawings.length; i++){
			totalDrawings+= arrDrawings[i];
		}
		//Simple for loop to get the starting balance of the company
		for (int i=0; i < arrStartingBalance.length; i++){
			startingBalance += arrStartingBalance[i];
		}
   		//Using formula to get increasseInCapital Value
		increaseInCapital= Income-totalDrawings;
    		//Getting final Balance again using the formula. This is the ending balance of the company.
		fBalance= increaseInCapital+startingBalance;
    		//Returns the fBalance for further caluclations as well as for displaying on the balance sheet.
		return fBalance;
	}
	
	//Checks if a line is empty to removes all commas and  checks if remaining line is empty
	//@param line from input csv file
	//@return true if line without commas is empty, otherwise false
	public static boolean empty(String line) {
		return trim(line).equals("");
	}
	
	//Removes all commas from a line 
	//@param line from input csv file
	//@return line without commas
	public static String trim(String line) {
		return line.replaceAll(Pattern.quote(","), "");
	}











    @Override
    public void start (Stage primaryStage) throws Exception{
        
        window = primaryStage;
        Scanner reader = new Scanner(System.in);
        
        //Sets title of window
        window.setTitle("Accounting Software");

        // Exit Confirmation
        window.setOnCloseRequest(e -> {
            e.consume();
            properExit();
        });

        // Creates layout
        BorderPane borderPane = new BorderPane();

        // Software Title Header
        Text accountingSoftware = new Text("Accounting Software");
        accountingSoftware.setFont(Font.font(null, FontWeight.BOLD, 24));

        VBox header = new VBox(20);
        header.getChildren().addAll(accountingSoftware);

        // Button to upload file
        Button fileUploadButton = new Button("Upload File");

        // Button to download template
        Text uploadText = new Text("To use this program, you must upload\nour template file filled with your\nbusiness' account information.");
        Text templateDownload = new Text("Dont have a template?\nDownload one HERE -");
        
        Button templateDownloadButton = new Button("Download Template");
        templateDownloadButton.setOnAction(e -> {
            downloadTemplate();
        });

        // Adds text and buttons to main menu scene
        VBox menu = new VBox(10);
        menu.getChildren().addAll(fileUploadButton, uploadText, templateDownload, templateDownloadButton);

        // Creates and adds menu buttons to the next screen (to go to income statement/balance sheet/return)
        VBox sheetButtons = new VBox(10);
        Button incomeStatementSelect = new Button("View Income Statement");
        Button balanceSheetSelect = new Button("View Balance Sheet");
        Button goBackButton = new Button("Go Back");
        sheetButtons.getChildren().addAll(incomeStatementSelect, balanceSheetSelect, goBackButton);

        // When file upload button is pressed
        fileUploadButton.setOnAction(e -> {
            // Opens file chooser and allows user to choose a file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload File");
            userFile = fileChooser.showOpenDialog(window);

            readInformation(userFile);
            fixTotalCounts();
            initializeArrays();
            readAccountBalances(userFile);

            // After user chooses file, switches to view selection menu
            borderPane.setCenter(sheetButtons);
            BorderPane.setMargin(sheetButtons, new Insets(20,100,100,80));
        });

        // When go back button is pressed. User taken back to upload screen.
        goBackButton.setOnAction(e -> {
            borderPane.setCenter(menu);
            BorderPane.setMargin(menu, new Insets(20,100,100,80));
        });

        // When income statement button is pressed, user is taken to income statement
        incomeStatementSelect.setOnAction(e -> {
            double netIncome = netIncome(expenseBalances, incomeBalances);

            //Calling method to get company name info
            String[] statementInfo = getStatementInfo(userFile);
            //Getting information from the array returned above. This information is regarding the company name and date which is important to add on the balance sheet and income statements.
            String companyName = statementInfo[0];
            String date = statementInfo[2];

            displayIncomeStatement(netIncome, companyName, date);
        });

        // When balance sheet button is pressed, user is taken to balance sheet
        balanceSheetSelect.setOnAction(e -> {
            //Calling method to get total assets
            double totalAssets = calculateTotalAsset(assetBalances);
            //Calling method to get total liability
            double totalLiability = calculateTotalLiability(liabilityBalances);
            // Calling income statement method to get net income/loss
            double netIncome = netIncome(expenseBalances, incomeBalances);
            
            //Calling method to get company name info
            String[] statementInfo = getStatementInfo(userFile);
            //Getting information from the array returned above. This infor mation is regarding the company name and date which is important to add on the balance sheet and income statements.
            String companyName = statementInfo[0];
            String date = statementInfo[2];

            double finalBalance= getOwnersEquity(drawingBalances, capitalBalances, netIncome);
            double ownerEquityandLiability= finalBalance+ totalLiability;

            displayBalanceSheet(totalAssets, totalLiability, netIncome, companyName, date, finalBalance, ownerEquityandLiability);
        });

        // Exit button in second menu
        Button exitButtonMenu2 = new Button("Exit Program");
        exitButtonMenu2.setOnAction(e -> {properExit();});

        // Sets program title text, menu buttons, and exit button in first scene
        borderPane.setTop(accountingSoftware);
        borderPane.setCenter(menu);
        borderPane.setBottom(exitButtonMenu2);

        // Formats above
        BorderPane.setAlignment(accountingSoftware, Pos.TOP_CENTER);
        BorderPane.setMargin(accountingSoftware, new Insets(12,12,12,12));
        BorderPane.setMargin(menu, new Insets(20,100,100,80));
        BorderPane.setAlignment(exitButtonMenu2, Pos.BASELINE_LEFT);
        BorderPane.setMargin(exitButtonMenu2, new Insets(10,10,10,10));

        // Sets scene size and type
        scene = new Scene(borderPane, 400, 400);
        window.setScene(scene);
        // Opens scene and program   
        window.show();

        reader.close();

    }

    // If user chooses to exit, window will close
    public void properExit(){
        Boolean answer = ExitConfirmation.display("Exit", "Are you sure you want to exit?");
        if(answer){
            window.close();
        }
    }

    // If user wants to download a template
    public static void downloadTemplate(){
        try {
            // Takes existing template and copies the lines to a new file for the user
            File templateForCopies = new File("TemplateForCopies.csv");
            Scanner reader = new Scanner(templateForCopies);

            File templateForUser = new File("Template.csv");
            PrintWriter out = new PrintWriter(templateForUser);

            String data = "";
            
            while (reader.hasNextLine()){
                data = reader.nextLine();
                out.println(data);
            }
            reader.close();
            out.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }




    // Displays Income Statement in the GUI
    public void displayIncomeStatement(double netIncome, String companyName, String date){
        
        // Create gridpane layout
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(1);

        // Title of income statement
        String incomeStatementTitlesString = companyName + "\nIncome Statement\n" + date;


        Text incomeStatementTitle = new Text(incomeStatementTitlesString);
        incomeStatementTitle.setUnderline(true);
        incomeStatementTitle.setFont(Font.font(null, 12));

        // Revenue Header
        Text revenueHeader = new Text("Revenue");
        revenueHeader.setFont(Font.font(null, FontPosture.ITALIC, 12));

        // Revenue Accounts
        String[] revenueAccounts = incomeNames;

        String revenueAccountsString = "";
        String lastRevenueAccount = "";
        for(int i = 0; i < revenueAccounts.length; i++){
            if(revenueAccounts.length == 1){
                revenueAccountsString = revenueAccounts[i];
            }
            else{
                if(i == revenueAccounts.length - 1){
                    lastRevenueAccount += (revenueAccounts[i]);
                }
                else if(i == revenueAccounts.length - 2){
                    revenueAccountsString += (revenueAccounts[i]);
                }
                else{
                    revenueAccountsString += (revenueAccounts[i] + "\n");
                }
            }
        }
        Text revenueAccountsText = new Text(revenueAccountsString);
        revenueAccountsText.setFont(Font.font(null, 12));

        Text finalRevenueAccount = new Text(lastRevenueAccount);
        
        // Revenue Account Values
        double[] revenueValues = incomeBalances;

        String revenueAmount = "";
        String revenueValuePlacement = "1";
        String lastRevenueValue = "";
        double sumOfRevenues = 0;
        for(int i = 0; i < revenueValues.length; i++){
            if(revenueValues.length == 1){
                revenueAmount += revenueValues[i];
                revenueValuePlacement = "1";
            }
            else{
                if(i == revenueValues.length - 1){
                    lastRevenueValue += (revenueValues[i]);
                }
                else if(i == revenueValues.length - 2){
                    revenueAmount += (revenueValues[i]);
                }
                else{
                    revenueAmount += (revenueValues[i] + "\n");
                    
                }
                revenueValuePlacement = "2";
                sumOfRevenues += revenueValues[i];
            }
        }

        Text revenue = new Text("$" + revenueAmount);
        revenue.setFont(Font.font(null, 12));

        Text finalRevenueValue = new Text(lastRevenueValue);
        finalRevenueValue.setUnderline(true);


        // Expense Header
        Text expenseHeader = new Text("Expenses");
        expenseHeader.setFont(Font.font(null, FontPosture.ITALIC, 12));


        // Expense Accounts
        String[] expenseAccounts = expenseNames;

        String expenseAccountsString = "";
        String lastExpenseAccount = "";
        for(int i = 0; i < expenseAccounts.length; i++){
            if(i == expenseAccounts.length - 1){
                lastExpenseAccount = expenseAccounts[i];
            }
            else if(i == expenseAccounts.length - 2){
                expenseAccountsString += (expenseAccounts[i]);
            }
            else{
                expenseAccountsString += (expenseAccounts[i] + "\n");
            }
        }

        Text expenseAccountsText = new Text(expenseAccountsString);
        expenseAccountsText.setFont(Font.font(null, 12));

        Text finalExpenseAccount = new Text(lastExpenseAccount);


        // Expense Account Values
        double[] expenseValues = expenseBalances;

        String expense = "";
        String lastExpense = "";
        double sumOfExpenses = 0;
        for(int i = 0; i < expenseValues.length; i++){
            if(i == expenseValues.length - 1){
                lastExpense = (expenseValues[i] +"");
            }
            else if(i == expenseValues.length - 2){
                expense += (expenseValues[i]);
            }
            else{
                expense += (expenseValues[i] + "\n");
            }
            sumOfExpenses += expenseValues[i];
        }

        Text expenses = new Text("$" + expense);
        expenses.setFont(Font.font(null, 12));

        Text finalExpense = new Text(lastExpense);
        finalExpense.setUnderline(true);


        // Total Revenue (Only if more than one revenue account)
        Text totalRevenue = new Text("Total Revenue");
        totalRevenue.setFont(Font.font(null, FontPosture.ITALIC, 12));

        double totalRev = 0;
        if(revenueValuePlacement.equals("1")){
            totalRev = Double.parseDouble(revenueAmount);
        }
        else{
            totalRev = sumOfRevenues;
        }

        Text totalRevenueValue = new Text("$" + totalRev);


        // Total Expenses
        Text totalExpenses = new Text("Total Expenses");
        totalExpenses.setFont(Font.font(null, FontPosture.ITALIC, 12));

        double totalExp = sumOfExpenses;
        Text totalExpenseValue = new Text("" + totalExp);
        totalExpenseValue.setUnderline(true);

        // Net Income / Net Loss
        Text netValueType;
        if (netIncome > 0){
            netValueType = new Text("Net Income");
        }
        else if(netIncome < 0){
            netValueType = new Text("Net Loss");
        }
        else{
            netValueType = new Text("Net Zero");
        }
        netValueType.setFont(Font.font(null, FontWeight.BOLD, 12));

        Text netValue = new Text("$" + netIncome);
        netValue.setUnderline(true);
        netValue.setFont(Font.font(null, FontWeight.BOLD, 12));
        

        // Buttons

        // Takes user back to menu
        Button mainMenu = new Button("Back to Menu");
        mainMenu.setOnAction(e -> {
            window.setScene(scene);
            window.show();

        });

        // Lets user view Balance sheet
        Button toBalanceSheetFromIS = new Button("View Balance Sheet");
        toBalanceSheetFromIS.setOnAction(e -> {
            //Calling method to get total assets
            double totalAssets = calculateTotalAsset(assetBalances);
            //Calling method to get total liability
            double totalLiability = calculateTotalLiability(liabilityBalances);
            // Calling income statement method to get net income/loss
            double netIncomeD = netIncome(expenseBalances, incomeBalances);
                        
            //Calling method to get company name info
            String[] statementInfo = getStatementInfo(userFile);
            //Getting information from the array returned above. This information is regarding the company name and date which is important to add on the balance sheet and income statements.
            String companyName2 = statementInfo[0];
            String date2 = statementInfo[2];
            
            double finalBalance= getOwnersEquity(drawingBalances, capitalBalances, netIncome);
            double ownerEquityandLiability= finalBalance+ totalLiability;
            
            displayBalanceSheet(totalAssets, totalLiability, netIncomeD, companyName2, date2, finalBalance, ownerEquityandLiability);
        });

        // Lets user exit program
        Button exitButtonIS = new Button("Exit Program");
        exitButtonIS.setOnAction(e -> {properExit();});


        
        // Formatting
        Text space = new Text("");
        Text space2 = new Text("");

        grid.add(space2, 0, 0, 1, 1);
        grid.add(incomeStatementTitle, 0, 1, 2, 1);
        grid.add(space, 0, 2, 1, 1);

        grid.add(revenueHeader, 0, 3, 2, 1);
        grid.add(revenueAccountsText, 0, 4, 2, 1);

        // If there is more than one revenue account
        int add = 0;
        if (revenueValuePlacement.equals("1")){
            grid.add(revenue, 3, 4, 1, 1);
            add = 0;
        }
        else{
            grid.add(revenue, 2, 4, 1, 1);
            grid.add(finalRevenueAccount, 0, 5, 2, 1);
            grid.add(finalRevenueValue, 2, 5, 1, 1);
            grid.add(totalRevenue, 0, 6, 2, 1);
            grid.add(totalRevenueValue, 3, 6, 1, 1);
            add = 2;
        }

        grid.add(expenseHeader, 0, (5+add), 2, 1);
        grid.add(expenseAccountsText, 0, (6+add), 2, 1);
        grid.add(expenses, 2, (6+add), 1, 1);
        grid.add(finalExpenseAccount, 0, (7+add), 2, 1);
        grid.add(finalExpense, 2, (7+add), 1, 1);
        grid.add(totalExpenses, 0, (8+add), 2, 1);
        grid.add(totalExpenseValue, 3, (8+add), 1, 1);
        grid.add(netValueType, 0, (9+add), 2, 1);
        grid.add(netValue, 3, (9+add), 2, 1);
        
        grid.add(mainMenu, 1, 70+add, 1, 1);
        grid.add(toBalanceSheetFromIS, 3, (70+add), 1, 1);
        grid.add(exitButtonIS, 1, 75+add, 1, 1);
        grid.setAlignment(Pos.TOP_CENTER);

        // Sets and shows income statement screen
        scene2 = new Scene(grid, 475, 430);
        window.setScene(scene2);   
        window.show();
    }

    // Displays balance sheet in the GUI
    public void displayBalanceSheet(double totalAssets, double totalLiability, double netIncome, String companyName, String date, double finalBalance, double ownerEquityandLiability){

        // Creates gridpane layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(1);

        // Balance sheet title
        String balanceSheetTitlesString = companyName + "\nBalance Sheet\n" + date;

        Text balanceSheetTitle = new Text(balanceSheetTitlesString);
        balanceSheetTitle.setUnderline(true);
        balanceSheetTitle.setFont(Font.font(null, 12));

        // Assets Header
        Text assetsHeader = new Text("Assets");
        assetsHeader.setFont(Font.font(null, FontWeight.BOLD, 12));

        // Asset Accounts
        String[] assetAccounts = assetNames;

        String assetAccountsString = "";
        String lastAssetAccount = "";
        for(int i = 0; i < assetAccounts.length; i++){
            if(assetAccounts.length == 1){
                assetAccountsString = assetAccounts[i];
            }
            else{
                if(i == assetAccounts.length - 1){
                    lastAssetAccount += (assetAccounts[i]);
                }
                else if(i == assetAccounts.length - 2){
                    assetAccountsString += (assetAccounts[i]);
                }
                else{
                    assetAccountsString += (assetAccounts[i] + "\n");
                }
            }
        }
        Text assetAccountsText = new Text(assetAccountsString);
        assetAccountsText.setFont(Font.font(null, 12));

        Text finalAssetAccount = new Text(lastAssetAccount);
        
        // Asset Account Values
        double[] assetValues = assetBalances;

        String assetsAmount = "";
        String lastAssetValue = "";
        for(int i = 0; i < assetValues.length; i++){
            if(assetValues.length == 1){
                assetsAmount += assetValues[i];
            }
            else{
                if(i == assetValues.length - 1){
                    lastAssetValue += (assetValues[i]);
                }
                else if(i == assetValues.length - 2){
                    assetsAmount += (assetValues[i]);
                }
                else{
                    assetsAmount += (assetValues[i] + "\n");
                    
                }
            }
        }

        Text assets = new Text("$" + assetsAmount);
        assets.setFont(Font.font(null, 12));

        Text finalAssetValue = new Text(lastAssetValue);
        finalAssetValue.setUnderline(true);

        // Total Assets
        Text totalAssetsText = new Text("Total Assets");
        totalAssetsText.setFont(Font.font(null, FontPosture.ITALIC, 12));
        totalAssetsText.setFont(Font.font(null, FontWeight.BOLD, 12));
        
        
        Text totalAssetsValue = new Text("$" + totalAssets);
        totalAssetsValue.setFont(Font.font(null, FontWeight.BOLD, 12));
        totalAssetsValue.setUnderline(true);


        // Liabilities Header
        Text liabilitiesHeader = new Text("Liabilities");
        liabilitiesHeader.setFont(Font.font(null, FontWeight.BOLD, 12));


        // Liability Accounts
        String[] liabilityAccounts = liabilityNames;

        String liabiilityAccountsString = "";
        String lastExpenseAccount = "";
        for(int i = 0; i < liabilityAccounts.length; i++){
            if(i == liabilityAccounts.length - 1){
                lastExpenseAccount = liabilityAccounts[i];
            }
            else if(i == liabilityAccounts.length - 2){
                liabiilityAccountsString += (liabilityAccounts[i]);
            }
            else{
                liabiilityAccountsString += (liabilityAccounts[i] + "\n");
            }
        }

        Text liabilityAccountsText = new Text(liabiilityAccountsString);
        liabilityAccountsText.setFont(Font.font(null, 12));

        Text finalLiabilityAccount = new Text(lastExpenseAccount);

        // Liability Account Values
        double[] liabilityValues = liabilityBalances;


        String liabilityValue = "";
        String lastLiability = "";
        for(int i = 0; i < liabilityValues.length; i++){
            if(i == liabilityValues.length - 1){
                lastLiability = (liabilityValues[i] +"");
            }
            else if(i == liabilityValues.length - 2){
                liabilityValue += (liabilityValues[i]);
            }
            else{
                liabilityValue += (liabilityValues[i] + "\n");
            }
        }

        Text liabilityBalances = new Text("$" + liabilityValue);
        liabilityBalances.setFont(Font.font(null, 12));

        Text finalLiability = new Text(lastLiability);
        finalLiability.setUnderline(true);

        // Total Liabilities
        Text totalLiabilities = new Text("Total Liabilities");
        totalLiabilities.setFont(Font.font(null, FontPosture.ITALIC, 12));
        totalLiabilities.setFont(Font.font(null, FontWeight.BOLD, 12));
        
        Text totalLiabilitiesValue = new Text("$" + totalLiability);


        // Owners Equity Header
        Text ownersEquityHeader = new Text("Owner's Equity");
        ownersEquityHeader.setFont(Font.font(null, FontWeight.BOLD, 12));

        // Owner Capital Subheading
        String ownersName = capitalNames[0];

        Text ownersNameSubheading = new Text(ownersName);
        ownersNameSubheading.setFont(Font.font(null, FontWeight.BOLD, 12));
        ownersNameSubheading.setFont(Font.font(null, FontPosture.ITALIC, 12));

        // Capital balance at start of month
        String month = "";
        String monthAndDay = "";
        
        for (int i = 0; i < date.length(); i++){
            if (date.charAt(i) == ' '){
                month = date.substring(0, i);
                monthAndDay = date.substring(0, i + 3);
                break;
            }
        }
        if (monthAndDay.charAt(monthAndDay.length()-1) == ','){
            monthAndDay = date.substring(0, date.length()-5);
        }
        
        String startBalance = "Balance "+month+" 1";

        Text balanceMonthStart = new Text(startBalance);

        double MonthStartValue = capitalBalances[0];
        Text balanceMonthStartValue = new Text("$" + MonthStartValue);

        // Net Income/Loss
        String netType = "";
        if (netIncome > 0){
            netType = "Net Income";
        }
        else if(netIncome < 0){
            netType = "Net Loss";
        }
        else{
            netType = "Net Zero";
        }

        Text netTypeText = new Text(netType);

        Text netValueText = new Text("$" + netIncome);

        // Drawings
        double drawingsAmount = drawingBalances[0];
        Text drawingsText = new Text("Less: Drawings");

        Text drawingsValue = new Text("(" + drawingsAmount + ")");
        drawingsValue.setUnderline(true);


        // Increase in Capital
        double grossCapital = netIncome - drawingsAmount;
        String grossCapitalString = "";

        if (grossCapital >= 0){
            grossCapitalString = "Increase in Capital";
        }
        else{
            grossCapitalString = "Decrease in Capital";
        }
        
        Text grossCapitalText = new Text(grossCapitalString);

        Text grossCapitalValue = new Text("" + grossCapital);
        grossCapitalValue.setUnderline(true);

        // Capital balance at end of month
        Text endMonthBalance = new Text("Balance " + monthAndDay);
        endMonthBalance.setFont(Font.font(null, FontPosture.ITALIC, 12));

        double endBalance = finalBalance;

        Text endBalanceValue = new Text("" + endBalance);
        endBalanceValue.setUnderline(true);


        // Total Liabilities and Owners Equity
        Text megaTotal = new Text("Total Liabilities and Equity");
        megaTotal.setFont(Font.font(null, FontWeight.BOLD, 12));

        double totalLOE = ownerEquityandLiability;

        Text megaTotalValue = new Text("$" + totalLOE);
        megaTotalValue.setFont(Font.font(null, FontWeight.BOLD, 12));
        megaTotalValue.setUnderline(true);


        // Buttons

        // Takes user back to menu
        Button mainMenu = new Button("Back to Menu");
        mainMenu.setOnAction(e -> {
            window.setScene(scene);
            window.show();

        });

        // Lets user view Income Statement
        Button toIncomeStatementFromBS = new Button("View Income Statement");
        toIncomeStatementFromBS.setOnAction(e -> {
            double netIncomeB = netIncome(expenseBalances, incomeBalances);

            //Calling method to get company name info
            String[] statementInfo = getStatementInfo(userFile);
            //Getting information from the array returned above. This information is regarding the company name and date which is important to add on the balance sheet and income statements.
            String companyName3 = statementInfo[0];
            String date3 = statementInfo[2];

            displayIncomeStatement(netIncomeB, companyName3, date3);
        });

        // Lets user exit program
        Button exitButtonBS = new Button("Exit Program");
        exitButtonBS.setOnAction(e -> {properExit();});


        
        // Formatting
        Text space = new Text("");
        Text space2 = new Text("");
        Text space3 = new Text("");
        Text space4 = new Text("");

        grid.add(space2, 0, 0, 1, 1);
        grid.add(balanceSheetTitle, 0, 1, 1, 1);
        grid.add(space, 0, 2, 1, 1);

        grid.add(assetsHeader, 0, 3, 1, 1);
        grid.add(assetAccountsText, 0, 4, 1, 1);

        grid.add(assets, 2, 4, 1, 1);
        grid.add(finalAssetAccount, 0, 5, 1, 1);
        grid.add(finalAssetValue, 2, 5, 1, 1);
        grid.add(totalAssetsText, 0, 6, 1, 1);
        grid.add(totalAssetsValue, 3, 6, 1, 1);
        grid.add(space3, 0, 7, 1, 1);


        grid.add(liabilitiesHeader, 0, 8, 1, 1);
        grid.add(liabilityAccountsText, 0, 9, 1, 1);
        grid.add(liabilityBalances, 2, 9, 1, 1);
        grid.add(finalLiabilityAccount, 0, 10, 1, 1);
        grid.add(finalLiability, 2, 10, 1, 1);
        grid.add(totalLiabilities, 0, 11, 1, 1);
        grid.add(totalLiabilitiesValue, 3, 11, 1, 1);
        grid.add(space4, 0, 12, 1, 1);


        grid.add(ownersEquityHeader, 0, 13, 1, 1);
        grid.add(ownersNameSubheading, 0, 14, 1, 1);
        grid.add(balanceMonthStart, 0, 15, 1, 1);
        grid.add(balanceMonthStartValue, 2, 15, 1, 1);
        grid.add(netTypeText, 0, 16, 1, 1);
        grid.add(netValueText, 1, 16, 1, 1);
        grid.add(drawingsText, 0, 17, 1, 1);
        grid.add(drawingsValue, 1, 17, 1, 1);
        grid.add(grossCapitalText, 0, 18, 1, 1);
        grid.add(grossCapitalValue, 2, 18, 1, 1);
        grid.add(endMonthBalance, 0, 19, 1, 1);
        grid.add(endBalanceValue, 3, 19, 1, 1);
        grid.add(megaTotal, 0, 20, 4, 1);
        grid.add(megaTotalValue, 3, 20, 1, 1);

        
        grid.add(mainMenu, 0, 70, 1, 1);
        grid.add(toIncomeStatementFromBS, 4, 70, 1, 1);
        grid.add(exitButtonBS, 0, 75, 1, 1);

        grid.setAlignment(Pos.TOP_CENTER);

        // Sets and shows scene
        scene3 = new Scene(grid, 600, 620);
        window.setScene(scene3);   
        window.show();
    }
}
