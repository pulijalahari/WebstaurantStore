WebstaurantStore Selenium Automation Project
This Maven project contains automated tests for the WebstaurantStore website using Selenium WebDriver with Java. 
The tests are designed to run on Windows 10 with the Chrome browser.
Setup
Clone the Repository:
git clone https://github.com/your-username/webstaurantstore-selenium.git
Install Dependencies:
Ensure you have Maven installed. Then, navigate to the project directory and run:
mvn clean install

WebDriver Setup:
The project uses WebDriverManager to manage the ChromeDriver binary. 
The driver will be automatically downloaded based on the browser version.
Running Tests
You can run the tests using the following command:
mvn test

Test Case
The project automates the following test case:
Go to https://www.webstaurantstore.com/
Search for 'stainless work table'.
Check the search result ensuring every product has the word 'Table' in its title.
Add the last found item to Cart.
Empty Cart.
Project Structure
src/main/java: Contains the Java source files.
src/test/java: Contains the automated test classes.
pom.xml: Maven project configuration file.
Build section added testng.xml suite file. even we can run the test using testng.xml suite file also.

Reports:
Reports can be viewed under test-output folder
1)index.html
2)emailable-report.html
3)Default suite/Default test.html
4)target/surefile-reports/index.html or target/surefile-reports/emailable-report.html
