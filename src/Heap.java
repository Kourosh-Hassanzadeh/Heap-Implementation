/* Alireza Sajjadi      9912762596
   Kourosh Hassanzadeh  9912762552
 */

public class Heap {
    final private Node headNode;
    private int headSize;
    final private Bin bin;
    Node p;
    Node pPrev;

    public Heap(int size) {
        this.headSize = size;
        headNode = new Node(size);
        headNode.next = null;
        bin = new Bin();
    }

    public void malloc(int size) {
        p = bin.searchForMalloc(size);

        if (p.prev != null) {
            pPrev = p.prev;

            p.prev.prev.next = p.prev;
            if (p.prev.next != null) {
                p.prev.next.prev = p.prev;
            }
            if (p.prev.forward != null) {
                p.prev = p.prev.forward;
                p.prev.forward.backward = p;
            } else {
                p.prev = null;
            }

        } else {
            Node chunk = new Node(size);
            chunk.next = null;
            chunk.prev = null;
            //if heap is empty
            if (headNode.next == null) {
                headNode.next = chunk;
                chunk.prev = headNode;
            } else {
                chunk.next = headNode.next;
                chunk.prev = headNode;
                headNode.next.prev = chunk;
                headNode.next = chunk;
            }
        }
        this.headSize -= size;
    }

    public void free(int size) {
        Node p = headNode.next;
        Node q = new Node(size);      //q=null
        Node temp;
        while (p.size != size) {
            if (p.next == null) {//if list was ended
                System.err.println("could not found");
            }
            q = p;
            p = p.next;
        }

        if (p.next != null && p.size == size) {      //reach to the wanted Node

            if (p == headNode.next) {             //free the first node(in a list with more than one node)
                headNode.next = p.next;
                p.next.prev = headNode;
//                headSize +=size;
            } else {
                bin.addChunk(p);
                pPrev = bin.searchForProperBin(p).prev;
            }
            //if p required to remove

            p = p.next;
            p.prev = q;
            q.next = p;

        } else if (q.size == size) {       //free the first node(in a One-Node list)
            headNode.next = null;
//            headSize +=size;
        } else {
            q.next = null;      //free the last node
            bin.addChunk(p);
            pPrev = bin.searchForProperBin(p).prev;
        }
        headSize += size;
    }


    public void print() {
        //display heap in output after each malloc and free
        System.out.println("Remaining Memory  : " + this.headSize);
        Node q = headNode;
        while (q.next != null) {
            System.out.printf("- %d ", q.next.size);
            q = q.next;
        }
        System.out.print("\n");

        //o = bin.searchForProperBin(p).prev;
        if (pPrev != null) {
            bin.print();
        }
    }

}