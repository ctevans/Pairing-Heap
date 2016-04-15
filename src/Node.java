import java.util.ArrayList;

/**
 * Node, the Pairing Heap Object that I will be using during the development and testing of this pairing heap.
 * These objects are going to be the elements that the heap is concerned with, commonly represented as nodes in
 * descriptions of the heap.
 *
 * Keeping it simple.
 */
public class Node {
    private int value;
    private String payload;

    //Maintain a list of the children of this object.
    private ArrayList<Node> children = new ArrayList<>();

    /**
     * Basic constructor, takes in a value and a payload to make a single object.
     * @param paramKey Integer value used as the value of the object.
     * @param paramPayload String value used as the payload of the object.
     */
    Node(int paramKey, String paramPayload){
        value = paramKey;
        payload = paramPayload;
    }

    /**
     * Adds the Node passed in this method, to the evoked upon Node's child list.
     * @param node A Node to be added as a child of this Node.
     */
    public void addChild(Node node){
        children.add(node);
    }

    /**
     * Getter method to get the list of Child Nodes from this node.
     * @return An ArrayList of Node Objects for the Node this method is evoked upon.
     */
    public ArrayList<Node> getChildList(){
        return children;
    }

    /**
     * Simple getter method that gets the value from the object.
     * @return Key (Integer)
     */
    public int getValue() {
        return value;
    }


    /**
     * Simple setter method for the value, I use it in the Dijkstra's implementation.
     * @param value New value for the node (Integer)
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * Simple getter method that gets the payload from the object.
     * @return Payload (String)
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Displays the children of the current node in the following format:
     * "Node number: Key: X Payload: Y"
     */
    public void printChildren(){
        int iteration = 1;
        for(Node node: children){
            System.out.println(iteration + ":  Key:" + node.getValue() + " Payload: " + node.getPayload());
            iteration++;
        }
    }

}
