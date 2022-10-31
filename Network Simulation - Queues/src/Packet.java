/**
 * This class represents a packet that will be sent through the network.
 *
 * @author Nicholas Stamatakis
 */
public class Packet {

    //Static Variables
    /**
     *  this value is used to assign an id to a newly created packet.
     *  It will start with the value 0, and every time a new packet
     *  object is created, increment this counter and assign the value
     *  as the id of the Packet.
     */
    private static int packetCount = 0;

    /**
     * Getter for packetCount
     * @return
     */
    public static int getPacketCount() {
        return packetCount;
    }

    /**
     * Setter for packetCount
     * @param packetCount
     */
    public static void setPacketCount(int packetCount) {
        Packet.packetCount = packetCount;
    }
    //Member variable of Packet Class
    /**
     * a unique identifier for the packet.
     * This will be systematically determined by using packetCount.
     */
    private int id;

    /**
     * the size of the packet being sent.
     * This value is randomly determined by the simulator by using the Math.random() method.
     */
    private int packetSize;

    /**
     * the time this Packet is created should be recorded in this variable
     */
    private int timeArrive;

    /**
     * this variable contains the number of simulation units that it takes for a packet
     * to arrive at the destination router. The value will start at one hundredth of the packet size,
     * that is: packetSize/100. At every simulation time unit, this counter will decrease.
     * Once it reaches 0, we can assume that the packet has arrived at the destination.
     */
    private int timeToDest;

    //Constructors

    /**
     * No Arg Constructor for Packet Class
     */
    public Packet(){
        packetCount++;
        id = packetCount;
        packetSize = 0;
        timeArrive = 0;
        timeToDest = 0;
    }

    /**
     * Arg Constructor for Packet Class
     * @param id1
     * id inputted into method signature
     * @param packetSize1
     * packetSize inputted into method signature
     * @param timeArrive1
     * timeArrival inputted into method signature
     * @param timeToDest1
     * timeToDest inputted into method signature
     */
    public Packet(int id1, int packetSize1, int timeArrive1, int timeToDest1){
        id = id1;
        packetSize = packetSize1;
        timeArrive = timeArrive1;
        timeToDest = timeToDest1;
    }

    //Getters/Setters

    /**
     * Getter for id
     * @return
     * Current value of id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Setter for id
     * @param id1
     * Sets value of id to inputted value in method signature
     */
    public void setId(int id1){
        this.id = id1;
    }

    /**
     * Getter for packetSize
     * @return
     * Current value of packetSize
     */
    public int getPacketSize(){
        return this.packetSize;
    }

    /**
     * Setter for packetSize
     * @param packetSize1
     * Sets value of packetSize to inputted value in method signature
     */
    public void setPacketSize(int packetSize1){
        this.packetSize = packetSize1;
    }

    /**
     * Getter for timeArrive
     * @return
     * Current value of timeArrive
     */
    public int getTimeArrive(){
        return this.timeArrive;
    }

    /**
     * Setter for timeArrive
     * @param timeArrive1
     * Sets value of timeArrive to inputted value in method signature
     */
    public void setTimeArrive(int timeArrive1){
        this.timeArrive = timeArrive1;
    }

    /**
     * Getter for timeToDest
     * @return
     * Current value of timeToDest
     */
    public int getTimeToDest(){
        return this.timeToDest;
    }

    /**
     * Setter for timeToDest
     * @param timeToDest1
     * Sets value of timeToDest to inputted value in method signature
     */
    public void setTimeToDest(int timeToDest1){
        this.timeToDest = timeToDest1;
    }

    //toString
    /**
     * Overrides toString method of Object Class
     * @return
     * Formatted String with id, timeArrive, and timeToDest data fields.
     */
    @Override
    public String toString(){
        return "[" + id + ", " + timeArrive + ", " + timeToDest + "]";
    }

}


