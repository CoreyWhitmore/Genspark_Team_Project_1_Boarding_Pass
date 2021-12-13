import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static Scanner in = new Scanner(System.in);
    static Random rand = new Random();
    public static void main(String[] args){
        String[] userInfo = getUserInput();
        String[] generatedInfo = ETAPrice(userInfo[3], userInfo[4]);
        String boardingNum = generateBoardingNum();
        writeToFile(userInfo, generatedInfo, boardingNum);
        System.out.println("\n\n" + userFriendlyTicket(userInfo, generatedInfo, boardingNum));
    }

    //Gets necessary user input and outputs it as an array formatted as {name, email, phone, gender, age, date, destination, departure time}
    public static String[] getUserInput(){
        String[] inputs = new String[8];

        System.out.println("We are gong to need some information in order to process your boarding pass");
        System.out.print("Please enter your full name: ");
        inputs[0] = in.nextLine();
        System.out.print("Please enter your Email: ");
        inputs[1] = in.nextLine();
        System.out.print("Please enter your Phone Number in the format ###-###-####: ");
        inputs[2] = in.nextLine();
        System.out.print("Please enter your Gender (M/F): ");
        inputs[3] = in.nextLine();
        System.out.print("Please enter your age: ");
        inputs[4] = in.nextLine();
        System.out.print("Please enter the date in the format MM/DD/YYYY: ");
        inputs[5] = in.nextLine();
        System.out.print("Please enter your destination in the format State, City: ");
        inputs[6] = in.nextLine();
        System.out.print("Please enter your Departure Time formatted as HH:MM in 24-hour time: ");
        inputs[7] = in.nextLine();

        return inputs;
    }

    //generate ETA and ticket price as {ETA, price}
    public static String[] ETAPrice (String gender, String age){
        String[] output = new String[2];
        int ETA = rand.nextInt(570) + 30; //initial ETA is in minutes
        double price = ETA * 75 + 2000; //initial price is in cents
        try{
            if(Integer.parseInt(age) >= 60){
                price *= 0.4;
            }
            if(Integer.parseInt(age) <= 12){
                price *= 0.5;
            }
            if(gender.equalsIgnoreCase("F")){
                price*= 0.75;
            }
        }catch (Exception e){
            System.out.println("There was an error calculating discounts. Non-discounted value has been used instead");
        }
        //convert ETA and price into strings
        output[0] = ETA / 60 + "hours " + ETA % 60 + "minutes";
        output[1] = "$" + String.format("%.2f",price/100);

        return output;
    }

    //Generate boarding number by checking previous number and adding 1
    public static String generateBoardingNum(){
        int lastBoardingNum = 0;
        try{
            String fileLocation = "C:/Genspark Assignments/Projects/Genspark_Team_Project_1_Boarding_Pass/ticketInfoHistory";
            BufferedReader brTest = new BufferedReader(new FileReader(fileLocation));
            lastBoardingNum = Integer.parseInt(brTest.readLine());
        } catch (Exception e){
            System.out.println("Error reading file or file is blank");
        }

        return String.valueOf(lastBoardingNum +1);
    }

    public static void writeToFile(String[] userInfo, String[] generatedInfo, String boardingNum){
        String fileText = "";
        try{
            fileText = Files.readString(Paths.get("C:/Genspark Assignments/Projects/Genspark_Team_Project_1_Boarding_Pass/ticketInfoHistory"));
        } catch (Exception e){
            System.out.println("Error Reading file");
        }
        String[] fileArray = fileText.split("\n");
        fileArray[0] = boardingNum;
        fileText = String.join("\n", fileArray);
        fileText += "\n" + boardingNum + " Name: " + userInfo[0] +" Email: " + userInfo[1] +" phone: " + userInfo[2] +" gender: " + userInfo[3] +" age: " + userInfo[4] +" Date: " + userInfo[5] + " Dest: " + userInfo[6] + " Departure: " + userInfo[7] + " ETA: " + generatedInfo[0] + " Ticket Price: " + generatedInfo[1];

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Genspark Assignments/Projects/Genspark_Team_Project_1_Boarding_Pass/ticketInfoHistory"));
            writer.write(fileText);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public static String userFriendlyTicket(String[] userInfo, String[] generatedInfo, String boardingNum){
        return "Your Boarding Ticket\n" +
                "============================================================" + "\n" +
                "BOARDING #" + boardingNum + "\n" +
                "NAME: " + userInfo[0] + "   GENDER: " + userInfo[3] + "   AGE:" + userInfo[4] + "\n" +
                "EMAIL: " + userInfo[1] + "   PHONE: " + userInfo[2] + "\n" +
                "DEPARTURE TIME: " + userInfo[7] + "   DESTINATION: " + userInfo[6] + "\n" +
                "TRIP DURATION:" + generatedInfo[0] + "   TICKET PRICE: " + generatedInfo[1] + "\n" +
                "============================================================";
    }
}
