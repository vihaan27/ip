import java.util.Scanner;
public class Chip {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Chip. What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String line;
        do {
            System.out.print("~ ");
            line = in.nextLine();
            if (!line.equals("bye")) {
                System.out.println(line);
            }
        } while(!line.equals("bye"));
        
        System.out.println(exit);
    }
}
