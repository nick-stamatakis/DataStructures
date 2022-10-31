/**
 * This class implements an abstract data type for a list of courses supporting some common operations on such as lists
 * @author Nicholas Stamatakis
 */


public class Planner {
    /**
     * Final data field sets the maximum number of 50 courses.
     */
    public static final int MAX_COURSES = 50;

    /**
     * Private courses data field which is an array that holds all of the Course objects created.
     */
    private Course[] courses;

    /**
     * Tracks the number of Courses within the Planner.
     */
    private int numCourses = 0;


    /**
     * This is the accessor method for the data field: Courses
     *
     * @return A Course[] array is returned in this method.
     */
    public Course[] getCourses() {
        return courses;
    }

    /**
     * This is the mutator method for the data field: Courses
     *
     * @return void
     */
    public void setCourses() {
        this.courses = courses;
    }

    /**
     * This is the no-arg constructor for Objects of the Planner Class
     *
     * @custom.postcondition The Planner object has been initialized to an empty list of courses.
     */
    public Planner() {
        courses = new Course[MAX_COURSES + 1];
    }

    /**
     * Size method is used to find how many values have been entered into the array
     *
     * @return the number of indeces in the array that have been added.
     * @custom.precondition The Planner being used has been intialized.
     */
    public int size() {
        return numCourses;
    }

    /**
     * Adds Course objects to Planner Class.
     *
     * @param newCourse new Course object
     * @param position  Integer that represents the target position of the new Course object
     * @throws IllegalArgumentException when the position is less than 1, the position above the Planner object size, or
     *                                  the newCourse is not instance of the Course class
     * @throws FullPlannerException     when the size of Planner object is greater than or equal to MAX_COURSES
     * @custom.precondition This Course object has been instantiated and 1 ≤ position ≤ items_currently_in_list + 1
     * The number of Course objects in this Planner is less than MAX_COURSES.
     * @custom.postcondition The new Course is now listed in the correct preference on the list.
     * All Courses that were originally greater than or equal to position are moved back one position.
     * <p>
     * Example: If there are 5 Courses in a Planner, positioned 1-5, and you insert a Course in position 4,
     * the new Course would be placed in position 4, the Course that was in position 4 will be
     * moved to position 5, and the Course that was in position 5 will be moved to position 6.
     */
    public void addCourse(Course newCourse, int position) {
        if (position > this.size() + 1 || position < 1) {
            throw new IllegalArgumentException("Please enter an item between 1 and items currently in list + 1.");
        }
        if (newCourse instanceof Course != true) {
            throw new IllegalArgumentException("Please add only Course objects to the Planner");
        }
        if (this.size() > MAX_COURSES) {
            throw new FullPlannerException("Please do not enter more than " + MAX_COURSES + "values");
        }
        //case where no courses have been added yet
        if (numCourses == 0) {
            courses[1] = newCourse;
        }
        //adding course to last part of array
        else if (numCourses + 1 == position) {
            courses[position] = newCourse;
        }
        //adding course in the middle of an array
        else {
            for (int i = numCourses + 1; i >= position; i--) {
                if (i > position) {
                    courses[i] = courses[i - 1];
                } else {
                    courses[i] = newCourse;
                }
            }
        }
        numCourses++;
        for (int i = 1; i <= numCourses; i++) {
            System.out.println(courses[i]);
        }
        System.out.println(numCourses);
        System.out.println("This Course has been added.");
    }

    /**
     * Adds courses to the end of Planar given Planar object has space.
     *
     * @param newCourse
     * @throws IllegalArgumentException the newCourse is not instance of the Course class
     * @throws FullPlannerException     when the size of Planner object is greater than or equal to MAX_COURSES
     * @custom.precondition This Course object has been instantiated.
     * The number of Course objects in this Planner is less than MAX_COURSES.
     * @custom.postcondition The new Course is now listed at the end of the list.
     */
    public void addCourse(Course newCourse) {
        if (newCourse instanceof Course != true) {
            throw new IllegalArgumentException("Please add only Course objects to the Planner");
        }
        if (this.size() >= MAX_COURSES) {
            throw new FullPlannerException("Please do not enter more than " + MAX_COURSES + "values");
        }
        this.addCourse(newCourse, numCourses + 1);
    }

