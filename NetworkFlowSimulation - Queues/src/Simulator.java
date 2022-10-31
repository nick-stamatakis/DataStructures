/**
 * This class contains the main method that tests my simulation.
 *
 * @author Nicholas Stamatakis
 */

/**
 * Imports needed
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulator Class
 */
public class Simulator {
    /**
     * Simulator Object used throughout simulation
     */
    private static Simulator simulator = new Simulator();

    /**
     * Simulator Constructor used to reset Simulator Object after every simulation
     */
    public Simulator(){
        Router<Packet> dispatcher = new Router();
        ArrayList<Router> routers = new ArrayList<>();
        totalServiceTime = 0;
        totalPacketsArrived = 0;
        packetsDropped = 0;
        arrivalProb = 0;
        numIntRouters = 0;
        maxBufferSize = 0;
        minPacketSize = 0;
        maxPacketSize = 0;
        bandwidth = 0;
        duration = 0;
        numPacketsInSimulation = 0;
        Packet.setPacketCount(0);
    }
    //Member Variables
    /**
     * Level 1 router
     */
    private Router<Packet> dispatcher = new Router();

    /**
     * Level 2 routers
     */
    private ArrayList<Router> routers = new ArrayList<>();


    /**
     * Contains the running sum of the total time each packet is in the network.
     * The service time per packet is simply the time it has arrived to the Destination
     * minus the time when the packet was created.
     * When a packet counter reaches 0,
     * dequeue it from the router queue and add the time to the total time.
     * Ignore the leftover Packets in the network when simulation time is up.
     */
    private int totalServiceTime;

    /**
     * contains the total number of packets that has been successfully forwarded to the destination.
     * When a packet counter reaches 0, dequeue it from the router queue and increase this count by 1.
     */
    private int totalPacketsArrived;

    /**
     * Records the number of packets that have been dropped due to a congested network.
     * Note: this can only happen when sendPacketTo(Collection routers) throws an exception.
     */
    private int packetsDropped;

    /**
     * The probability of a new packet arriving at the Dispatcher.
     */
    private double arrivalProb;

    /**
     * The number of Intermediate routers in the network.
     */
    private int numIntRouters;

    /**
     * The maximum number of Packets a Router can accommodate for.
     */
    private int maxBufferSize;

    /**
     * Getter for Max_Buffer_Size
     * @return
     * returns current maxBufferSize
     */
    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    /**
     * Setter for Max_Buffer_size
     * @param maxBufferSize
     * Changes maxBufferSize to the method signature
     */
    public void setMaxBufferSize(int maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
    }

    /**
     * The minimum size of a Packet
     */
    private int minPacketSize;

    /**
     * The maximum size of a Packet
     */
    private int maxPacketSize;

    /**
     * The maximum number of Packets the Destination router can accept at a given simulation unit
     */
    private int bandwidth;

    /**
     * The number of simulation units
     */
    private int duration;

    //Static Variable
    /**
     * Max number of packets that can be at a singular router.
     */
    public static int MAX_PACKETS = 3;

    /**
     * Number of packets in each simulation unit
     */
    public static int numPacketsInSimulation = 0;

    //Methods
    /**
     * Runs the simulator as described in the specs.
     * @param simulator
     * Object needed to access data fields in simulator class
     * @return
     * Calculate and return the average time each packet spends within the network.
     */
    public double simulate(Simulator simulator){
        for(int simulationTime = 1; simulationTime <= duration; simulationTime++) {
            //Sets Current time in Simulation unit
            System.out.println("Time: " + simulationTime);

            //Case 1:
            // Decide whether packets have arrived at the Dispatcher.
            // A maximum of 3 can arrive at a given time.
            addToDispatcher(simulator, dispatcher, simulationTime, 0);

            //Case 2:
            //If the Dispatcher contains unsent packets, send them off to one of the Intermediate routers.
            // You will write the method sendPacketTo(Collection intRouters)
            // to decide which router the packet should be forwarded to.
            dispatcherSend(dispatcher, routers, simulator);

            //Case 3:
            //If any packets are ready to be forwarded to the Destination router, do so.
            //Case 4:
            //Once a packet has arrived at the Destination router,
            //take note of its arrival by recording the total time in the network.
            // (Be careful of the bandwidth.)
            sendToDestination(routers, simulator, simulationTime);

            //Tracks Number of Router throughout simulation
            int routerNum = 1;
            for (Router eachRouter : routers) {
                //Prints out Intermediate Routers
                System.out.println("R" + (routerNum++) + ": " + eachRouter.toString());
            }
            System.out.println();

            //Case 5:
            //Decrement all packets counters in the beginning of the queue at each Intermediate router.
            decrementBegPackets(routers, numPacketsInSimulation);
        }

        //Print statements at end of simulation
        System.out.println();
        System.out.println("Simulation ending...");
        System.out.println("Total service time: " + simulator.totalServiceTime);
        System.out.println("Total packets served: " + simulator.totalPacketsArrived);
        if(simulator.totalPacketsArrived > 0) {
            System.out.println("Average service time per packet: " +
                    ((double) simulator.totalServiceTime / (double) simulator.totalPacketsArrived));
        }
        System.out.println("Total packets dropped: " + simulator.packetsDropped);
        System.out.println("\n");
        //return average time
        if (simulator.totalPacketsArrived > 0){
            return ((double)simulator.totalServiceTime / (double)simulator.totalPacketsArrived);
        }
        return 0;
    }

