# GPA Calculator


##Features


##Feature Goals
- calc gpa for any number of classes per semester
- given a current GPA (with credits & hours), determine new gpa by adding new classes
- gui to add multiple classes
  - `addNewClass()`
    - dynamically update gui to add boxes
  - `updateClass()`
  - `moveUp()` / `moveDown()` no drag & drop, but arrows to reorder
- button or `enter key` to calculate
- `calculateGpa()`    fired on button press
  - just get values from existing text fields
  - checkboxes to **include class in calculation**
  - loop through ArrayList of classes & call `hasValidValues()`
- `Class` object, add class to `GpaCalculator` object
  - **gui object & txt fields part of it**  
  fields CAN have invalid numbers in them, but not used & err message printed
  - bind text field value to objecy's field
  - fields for credits, gpa hours, grade, letter grade, ect
  - letter grade based on `Map` with cutoffs
  - `hasValidValues()`  
  calls multiple validation methods??
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
