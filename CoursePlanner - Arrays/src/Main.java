public class Main {
    /**
     *
     * @param args
     */
    // psvm
    // sout
    public static void main(String[] args) {
        Planner p1 = new Planner();
        Course c1 = new Course("Data Structures", "CSE", 214, (byte)1, "Ahmad Esmaili");
        Course c2 = new Course("Linear Algebra", "AMS", 210, (byte)2, "Alan Tucker");
        Course c3 = new Course("System Fundamentals", "CSE", 220, (byte)1, "Kevin McDonnell");
        Course c4 = new Course("Elements of Music", "MUS", 119, (byte)2, "Taylor Ackley");
        Course c5 = new Course("Intro to Obj Oriented Prog", "CSE", 114, (byte)1, "Praveen Tripathi");
        p1.addCourse(c1);
        p1.addCourse(c2);
        p1.addCourse (c3);
        p1.addCourse (c4,2);
        //p1.addCourse (c5,2);

        p1.removeCourse(2);
        p1.getCourse(2);
        System.out.println(p1.exists(c5));
        System.out.println(p1);
        System.out.println(p1.toString());
        p1.printAllCourses();
        Planner.filter(p1, "CSE");



//        p1.addCourse (c2, 2);
//        p1.addCourse (c3, 3);
//        p1.addCourse (c4, 4);
//        p1.addCourse (c5, 5);
//
//        System.out.println(p1);
//    }
}}
//        /**
//         * Adds a new course into the list.
//         */
//        System.out.println("A - Add Course <Name> <Code> <Section> <Instructor> <Position>");
//
//        /**
//         * Displays information of a Course at the given position.
//         */
//        System.out.println("G - Get Course <Position>");
//
//        /**
//         * Removes the Course at the given position.
//         */
//        System.out.println("R - Remove Course <Position>");
//
//        /**
//         * Displays all courses in the list.
//         */
//        System.out.println("P - Print Courses in Planner");
//
//        /**
//         * Displays courses that match the given department code.
//         */
//        System.out.println("F - Filter By Department Code <Code");
//
//        /**
//         * Determines whether the course with the given attributes is in the list.
//         */
//        System.out.println("L - Look For Course <Name> <Code> <Section> <Instructor>");
//
//        /**
//         * Determines the number of courses in the Planner.
//         */
//        System.out.println("S - Size");
//
//        /**
//         * Creates a copy of the given Planner.
//         * Changes to the copy will not affect the original and vice versa.
//         */
//        System.out.println("B - Backup");
//
//        /**
//         * Displays all the courses from the backed-up list.
//         */
//        System.out.println("PB - Print Courses in Backup");
//
//        /**
//         * Reverts the current Planner to the backed up copy.
//         */
//        System.out.println("RB - Revert to Backup");
//
//        /**
//         * Terminates the program.
//         */
//        System.out.println("Q - Quit");
