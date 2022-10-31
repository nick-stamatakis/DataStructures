import javax.print.attribute.standard.Destination;
import java.util.ArrayList;

public class Testing {
    public static void main(String[] args) {
        //Packet Class test Cases
//        Packet packet1 = new Packet(1, 2, 3, 4);
//        Packet packet2 = new Packet(5,6,7,8);
//        Packet packet3 = new Packet(0,1,2,3);
//        packet1.setId(18);
//        packet1.setPacketSize(7);
//        packet1.setTimeArrive(11);
//        packet1.setTimeToDest(12);
//        System.out.println(packet1.getId());
//        System.out.println(packet1.getPacketSize());
//        System.out.println(packet1.getTimeArrive());
//        System.out.println(packet1.getTimeToDest());
//        System.out.println(packet1.toString());

        //Test Cases for Router Class
//        System.out.println("Empty Router: ");
//        Router router = new Router();
//        System.out.println(router);
//        System.out.println(router.isEmpty());
//        System.out.println("Size " + router.size());
//        System.out.println("Peek " + router.peek());
//        System.out.println("2 Router: ");
//        router.enqueue(packet1);
//        router.enqueue(packet1);
//        System.out.println(router);
//        System.out.println(router.isEmpty());
//        System.out.println("Size " + router.size());
//        System.out.println("Peek " + router.peek());
//        System.out.println("1 Router: ");
//        router.dequeue();
//        System.out.println(router);
//        System.out.println(router.isEmpty());
//        System.out.println("Size " + router.size());
//        System.out.println("Peek " + router.peek());

        //Test Cases for sendPacketTo
        Packet packet1 = new Packet(1, 2, 3, 4);
        Packet packet2 = new Packet(5,6,7,8);
        Packet packet3 = new Packet(0,1,2,3);
//
        Router router1 = new Router();
        Router router2 = new Router();
        router2.enqueue(packet1);

        Router router3 = new Router();
        router3.enqueue(packet1);
        router3.enqueue(packet2);
//
        Router router4 = new Router();
        router4.enqueue(packet1);
        router4.enqueue(packet2);
        router4.enqueue(packet3);
//
        ArrayList<Router> routers = new ArrayList<Router>();
        routers.add(router4);
        routers.add(router4);
        routers.add(router4);
        routers.add(router4);
//        routers.get(0).enqueue(packet1);
//        routers.get(0).enqueue(packet2);
//        System.out.println(router1.toString());

//        try {
//            System.out.println(Router.sendPacketTo(routers, simulator));
//        }
//        catch(FullRouterBuffersException ex){
//            System.out.println("All routers are full, Packets cannot be sent at this time.");
//        }
//        Simulator sim1  = new Simulator();
//        for (int i = 0; i <25; i++) {
//            System.out.println(sim1.randInt(1, 6));
//        }
        System.out.println((730 / 100));

    }
//    public void nToOne(int n){
//        if (n == 0) return;
//        System.out.println(n);
//        nToOne(n-1);
//    }
//    //
//    public int pow(int x, int y){
//        if(y == 1) return x;
//        if (y == 0) return 1;
//
//        //recursive call
//        return x * pow(x,y-1);
//    }
//
//    //Fibonacci sequence
//    public int fib(int n){
//        if (n <= 1) return n;
//
//        //Recursive call
//        int fst = fib(n-1);
//        int snd = fib(n-2);
//        return fst + snd;
//    }
//    //2^n time complexity
//
//    public int Count(String s){
//        if (s.length() == 0) return 0;
//        int count = 0;
//        return (s.charAt(0) == '1') ? (1 + Count(s.substring(1))) : Count(s.substring(1)))
//    }
//
//    public String reverseString(String s){
//        if(s.length() == 0) return s;
//
//        //Recursive call
//        return reverseString(s.substring(1)) + s.charAt(0);
//    }
//
//    public LinkedListNode nthElement(Linked List l){
//
//        if (l.size() == 0) return node.getValue;
//        return nthElement(node.getNext(), n-1)
//    }
//
//    public int length(LinkedListNode node){
//        if (node == null) return 0;
//
//        return 1+ length(node.next);
//    }
//
//    public void removeConsec(LinkedListNode node){
//        if (node == null) return;
//
//        if(node.getValue() == node.getNext().getValue()){
//            removeConsec(node.getNext());
//
//        }
//    }
//
//    public int sum(int n, int acc){
//        if (n <= 1) return n;
//
//        //Recursive call
//        return n + sum(n - 1, acc + n);
//        //return n + sum(n-1);
//    }
//
//    public int binarySearch(int[] nums, int target, int left, int right){
//        if (right - left <= 0){
//            return -1;
//        }
//
//        int mid = (right - left) / 2;
//        //DID we find the result?
//
//        if (nums[mid] == target) return mid;
//
//        if (nums[mid] < target){
//            //Search the right subarray
//            return binarySearch(nums, target, mid + 1, right);
//        }
//        else{
//            //Search the left subarray
//            return binarySearch(nums, target, left, mid -1)
//        }
//
//    }
//for (Router<Packet> perRouter : routers){
//        Packet front = perRouter.peek();
//        if (front != null) {
//            if (!(packetSentToDestination == simulator.bandwidth && front.getTimeArrive() == 1) &&
//                    (front.getTimeArrive() != simulationTime)) {
//                front.setTimeToDest(front.getTimeToDest() - 1);
//            }
//        }
//    }
//public void sendToDestination(ArrayList<Router> routers, Router Destination, Simulator simulator, int simulationTime) {
//    for (Router<Packet> perRouter : routers) {//for each Router in routers
//        Packet front = perRouter.peek();
//        if (front != null) {
//            for (Packet perPacket : perRouter) {//for each Packet in each Router
//                if (perPacket.getTimeToDest() == 0 && Packet.packetCount < simulator.bandwidth) {
//                    //Increment num packets sent to destination
//                    simulator.totalPacketsArrived++;
//                    //Increment total service time
//                    simulator.totalServiceTime += (simulationTime - front.getTimeArrive());
//                    //Print message the Packet has reached destination
//                    System.out.println("Packet " + front.getId() + "has successfully reached its destination " +
//                            "+" + (simulationTime - perPacket.getTimeArrive()));
//                    perRouter.dequeue();
//                }
//            }
//        }
//    }
//}

}

