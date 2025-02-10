# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools) \
![Initial Design](https://github.com/5004-SEA-Fa24-Geeng/payroll-gen-egsui/blob/main/UMLDiagram/mermaid-diagram-original.png?raw=true)




## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `ITimeCard` class properly returns `id` from `getEmployeeID()`
2. Test that the `ITimeCard` class properly returns `hours Worked` from `getHoursWorked()`
3. Test that the `FieUtil` class properly prints the error message when IOException raised from `ReadFileToList`
4. Test that the `FieUtil` class properly prints the error message when IOException raised from `WriteFile`
5. Test that the `EmployeeTyoe` enum class properly returns `value` 
6. Test that the `EmployeeTyoe` enum class properly returns `string` from `toString()`
7. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `name` from `getName()`
8. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `id` from `getId()`
9. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `pay Rate` from `getPayRate()`
10. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `employee Type` from `getEmployeeType()`
11. Test that the `HourlyEmployee`, `SalaryEmployee` class properly sets `employee Type` from `setEmployeeType()`
12. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `YTD Earnings` from `getYTDEarnings()`
13. Test that the `HourlyEmployee`, `SalaryEmployee` class properly sets `YTD Earnings` from `setYTDEarnings()`
14. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `YTD paid taxes` from `getYTDTaxesPaid()`
15. Test that the `HourlyEmployee`, `SalaryEmployee` class properly sets `YTD paid taxes` from `setYTDTaxesPaid()`
16. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `PretaxDeductions` from `getPretaxDeductions()`
17. Test that the `HourlyEmployee`, `SalaryEmployee` class properly returns `CSV string` from `toCSV()`
18. Test that the `HourlyEmployee`, `SalaryEmployee` class properly generates `IPayStub` from `runPayroll()`
19. Test that the `HourlyEmployee`, `SalaryEmployee` class properly sets decimal scale `value` from `decimalRoundUp()`
20. Test that the `PayrollGenerator`class properly generates files `employees.csv` and `pay_stubs.csv` from `PayrollGenerator`



## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 
```mermaid
---
title: Payroll Generator UML
---
classDiagram
    class PayrollGenerator {
        - static final String DEFAULT_EMPLOYEE_FILE
        - static final String DEFAULT_PAYROLL_FILE
        - static final String DEFAULT_TIME_CARD_FILE
        - PayrollGenerator()
        + static main(String[] args) void
    }

    class Arguments {
        - String employeeFile
        - String payrollFile
        - String timeCards
        - Arguments()
        + getEmployeeFile() String
        + getPayrollFile() String
        + getTimeCards() String
        + printHelp() void
        + static process(String[] args) Arguments
    }

    class Builder {
        - Builder()
        + static buildEmployeeFromCSV(String csv) IEmployee
        + static buildTimeCardFromCSV(String csv) ITimeCard
    }

    class FileUtil {
        + static final String EMPLOYEE_HEADER
        + static final String PAY_STUB_HEADER
        - FileUtil()
        + static readFileToList(String file) List<String>
        + static writeFile(String outFile, List<String> lines) void
        + static writeFile(String outFile, List<String> lines, boolean backup) void
    }

    class IEmployee {
        <<interface>>
        + getName() String
        + getID() String
        + getPayRate() double
        + getYTDEarnings() double
        + getYTDTaxesPaid() double
        + getPretaxDeductions() double
        + runPayroll(double hoursWorked) IPayStub
        + toCSV() String
    }

    class Employee {
        <<abstract>>
        - final String name
        - final String ID
        - final double payRate
        - final double pretaxDeductions
        - final double YTDEarnings
        - final double YTDTaxesPaid
        - final EmployeeType employeeType
        - static double taxRate
        + Employee(String name, String ID, double payRate, double pretaxDeductions, double YTDEarnings, double YTDTaxesPaid, EmployeeType employeeType)
        + getName() String
        + getID() String
        + getPayRate() double
        + getEmployeeType() String
        + getYTDEarnings() double
        + getYTDTaxesPaid() double
        + getPretaxDeductions() double
        + toCSV() String
        # abstract calculateGrossPay(double hoursWorked) double
        + runPayroll(double hoursWorked) IPayStub
        + decimalRoundUp(double val) double
        + setYTDEarnings(double YTDEarnings) void
        + setYTDTaxesPaid(double YTDTaxesPaid) void
    }

    class HourlyEmployee {
        - double maxWorkHours
        - double overWorkPayRate
        + HourlyEmployee(String name, String id, double payRate, double ytdEarnings,double ytdTaxesPaid, double pretaxDeductions)
        # calculateGrossPay(double hoursWorked) double
    }

    class SalaryEmployee {
        - final double salaryWorkWeeks
        + SalaryEmployee(String name, String id, double payRate, double ytdEarnings,double ytdTaxesPaid, double pretaxDeductions)
        # calculateGrossPay(double hoursWorked) double
    }

    class EmployeeType {
        <<enumeration>>
        HOURLY
        SALARY
    }

    class IPayStub {
        <<interface>>
        + getPay() double
        + getTaxesPaid() double
        + getYtdEarnings() double
        + getYtdTaxesPaid() double
        + toCSV() String
    }

    class PayStub {
        - String name
        - double netPay
        - double taxes
        - double ytdEarnings
        - double ytdTaxesPaid
        + PayStub(String employeeName,double netPay, double taxes, double ytdEarnings, double ytdTaxesPaid)
        + getPay() double
        + getTaxesPaid() double
        + getYtdEarnings() double
        + getYtdTaxesPaid() double
        + toCSV() String
    }

    class ITimeCard {
        <<interface>>
        + getEmployeeID() String
        + getHoursWorked() double
    }

    class TimeCard {
        - final String ID
        - final double hoursWorked
        + TimeCard(String ID, double hoursWorked)
        + getEmployeeID() String
        + getHoursWorked() double
    }




    Arguments --|> PayrollGenerator : subclass
    PayrollGenerator --> Builder : uses
    PayrollGenerator --> FileUtil : uses
    PayrollGenerator --> IEmployee : uses
    Builder --> IEmployee : creates
    Builder --> ITimeCard : creates
    IEmployee <|-- Employee: implements
    ITimeCard <|-- TimeCard: implements
    Employee <|-- HourlyEmployee : extends
    Employee --> IPayStub : creates
    Builder --> EmployeeType : uses
    EmployeeType <-- Employee : uses
    Employee <|-- SalaryEmployee : extends
    IPayStub <|-- PayStub : implements


```

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

At first, I didn't understand the purpose of using abstract class well, and I was hurried to implement it without 
drawing a comprehensive UML diagram. I designed an Employee class and an abstract employee class to separate the fields
of the employee and the general methods into two independent classes. However, I discovered this approach doesn't reach 
a good code reusability. The Employee class only stored the fields of employee and had nothing else functionality, which 
is a redundant process. Therefore, I combined these two classes into an Employee abstract class. 
The most challenging part of this process is to give up the written codes. It was hard to modify the code base and
merge two classes into one without causing conflicts in the present application. Every step changed led to affecting
the usage of variables and methods. If there was a single neckless, the application executed incorrectly. It took me
lots of time and efforts optimizing the code base and completing the modification. After modifying, the code base became 
more neat and the attributes and methods of an employee could be accessed in one class, making the application more 
efficient. 

The lesson I learned from this process is drawing a comprehensive UML diagram first and going through the 
flow, then implementing it. If we implement first, the efforts taken to modify codes will be much more than to redraw a UML diagram.