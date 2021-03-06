# TravelChat V1

**Team Members:** Front-end: Nathaniel Nguyen, Colton Rusch, Raj Paul, Back-end: Suhye Park, Nathan Mugerwa

Welcome to TravelGuide. When traveling with a group, it can be hard to keep track of everything you need, and everything you need to keep in mind. We’d like to create an all-inclusive, streamlined process for traveling that accommodates group travel on a budget. In summary, we’re envisioning a group chat especially designed for travel which takes into account group budget and calendar availability, and makes food, lodging, and transportation recommendations accordingly.

* Key Features
  * Data Scraping
    * Transportation and Housing
      * Flights
      * Uber/Lyft/Rentals
      * Hotel/Airbnb
    * Activities
      * Various activities based on the destination, user preferences
    * If we have time: take into account ratings of restaurants, locations, specific foods/activities
      * Google review API?
    * Challenges:
      * Lots of repeated data
      * Real-time changes can cause issues
   * Schedule Matching
     * Imports data from each user’s Google Calendar. User can manually input as well
     * No one can see any other person’s exact calendar, but they can see what blocks of time are occupied for them, and an entire group availability calendar is generated and easily visible
     * Challenges: 
       * There may not be a perfect match, how do we accommodate this
   * Chat
     * Main interface users will be interacting with
     * Each member in the chat has a profile, which includes that user’s preferences/restrictions, budget, availability, etc.
     * If we have time: photo sharing, polls, animated custom emoticons
   * Budget
     * Give users option between a group budget which everyone splits evenly, or allows each user to put in their own individual contribution
   * Suggestions:
     * Central algorithm, see below for explanation.


-----------------------------------------------
1. Each user gives their individual budget
2. We then plan the trip around a group budget = sum of individual budgets.
3. The main algorithm is a preference matching algorithm:
   * Each user submits a list of preferences for a set of travel considerations:	
     * Travel quality (first class or coach, uber X or luxe, ...)
     * Hotel quality (5-star, ...)
     * The algorithm then maximizes travel quality while minimizing cost.
   * Each user must also submit activity preferences. 
     * Again, we try to reach the "consensus" preference while minimizing price

Example:
Tim wants a 5 star hotel and Julia wants a 3 star hotel. The "consensus" preference is a 4 star hotel. We search for the "best" 4-star hotel that stays within their price. i.e:
   1. Query all 4 star hotels in region of travel from some API
   2. Remove all hotels with less than 100 reviews, rank the rest by their avg review rating (i.e. 4.3 stars/5)
   3. Order the list by this ranking, descending.
   4. Walk down the list, returning the first one whose cost is within the group budget.

## How to Build and Run
To build: \
`mvn package` \
To run the Spark server: \
`./run` will start the server at `http://localhost:4567/login`
