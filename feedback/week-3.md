# Feedback week 3 
#### Group 11

## Exercise 1

### 1.1 - 2/4 pts.
The classes you derived using CRC look decent, but I agree with you that using inheritance for the different Tetromino's is probably best. Now, the view is responsible for displaying the score and the preview panel, but I see no logic to support these features in your card (e.g. where is the score calculated).
You also could've talked more about the steps you took creating these classes (with help of your requirements).

### 1.2 - 3/4 pts.
Your CRC is fine but I can see that some classes are packed (with resposibility and or methods). You could think about moving the *check* methods in the controller to another class in the future .for instance. It's not a large problem right now, but as your project grows, don't lose sight of these things.

### 1.3 - 4/4 pts.
--

### 1.4 - 2/2 pts.
I've taken the liberty of grading your class diagram from exercise 2 here.

### 1.5 2/4 pts.
I had a look at your activity diagram in the apendix, but please refer to it properly in the future. It looks good, but I'd say the user receives feedback from the controller via the view, and not directly.

## Exercise 2

### 2.1 - 2/3 pts.
You could've been a bit more elaborate / clear about instances of aggregation / composition in your game.

### 2.2 - 2/3 pts.
You are correct, but please elaborate some more.

### 2.3 - 2/3 pts.
You could've said something about the hierarchy that is good. 

## Exercise 3

### 3.1 - 10/20 pts.
You failed to let me approve your requirements; I would have liked to have console logging and timestamps as requirements as well.
Additionally I think you are missing a few events. I get no logs of when a line is cleared for instance. I also don't know what shape a new tetromino is, that would be nice to know.
Technically: I had some trouble getting your logger to write to a file. I changed line 15 in your logger to 
```    private static File file = new File("src/main/java/logging/log.log"); ```
Did you guys get this working on all your machines?

### 3.2 - 6/13 pts.
Unfortunately I see no UML's that incorporate the logger.

## Sprint management 

##### Definition 20% - 7

##### Splitting 15% - 8

##### Responsibility 10% - 10

##### Estimation 15% - N.A.

##### Proritization 15% - N.A.

##### Reflection 25% - N.A.

## Code Evolution Quality 

##### Code Change Quality 25% - 7

##### Code change readability: 
 
###### Formatting 3% - 9

###### Naming 5% - 8

###### Comments 5% - 6

##### Continuous integration: 

###### Building 5% - 8

###### Testing 20% - 8

##### Tooling 5%  - 8

##### Pull-based development:

###### Branching 10% - 8

###### Code Review 22% - 6

## Concluding

### Assignment 40% - 35/60 pts. -- 6.3

### Sprint management 20% - 8.0

### Code Evolution Quality 40% - 7.2


# Final Grade: 7.0
