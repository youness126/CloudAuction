# CloudAuction
An add-on package for CloudSim framework to model resource allocation for computational clouds.

Youness Teimoury

http://youness-teimoury.blogspot.com

Prerequisites:
- Java (https://java.com/download)
- Maven (https://maven.apache.org/download.cgi)
- cloudsim 2.1.1 (included in this project)

First run:
Open your terminal and go to the project folder.
Compile the project to make sure everything is in place:
 $ mvn compile

To run each example using maven:
 $ mvn -Dtest=org.cloudbus.cloudsim.examples.auction.AuctionExample0 test
There are six different examples starting from 0 to 5
