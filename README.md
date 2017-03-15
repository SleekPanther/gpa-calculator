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
- [Youtube - MVC Java Tutorial](https://www.youtube.com/watch?v=dTVVa2gfht8)
- [StackOverflow - Applying MVC With JavaFx](http://stackoverflow.com/a/32343342)
- [Stack Overflow - javafx: Separate EventHandler and gui code](http://stackoverflow.com/a/35659515)
- Less Helpful
- [Youtube - JavaFX Software Tutorial: Calculator (MVC)](https://www.youtube.com/watch?v=y1ZaBalVZic) long
- [Youtube - JavaFX Software: Alarm Clock (MVC)](https://www.youtube.com/watch?v=wIpgGpmFUjA)
