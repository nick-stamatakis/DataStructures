/**
 * This class represents a router in the network, which is ultimately a queue.
 *
 * @author Nicholas Stamatakis
 */
import java.util.ArrayList;

public class Router<Packet> extends ArrayList<Packet>{
    //throw exception if router is full
    //Constructors

    /**
     *Consurtuctor for Router
     */
    public Router(){

    }

    //Queue Functionality Methods (Not Including Ones Already in ArrayList)
    //i.e. size() and isEmpty() are inherited from the ArrayList Class

    /**
     * Uses Arraylist to implement enqueue
     * @param p
     * inputs Packet at the end of the Arraylist
     */
    public void enqueue(Packet p){
        this.add(p);
    }

    /**
     * Uses Arraylist to implement dequeue
     * @return
     * Packet removed from the front of the ArrayList
     */
    public Packet dequeue(){
        Packet firstIndex = this.get(0);
        this.remove(0);
        return firstIndex;
    }

    /**
     * Uses Arraylist to implement peek
     * @return
     * Packet returned from front-most index of ArrayList
     */
    public Packet peek(){
        if (this.size() > 0){
            return this.get(0);
        }
        return null;
    }

    /**
     *toString method for Router Class
     * @return
     * Formatted String with all the Packets in the Router
     */
    @Override
    public String toString(){
        if (this.size() == 0){
            return "{}";
        }
        StringBuilder routers = new StringBuilder("{");
        for (int i = 0; i < this.size(); i++){
            routers.append(this.get(i)).append(", ");
        }
        if (this.size() > 0){
            routers = new StringBuilder(routers.substring(0, routers.length() - 2));
        }
        routers.append("}");
        return routers.toString();
    }

    /**
     * Loops through the list Intermediate routers
     * Finds the router with the most free buffer space (contains the least Packets)
     * @param routers
     * ArrayList
     * @return
     * Returns the index of the router.
     * If there are multiple routers, any corresponding indices will be acceptable.
     * @throws FullRouterBuffersException
     * If all router buffers are full, this exception is thrown.
     */
    public static int sendPacketTo(ArrayList<Router> routers, Simulator simulator) throws FullRouterBuffersException {
        //Ask what to do in the case that multiple routers have the same amount of free space
        if (routers.size() > 0) {
            Router leastPackets = routers.get(0);//indices start at 1 for this project
            int leastIndex = 0;
            for (int i = 1; i < routers.size(); i++) {
                if (routers.get(i).size() < leastPackets.size()) {
                    leastPackets = routers.get(i);
                    leastIndex = i;
                }
            }
            if (leastPackets.size() == simulator.getMaxBufferSize()) {
                throw new FullRouterBuffersException();
            } else {
                return leastIndex;
            }
        }
        return -1;
    }
}
