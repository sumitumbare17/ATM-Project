import java.sql.*;
import java.util.Scanner;
public class ATM {
    public static void main(String args[]) {
        String bankName , nextOption ,sql;
        int Option = 0,accno;
        double amount = 0.0 , withdrawAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        outerWhile:
        while(true)
        {
            System.out.println("Enter ATM Card(Bank Name)");
            bankName =sc.next();
            System.out.println("Enter Your Account Number ");
            accno = sc.nextInt();
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "0503");
                Statement stmt = con.createStatement();
                ResultSet rs =stmt.executeQuery("select name from atm where acc_no = 1");

                Bank bank = (Bank)Class.forName(bankName).getDeclaredConstructor().newInstance();
                innerWhile:
                while(true)
                {
                    System.out.println("**** Choose Option ****");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Exit");
                    System.out.println("Enter Your Choice");
                    Option = sc.nextInt();
                    try
                    {
                        switch(Option)
                        {
                            case 1:
                                System.out.println("Enter Amount To Deposit :");
                                amount = sc.nextDouble();
                                bank.deposite(amount);
                                sql = "update atm set deposite_amt = ? where acc_no =? ";
                                PreparedStatement stm = con.prepareStatement(sql);
                                stm.setString(1,Double.toString(amount) );
                                stm.setString(2, Integer.toString(accno));
                                stm.executeUpdate();
                                break;
                            case 2:
                                System.out.println("Enetr Amount To Withdraw");
                                amount = sc.nextDouble();
                                withdrawAmount = bank.withdraw(amount);
                                System.out.println("Collect "+withdrawAmount+" Rs");
                                break;
                            case 3:
                                break innerWhile;
                            default:
                                System.out.println("Invalide Choice \n Enter Valid Choice");
                        }
                        System.out.println("Do You Want To Continue (Y/N)");
                        nextOption = sc.next();
                        if(nextOption.equalsIgnoreCase("N"))
                        {
                            System.out.println("Transaction Completed"+"\n Thank You");
                            break outerWhile;
                        }
                    }
                    catch(InSufficientFundsException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch(InvalidAmountException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
            catch(ClassNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}
