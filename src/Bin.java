/* Alireza Sajjadi      9912762596
   Kourosh Hassanzadeh  9912762552
 */

public class Bin {

    Node headChunk;
    Node first;
    Node firstChunk;            //the first chunk after headChunk
    int count = 0;

    public Bin() {
        createBins();
    }

    public void createBins() {
        first = new Node(8);
        headChunk = first;
        for (int i = 1; i < 32; i++) {

            headChunk.next = new Node((i + 1) * 8);
            headChunk = headChunk.next;
        }
    }

    public void addChunk(Node inp) {       // add to first

        Node properHead = searchForProperBin(inp);

        if (properHead.prev == null) {
            firstChunk = inp;
            properHead.prev = firstChunk;
            firstChunk.backward = properHead;
        } else {
            Node p = properHead.prev;
            while (p.forward != null) {
                p = p.forward;
            }
            p.forward = inp;
            inp.backward = p;
        }


    }

    public Node searchForProperBin(Node input) {
        Node p;   //point to proper bin

        if (first == null) {
            return null;
        } else {
            p = first;
            while (p != null) {
                if (p.size == input.size) {
                    return p;
                }
                p = p.next;
            }
        }

        return null;
    }

    public Node searchForMalloc(int size) {
        Node p;   //point to proper head

        p = first;
        while (p != null) {
            if (p.size == size) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public void print() {

        Node p = first;
        while (p.next != null) {
            Node q = p.prev;
            while (q != null) {
                count++;
                q = q.forward;
                if (q == null) {
                    System.out.print("bin" + (p.size)/8 + " (" + p.size + ") : " + count);
                    System.out.print("\n");
                }
            }
            count = 0;
            p = p.next;
        }

    }

}
