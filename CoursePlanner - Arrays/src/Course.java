/**
 * This class represents a course which has a course name, department, instructor, code, and section
 * @author Nicholas Stamatakis
 */

public class Course {

    /**
     * Data fields for each Course object
     */
    private String courseName;
    private String department;
    private int code;
    private byte section;
    private String instructor;


    /**
     * Getter method for courseName data field in Course.
     *
     * @return
     * Current value of given courseName.
     */
    public String getCourseName(){
        return courseName;
    }

    /**
     * Getter method for department data field in Course.
     *
     * @return
     * Current value of given department.
     */

    public String getDepartment(){
        return department;
    }

    /**
     * Getter method for code data field in Course.
     *
     * @return
     * Current value of given code.
     */
    public int getCode(){
        return code;
    }

    /**
     * Getter method for section data field in Course.
     *
     * @return
     * Current value of given section.
     */
    public byte getSection(){
        return section;
    }

    /**
     * Getter method for instructor data field in Course.
     *
     * @return
     * Current value of given instructor.
     */
    public String getInstructor(){
        return instructor;
    }

    /**
     * Setter methods for courseName data field.
     * @param courseName
     * Changes value of courseName to the parameter of the method.
     */
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    /**
     * Setter methods for department data field.
     * @param department
     * Changes value of courseName to the parameter of the method.
     */
    public void setDepartment(String department){
        this.department = department;
    }

    /**
     * This is the mutator method for the data field: code
     *
     * @param code
     * The code of the specific course
     *
     * @custom.precondition
     * code must not be negative
     *
     * @throws IllegalArgumentException
     * when the number is negative
     *
     */
    public void setCode(int code){
        if (code < 0) {
            throw new IllegalArgumentException("Please do not enter a negative value.");
        }
        else{
            this.code = code;
        }
    }

    /**
     * This is the mutator method for the data field: section
     *
     * @param section
     * The code of the specific course
     *
     * @custom.precondition
     * code must not be negative
     *
     * @throws IllegalArgumentException
     * when the number is negative
     */
    public void setSection(byte section){
        if (section < 0) {
            throw new IllegalArgumentException("Please do not enter a negative value.");
        }
        else{
            this.section = section;
        }
    }

    /**
     * Setter methods for instructor data field.
     * @param instructor
     * Changes value of courseName to the parameter of the method.
     */
    public void setInstructor(String instructor){
        this.instructor = instructor;
    }

    /**
     * This is the no-arg constructor method for the Course class
     */

    public Course(){
        courseName = "";
        department = "";
        code = 0;
        section = 0;
        instructor = "";
    }

    /**
     * This is the arg-constructor method
     *
     * @param courseName
     * The name of the course
     *
     * @param department
     * The name of the department
     *
     * @param code
     * The code of the course
     *
     * @param section
     * The number of the section
     *
     * @param instructor
     * The name of the instructor
     *
     */
    public Course(String courseName, String department, int code, byte section, String instructor){
        this.courseName = courseName;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }

    /**
     * This method create a copy of the data fields from a given object
     *
     * @return
     * Object of the same data fields as the base object
     *
     */
    @Override
    public Object clone(){
        Course clone = new Course(this.courseName, this.department, this.code, this.section, this.instructor);
        return (Course)clone;
    }


    /**
     * This method checks if two objects are the same not based on addresses,
     * but on the contents of their specific data fields.
     *
     * @param obj
     * Given object
     *
     * @return
     * boolean value if data fields of said object are equivalent to a base object
     *
     */
    public boolean equals(Object obj){
        if(obj instanceof Course c){
            return this.courseName.equals(c.courseName)
                    && this.department.equals(c.department)
                    && this.code == c.code
                    && this.section == c.section
                    && this.instructor.equals(c.instructor);
        }
        return false;
    }

    @Override
    public String toString() {
        String newString = "";
        newString += "1" + " " + this.getCourseName();

        for (int i = 1; i <= (30 - this.getCourseName().length()); i++) {
            newString += " ";
        }
        newString += this.getDepartment() + "         " +
               this.getCode() + "      " + "0" + this.getSection() + " "
                + this.getInstructor() + "\n ";
        return newString;
    }
}
