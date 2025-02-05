import java.util.Scanner;
public class Chip {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Chip. What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner in = new Scanner(System.in);
        String line;
        do {
            System.out.print("~ ");
            line = in.nextLine();
            String[] words = line.split(" ");
            if(line.equals("list")){
                printList(tasks, taskCount);
            }
            else if(words[0].equals("mark")){
                int pos = Integer.parseInt(words[1]);
                if (pos > 0 && pos <= taskCount) {
                    tasks[pos - 1].setComplete(true);
                }
                else{
                    System.out.println("No item found at position "+ pos);
                }
            }
            else if(words[0].equals("unmark")){
                int pos = Integer.parseInt(words[1]);
                if (pos > 0 && pos <= taskCount) {
                    tasks[pos - 1].setComplete(false);
                }
                else{
                    System.out.println("No item found at position "+ pos);
                }
            }
            else if (!line.equals("bye")) {
                tasks[taskCount] = new Task(line, false);
                taskCount++;
                System.out.println("Added: " + line);
            }
        } while(!line.equals("bye"));

        System.out.println(exit);
    }
    public static void printList(Task[] tasks, int taskCount){
        for (int i = 0; i < taskCount; i++){
            if (tasks[i].isComplete()){
                System.out.print((i+1) + ". [X] ");
            }
            else{
                System.out.print((i+1) + ". [ ] ");
            }
            System.out.println(tasks[i].getItem());

        }
    }
}
