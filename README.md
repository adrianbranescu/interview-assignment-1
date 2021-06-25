This application is a job interview assignment that I had to finish in two days.
It was the company's preference to send my work via GitHub.

I had almost no experience with Spring Boot before this, so I spent a lot of time figuring out how to piece things together. Due to time constraints, the application is quite messy, but it was a learning experience for me, so I appreciated the challenge.

It is a Spring Boot (2.5.1) application developed in Java 8, with Maven 3.8.1 and Eclipse Neon. It uses H2 in-memory database.

Starting project was generated online, with Spring Initializr (dependencies: Spring Web, Spring Data JPA, H2 Database).

To run the application, change directory to main application folder and run these CLI commands:

mvn clean install

mvn spring-boot:run

The application runs on localhost:8080 (but nothing is mapped to root address). URLs are mentioned in the following paragraphs.

http://localhost:8080/h2/
=> H2 console access. JDBC URL is "jdbc:h2:mem:testdb". Username = "sa", empty password (all these are configured in "application.properties" file).

Tables are created in "schema.sql" and populated in "data.sql" files:

<br>DROP TABLE IF EXISTS TBL_CLIENTS;
<br><br>CREATE TABLE TBL_CLIENTS (
<br>id INT AUTO_INCREMENT PRIMARY KEY,
<br>name VARCHAR(50),
<br>address VARCHAR(250)
<br>);


<br>DROP TABLE IF EXISTS TBL_METERS;
<br><br>CREATE TABLE TBL_METER_MEASUREMENTS (
<br>  id INT AUTO_INCREMENT PRIMARY KEY,
<br>  client_id INT,
<br>  date DATE,
<br>  value INT,
<br>  CONSTRAINT client_id FOREIGN KEY (client_id) REFERENCES TBL_CLIENTS (id)
<br>);


<br>INSERT INTO TBL_CLIENTS (name, address) VALUES
<br>  ('Client1', 'Address1'),
<br>  ('Client2', 'Address2'),
<br>  ('Client3', 'Address3');
  
<br>INSERT INTO TBL_METER_MEASUREMENTS (client_id, date, value) VALUES
<br>  ('1', '2021-01-11', '10'),
<br>  ('1', '2021-05-10', '11'),
<br>  ('2', '2021-01-29', '20'),
<br>  ('3', '2021-01-30', '30');


TBL_METERS table contains meter records for all clients. It can be used to simulate monthly consumptions, for example. In the above example, client with ID = 1 has two records, one in January 2021 and one in May 2021. Aggregate consumption for client 1 in 2021 is 10 + 11 = 21 energy units (to keep things simple, I assumed that measured values are not indexes, but direct values; otherwise I would have had to do more math to calculate index differences).

http://localhost:8080/clients/
=> listing all clients

http://localhost:8080/clients/1
=> listing client with id = 1

http://localhost:8080/clients/measurements/1
=> returns all metering entries for client with id = 1

http://localhost:8080/clients/report/consumption/1/2021
=> returns 2021 annual energy consumption for client with id = 1
(returns sum of all data in 'value' column stored in TBL_METER_MEASUREMENTS table for client with id = 1)
=> For year 2021, client energy consumption is 21

<br>In Postman:
<br>http://localhost:8080/clients/
<br>POST request, data as JSON:
<br>{"name":"Client4", "address":"Address4"}
<br>=> new client is added to TBL_CLIENTS table

<br>In Postman:
<br>DELETE:
<br>http://localhost:8080/clients/4
<br>=> entry (client with id = 4) is deleted from TBL_CLIENTS table

<br>In Postman:
<br>PUT:
<br>localhost:8080/clients/3
<br>{"name":"NewName", "address":"NewAddress"}
<br>=> client with id = 3 is updated in TBL_CLIENTS table

http://localhost:8080/meters/
=> listing all meter entries

http://localhost:8080/meters/1
=> listing meter with id = 1

I did not have time to implement put/post/delete operations for meters.
However, as a proof of concept, I did implement such operations for clients.

I did not have time for request parameters validation and exception handling.

I did not have time for safety checks on business logic (such as deleting a client that does not exist, or adding a duplicate).

Unit tests: I wrote two, as samples (testing Add client and Get client). Plus one test that only checks if context is loaded.
All tests are placed in "MeterreadingsApplicationTests.java".

Chosen paths for REST mappings could have been better, too.

Database table designs could have been better, too.

I simply did not have time to make everything smooth and elegant. I prioritized the functional part and the rest of the code did not get enough time and attention, unfortunately.

Over all, I'm not proud of how the application looks, but I like that I learnt a lot while working on it.

