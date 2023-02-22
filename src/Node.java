/* Alireza Sajjadi      9912762596
   Kourosh Hassanzadeh  9912762552
 */

public class Node {

    int size;
    Node next;
    Node prev;
    Node backward = null;
    Node forward = null;

    public Node(int size) {
        this.size = size;
        this.next = null;
        this.prev = null;
    }
}
