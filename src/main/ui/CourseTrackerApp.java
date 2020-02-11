package ui;

import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;
import static com.sun.tools.javac.util.StringUtils.toUpperCase;

public class CourseTrackerApp {

    Scanner input;
    AcademicHistory ah;


    public CourseTrackerApp() {
        runTracker();
    }

    public void runTracker() {
        boolean keepGoing = true;
        String key = null;
        input = new Scanner(System.in);
        ah = new AcademicHistory();

        while (keepGoing) {
            displayMainOptions();
            key = input.next();
            key = key.toUpperCase();

            if (key.equals("B")) {
                keepGoing = false;
            } else {
                process(key);
            }
        }
        System.out.println("\nBye! Hope to see you again soon!");
    }

    //EFFECTS: Displays the options to the user
    private void displayMainOptions() {
        System.out.println("\nChoose an option!");
        System.out.println("\tA -> Add a new term!");
        System.out.println("\tB -> Quit");
    }

    private void displayTermOptions() {
        System.out.println("\nWould you like to add a course?");
        System.out.println("\tC -> Add a course");
        System.out.println("\tD -> Go back to your academic history");
    }

    private void displayCourseOptions() {
        System.out.println("\nWould you like to add a component to this course?");
        System.out.println("\tE -> Add a component");
        System.out.println("\tF -> Go back to academic history");
    }

    private void displayAssignmentOptions() {
        System.out.println("\nWould you like to add an assignment to this component?");
        System.out.println("\tG -> Add an assignment");
        System.out.println("\tH -> Go back to your academic history");
    }

    private void process(String s) {
        if (s.equals("A")) {
            addTerm();
        } else if (s.equals("C")) {
            addCourse();
        } else if (s.equals("E")) {
            addComponent();
        } else if (s.equals("G")) {
            addAssignment();
        }
    }

    private void addTerm() {
        System.out.println("What would you like to call this new term?");
        input = new Scanner(System.in);
        String temp = input.nextLine();
        Term term = new Term(temp);
        ah.addTerm(term);
        System.out.println("A new term has been added!");
        displayTermOptions();

        input = new Scanner(System.in);
        String key = input.next();
        key = key.toUpperCase();

        if (key.equals("C")) {
            process(key);
        }
    }

    private void addCourse() {
        System.out.println("Which term would you like to add this course to?");
        input = new Scanner(System.in);
        String temp = input.nextLine();

        Term term = getTerm(temp);

        System.out.println("What's the name of this course?");
        String temp3 = input.nextLine();
        temp3 = temp3.toLowerCase();
        Course course = new Course(temp3);
        term.addCourse(course);
        System.out.println("The course has been added!");
        displayCourseOptions();

        String key = toUpperCase(input.next());

        if (key.equals("E")) {
            process(key);
        }
    }

    private void addComponent() {
        System.out.println("In which term is this does this component belong to?");
        input = new Scanner(System.in);
        String temp = input.nextLine();

        Term term = getTerm(temp);
        ArrayList<Course> courses = term.getListOfCourse();

        System.out.println("Which course is this component for?");
        String courseName = toLowerCase(input.nextLine());

        Course course = getCourse(courseName, courses);

        System.out.println("What's the name of this component?");
        String componentName = input.nextLine();

        System.out.println("What's the weight of this component? (In percentage)");
        double weight = input.nextDouble();

        Component component = new Component(weight,componentName);
        course.addComponent(component);
        System.out.println("Component has been added!");
        displayAssignmentOptions();
        String temp4 = toUpperCase(input.next());

        if (temp4.equals("G")) {
            process(temp4);
        }
    }

    private void addAssignment() {
        System.out.println("In which term is this does this assignment belong to?");
        input = new Scanner(System.in);
        String temp = input.nextLine();

        Term term = getTerm(temp);
        ArrayList<Course> courses = term.getListOfCourse();

        System.out.println("Which course is this assignment for?");
        String courseName = toLowerCase(input.nextLine());

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
        component.addAssignment(assignment);
        System.out.println("Assignment has been added!");
        System.out.println("Returning back to your Academic History");
    }

    private Component getComponent(String componentName, ArrayList<Component> components) {
        ArrayList<String> temp = new ArrayList<>();

        for (Component c: components) {
            String name = c.getComponentName();
            temp.add(name);
        }

        int index = temp.indexOf(componentName);
        return components.get(index);
    }

    private Course getCourse(String courseName, ArrayList<Course> courses) {
        ArrayList<String> temp = new ArrayList<>();

        for (Course c: courses) {
            String name = c.getCourseName();
            temp.add(name);
        }

        int index = temp.indexOf(courseName);
        return courses.get(index);
    }

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
