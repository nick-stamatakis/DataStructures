import java.util.Scanner;

/**
 * This class creates a user menu to execute functions defined in previous classes
 * @author Nicholas Stamatakis
 */

public class PlannerManager {

    /**
     * Planner Object to add Courses to
     */
    Planner Planner1 = new Planner();
    Planner backupPlanner = new Planner();


    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        //Planner Manager instance created to call designated methods
        PlannerManager PM1 = new PlannerManager();

        Scanner in = new Scanner(System.in);

        System.out.println("Here is your Planner for your Courses this semester:");
        String userInput = "";
        System.out.println("A - Add Course <Name> <Code> <Section> <Instructor> <Position>" +
                "\n G - Get Course <Position>\n R - Remove Course <Position>\n" +
                "P - Print Courses in Planner\n F - Filter By Department Code <Code>\n" +
                "L - Look For Course <Name> <Code> <Section> <Instructor>\n S - Size" +
                "\n B - Backup\n PB - Print Courses in Backup\n " +
                "RB - Revert to Backup\n Q - Quit\n");

        /**
         * Runs until input is Q or q
         */
        while (userInput.equals("Q") == false && in.hasNext()) {
            userInput = "";
            System.out.println("Enter a selection:");
            System.out.println("A - Add Course <Name> <Code> <Section> <Instructor> <Position>" +
                    "\n G - Get Course <Position>\n R - Remove Course <Position>\n" +
                    "P - Print Courses in Planner\n F - Filter By Department Code <Code>\n" +
                    "L - Look For Course <Name> <Code> <Section> <Instructor>\n S - Size" +
                    "\n B - Backup\n PB - Print Courses in Backup\n " +
                    "RB - Revert to Backup\n Q - Quit\n");

            userInput = in.nextLine();
            System.out.println();
            userInput = userInput.toUpperCase();

            //try catch block to catch IllegalArgumentException
            try {
                //Switch statement to determine which method is called
                switch(userInput){
                    case "A":
                        PM1.A();
                        break;
                    case "G":
                        PM1.G();
                        break;

                    case "R":
                        PM1.R();
                        break;

                    case "P":
                        PM1.P();
                        break;

                    case "F":
                        PM1.F();
                        break;

                    case "L":
                        PM1.L();
                        break;

                    case "S":
                        PM1.S();
                        break;

                    case "B":
                        PM1.B();
                        break;

                    case "PB":
                        PM1.PB();
                        break;

                    case "RB":
                        PM1.RB();
                        break;

                    case "Q":
                        PM1.Q();
                        in.close();
                        break;

                    default:
                        throw new IllegalArgumentException("Please enter a valid input.");
                }
                System.out.println("A - Add Course <Name> <Code> <Section> <Instructor> <Position>" +
                        "\n G - Get Course <Position>\n R - Remove Course <Position>\n" +
                        "P - Print Courses in Planner\n F - Filter By Department Code <Code>\n" +
                        "L - Look For Course <Name> <Code> <Section> <Instructor>\n S - Size" +
                        "\n B - Backup\n PB - Print Courses in Backup\n " +
                        "RB - Revert to Backup\n Q - Quit\n");

            }
            catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid input from the menu.");
            }

        }
        in.close();
    }

    /**
     * Adds a new course into the list.
     */
    public void A(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter course name:");
        String courseName = in.nextLine();

        System.out.println("Enter department:");
        String department = in.nextLine();

        System.out.println("Enter course code:");
        int code = Integer.parseInt(in.nextLine());

        System.out.println("Enter course section:");
        byte section = Byte.parseByte(in.nextLine());

        System.out.println("Enter instructor:");
        String instructor = in.nextLine();

        Course course = new Course(courseName, department, code, section, instructor);

        System.out.println("Enter position:");
        int position = Integer.parseInt(in.nextLine());

        Planner1.addCourse(course, position);

        System.out.println(courseName + " successfully added to planner.");


    }

    /**
     * Displays information of a Course at the given position.
     */
    public void G(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a position");
        int position = in.nextInt();
        Course course1 = Planner1.getCourse(position);
        if (course1 == null) {
            System.out.println("No course has been added at this position");
        }
         else {
            System.out.println(course1.toString());
        }
    }

    /**
     * Removes the Course at the given position.
     */
    public void R(){
        Scanner in = new Scanner (System.in);
        System.out.println("Enter a position");
        int position = in.nextInt();
        Planner1.removeCourse(position);
    }

    /**
     * Displays all courses in the list
     */
    public void P(){
        Planner1.printAllCourses();
    }

    /**
     * Displays courses that match the given department code.
     */
    public void F(){
        System.out.println("Enter a department code: ");
        Scanner in = new Scanner(System.in);
        String department = in.nextLine();
        Planner.filter(Planner1, department);
    }

    /**
     *Determines whether the course with the given attributes is in the list.
     */
    public void L(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter course name:");
        String courseName = in.nextLine();

        System.out.println("Enter department:");
        String department = in.nextLine();

        System.out.println("Enter course code:");
        int code = Integer.parseInt(in.nextLine());

        System.out.println("Enter course section:");
        byte section = Byte.parseByte(in.nextLine());

        System.out.println("Enter instructor:");
        String instructor = in.nextLine();

        Course course = new Course(courseName, department, code, section, instructor);
        Planner1.exists(course);
    }

    /**
     * Determines the number of courses in the Planner.
     */
    public void S(){
        int size = Planner1.size();
        System.out.println("There are " + size + " courses in the planner.\n");
    }

    /**
     *Creates a copy of the given Planner.
     *Changes to the copy will not affect the original and vice versa.
     */
    public void B(){
        backupPlanner = (Planner)Planner1.clone();
        System.out.println("Created a backup of the current planner.\n");
    }

    /**
     * Displays all the courses from the backed-up list.
     */
    public void PB(){
        backupPlanner.printAllCourses();
    }

    /**
     * Reverts the current Planner to the backed up copy.
     */
    public void RB(){
        Planner1 = (Planner)backupPlanner.clone();
        System.out.println("Planner successfully reverted to the backup copy.\n");
    }

    /**
     * Terminates the program.
     */
    public void Q(){
        System.out.println("Program terminating successfully...");
    }
}

