# FlyPortBE

Command to config project for azure deployment: ```mvn azure-webapp:config```  


After this command in pom.xml file should appear plugin ```com.microsoft.azure``` with all information abou web app in azure portal 


Then it is important to run command ```mvn clean package``` Without this command connection in azure to MySQL database will be refused  


Finally deploy application with ```mvn azure-webapp:deploy```
