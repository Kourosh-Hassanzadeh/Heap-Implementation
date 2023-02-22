                      /* Alireza Sajjadi      9912762596
                         Kourosh Hassanzadeh  9912762552
                       */

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Heap heap;

        Scanner sc = new Scanner(System.in);

        System.out.println("enter the size of heap");
        int heapSize = Integer.parseInt(sc.nextLine());
        heap = new Heap(heapSize);

        System.out.println("enter the number of commands");
        int numCommands = Integer.parseInt(sc.nextLine());

        System.out.println("enter the commands");
        while (numCommands != 0) {
            // put all input command to queue because of FIFO, when inputs ends input command get out of queue form fist

            String command = sc.nextLine();
            numCommands--;

            //recognize top command in queue (write function for it)
            String[] topCmd = command.split(" ");    //topCmd[0] = malloc or free //topCmd[1] = size
            if (topCmd[0].equalsIgnoreCase("malloc")) {
                heap.malloc(Integer.parseInt(topCmd[1]));
                heap.print();
            } else if (topCmd[0].equalsIgnoreCase("free")) {
                heap.free(Integer.parseInt(topCmd[1]));
                heap.print();

            }
        }

    }
}
