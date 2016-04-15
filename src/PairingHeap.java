import java.util.ArrayList;

/**
 * PairingHeap object.
 */
public class PairingHeap {
    //Maintain the size of the Pairing Heap.
    private int size;

    //Maintain the minimum node of this heap.
    private Node minNode;

    /**
     * Constructor, set the size of the pairingheap to be zero.
     */
    PairingHeap(){
        size = 0;
    }

    /**
     * Returns the minimum node of the PairingHeap.
     * @return Node object representing the minimum node.
     */
    public Node getMin(){
        return minNode;
    }

    /**
     * Gets the size of the pairing heap.
     * @return Integer stating the size of the PairingHeap.
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Merge is described in the article on page 3, depicting the joining of two separate heaps.
     * In this implementation I avoid the extra overhead of creating a completely new heap, instead for this
     * implementation I am going to compare node values and place those nodes into this heap.
     *
     * The node values represent the root nodes of a pairing heap.
     *
     * This follows the exact same idea, however it avoids the extra pain of making more heaps.
     * @param n1 The first node to be compared, represents the root of a pairing heap.
     * @param n2 The second node to be compared, represents the root of this second pairing heap.
     * @return The merged root of the heaps, the smaller root will become the root of the merged heaps.
     */
    private Node merge(Node n1, Node n2){
        //The holder for the return value from this function.
        Node subtreeHeadNode;

        if(n1.getValue() > n2.getValue()){
            n2.addChild(n1);
            subtreeHeadNode = n2;
        }
        else{
            n1.addChild(n2);
            subtreeHeadNode = n1;
        }

        return subtreeHeadNode;
    }

    /**
     * Insert method following the pairing heap guidelines.
     * Creates a new "pairing heap", which in my implementation I chose to simplify to being represented merely by
     * it's root node Therefore in this implementation of insert, following the same guidelines for a pairing heap,
     * I take the Node (representing the root of a "brand new pairing heap") and then merge it with the main heap.
     *
     * @param node Takes in a new Node object, representing the root of a brand new pairing heap object.
     */
    public void insert(Node node){
        //If there is no node already there, make the minimum node (root) the inserted node.
        if(this.size==0){
            this.minNode = node;
            this.size++;
        }
        else{
            //The minimum node will be the result of the merge operation.
            this.minNode =  merge(minNode, node);
            this.size++;
        }
    }

    /**
     * Following the pairing heap guidelines, this function will remove the minimum node from the pairing heap.
     * Steps:
     * 1- Remove the minimum node (and return it), putting it into a temporary holder to return.
     * 2- I choose to follow the delection method on page 5 here involving creating a queue and iterating over it
     *     until all subheaps are merged into ONE single heap-- representing this "pairing heap object" after
     *     the removal.
     * 3- Set the minimum Node to the head of this pairing heap created.
     *
     * (Source: http://www.uqac.ca/azinflou/Fichiers840/pairing.pdf)
     * @return The Node representing the root of the Pairing Heap after the deletion of the minimum element.
     */
    public Node deleteMin(){
        //Handle the size of 0 case, creates a dummy node to return-- but informs the user of the error.
        if(this.size==0){
            System.out.println("Sorry, no more nodes!");
            new Node(0,"dummynode");
        }

        //If the size is just 1 node, then just return the minimum node and don't do anything more.
        if(this.size==1){
            this.size--;
            return this.minNode;
        }

        //Holds the minimum node that will be removed, it must be returned at the end.
        Node tempHolder = this.minNode;

        //For each child node of the current root, put it into a queue.
        ArrayList<Node> childQueue = new ArrayList<>();
        for(Node child: minNode.getChildList()){
            childQueue.add(child);
        }

        //While the size of the queue, holding subtrees, is greater than 1-- continue to merge the subtrees.
        while(childQueue.size() > 1){
            Node subtreeRoot = merge(childQueue.get(0), childQueue.get(1));
            childQueue.remove(0);
            childQueue.remove(0);
            childQueue.add(subtreeRoot);
        }

        //After merging of the subtrees is done, set the minimum node of the Pairing Heap to the root.
        this.minNode = childQueue.get(0);
        this.size--;
        return tempHolder; //return the old root.
    }


    /**
     * Pairing Heaps are traditionally about merging separate Pairing Heaps, but I took the route where I could
     * have objects already linked together and merely merge the root nodes.
     *
     * This function is an addition made for fun, where I take the more traditional approach to merge two separate
     * PairingHeap objects into one!
     * When this method is called upon a PairingHeap object, it will assimilate the heap passed in as a parameter into
     * the heap that provided the method!
     *
     * @param pairingHeap A separate PairingHeap object to be assimilated into the other!
     */
    public void mergeHeaps(PairingHeap pairingHeap){

        //If this PairingHeap's value is less than the one passed into this method, absorb the other.
        if(pairingHeap.minNode.getValue() > this.minNode.getValue()){

            this.size += pairingHeap.size;
            this.size++;

            this.minNode.addChild(pairingHeap.minNode);
        }

        //If this PairingHeap's value is greater than the other, need to do some swapping around.
        if(pairingHeap.minNode.getValue() < this.minNode.getValue()){
            this.size += pairingHeap.size;
            this.size++;
            pairingHeap.minNode.addChild(this.minNode);
            this.minNode = pairingHeap.minNode;
        }

    }
}