    /**
     * Helper method that can generate a random number between minVal and maxVal, inclusively.
     * @param minVal
     * Low end of Range
     * @param maxVal
     * High end of Range
     * @return
     * Return that randomly generated number.
     */
    public int randInt(int minVal, int maxVal){//change to private
        if (maxVal < minVal){
            throw new IllegalArgumentException("maxVal must be greater than or equal to minVal");
        }
        return (int) ((Math.random() * (maxVal - minVal + 1)) + minVal);
    }

    //Static Methods
    /**
     * main() method will prompt the user for inputs to the simulator.
     * It will then run the simulator, and outputs the result.
     * @param args
     * An array of Strings
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        char answer = 'y';
        do {
            simulator = new Simulator();
            try {
                simulator.askUserQuestions();

                System.out.print("Do you want to try another simulation? (y/n): ");
                String answerString = in.next();
                answer = answerString.toLowerCase().charAt(0);
                if (answer != 'y' || answer != 'n'){
                    throw new IllegalArgumentException();
                }
            }
            catch (IllegalArgumentException ex){
                System.out.println("\nThis is an invalid input. Please try again.\n");
            }
        }
        while (answer == 'y');

        System.out.println("Program terminating successfully...");
    }

    /**
     * Asks user questions to enable simulation run.
     */
    public void askUserQuestions(){
        Scanner in = new Scanner(System.in);
        System.out.println("Starting simulator...\n");

        System.out.print("Enter the number of Intermediate routers: ");
        simulator.numIntRouters = in.nextInt();
        if (simulator.numIntRouters < 0){
            throw new IllegalArgumentException();
        }
        System.out.println();

        System.out.print("Enter the arrival probability of a packet: ");
        simulator.arrivalProb = in.nextDouble();
        if (simulator.arrivalProb < 0 || simulator.arrivalProb > 1){
            throw new IllegalArgumentException();
        }
        System.out.println();

        System.out.print("Enter the maximum buffer size of a router: ");
        simulator.maxBufferSize = in.nextInt();
        if (simulator.maxBufferSize < 0){
            throw new IllegalArgumentException();
        }
        System.out.println();

        System.out.print("Enter the minimum size of a packet: ");
        simulator.minPacketSize = in.nextInt();
        if (simulator.minPacketSize < 100){
            throw new IllegalArgumentException();
        }
        System.out.println();

        System.out.print("Enter the maximum size of a packet: ");
        simulator.maxPacketSize = in.nextInt();
        if (simulator.maxPacketSize < 100){
            throw new IllegalArgumentException();
        }
        System.out.println();

        System.out.print("Enter the bandwidth size: ");
        simulator.bandwidth = in.nextInt();
        if (simulator.bandwidth < 0){
            throw new IllegalArgumentException();
        }
        System.out.println();

        System.out.print("Enter the simulation duration: ");
        simulator.duration = in.nextInt();
        if (simulator.duration < 0){
            throw new IllegalArgumentException();
        }
        System.out.println();

        //Adds number of intermediate routers to simulator
        for (int i = 0; i < simulator.numIntRouters; i++){
            Router newRouter = new Router();
            simulator.routers.add(newRouter);
        }

        simulator.simulate(simulator);

        System.out.println();
    }

