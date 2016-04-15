import com.sun.javafx.image.impl.IntArgb;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Main testing bed for the development of this Pairing Heap.
 *
 * Please note: This will fill out the terminal with a lot of data as it just runs the tests all at once.
 * To see earlier tests please just scroll up to see them.
 *
 * Tests: (BRIEF, please refer to java docs or the function headers for more detailed information!)
 *    1: Basic Test that checks corner cases out.
 *    2: Test that checks the values of merging two heaps.
 *    3: Run the PairingHeap against the Built in Java "PriorityQueue" class. Compares values.
 *    4: Large data input into PairingHeap and Java built in "PriorityQueue". Displays first 5 minimum values from each.
 *    5: Compare run times between built in Priority Queue and the Pairing Heap.
 *    6: Dijkstra's Algorithm
 */
public class Tests {
    public static void main(String args[]){
        //Basic test of staple input values being inserted into the PairingHeap and then the iterative
        //removal of the values. Here I try and probe corner cases!
        basicTest();

        //Let's try merging two separate pairing heaps together!
        mergeTwoHeapsTest();

        //Now run it against Java's built in heap and compare values!
        runAgainstJavaBuiltInHeap();

        //Add 100,000 nodes to the Pairing Heap and 100,000 values to the Java built in PQ. Expect same results fromeach
        runStressTest();

        //Add 10,000 nodes to the Pairing Heap and 10,000 values to the Java built in PQ.
        //This time I am going to be taking the literal time it takes to
        // 1: Insert the nodes
        // 2: Obtain the first 10 results through delete min.
        compareRunTimes();

        //Run Dijkstra's Algorithm on sample test data using the built in PQ and this Pairing Heap.
        //Expect similar results.
        dijkstraTest();

        //Now display closing message! :) Thanks!
        System.out.println("Thanks for running the tests :)!  (Sorry about the terminal flood...) ");
        System.out.println("If any tests didn't make sense, please refer to javadocs, README or comment headers!");
    }

    /**
     * Evokes a basic test on the PairingHeap where I attempt to probe corner cases.
     * Displays results in order of the node's value.
     *
     * Demonstrates:
     *   handles unordered input
     *   handles MAX_VALUE for Integers
     *   handles MIN_VALUE for Integers
     *   handles negative numbers
     *   handles 0
     *   handles input in random order.
     *   handles multiple instances of the same "value" of a node with different payloads.
     */
    public static void basicTest(){
        //State the test.
        System.out.println("******TEST 1: Basics and Corner Cases ");
        System.out.println("Expect to see values printed to the screen in ASCENDING order.");
        System.out.println("These values represent the deleteMin function of the Pairing Heap\n");

        //First, create the nodes for clarity's sake.
        //The first value in the constructor is the value of the node, which is what the pairingheap operates upon
        //The second value is the payload, in this instance I made it a String value of the number in english.
        Node nodeNegative191 = new Node(-191, "-191");
        Node node0 = new Node(0, "0");
        Node node1 = new Node(1, "1");
        Node node2 = new Node(2, "2");
        Node node3 = new Node(3, "3");
        Node node4 = new Node(4, "4");
        Node node5 = new Node(5, "5");
        Node node6 = new Node(6, "6");
        Node node7 = new Node(7, "7");
        Node node9 = new Node(9, "9");
        Node node8 = new Node(8, "8");
        Node node10= new Node(10, "10");
        Node strange2 = new Node(2, "2 (A different payload!)");
        Node hugeNode = new Node(Integer.MAX_VALUE, "max value int!!!!");
        Node maxNodeAgain = new Node(Integer.MAX_VALUE, "max value int AGAIN!");
        Node smallestNode = new Node(Integer.MIN_VALUE, "min value int!!!!");

        //Create the pairing heap.
        PairingHeap pairingHeap = new PairingHeap();

        //Insert values into the pairing heap.
        //Please note the extremely strange order of input. This is testing the heap will maintain it's properties
        //in spite of rather... disordered and hectic input ordering.
        pairingHeap.insert(hugeNode);       //MAXIMUM integer value.
        pairingHeap.insert(node10);         //10
        pairingHeap.insert(node7);          //7
        pairingHeap.insert(node5);          //5
        pairingHeap.insert(node4);          //4
        pairingHeap.insert(node9);          //9
        pairingHeap.insert(node2);          //2
        pairingHeap.insert(node3);          //3
        pairingHeap.insert(strange2);       //2 (the SAME value as the other 2, different payload!)
        pairingHeap.insert(node1);          //1
        pairingHeap.insert(node0);          //0
        pairingHeap.insert(nodeNegative191);//-191
        pairingHeap.insert(smallestNode);   //MINIMUM integer value.
        pairingHeap.insert(node6);          //6
        pairingHeap.insert(maxNodeAgain);   //MAXIMUM integer value.

        //Now display the results from the heap.
        while (pairingHeap.getSize() > 0){
            Node delMinNode = pairingHeap.deleteMin();
            System.out.println(delMinNode.getPayload());
        }
    }

