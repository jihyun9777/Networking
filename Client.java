import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client 
{
    public static void main(String args[]) throws Exception
    {
        Scanner keyboard = new Scanner(System.in);
        //Get integer input
        System.out.print("Enter a number: ");
        int number = keyboard.nextInt();
        keyboard.nextLine();
        //Get client name
        System.out.print("Enter name: ");
        String name = keyboard.nextLine();
        //Create client socket connected to server
        Socket clientSocket = new Socket("localhost", 1300);
        //Create output stream and send client name and client number
        DataOutputStream outToServer = new 
DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(name + '\n');
        outToServer.writeBytes(String.valueOf(number) + '\n');
        //Shut down client socket when input is out of range
        if(number > 0 && number <= 100)
        {
            //Create input stream and receive server name and server number
            BufferedReader inFromServer = new BufferedReader(new 
InputStreamReader(clientSocket.getInputStream()));
            String serverName = inFromServer.readLine();
            String serverNumber = inFromServer.readLine();
            int serverInt = Integer.parseInt(serverNumber);
            System.out.println("Client of " + name);
            System.out.println("Server of " + serverName);
            System.out.println("Client Number: " + number);
            System.out.println("Server Number: " + serverInt);
            System.out.println("Sum = " + (number + serverInt));
        }
        clientSocket.close();
    }
}