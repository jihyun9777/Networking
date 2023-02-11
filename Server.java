import java.io.*;
import java.net.*;
import java.util.Random;
public class Server
{
    public static void main(String args[]) throws Exception
    {
        //Create welcome socket at port 1300
        ServerSocket welcome = new ServerSocket(1300);
        while(true)
        {
            //Create socket for contact by client
            Socket connectionSocket = welcome.accept();
            String serverName = "Jenny Lee";
            //Create input stream and receive client name and client number
            BufferedReader inFromClient = new BufferedReader(new 
InputStreamReader(connectionSocket.getInputStream()));
            String clientName = inFromClient.readLine();
            String clientNumber = inFromClient.readLine();
            int clientInt = Integer.parseInt(clientNumber);
            //Shut down server socket when input is out of range
            if(clientInt < 0 || clientInt > 100)
            {
                connectionSocket.close();
                break;
            }
            //Server pick a number between 1 and 100 randomly
            Random random = new Random();
            int serverInt = random.nextInt(101);
            System.out.println("Client of " + clientName);
            System.out.println("Server of " + serverName);
            System.out.println("Client Number: " + clientInt);
            System.out.println("Server Number: " + serverInt);
            System.out.println("Sum = " + (clientInt + serverInt));
            //Create output stream and send server name and server number
            DataOutputStream outToClient = new 
DataOutputStream(connectionSocket.getOutputStream());
            outToClient.writeBytes(serverName + '\n');
            outToClient.writeBytes(String.valueOf(serverInt) + '\n');
        }
        welcome.close();
    }
}