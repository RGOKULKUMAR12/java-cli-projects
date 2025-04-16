import java.util.*;

class Customer {
    int cusId;
    String name;
    int creditScore;
    int incomeDetails;
    String collateral;

    public Customer(int cusId, String name, int creditScore, int incomeDetails, String collateral) {
        this.cusId = cusId;
        this.name = name;
        this.creditScore = creditScore;
        this.incomeDetails = incomeDetails;
        this.collateral = collateral;
    }

    public String toString() {
        return "Customer ID: " + cusId + ", Name: " + name + ", Credit Score: " + creditScore +
                ", Income: " + incomeDetails + ", Collateral: " + collateral;
    }
}

class Loan {
    int loanId;
    String loanName;
    int loanAmount;
    int interestRate;

    public Loan(int loanId, String loanName, int loanAmount, int interestRate) {
        this.loanId = loanId;
        this.loanName = loanName;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
    }

    public String toString() {
        return "Loan ID: " + loanId + ", Loan Name: " + loanName + ", Amount: " + loanAmount +
                ", Interest Rate: " + interestRate + "%";
    }
}

class AppliedLoan {
    int loanId;
    int cusId;
    String loanName;
    int loanAmount;
    int interestRate;
    String status = "Pending";

    public AppliedLoan(int loanId, int cusId, String loanName, int loanAmount, int interestRate) {
        this.loanId = loanId;
        this.cusId = cusId;
        this.loanName = loanName;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
    }

    public String toString() {
        return "Loan ID: " + loanId + ", Customer ID: " + cusId + ", Loan Name: " + loanName +
                ", Amount: " + loanAmount + ", Interest Rate: " + interestRate + "%, Status: " + status;
    }
}

public class BankLoan {
    static Scanner sc = new Scanner(System.in);
    static List<Customer> customerList = new ArrayList<>();
    static List<Loan> availableLoans = new ArrayList<>();
    static List<AppliedLoan> appliedLoanList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Bank Loan System Menu =====");
            System.out.println("1. Add New Customer");
            System.out.println("2. Add New Loan Type");
            System.out.println("3. Apply for Loan");
            System.out.println("4. Validate Loan Applications");
            System.out.println("5. Display All Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNewCustomer();
                    break;
                case 2:
                    addNewLoanType();
                    break;
                case 3:
                    applyForLoan();
                    break;
                case 4:
                    validateLoans();
                    break;
                case 5:
                    displayAllData();
                    break;
                case 6:
                    System.out.println("Exiting system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void addNewCustomer() {
        System.out.print("Enter Customer ID: ");
        int cusId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Credit Score: ");
        int creditScore = sc.nextInt();

        System.out.print("Enter Income: ");
        int income = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Collateral: ");
        String collateral = sc.nextLine();

        customerList.add(new Customer(cusId, name, creditScore, income, collateral));
        System.out.println("✅ Customer added successfully!");
    }

    public static void addNewLoanType() {
        System.out.print("Enter Loan ID: ");
        int loanId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Loan Name: ");
        String loanName = sc.nextLine();

        System.out.print("Enter Loan Amount: ");
        int amount = sc.nextInt();

        System.out.print("Enter Interest Rate (%): ");
        int rate = sc.nextInt();

        availableLoans.add(new Loan(loanId, loanName, amount, rate));
        System.out.println("✅ Loan type added successfully!");
    }

    public static void applyForLoan() {
        System.out.print("Enter Loan ID: ");
        int loanId = sc.nextInt();

        System.out.print("Enter Customer ID: ");
        int cusId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Loan Name: ");
        String loanName = sc.nextLine();

        System.out.print("Enter Loan Amount: ");
        int amount = sc.nextInt();

        System.out.print("Enter Interest Rate: ");
        int rate = sc.nextInt();

        appliedLoanList.add(new AppliedLoan(loanId, cusId, loanName, amount, rate));
        System.out.println("✅ Loan application submitted successfully!");
    }

    public static void validateLoans() {
        for (AppliedLoan loan : appliedLoanList) {
            for (Customer customer : customerList) {
                if (loan.cusId == customer.cusId) {
                    if (customer.incomeDetails >= 500000 && customer.creditScore > 500) {
                        loan.status = "Approved";
                    } else {
                        loan.status = "Rejected";
                    }
                }
            }
        }
        System.out.println("\n✅ Loan applications validated.");
    }

    public static void displayAllData() {
        System.out.println("\n--- Customers ---");
        if (customerList.isEmpty()) System.out.println("No customers yet.");
        else customerList.forEach(System.out::println);

        System.out.println("\n--- Available Loans ---");
        if (availableLoans.isEmpty()) System.out.println("No loan types yet.");
        else availableLoans.forEach(System.out::println);

        System.out.println("\n--- Applied Loans ---");
        if (appliedLoanList.isEmpty()) System.out.println("No loan applications yet.");
        else appliedLoanList.forEach(System.out::println);
    }
}