    /**
     * Create two individual pairing heaps, merge them and then show their contents.
     * First off I will create two heaps, and then display their values individually.
     *
     * I will then populate the heaps again (displaying their values removes the values from the heap) with data
     * and then will merge the heaps. Afterwards I will display the heap contents.
     */
    public static void mergeTwoHeapsTest(){
        //State the test.
        System.out.println();
        System.out.println("******TEST 2: Merging Two Heaps ");

        //Make the two heaps.
        PairingHeap heap1 = new PairingHeap();
        PairingHeap heap2 = new PairingHeap();

        //Make the Nodes that will show the merge
        Node heap1Node1 = new Node(8, "heap1node1  (val = 8)");
        Node heap1Node2 = new Node(101, "heap1node2  (val = 101)");
        Node heap1Node3 = new Node(Integer.MAX_VALUE, "heap1node3  (val = MaxInt)");

        Node heap2Node1 = new Node(-7, "heap2node1  (val = -7)");
        Node heap2Node2 = new Node(15, "heap2node2  (val = 15)");
        Node heap2Node3 = new Node(9999, "heap2node3  (val = 9999)");

        heap1.insert(heap1Node1);
        heap1.insert(heap1Node2);
        heap1.insert(heap1Node3);

        System.out.println("-> Heap 1 data");
        displayHeap(heap1);

        heap2.insert(heap2Node1);
        heap2.insert(heap2Node2);
        heap2.insert(heap2Node3);

        System.out.println("-> Heap 2 data");
        displayHeap(heap2);

        System.out.println("Now repopulating the 2 heaps and will merge...");
        heap1.insert(heap1Node1);
        heap1.insert(heap1Node3);
        heap2.insert(heap2Node1);
        heap2.insert(heap2Node3);

        System.out.println("-> Merged data:");
        //Evoke the merge with heap1 and heap 2.
        heap1.mergeHeaps(heap2);

        //Check the data within...
        displayHeap(heap1);
    }


    /**
     * Using the build in Java Priority Queue implementation, I will insert into both the PairingHeap and the
     * Java Priority Queue a series of values, I will then at whim get the minimum value from each and compare
     * the values. Sometimes I remove values early, this is to test the resilience of the heap in spite of changes.
     *
     * This test will demonstrate the equal output from the Pairing Heap to the rigorously proven Java Priority Queue.
     */
    public static void runAgainstJavaBuiltInHeap(){
        //State the test.
        System.out.println();
        System.out.println("******TEST 3: Run against Java Built In Heap! ");
        System.out.println("Check that the values output are paired and matching.");
        System.out.println("Note in this test a lot of various values are put in and removed randomly.");
        System.out.println("As in yes, the output will have -8 \"appear\" before -16 but that is because of how " +
                "I put in the data not because of the heap!");

        //Create my pairingHeap first.
        PairingHeap pairingHeap = new PairingHeap();


        //Secondly create a traditional java priority queue.
        //How to use Java PQ:  Author: Jon Skeet
        //Source: http://stackoverflow.com/questions/683041/java-how-do-i-use-a-priorityqueue
        Comparator<Integer> comparator = new SimpleIntComparator(); //In another file, basic java comparator class.
        PriorityQueue<Integer> builtInHeap = new PriorityQueue<Integer>(10, comparator);

        //Add the value of -8 to each.
        Node nodeNeg8 = new Node(-8, "-8");
        pairingHeap.insert(nodeNeg8);
        builtInHeap.add(-8);

        //Add the value of 0 to each.
        Node nodeZero = new Node(0, "0");
        pairingHeap.insert(nodeZero);
        builtInHeap.add(0);

        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Add MAXINT value to each
        //Add 1699 to each
        //Maxint = 2^31 -1 = 2147483647
        Node nodeMaxInt = new Node(Integer.MAX_VALUE, "2147483647");
        pairingHeap.insert(nodeMaxInt);
        builtInHeap.add(Integer.MAX_VALUE);

        //Add the value of -16 to each.
        Node nodeNeg16 = new Node(-16, "-16");
        pairingHeap.insert(nodeNeg16);
        builtInHeap.add(-16);
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Add -17 to each
        Node nodeNeg17 = new Node(-17, "-17");
        pairingHeap.insert(nodeNeg17);
        builtInHeap.add(-17);

        //Add 16 to each
        Node node16 = new Node(16, "16");
        pairingHeap.insert(node16);
        builtInHeap.add(16);

        //Add 1699 to each
        Node node1699 = new Node(1699, "1699");
        pairingHeap.insert(node1699);
        builtInHeap.add(1699);

        //Add 89 to each
        Node node89 = new Node(89, "89");
        pairingHeap.insert(node89);
        builtInHeap.add(89);

        //Add 178 to each
        Node node178 = new Node(178, "178");
        pairingHeap.insert(node178);
        builtInHeap.add(178);

        //Compare
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Compare again
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Compare yet again
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Compare again..
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Compare again..
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());
        //Compare again..
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Compare again..
        System.out.println("Built in heap value: " + builtInHeap.remove());
        System.out.println("Pairing heap value : " + pairingHeap.deleteMin().getPayload());

