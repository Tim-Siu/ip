package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;


public class Ui {

    private static final String NAME = "Kevin";
    private static final int SPLITTER_LENGTH = 50;
    private final Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    public void showLine() {
        for (int i = 0; i < Ui.SPLITTER_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm " + Ui.NAME + "\n" + "What can I do for you?\n");
    }


    public void showLoadingError() {
        System.out.println("OOPS!!! There was an error loading the file.");
    }

    public void showCommandNotRecognized() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showNoCommandDetail() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public void showTimeParsingError() {
        System.out.println("OOPS!!! Please enter the date and Duke.time in the format: yyyy-mm-dd");
    }

    public void showAddTask(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this Duke.task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showDelete(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this Duke.task:");
        System.out.println(tasks.getTask(index));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showMark(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this Duke.task as done:");
        System.out.println(tasks.getTask(index));
    }

    public void showUnmark(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this Duke.task as undone:");
        System.out.println(tasks.getTask(index));
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }


    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }
}