    /**
     * Adds Packets to Dispatcher
     * @param simulator
     * Simulator object
     * @param dispatcher
     * dispatcher (Router) Object
     * @param simulationTime
     * Current simulationTime
     * @param numPacketsInSimulation
     * Number of Packets added so far in simulation unit
     */
    public void addToDispatcher(Simulator simulator, Router<Packet> dispatcher, int simulationTime,
                                int numPacketsInSimulation){
        //checks if packets is below limit and > 0
        if (!(numPacketsInSimulation >= MAX_PACKETS) && !(numPacketsInSimulation < 0)
                && !(dispatcher.size() > simulator.maxBufferSize)){
            for (int i = 0; i < MAX_PACKETS; i++) {//Tries to add packets 3 times
                if (Math.random() < simulator.arrivalProb) {
                    //Increments number of packets to set id of each packet correctly
                    //Packet.packetCount++;
                    Packet.setPacketCount(Packet.getPacketCount() + 1);
                    //Increments number of Packets Per Simulation
                    numPacketsInSimulation++;
                    //Finds size of each packet
                    int packetSize = simulator.randInt(simulator.minPacketSize, simulator.maxPacketSize);
                    //Creates new packet with Data Fields
                    Packet packet = new Packet();
                    packet.setId(Packet.getPacketCount());
                    packet.setPacketSize(packetSize);
                    packet.setTimeArrive(simulationTime);
                    packet.setTimeToDest(packetSize / 100);

                    //Prints formatted Packet
                    System.out.println("Packet " + Packet.getPacketCount() + " at dispatcher with size " + packetSize + ".");
                    //Enqueues Packet
                    dispatcher.enqueue(packet);
                }
            }
        }
        else if (numPacketsInSimulation == 0 ){
            System.out.println("No packets arrived.");
        }
    }

    /**
     * Sends Dispatcher to Correct Intermediate Router
     * @param dispatcher
     * dispatcher (Router) Object
     * @param routers
     * Arraylist of Routers
     * @param simulator
     * Simulator Object
     */
    public void dispatcherSend(Router<Packet> dispatcher, ArrayList<Router> routers, Simulator simulator) {
        for (int i = 0; i < MAX_PACKETS; i++) {
            try {
                int indexOfIntRouter = Router.sendPacketTo(routers, simulator);//Finds desired index to send packet
                if(dispatcher.size() >= 1){
                    Packet newPacket = dispatcher.dequeue();
                    routers.get(indexOfIntRouter).enqueue(newPacket);//Sends first packet to router
                    //Prints Packet and which index it was sent to
                    System.out.println("Packet " + newPacket.getId() + " sent to Router "
                            + (indexOfIntRouter + 1) + ".");
                }
            } catch (FullRouterBuffersException ex) {
                //Record num packets dropped due to congested network
                if (dispatcher.size() > 0) {
                    simulator.packetsDropped++;
                    Packet dropped = dispatcher.dequeue();
                    System.out.println("Network is congested. Packet " + dropped.getId() + " is dropped.");
                }
            }
        }
    }

    /**
     * Sends beginning Packets of Intermediate Routers to Destination
     * @param routers
     * Arraylist of Routers
     * @param numPacketsInSimulation
     * Number of Packets in the Simulation
     */
    public void decrementBegPackets(ArrayList<Router> routers,
                                            int numPacketsInSimulation){
        //for every router in routers
        for (int i = 0; i < routers.size(); i++){
            Packet front = (Packet)routers.get(i).peek();
            //Makes sure front is not null
            if (front != null) {
                //Decrements Counter at beginning of each intermediate Router
                if (!(numPacketsInSimulation >= simulator.bandwidth)) {
                    front.setTimeToDest(front.getTimeToDest() - 1);
                }
            }
        }
    }

    /**
     * Sends Packets from Intermediate Routers to destination
     * @param routers
     * Arraylist of routers
     * @param simulator
     * Simulator Object
     * @param simulationTime
     * Current time in simulation
     */
    public void sendToDestination(ArrayList<Router> routers, Simulator simulator, int simulationTime) {
        for (Router<Packet> perRouter : routers) {//for each Router in routers
            Packet front = perRouter.peek();
            if (front != null) {
                for (int j = 0; j < perRouter.size(); j++) {//for each Packet in each Router
                    if (perRouter.get(j).getTimeToDest() == 0 && numPacketsInSimulation < simulator.bandwidth) {
                        //Increment num packets sent to destination
                        simulator.totalPacketsArrived++;
                        //Increment total service time
                        simulator.totalServiceTime += (simulationTime - front.getTimeArrive());
                        //Print message the Packet has reached destination
                        System.out.println("Packet " + front.getId() + " has successfully reached its destination " +
                                "+" + (simulationTime - perRouter.get(j).getTimeArrive()));
                        perRouter.dequeue();
                    }
                }
            }
        }
    }

}
