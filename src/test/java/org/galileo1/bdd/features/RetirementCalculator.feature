Feature: Kiwi Save Retirement Projections
  In order to the find my kiwi saver retirement projections 
  As a customer
  I should be able to use kiwi saver retirement calculator

Background: 
    Given a user wants to see their kiwisaver retirement projections

Scenario Outline: Help message against each field should be displayed to user
    When user selects the "<labeled>" information icon
    Then user is able to see the information "<message>" below "<labeled>" field

  Examples:
    | labeled    | message |
    | AGE        | This calculator has an age limit of 64 years old as you need to be under the age of 65 to join KiwiSaver. |
    | EMPSTATUS  | If you are earning a salary or wage, select ‘Employed’. Your employer contributions will be automatically calculated at a rate of 3% of your before-tax salary or wages. You can also select ‘Self-employed’ or ‘Not employed’ and then enter below (in the Voluntary contributions field), the amount and frequency of any contributions that you wish to make. |
    | PIR        | This is your prescribed investor rate (PIR) for tax purposes. If you don't know what your PIR is, click on the ‘Find My Rate’ button. |
    | CURRENTBALANCE| If you do not have a KiwiSaver account, then leave this field blank.|
    | VOLUNTARYCONTRIBUTIONS|If you are 'Self-Employed' or 'Not employed', you can make direct contributions to your KiwiSaver account. If you are 'Employed', you can make voluntary contributions in addition to your regular employee contributions.|
    | SAVINGSGOAL|Enter the amount you would like to have saved when you reach your intended retirement age. If you aren’t sure what this amount is, you can leave it blank or use the Sorted Retirement Planner|
    | RISKPROFILE| The risk profile affects your potential investment returns:Low risk investments usually contain mostly income assets. Generally, investments of this nature can be expected to deliver modest but more consistent returns. They are less likely to go up and down, but will usually provide lower returns over the long term.Medium risk investments are spread more evenly between income assets and growth assets. Generally, these types of investments can be expected to provide moderate levels of returns with moderate levels of investment risk. Returns will vary and may be low or negative in some years.High risk investments usually contain mostly growth assets. Investments with more exposure to growth assets have the potential for higher long-term returns, but they are more likely to go up and down in the short term and will experience periods of negative returns.You can also use our KiwiSaver Risk Profiler to help determine your tolerence to risk.|

Scenario Outline: User who run their own Business or is Unemployed should be able to calculated their projected savings 
    Given user who is "<age>" years old
    And has been "<emp status>"
    And has a prescribed intrest rate of "<pir>"
    And already have "<kiwisaver balance>" in kiwisaver balance
    And is willing to contribute "<contribution amount>" dollar "<contribution frequency>"
    And bears a "<risk profile>" risk profile
    And is expecting to save "<savings goal>" at retiremnet 
    When user enters these details in retirement calculator
    Then user is able to see the projected value

    Examples:
      |age|emp status|pir|kiwisaver balance|contribution amount|contribution frequency|risk profile|savings goal|annual Income|member contribution|
      |45 |Self-employed|10.5%|100000|90|Fortnightly|medium|290000| | |
      |55 |Not employed|10.5%|140000|10|Annually|medium|200000| | |
  
  Scenario: User who is in Service should be able to calculated their projected savings
    
    Given user who is "30" years old
    And has been "Employed"
    And has a prescribed intrest rate of "17.5%"
    And has been earning "82000" annually
    And current makes a contribution of "4" percent
    And bears a "high" risk profile
    When user enters these details in retirement calculator
    Then user is able to see the projected value