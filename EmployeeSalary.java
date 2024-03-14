/**
 * Program description:Required to construct a Java program based on the given class diagram related to the
employee salary data from an input file
 *
 * Programmer: Nur Athirah
 * Date:14 March 2024
 */
//import the respective packages
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

//driver class
public class EmployeeSalary
{
    //Driver method
    public static void main(String[]args) throws IOException
    {
        //Instantiate the object of DecimalFormat
        DecimalFormat dF = new DecimalFormat("0.00");
        try 
        {
            //Set the input/output  file
            //input file
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));
            
            //1 output file
            PrintWriter fileoutput1 = new PrintWriter(new FileWriter("employeeData.txt"));

            //Declare the variables
            String inputData = null;
            String employeeName = "";
            double employeeSalary = 0.00;
            int employeeWorkingYear = 0;

            //variable for top performing employee
            String top_employeeName = "";
            double top_employeeSalary = 0.00;
            int top_employeeWorkingYear = 0;

            //variable for newest employee
            String latest_employeeName = "";
            double latest_employeeSalary = 0.00;
            int latest_employeeWorkingYear = 0;

            //Write the title header of the employee details to the employeeData.txt
            fileoutput1.println("******************List of Employees******************");

            while((inputData = inputFile.readLine()) != null)
            {
                //Instantiate the object reference of the StringTokenizer class
                //to pass the string line (input data)& to set the delimeter
                StringTokenizer strT = new StringTokenizer(inputData,"|");
                
                //to pass the string line & delimeter
                //Break into tokens and assign to the appropriate variables
                employeeName= strT.nextToken();
                employeeSalary = Double.parseDouble(strT.nextToken());
                employeeWorkingYear = Integer.parseInt(strT.nextToken());

                double annualSalary = employeeSalary + (employeeSalary * 0.05);
                //to test for the negative number
                if(employeeSalary < 0 || employeeWorkingYear <0)
                    throw new IllegalArgumentException();

                //find top performing employee
                if(annualSalary > top_employeeSalary){
                    top_employeeName = employeeName;
                    top_employeeSalary = annualSalary;
                    top_employeeWorkingYear = employeeWorkingYear;
                }
                //find the employee with the least years of service
                if(latest_employeeWorkingYear == 0 || employeeWorkingYear < latest_employeeWorkingYear){
                    latest_employeeName = employeeName;
                    latest_employeeSalary = annualSalary;
                    latest_employeeWorkingYear = employeeWorkingYear;

                }

                //store list of employees
                String employeeData = employeeName+"\t\t RM "+annualSalary+"\t\t "+employeeWorkingYear+" years";
                fileoutput1.println(employeeData);

            }
            //top performing employee
            fileoutput1.println("\n\n ******************Top Performing Employee Details ******************");
            String top_employeeData = top_employeeName+"\t\t RM "+top_employeeSalary+"\t\t "+top_employeeWorkingYear+" years";
            fileoutput1.println(top_employeeData);
            
            //display top performing employee
            JOptionPane.showMessageDialog(null,"******************Top performing Employee Details ******************\n"+top_employeeData);

            //latest employee
            fileoutput1.println("\n\n ****************** Details of Employee with the least years of service ******************");
            String latest_employData = latest_employeeName+"\t\t RM "+latest_employeeSalary+"\t\t "+latest_employeeWorkingYear+" years";
            fileoutput1.println(latest_employData);
            JOptionPane.showMessageDialog(null,"******************Details of Employee with the least years of service ******************\n"+latest_employData);

            //close all the input/output files
            inputFile.close();
            fileoutput1.close();

        }//end of try block
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input!";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
    }//end of main
}//end of class
