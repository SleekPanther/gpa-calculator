# GPA Calculator
A GPA calculator in Java attempting to use the **Model View Controller** (MVC) pattern.

## Features


## Feature Goals
- calc gpa for any number of classes per semester
- given a current GPA (with credits & hours), determine new gpa by adding new classes

- gui to add multiple classes
  - `addNewClass(Scene/Pane whereItemAppearsOnScreen)`
    - dynamically update gui to add new boxes
  - `updateClass()` ? maybe unnecessary
  - `moveUp()` / `moveDown()` maybe drag & drop, but arrows to reorder
    - must fix ArrayList of classes
- `calculateGpa()`    button / `Enter key`
  - just get values from existing text fields
  - checkboxes to **include class in calculation**
  - loop through ArrayList of classes & call `hasValidValues()`
- ~~`Class` object, add class to `GpaCalculator` object~~
- **`Class` object extends Pane somehow**
  - **gui object & txt fields part of it**  
  fields CAN have invalid numbers in them, but not used & err message printed
  - bind text field value to objecy's field
  - fields for credits, gpa hours, grade, letter grade, ect
  - **ALSO CONVERT BETWEEN 0-100 scale  
  toggle between gpa & numerical (event with radio button?) **
  - letter grade based on `Map` with cutoffs
    - switch statements / else if() for descending grades
  - `hasValidValues()` calls multiple validation methods
    - `validateAndPrintErrors()`
    - `isNumeric()` ==> pass argument & do exception handling
      - print error if error found
    - **static** `isValidGrade()`
      - number in range
- save inputs to txt file  
somehow make it hidden/unreadable
- sort by best gpa? / contributing gpa
- desired gpa & calculate for current classes
- 2nd tab for formula explanation

## References
- [Youtube - MVC Java Tutorial](http://www.newthinktank.com/2013/02/mvc-java-tutorial/) Great video, provided the main motivation to use MVC, but it uses **Java swing** and I had to adapt it to **JavaFX**
  - [UI Control Sample - JavaFX Tutorials and Documentation](https://docs.oracle.com/javafx/2/ui_controls/ButtonSample.java.html) Finally just copied the stuff inside `setOnAction()` to separate inner class in **Controller**
  -  
  - **Dead ends**
  - [JavaFX separating event handling from controller class - Stack Overflow](http://stackoverflow.com/q/36663988) not enough details
  - [javafx multiple buttons to same handler - Stack Overflow](http://stackoverflow.com/a/25410169) initial thougts, but didn't pan out
  - [Multiple Buttons To Same Action Listener](http://www.dreamincode.net/forums/topic/234827-multiple-buttons-to-same-action-listener/#entry1357573) right idea, but using ActionListener instead of EventHandler
  - [JavaFX 8 Event Handling Examples](http://code.makery.ch/blog/javafx-8-event-handling-examples/) JavaFX, but too complex
  - [4 Working with Event Handlers](https://docs.oracle.com/javafx/2/events/handlers.htm) `addEventHandler()`
  - [Difference between setOnXXX() method and addEventHandler() JavaFx - Stack Overflow](http://stackoverflow.com/q/37821796)
  - [Handling Events with Listeners - vaadin](https://vaadin.com/docs/-/part/framework/application/application-events.html) anonymous inner classes
- [StackOverflow - Applying MVC With JavaFx](http://stackoverflow.com/a/32343342)
- [Stack Overflow - javafx: Separate EventHandler and gui code](http://stackoverflow.com/a/35659515)
- Less Helpful
- [Youtube - JavaFX Software Tutorial: Calculator (MVC)](https://www.youtube.com/watch?v=y1ZaBalVZic) long
- [Youtube - JavaFX Software: Alarm Clock (MVC)](https://www.youtube.com/watch?v=wIpgGpmFUjA)
