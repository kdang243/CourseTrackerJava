# My Course Tracker

## What will this application do?

This application will keep track of all the user's marks for all their courses. The user will be able to create a new term
with all the courses that they are taking in that term. In each course, the user will be able to input the components that
makes up their total mark for that course and the weights of each individual components. This way, they are able to reliably
keep track of how they are doing in each course as they input new marks that they receive throughout the term. The user will
also be able to see what marks they are aiming for in an assignment in order to achieve the final mark that they want
(for example, I need to get at least 70% on my final to get a final mark of 80% in MATH 101.)

*Summary of what this application would do:*
- Allow user to create a new school term and input courses that they are taking.
- User can input the components that makes up the total mark for a course.
- User can input the weight of a component used to calculate the final mark.
- User can input the assignments for each of the components (for example, lab 1 would go under the component Labs) 
- User can input score/grades for each assignments throughout the school year
- User can use the program to find a certain mark that they need for an exam.

## Who will use this application?

The main audience that this application is intended for is students in high school or post-secondary as keeping track of 
marks play a big role in what the user wants to do and achieve. I believe that this application would be very useful for
a user in that situation. 

## Why does this project of interest to me?

In my limited experience with post-secondary so far is that keeping track of my own grades is a very important part as it
tells me if I need to put more effort and time into a course that I'm struggling with or if I'm on track to get the grades
that I'm aiming for to get a specific program in university. Furthermore, in UBC, most courses release grades on Canvas which
unfortunately doesn't take into account the weighted average when calculating the current grade in a course. I find myself
calculating the weighted average myself before taking a final or a midterm to see what I'm aiming for and to me, this is 
somewhat of a chore. I think it would be useful to have a program that keeps track of all my courses and tells me my current
grades correctly so I could accurately reflect on my progress.

## User Stories

*User Stories Week 1*
- As a user, I want to be able to create a new term in my academic history.
- As a user, I want to be able to add a course to my term
- As a user, I want to be able to add a component to a course
- As a user, I want to be able to add a assignment to a component 

*User Stories Week 2*
- As a user, when I select the quit option, the program will save the academic history to file (as a course tracker should).
- As a user, I want the previous data to be presented to me right away (display the user's academic history so they can interact)

##Instructions for Grader

Note: the program auto-loads and auto-saves after every "add X to Y", so when you press an enter button, this is done automatically by the code.

First required event:
1. To add a new term to your academic history, click the button labelled "Add a new term!" from the main menu which will bring you to a new window.
2. Write what you want to call your new term in the text area and press the button that says enter in the new window. (A message should pop up saying 
"A new term has been added!)
3. Write the same exact name and press enter again and the message should say "this term already exists!". Click the back to main menu button to return to main menu.
4. To add courses to a term, components to a course or assignment to a component, just press the corresponding button from the main
menu page. (Note: This is the same process as the add X to Y, but the Y has to exist first before you add an X to it!)

Second required event:
5. In this program right now, you could only look at a list of terms in your academic history (I will add more in when I have time), to do this
click on the button that's labeled "My Academic History" from main menu, then press the button that will load your academic history. Note that this requires you
to have added at least 1 term to your academic history or nothing will show up.
6. In the same window, to remove a term from your current academic history, select a term on the list shown and click the button that's labeled "Remove an Existing Term!"
7. To add a new term to your academic history, press the button that's labeled "Add a New Term!", this step is the same as step 1 and 2 from the first required event.

Note: In the My Academic History window, there is a button on the bottom right corner, this was just an easter egg I inserted for fun. I've disabled it, it does nothing.

Audio component:
8. For the audiovisual component, a click sound is played whenever you press a button in the program.

Saving/Reload State:
9. As mentioned on top of this list, the saving and reading component is done automatically whenever you press an enter button. 
(You could verify this by adding a new term and viewing the list of terms right away without needing to press a "save" or "reload" button )
