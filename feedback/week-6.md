# Feedback week 6 
#### Group 11

- Your report was really hard to read this week, if you don't clearly state which part corresponds with what assignment I can have a hard time figuring things out; especially since you combined a designpattern with another feature. Please divide the assignment report into more clear subsections corresponding to exercises next week.

## Exercise 1.1

### 1.1.1 - 5/5 pts.

### 1.1.2 - 5/5 pts.

### 1.1.3 - 5/5 pts.

## Exercise 1.2

### 1.2.1 - 0/5 pts.

### 1.2.2 - 0/5 pts.

### 1.2.3 - 0/5 pts.

## Exercise 2
- I am going to grade the centering of the block and the code improvement here.

### 2.1 - 14/22 pts.
- Your requirements didn't mention the centering of the tetromino's in the preview pane anywhere.
- Preview pane looks really nice
- What is the 'UI improvement' mentioned in your sprint plan exactly? I can't grade undocumented things
- Good job on the extra tests, don't forget to test your new features

### 2.2 - 5/8 pts.
- Missing documentation on some features.

## Exercise 3
- I am going to grade the highscore board & saving here.

### 3.1 - 14/22 pts.
- The saving feature seems bugged, sometimes my changes get saved, sometimes they don't (especially the keybindings seem to get reset every time).
- You might to write tests for your settings menu
- There are no tests for the highscore menu
- I noticed that it would be nice to have an 'exit' option in the main menu, and not only in the game.


### 3.2 - 8/8 pts.
- <3

## Sprint management 

##### Definition 20% - 8

##### Splitting 15% - 8
- 47/5 = 9.4 hours workload planned on average is good.
- Try to distribute the load a little more evenly (Wouter had 17 hours, whereas the rest had a bit less).

##### Responsibility 10% - 10

##### Estimation 15% - 6
- Your retrospective is lacking some numbers and yes/no's

##### Proritization 15% - 10
- Try utilyzing the entire spectrum from A to E.

##### Reflection 25% - 9

## Code Evolution Quality 

##### Code Change Quality 25% - 6
- I sometimes get an java.lang.IllegalThreadStateException while playing your game.
- Failing tests are unacceptable in a delivery.
- You have an increasing number of bugs in your code, I am sure you notice weird things from time to time, look into them.

##### Code change readability:  

###### Formatting 3% - 10

###### Naming 5% - 10

###### Comments 5% - 4
- I saw some outdated Javadoc!
- There are no comments in your HighscoreScreen class
- There are few comments in your SettingsScreen class
- There are hardly any in your ScoreBoard class

##### Continuous integration: 

###### Building 5% - 3
- If tests start failing in a branch, don't merge it. Fix it.
- The master should ALWAYS cointain a working version of your game. That includes no warnings and no failing tests.

###### Testing 20% - 6
- You wrote tests for a couple of classes, yet you added new classes without tests. Meh.

##### Tooling 5%  - 6

##### Pull-based development:

###### Branching 10% - 8
- e.g. The decorator PR is too large.

###### Code Review 22% - 4
- I hardly saw any proper code review

## Concluding

### Assignment 40% - 56/90 pts. -- 6.6

### Sprint management 20% - 8.7

### Code Evolution Quality 40% - 5.8


# Final Grade: 6.7


