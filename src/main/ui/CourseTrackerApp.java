package ui;

import com.google.gson.Gson;
import exceptions.PreExistingAssignException;
import exceptions.PreExistingCompException;
import exceptions.PreExistingCourseException;
import exceptions.PreExistingTermException;
import model.*;
import persistance.Writer;
import persistance.Reader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class CourseTrackerApp {

    public static final String SAVE_PATH = "./data/saved";
    Scanner input;
    AcademicHistory ah;
    AcademicHistory ahObject;

    //EFFECTS: runs the CourseTracker app
    public CourseTrackerApp() {
        runTracker();
    }

    //MODIFIES: this
    //EFFECTS: processes the user input
    public void runTracker() {
        boolean keepGoing = true;
        String key = null;
        input = new Scanner(System.in);
        ah = loadData();

        while (keepGoing) {
            displayMainOptions();
            key = input.next();
            key = key.toUpperCase();

            if (key.equals("B")) {
                keepGoing = false;
                autoSave();
            } else {
                process(key);
            }
        }
        System.out.println("\nBye! Hope to see you again soon!");
    }

    //MODIFIES: this
    //EFFECTS: loads previously saved data into the app for the user
    private AcademicHistory loadData() {
        try {
            ahObject = Reader.readAHistory(new File(SAVE_PATH));
        } catch (IOException e) {
            System.out.println("File not found");
        }

        return ahObject;
    }


    //EFFECTS: saves the state of the academic history to SAVE_PATH
    private void autoSave() {
        Gson gson = new Gson();
        String json = gson.toJson(ah);

        try {
            Writer writer = new Writer(new File(SAVE_PATH));
            writer.write(json);
            writer.close();
            System.out.println("Your Academic History has been saved!");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }


    //EFFECTS: Displays the main options to the user
    private void displayMainOptions() {
        System.out.println("\nChoose an option!");
        System.out.println("\tA -> Add a new term!");
        System.out.println("\tC -> Add a new course");
        System.out.println("\tE -> Add a component");
        System.out.println("\tG -> Add an assignment");
        System.out.println("\tB -> Quit");
    }

    //EFFECTS: Displays the term options to the user
    private void displayTermOptions() {
        System.out.println("\nWould you like to add a course?");
        System.out.println("\tC -> Add a course");
        System.out.println("\tD -> Go back to main menu");
    }

    //EFFECTS: Displays the course options to the user
    private void displayCourseOptions() {
        System.out.println("\nWould you like to add a component to this course?");
        System.out.println("\tE -> Add a component");
        System.out.println("\tF -> Go back to main menu");
    }

    //EFFECTS: Displays the assignment options to the user
    private void displayAssignmentOptions() {
        System.out.println("\nWould you like to add an assignment to this component?");
        System.out.println("\tG -> Add an assignment");
        System.out.println("\tH -> Go back to main menu");
    }

    //MODIFIES: this
    //EFFECTS: processes the user command
    private void process(String s) {
        if (s.equals("A")) {
            addTerm();
        } else if (s.equals("C")) {
            addCourse();
        } else if (s.equals("E")) {
            addComponent();
        } else if (s.equals("G")) {
            addAssignment();
        } else {
            System.out.println("That's not a valid option! Please try again...");
        }
    }

    //MODIFIES: this
    //EFFECTS: add a new term to the existing academic history
    private void addTerm() {
        System.out.println("What would you like to call this new term?");
        input = new Scanner(System.in);
        String temp = input.nextLine();
        Term term = new Term(temp);
        try {
            ah.addTerm(term);
        } catch (PreExistingTermException e) {
            System.out.println("This term already exists! Please try again...");
        }
        System.out.println("A new term has been added!");
        displayTermOptions();

        input = new Scanner(System.in);
        String key = input.next();
        key = key.toUpperCase();

        if (key.equals("C")) {
            process(key);
        }
    }

    //MODIFIES: this
    //EFFECTS: add a new course to an existing term
    private void addCourse() {
        System.out.println("Which term would you like to add this course to?");
        input = new Scanner(System.in);
        String temp = input.nextLine();

        Term term = getTerm(temp);

        System.out.println("What's the name of this course?");
        String temp3 = input.nextLine();
        temp3 = temp3.toLowerCase();
        Course course = new Course(temp3);
        try {
            term.addCourse(course);
        } catch (PreExistingCourseException e) {
            System.out.println("This course already exists in this term! Please try again...");
        }
        System.out.println("The course has been added!");
        displayCourseOptions();

        String key = input.next();
        key = key.toUpperCase();

        if (key.equals("E")) {
            process(key);
        }
    }

    //MODIFIES: this
    //EFFECTS: add a component to a pre-existing course
    private void addComponent() {
        System.out.println("In which term is this does this component belong to?");
        input = new Scanner(System.in);
        String temp = input.nextLine();

        Term term = getTerm(temp);
        ArrayList<Course> courses = term.getListOfCourse();

        System.out.println("Which course is this component for?");
        String courseName = input.nextLine();
        courseName = courseName.toLowerCase();

        Course course = getCourse(courseName, courses);

        System.out.println("What's the name of this component?");
        String componentName = input.nextLine();

        System.out.println("What's the weight of this component? (In percentage)");
        double weight = input.nextDouble();

        Component component = new Component(weight,componentName);
        try {
            course.addComponent(component);
        } catch (PreExistingCompException e) {
            System.out.println("This component already exists in this course! Please try again...");
        }
        System.out.println("Component has been added!");
        displayAssignmentOptions();
        String temp4 = input.next().toUpperCase();

        evalTemp(temp4);
    }

    //EFFECTS: process user input to avoid checkstyle
    private void evalTemp(String temp4) {
        if (temp4.equals("G")) {
            process(temp4);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a new assignment to a pre-existing component
    private void addAssignment() {
        System.out.println("In which term is this does this assignment belong to?");
        input = new Scanner(System.in);
        String temp = input.nextLine();

        Term term = getTerm(temp);
        ArrayList<Course> courses = term.getListOfCourse();

        System.out.println("Which course is this assignment for?");
        String courseName = input.nextLine().toLowerCase();

        Course course = getCourse(courseName, courses);
        ArrayList<Component> components = course.getListOfComponents();

        System.out.println("What component does this assignment fall under?");
        String componentName = input.nextLine();

        Component component = getComponent(componentName, components);

        System.out.println("What's the title of this assignment?");
        String assignName = input.nextLine();

        System.out.println("What'd you score on this assignment?");
        double score = input.nextDouble();

        Assignment assignment = new Assignment(assignName,score);
        try {
            component.addAssignment(assignment);
        } catch (PreExistingAssignException e) {
            System.out.println("This assignment already exists for this component! Please try again");
        }
        System.out.println("Assignment has been added!\nReturning back to your Academic History");
    }

    //REQUIRES: the list of components input can't be empty
    //EFFECTS: returns a component with given name from the given list of components
    private Component getComponent(String componentName, ArrayList<Component> components) {
        ArrayList<String> temp = new ArrayList<>();

        for (Component c: components) {
            String name = c.getComponentName();
            temp.add(name);
        }

        int index = temp.indexOf(componentName);
        return components.get(index);
    }

    //REQUIRES: the list of courses input can't be empty
    //EFFECTS: returns a course with given name from the given list of courses
    private Course getCourse(String courseName, ArrayList<Course> courses) {
        ArrayList<String> temp = new ArrayList<>();

        for (Course c: courses) {
            String name = c.getCourseName();
            temp.add(name);
        }

        int index = temp.indexOf(courseName);
        return courses.get(index);
    }

    //REQUIRES: the list of terms inside academic history can't be empty
    //EFFECTS: returns a term with given name from academic history's list of terms
    private Term getTerm(String temp) {
        ArrayList<Term> temp2 = ah.getListOfTerms();
        ArrayList<String> temp3 = new ArrayList<>();

        for (Term t: temp2) {
            String termName = t.getTermName();
            temp3.add(termName);
        }

        int index = temp3.indexOf(temp);
        return temp2.get(index);
    }








}