    /**
     * Removes Course object from desired position of Planner.
     *
     * @param position
     * @throws IllegalArgumentException When the position is less than 1 or the position above the Planner object size.
     * @custom.precondition This Planner object has been instantiated and 1 ≤ position ≤ items_currently_in_list + 1
     * @custom.postcondition The Course object at the desired position has been removed.
     */
    public void removeCourse(int position) {
        if (position > this.size() + 1 || position < 1) {
            throw new IllegalArgumentException("Please enter an item between 1 and items currently in list + 1.");
        }

        //removing course from last part of array
        if (numCourses == position) {
            courses[position] = null;
        }
        //removing course in the middle of an array
        else {
            for (int i = 1; i <= numCourses; i++) {
                if (i == position) {
                    courses[position] = null;
                }
                if (i > position) {
                    courses[i - 1] = courses[i];
                }
                if (i == numCourses) {
                    courses[i] = null;
                }
            }
        }

        for (int i = 1; i <= numCourses; i++) {
            System.out.println(courses[i]);
        }
        System.out.println(numCourses);
        numCourses--;
        System.out.println(numCourses);
        System.out.println("This Course has been removed.");
    }

    /**
     * Retrieves Course at desired position in the Planner.
     *
     * @param position Desired position (integer) in Planner to retrieve Course Object
     * @return Course class object is returned if valid position is given.
     * @throws IllegalArgumentException When the position is less than 1 or the position above the Planner object size.
     * @custom.precondition This Planner object has been instantiated and 1 ≤ position ≤ items_currently_in_list + 1
     */
    public Course getCourse(int position) {
        if (position > this.size() + 1 || position < 1) {
            throw new IllegalArgumentException("Please enter an item between 1 and items currently in list + 1.");
        }
        Course requestedCourse = courses[position];
        return requestedCourse;
    }

    /**
     * Prints out all courses within a specific department
     *
     * @param planner    Planar object which you desire to print courses from.
     * @param department Department in which you want the courses in the Planner to come from.
     * @return void
     * @custom.precondition This Planner object has been instantiated.
     * @custom.postcondition Displays a neatly formatted table of each course filtered from the Planner.
     * Keep the preference numbers the same.
     */
    public static void filter(Planner planner, String department) {
        Course[] filteredCourses = new Course[MAX_COURSES + 1];
        for (int i = 1; i <= planner.numCourses; i++) {
            if (planner.getCourse(i).getDepartment().equals(department)) {
                filteredCourses[i] = planner.getCourse(i);
                System.out.println(filteredCourses[i].toString());
            }
        }
    }

    /**
     * Sees whether the inputted Course object is in the Planner.
     *
     * @param course Course object the user would like to check.
     * @return A boolean value. True if the Course object is in the Planner.
     * False if the Object is not in the Planner.
     * @custom.precondition The Planner and Course have both been instantiated.
     */
    public boolean exists(Course course) {
        if (course instanceof Course != true) {
            throw new IllegalArgumentException("Please add only Course objects to the Planner");
        }
        if (this instanceof Planner != true) {
            throw new IllegalArgumentException("Please add only Course objects to the Planner");
        }
        for (int i = 1; i <= numCourses; i++) {
            if (this.getCourse(i).equals(course)) {
                System.out.println(this.getCourse(i).getCourseName()
                        + " is found in the planner at position " + i);
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a copy of this Planner which does not affect the original.
     *
     * @return An object of the Planner class.
     * @custom.precondition The Planner has been instantiated.
     */
    public Object clone() {

        Planner p2 = new Planner();
        for (int i = 1; i <= numCourses; i++) {
            p2.addCourse((Course)courses[i].clone());
        }

        return p2;
    }


    /**
     * Prints all of the courses in the desired Planner
     *
     * @return void
     * @custom.precondition The Planner has been instantiated.
     * @custom.postcondition Displays a neatly formatted table of each course from the Planner.
     */
    public void printAllCourses() {
        System.out.println("No. Course Name               Department Code Section Instructor");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(this.toString());
    }

    /**
     * Will display a string representation which displays each Course
     * object in the Planner on its own line.
     *
     * @return String representation of each course in the Planner.
     */
    @Override
    public String toString() {
        String newString = "";
        for (int j = 1; j <= numCourses; j++) {
            newString += j + " " + courses[j].getCourseName();

            for (int i = 1; i <= 30 - (courses[j].getCourseName().length()); i++) {
                newString += " ";
            }
            newString += courses[j].getDepartment() + "         " +
                    courses[j].getCode() + "      " + "0" + courses[j].getSection() + " "
                    + courses[j].getInstructor() + "\n ";
        }

        return newString;
    }
}
