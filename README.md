Test Cases validate the features below :
● New users should be able to create an account giving name, email and password.
● Password should be at least 8 characters.
● User should be able to Add / Edit / Delete records that indicate the food type, the
calories and the Date.
● (NOT COMPLETED) User should be able to set Max daily calories in his Profile section. If this number is
exceeded in Edibles section a validation message should be displayed.
● User should be able to logout and login again.

In order to execute the tests please follow the instructions,:
1. git clone <Project path on GitHub>
2. install maven locally
3. run : mvn clean compile package -DskipTests=true (to build the project)
4. run : mvn test (to execute test scripts)
