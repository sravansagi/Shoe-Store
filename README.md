# Shoe-Store
This is Automation test suite for 2 User Stories of shoe store

-----------------------------
Approch Used for Automation:
-----------------------------

- I have used TestNG + Selenium for the automation of the user stores in a windows 10 box.

--------------------------------
Algorithm Used for User Stories
--------------------------------

User Story 1-Monthly display of new releases:

- Opened the home page
- Checked for all the Month links
- Clicked on each month's link accessed all the available shoes and check the for description, valid price and 
displayed image for each product
- After checking all products in a accessed month, Home page is opened prior to clicking on next months link
- If a acceptence criteria is not met then the test is failed with required message and control is passed to next test

User Story 2-Submit email for reminder

- Opened the home page(State-0 stage)
- Checked if input for email remaninder field is available and has an size of height>10 and width>35 to make sure 
the field is visible for the user
- Clicked on submit email to check for no input condition, verified if "Please enter an email address" is displayed
- Clicked on Home to navigate to home page(to try the other email conditions on a State 0 stage)
- Checked if input for email remaninder field is available and has an size of height>10 and width>35 to make sure 
the field is visible for the user
- Entered invalid email in the email input field and checked if message is Invalid email format. Ex. name@example.com
- Clicked on Home to navigate to home page(to try the other email conditions on a State 0 stage)
- Checked if input for email remaninder field is available and has an size of height>10 and width>35 to make sure 
the field is visible for the user
- Entered invalid email in the email input field and checked if message is Thanks! We will notify you of our new shoes at this email: xxxxxxx@xx.xxx
** Generated the valid email addresses using a random number generator

---------------------------------------
Environment used for testing
---------------------------------------
- Eclipse
- Windows 10 64 bit
- Selenium 3.3.1
- Firefox 53.0 beta channel 9 (32-bit)
- Chrome 57.0.2987.133 (64-bit)
- Gecko driver .15(64-bit windows box)
- Chrome Driver 2.29 (32-bit)
- jdk 1.8

I have used firefox and google chrome drivers,Same tests with little modification in the initilization of the browser can be used to extend the test cases to PhantomJS, HTML Unit ( both for head less automation ), Edge , IE , Opera browsers 

Edit:
Have shared the execution video(.mp4) and execution result(TestNG report) in results folder

** have not uploaded the selenium dependency and browser drivers to the github repo. New locations of the drivers have to be updated in the commonconstants class
--------------
Libraries Used
--------------

1) Selenium - http://www.seleniumhq.org/
2) TestNG - http://testng.org/
3) Apache Commons Lang3 - https://commons.apache.org/proper/commons-lang/