        //Both empty. Test complete.
    }

    /**
     * This is the stress test for the Pairing Heap and will be pitted against the typical Java Priority Queue.
     * However I am intentionally making a fairly large loop and will be hurling values into these two data structures.
     *
     * Using the Java built in pseudorandom number generator to populate the data structures.
     *
     * Here I put in 100,000 values into the Pairing Heap and then 100,000 values into the java Priority Queue.
     * The outputs that are paired should match.
     *
     */
    public static void runStressTest(){
        System.out.println();
        System.out.println("******TEST 4: Stress test! ");

        //Create the Pairing Heap
        PairingHeap pairingHeap = new PairingHeap();
        Comparator<Integer> comparator = new SimpleIntComparator(); //In another file, basic java comparator class.
        PriorityQueue<Integer> builtInHeap = new PriorityQueue<Integer>(1000, comparator);

        //Standard way of generating a random number in java.     Author: Scary Wombat
        //Source: http://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
        Random rand = new Random();
        for(int i =0; i < 100000; i++){
            int randomInt = rand.nextInt();
//            System.out.println(randomInt);

            Node node = new Node(randomInt, "notUsedHere");
            pairingHeap.insert(node);
            builtInHeap.add(randomInt);
        }

        System.out.println("Display the results of removing the minimum element from each 10 times");
        System.out.println("  Check the values match following the 0 -> 10");
        System.out.println("  [Sadly due to the nature of this, these will be large negative numbers]");
        for(int i = 0; i < 10; i++){
            System.out.println("Built in heap value " + i + " : " + builtInHeap.remove());
            System.out.println("Pairing heap value  " + i + " : " + pairingHeap.deleteMin().getValue());
        }
    }

    /**
     * This function will be obtaining the time and comparing the time that my Pairing Heap takes vs.
     * the built in Java Priority Queue to insert 10,000 nodes and then get delete 5,000 of them.
     *
     * As an added bonus I will print the next value after that 5,000.
     *
     * Since my Pairing Heap is Object Orriented I am going to first create all of the Nodes first and keep them
     * in an ArrayList of Node Objects. I will maintain an ArrayList of equivalent Integers and insert them into
     * the Priority Queue from Java.
     *
     * So we instantly are incurring an O(n) cost, but this helps even out the playing field from creating Node
     * Objects.
     */
    public static void compareRunTimes(){
        System.out.println();
        System.out.println("******TEST 5: Compare Running times! ");

        //Create the Pairing Heap
        PairingHeap pairingHeap = new PairingHeap();
        Comparator<Integer> comparator = new SimpleIntComparator(); //In another file, basic java comparator class.
        PriorityQueue<Integer> builtInHeap = new PriorityQueue<Integer>(1000, comparator);

        //Now create the ArrayList of Node objects that will hold the node objects before the test.
        ArrayList<Node> nodeList = new ArrayList<>();

        //Create an equivalent ArrayList of Integers for the built in Java Priority Queue.

        ArrayList<Integer> intList = new ArrayList<>();

        //Standard way of generating a random number in java.     Author: Scary Wombat
        //Source: http://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
        Random rand = new Random();
        for(int i =0; i < 10000; i++){
            int randomInt = rand.nextInt();

            Node node = new Node(randomInt, "notUsedHere");
            nodeList.add(node);
            intList.add(randomInt);
        }

        System.out.println("The next two values below should also be equal.");

        //Timing procedure in Java here.     Authors: Diastrophism, Eric Leschinski
        //Source: http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
        //Begin the timing for the PairingHeap implementation.
        long startPairingHeapTime = System.nanoTime();
        for(int i = 0; i< nodeList.size(); i++){
            pairingHeap.insert(nodeList.get(i));
        }

        for(int i = 0; i < 5000; i++){
            pairingHeap.deleteMin();
        }

        //Display pairing heap value 4999.
        System.out.println(pairingHeap.deleteMin().getValue());

        //Begin the timing for the built in implementation.
        long startBuiltInTime = System.nanoTime();
        for(int i = 0; i< nodeList.size(); i++){
            builtInHeap.add(intList.get(i));
        }
        for(int i = 0; i < 5000; i++){
            builtInHeap.remove();
        }

        //Display built in heap value 4999.
        System.out.println(builtInHeap.remove());

        //Get time values for each.
        long endPairingHeapTime = System.nanoTime();
        long durationPairingHeap = (endPairingHeapTime - startPairingHeapTime);

        long endBuiltInTime = System.nanoTime();
        long durationBuiltIn = (endBuiltInTime - startBuiltInTime);

        //Output results to the terminal.
        System.out.println("How long each took to run:");
        System.out.println("Duration of built in    : " + durationBuiltIn/1000000 + " (Milliseconds)");
        System.out.println("Duration of Pairing Heap: " + durationPairingHeap/1000000 + " (Milliseconds)");
    }

    /**
     * This test runs Dijkstra's algorithm done by the Pairing Heap vs. Dijkstra's algorithm done by the
     * built in Java Priority Queue.
     *
     * Results should be the same.
     *
     * I will also be timing each.
     *
     * Dijkstra's algorithm here is following the CMPUT 204 slides from Martin Mueller's Lecture, 2015.
     * Note: I am using the version where instead of doing decrease key, I am instead putting the nodes back into the
     * heaps.
     *
     * Sort of a game...
     * "What is the shortest distance to each of these awesome locations from Main Street?"
     *
     * Node guide:
     *
     *  Node Key   ---- Location Name -----   Distances in Miles...? (units of distance arbitrary)
     *     1            Main Street             1 <-> 2 = 7
     *     2            Burberry Avenue         2 <-> 3 = 18
     *     3            Falcon Reach            3 <-> 4 = 49
     *     4            Mystical Forest         4 <-> 3 = 49
     *     5            Fairy River             5 <-> 1 = 29
     *     6            Dragon's Den            6 <-> 5 = 6
     *     7            Magical Portal          7 <-> 6 = 0   (So it is basically directly attached to Dragon's Den).
     *
     *  Expecting to start from Main Street the following values:
     *  MainStreet = 0
     *  Burberry avenue = 7
     *  Falcon Reach = 7+18 = 25
     *  Mystical Forest = 25 + 49 = 74
     *  Fairy River = 29
     *  Dragon's Den = 35
     *  Magical Portal = 35
     *
     */
    public static void dijkstraTest(){
        System.out.println();
        System.out.println("******TEST 6: Dijkstra's! ");

        //Here I will "create" the nodes, mostly to demonstrate my idea.
        //The first value of a Node represents the weight of the node in the Pairing Heap.
        //The second value is the payload, which identifies the location!
        Node nodeOne = new Node(1, "Main Street");
        Node nodeTwo = new Node(2, "Burberry Avenue");
        Node nodeThree = new Node(3, "Falcon Reach");
        Node nodeFour = new Node(4, "Mystical Forest");
        Node nodeFive = new Node(5, "Fairy River");
        Node nodeSix = new Node(6, "Dragon's Den");
        Node nodeSeven = new Node(7, "Magical Portal");

        //AdjList representation, follows the guide above in the function header.
        HashMap<String,ArrayList<String>> adjList = new HashMap<>();
        adjList.put(nodeOne.getPayload(), new ArrayList<String>());
        adjList.put(nodeTwo.getPayload(), new ArrayList<String>());
        adjList.put(nodeThree.getPayload(), new ArrayList<String>());
        adjList.put(nodeFour.getPayload(), new ArrayList<String>());
        adjList.put(nodeFive.getPayload(), new ArrayList<String>());
        adjList.put(nodeSix.getPayload(), new ArrayList<String>());
        adjList.put(nodeSeven.getPayload(), new ArrayList<String>());


        //Get my pairing heap.
        PairingHeap pairingHeap = new PairingHeap();
        //Keep track of visited Nodes.
        HashMap<String, Boolean> visited = new HashMap<>();
        //Distances for each node calculated from the source.
        HashMap<String,Integer> distances = new HashMap<>();



        //Lengths. I will input them manually. Refer to function header for details.
        HashMap<String, Integer> lengths = new HashMap<>();
        //1 <-> 2 = 7
        String distance12 = nodeOne.getPayload() + nodeTwo.getPayload();
        lengths.put(distance12, 7);
        adjList.get(nodeOne.getPayload()).add(nodeTwo.getPayload());
        //3 <-> 2 = 18
        String distance23 = nodeTwo.getPayload() + nodeThree.getPayload();
        lengths.put(distance23, 18);
        adjList.get(nodeTwo.getPayload()).add(nodeThree.getPayload());
        //3 <-> 4 = 49
        String distance34 = nodeThree.getPayload() + nodeFour.getPayload();
        lengths.put(distance34, 49);
        adjList.get(nodeThree.getPayload()).add(nodeFour.getPayload());
        //5 <-> 1 = 29
        String distance15 = nodeOne.getPayload() + nodeFive.getPayload();
        lengths.put(distance15, 29);
        adjList.get(nodeOne.getPayload()).add(nodeFive.getPayload());
        //6 <-> 5 = 6
        String distance56 = nodeFive.getPayload() + nodeSix.getPayload();
        lengths.put(distance56, 6);
        adjList.get(nodeFive.getPayload()).add(nodeSix.getPayload());
        //7 <-> 6 = 0   (So it is basically directly attached to Dragon's Den).
        String distance67 = nodeSix.getPayload() + nodeSeven.getPayload();
        lengths.put(distance67, 0);
        adjList.get(nodeSix.getPayload()).add(nodeSeven.getPayload());


        //Initialize distances for each location to be 0
        for(Map.Entry<String,ArrayList<String>> entry: adjList.entrySet()){

            //Source node is node one!
            if(entry.getKey().equals("Main Street")){
                visited.put(entry.getKey(),true);
                distances.put(entry.getKey(),0);
                pairingHeap.insert(new Node(0,entry.getKey()));
            }
            //All other nodes aren't visited yet, and their distance (for now) is max int.
            else{
                visited.put(entry.getKey(), false);
                distances.put(entry.getKey(), Integer.MAX_VALUE);
            }
        }

        //While the pairing heap has a node, continue processing.
        while(pairingHeap.getSize() > 0){
            //Get the smallest node.
            Node chosenNode = pairingHeap.deleteMin();

            //If it isn't already marked as visited, mark it now.
            if(!visited.get(chosenNode.getPayload())){
                visited.put(chosenNode.getPayload(), true);
            }

            //For each neighbour of the chosen node, process it!
            for(String neighbour: adjList.get(chosenNode.getPayload())){
                int lengthOfEdge = lengths.get(chosenNode.getPayload() + neighbour);

                //Now compare distances, and if the distance is improved then replace the old distance.
                if(distances.get(neighbour) > distances.get(chosenNode.getPayload()) + lengthOfEdge){
                    distances.put(neighbour, distances.get(chosenNode.getPayload()) + lengthOfEdge);
                    //Source of slower Dijkstra, here I put a NEW NODE into the Pairing Heap, rather than
                    //decrease key.
                    pairingHeap.insert(new Node(distances.get(chosenNode.getPayload()) + lengthOfEdge, neighbour));
                }
            }
        }

        //Now display the results of Dijkstra's algorithm WITH the Pairing Heap!
        System.out.println("Results of running Dijkstra's, please refer to function header or readme for details:");
        System.out.println(distances);
        System.out.println("Expected output (We start at \"MainStreet\"):");
        System.out.println("     *  MainStreet = 0\n" +
                "     *  Burberry avenue = 7\n" +
                "     *  Falcon Reach = 7+18 = 25\n" +
                "     *  Mystical Forest = 25 + 49 = 74\n" +
                "     *  Fairy River = 29\n" +
                "     *  Dragon's Den = 35\n" +
                "     *  Magical Portal = 35");
    }

    /**
     * Evokes the method to delete the minimum value from the heap until the size of the heap is 0.
     * This will also display the results of each minimum value that was in the heap as it proceeds.
     * In essence, this shows the contents of the heap from top to bottom.
     *
     * NOTE: The data within the heap will be removed! Intentional! This is meant to demonstrate the correctness
     *    of the heap!
     *
     * @param pairingHeap Takes in a PairingHeap that it will display the contents of.
     **/
    public static void displayHeap(PairingHeap pairingHeap){
        //Until the pairing heap is empty, continue printing!
        while (pairingHeap.getSize() > 0){
            Node delMinNode = pairingHeap.deleteMin();
            System.out.println(delMinNode.getPayload());
        }
    }
}

