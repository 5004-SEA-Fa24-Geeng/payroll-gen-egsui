# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for? \
Comma-Separated Values

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?\
Due to ArrayList implementing List interface, we declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>` \
to have a better approach for future object scalability.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?\
This type of relationship is called "has-a". 

4. Can you provide an example of a has-a relationship in your code (if one exists)?\
I don't have one in my code, but I can provide an instance, which class Kitchen might have a Sink object.

5. Can you provide an example of an is-a relationship in your code (if one exists)?\
HourlyEmployee and SalaryEmployee are both IEmployee objects.

6. What is the difference between an interface and an abstract class?\
  - An interface provides a blueprint for the classes implement it. If two classes implement an interface, they both need to contain all methods provided in the interface, but the 
  details of implementation can be different.
  - An abstract class provides the common methods with implementation for the classes extend it. If they extend an abstract class, they can call the method in the abstract class without implementing the same code by themselves.

7. What is the advantage of using an interface over an abstract class?\
If we want to have polymorphic solution for similar but not identical classes, using an interface provides more scalability for code implementation than applying an abstract class.

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. \
Due to type argument cannot be of primitive type, we have to replace "int" to object "Integer". `List<Integer> numbers = new ArrayList<Integer>();`

9. Which class/method is described as the "driver" for your application? \
PayrollGenerator. The driver is essential for enabling applications and databases communicating with each other.

10. How do you create a temporary folder for JUnit Testing? \
I use the @TempDir annotation to create temporary directories. JUnit will automatically create and clean up the temp directory after tests.

## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

To ensure a fair payroll system, I would recommend storing additional data points in the employee file, 
such as race, gender, age, property ownership, outstanding loans, and household income. These data points are essential 
for assessing pretax benefits and promoting salary equity. Pretax benefits should be tailored to individual financial 
situations. For example, employees with significant property holdings, which generate additional income, may require 
more pretax benefit limits. Conversely, employees burdened by loans, such as student or mortgage loans, should 
have easier access to pretax deductions to mitigate their financial stress. This approach aligns with tax equity 
principles by accounting for financial obligations and income disparities. Additionally, data like race, 
gender, and age can be used to generate reports for reviewing and improving salary distribution policies. 
By analyzing these reports, organizations can identify potential biases and take action to ensure salary fairness.


Reference:\
[1]. [The Enduring Grip of the Gender Pay Gap](https://www.pewresearch.org/social-trends/2023/03/01/the-enduring-grip-of-the-gender-pay-gap/)\
[2]. [Housing Subsidies Affect Income Inequality](https://nlihc.org/resource/housing-subsidies-affect-income-inequality)\
[3]. [The Impact of Age on the Gender Pay Gap in the Federal Sector](https://www.eeoc.gov/federal-sector/reports/impact-age-gender-pay-gap-federal-sector)
