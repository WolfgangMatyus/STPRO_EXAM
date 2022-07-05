import java.util.Scanner;

class GasStation {
    String name;
    Fuel[] fuels = new Fuel[2];
    float distance;
}

class Fuel {
    String name;
    float price;
}

class Node{
    GasStation data;
    Node next;
}

class List{
    Node head;
    int size = 0;
        }

public class GasStationFinder {
    public static void main(String[] args) {

        // create Testdata
        GasStation gs1 = createGasStation("BP", 0.6f, 1.4f, 1.36f);
        GasStation gs2 = createGasStation("123tanken", 1.5f, 1.328f, 1.306f);
        GasStation gs3 = createGasStation("avanti", 2f, 1.384f, 1.364f);
        GasStation gs4 = createGasStation("Spritinn", 1.5f, 1.4f, 1.344f);
        GasStation gs5 = createGasStation("TankEnergie", 1.3f, 1.389f, 1.349f);

        // create new linked list of Gasstations
            List gsL = new List();
        // add Testdata to list
        add(gsL, gs1);
        add(gsL, gs2);
        add(gsL, gs3);
        add(gsL, gs4);
        add(gsL, gs5);


        // print all elements of the list
            printList(gsL);
        System.out.println("***** Sorted by distance *****");
        // sort list by distance and print it
        sort(gsL, 1);
        printList(gsL);
        System.out.println("***** Sorted by diesel price *****");
        // sort list by diesel price and print it
        sort(gsL, 3);
        printList(gsL);
        System.out.println("***** Sorted by super price *****");
        // sort list by super price and print it
        sort(gsL, 2);
        printList(gsL);
        System.out.println("***** Filtered by distance (<= 1.3km) *****");
        // filter list by distance and print the filtered list
        printList(filterDistance(gsL, 1.3f));



        //TEST CASES:
        //printGasStation(gsL, gs3);

        //printList(gsL);

        //System.out.printf("length: %d", length(gsL));

        //System.out.printf("GetNode Name: %s", getNode(gsL, 3).data.name);

        //Sort input:
        //Scanner sc = new Scanner(System.in);
        //System.out.print("[1] distance [2] Super 95 [3] Diesel\n" +
        //        "Choose a sort option: ");
        //int option = sc.nextInt();
        //sort(gsL, option);
        //printList(gsL);

    }

    private static GasStation createGasStation(String name, float distance, float priceSuper, float priceDiesel){
        GasStation station = new GasStation();
        station.name = name;
        station.distance = distance;
        Fuel sup = new Fuel();
        sup.name = "Super 95";
        sup.price = priceSuper;
        station.fuels[0] = sup;
        Fuel die = new Fuel();
        die.name = "Diesel";
        die.price = priceDiesel;
        station.fuels[1] = die;
        return station;
    }

    // implement printGasStation method
        public static void printGasStation(List gsL, GasStation station){
            Node cur = new Node();
            cur.data = station;
            System.out.printf(
                    "Gasstation: %s\n" +
                    "  Distance: %.1f km\n" +
                    "  Super 95: %.3f €\n" +
                    "    Diesel: %.3f €\n", cur.data.name, cur.data.distance, cur.data.fuels[0].price, cur.data.fuels[1].price);
        }


    // implement add method
    public static void add(List gsL, GasStation station){
        Node n = new Node();
        gsL.size++;
        n.data = station;
        n.next = gsL.head;
        gsL.head = n;
    }
    // implement printList method
    public static void printList(List gsL){
        for(Node cur = gsL.head; cur != null; cur = cur.next){
            printGasStation(gsL, cur.data);
            System.out.println("****");
        }
    }
    // implement length method
        public static int length(List gsL) {
            int l = 0;
            for (Node cur = gsL.head; cur != null; cur = cur.next) {
                l++;
            }
            return l;
        }

    // implement getNode method
    public static Node getNode(List gsL, int idx){
        int i = 0;
        for (Node n = gsL.head; n != null; n = n.next) {
            if(i == idx-1){
                return n;
            }
            i++;
        }
        return null;
    }
    // adapt the given bubble sort algorithm to sort a list of GasStations by distance, diesel price and super price
    public static void sort(List gsL, int option) {
        boolean swapped;
        int i=0;
        do {
            swapped = false;
            int j = 1;
            float a = 0, b = 0;
            for (Node cur = gsL.head; j < gsL.size; j++){
                switch(option){
                    case 1: //distance
                        a = getNode(gsL, j).data.distance;
                        b = getNode(gsL, j+1).data.distance;
                        break;
                    case 2: //Super 95
                        a = getNode(gsL, j).data.fuels[0].price;
                        b = getNode(gsL, j+1).data.fuels[0].price;
                        break;
                    case 3: //Diesel
                        a = getNode(gsL, j).data.fuels[1].price;
                        b = getNode(gsL, j+1).data.fuels[1].price;
                        break;
                }
                if (a > b) {
                    Node tmp = new Node();
                    tmp.data = getNode(gsL, j).data;
                    cur.data = getNode(gsL, j+1).data;
                    cur.next.data = tmp.data;
                    swapped = true;
                }
                cur = cur.next;
            }
            i++;
        } while (swapped);
    }

    // implement a filter method to filter a list by a maximum distance.
    // it should return a new list containing all elements that have a distance smaller then the maximum distance
    public static List filterDistance(List gsL, float distance){
        List flist = new List();
        for (Node cur = gsL.head; cur != null; cur = cur.next){
            if (cur.data.distance <= distance){
                add(flist, cur.data);
            }
        }
        return flist;
    }
}
