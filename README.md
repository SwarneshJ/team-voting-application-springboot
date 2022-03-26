# Voting Application
This repository contains a spring boot project which allows users to vote for a particular team member from a given list.

The votes are updated in real time, and number of votes are displayed.

https://team-voting-application.herokuapp.com/

**Steps to run the application:**

Prerequisites - Java 11, Maven

1. Use pom.xml file to resolve all the maven dependencies.
2. Use the main method of "VotingApplication.java" as an entry point to this spring boot project.
3. Server will start at http://localhost:8080

This application uses an in-memory H2 database to keep a track of the email addresses which have already been used to vote

**Further improvements to be done:**

Verify email addresses using OTP before allowing the user to vote for a team member
