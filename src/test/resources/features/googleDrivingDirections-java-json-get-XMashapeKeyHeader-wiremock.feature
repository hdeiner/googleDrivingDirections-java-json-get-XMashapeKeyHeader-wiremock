Feature: Google Maps Driving Directions
  Unofficial barebones wrapper of the google maps API provides basic driving directions between two points.
  https://market.mashape.com/montanaflynn/google-maps-driving-directions
  http://montanaflynnhttp://montanaflynn.me/lab/mapit/

  Scenario Outline: Home to N07
    Given I am testing on the actual web service
    When I want to go from "28 Lincoln Park Road, Pequannock NJ"
    And to "Lincoln Park Airport, Lincoln Park NJ"
    Then I should see that the total Distance is "1.5 mi"
    And that it will take "3 mins"
    And leg "<leg>", "<instruction>", for "<timeToTravel>", which should take "<distanceToTravel>" is returned
    Examples:
      |leg|instruction                               |timeToTravel|distanceToTravel|
      |1  |Head north on Lincoln Park Rd             |1 min       |295 ft          |
      |2  |Keep left to continue on Boulevard        |1 min       |0.7 mi          |
      |3  |Turn left onto Jacksonville Rd	         |2 mins      |0.8 mi          |
      |4  |Turn left onto Beaverbrook Rd/Hillview Rd |1 min       |157 ft          |
      |5  |Turn right Destination will be on the left|1 min       |30 ft           |

  Scenario Outline: Atrium to National Car Rental return at KORD
    Given I am testing on the fake web service
    When I want to go from "3800 Gulf Road, Rolling Meadows IL"
    And to "560 Bessie Coleman Dr, Chicago"
    Then I should see that the total Distance is "13.0 mi"
    And that it will take "18 mins"
    And leg "<leg>", "<instruction>", for "<timeToTravel>", which should take "<distanceToTravel>" is returned
    Examples:
      |leg|instruction                                                                                              |timeToTravel|distanceToTravel|
      |1  |Head west on Golf Rd toward Ring Rd                                                                      |1 min       |0.3 mi          |
      |2  |Turn left onto McConnor Pkwy                                                                             |1 min       |0.5 mi          |
      |3  |Turn left onto Woodfield Rd                                                                              |1 min       |0.1 mi          |
      |4  |Turn left onto E Frontage Rd                                                                             |1 min       |0.1 mi          |
      |5  |Take the IL-53 N/Interstate 90 ramp on the left                                                          |1 min       |0.6 mi          |
      |6  |Keep right at the fork, follow signs for I-90 E/Chicago and merge onto I-90 E Toll road                  |10 mins     |9.5 mi          |
      |7  |Take the I-190 W/I-294 S exit toward Ohare/Indiana Toll road                                             |1 min       |0.1 mi          |
      |8  |Keep right at the fork, follow signs for Interstate 190 W/O'Hare and merge onto I-190 W Partial toll road|2 mins      |1.2 mi          |
      |9  |Take the exit toward Bessie Coleman Dr                                                                   |1 min       |0.4 mi          |
      |10 |Continue straight                                                                                        |1 min       |184 ft          |
      |11 |Turn left                                                                                                |1 min       |92 ft           |
      |12 |Turn left                                                                                                |1 min       |384 ft          |
      |13 |Turn right                                                                                               |1 min       |82 ft           |
      |14 |Turn left                                                                                                |1 min       |151 ft          |
      |15 |Turn left Destination will be on the left                                                                |1 min       |49 ft           |