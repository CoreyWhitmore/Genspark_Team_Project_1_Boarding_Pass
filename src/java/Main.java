import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args){

    }

    public static String[] getUserInput(){
        String[] inputs = new String[8];

        System.out.println("We are gong to need some information in order to process your boarding pass");
        System.out.print("Please enter your full name:");
        inputs[0] = in.nextLine();
        System.out.print("Please enter your Email:");
        inputs[1] = in.nextLine();
        System.out.print("Please enter your Phone Number:");
        inputs[2] = in.nextLine();
        System.out.print("Please enter your Gender (M/F):");
        inputs[3] = in.nextLine();
        System.out.print("Please enter your age:");
        inputs[4] = in.nextLine();
        System.out.print("Please enter the date in the format MM/DD/YYYY:");
        inputs[5] = in.nextLine();
        System.out.print("Please enter your destination:");
        inputs[6] = in.nextLine();
        System.out.print("Please enter your Departure Time:");
        inputs[7] = in.nextLine();

        return inputs;
    }
}
