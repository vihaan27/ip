import java.util.Scanner;
public class Chip {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Chip. What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
        String[] items = new String[100];
        int itemCount = 0;

        Scanner in = new Scanner(System.in);
        String line;
        do {
            System.out.print("~ ");
            line = in.nextLine();
            if(line.equals("list")){
                printList(items, itemCount);
            }
            else if (!line.equals("bye")) {
                items[itemCount] = line;
                itemCount++;
                System.out.println("Added: " + line);
            }
        } while(!line.equals("bye"));

        System.out.println(exit);
    }
    public static void printList(String[] items, int itemCount){
        for (int i = 0; i < itemCount; i++){
            System.out.println((i+1) + "." + items[i]);
        }
    }
}
