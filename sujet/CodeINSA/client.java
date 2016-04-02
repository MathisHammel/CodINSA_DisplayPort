package defaut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		
		int nbP = 2;
		int portNb = 8080;
		Socket clientSocket[] = new Socket[nbP];
		PrintWriter outClient[] = new PrintWriter[nbP];
		BufferedReader inClient[] = new BufferedReader[nbP];
		String inUser="";
		String IP = "127.0.0.1";
	    Scanner scanIn = new Scanner(System.in);
	    int a =0;
 
		for(int i =0; i < nbP; i++)
		{
			clientSocket[i]= new Socket(IP,portNb);
			outClient[i] = new PrintWriter(clientSocket[i].getOutputStream(),true);
			inClient[i] = new BufferedReader(new InputStreamReader(clientSocket[i].getInputStream()));
			inClient[i].readLine();
			outClient[i].println("OK");
			Thread.sleep(100);
		}
		
		for(int i = 0; i < 20 ; i++)
		{
			System.out.println("waint for input "+a+":");
			inUser = scanIn.nextLine();
			System.out.println("inptu OK");
			if(inUser.equals("E"))
			{
				outClient[a].println(inUser);
				System.out.println(inClient[a].readLine());
				a = (a+1) % nbP;
			}else
			{
				outClient[a].println(inUser);
				for(i = (a+1) %nbP; i%nbP != a%nbP; i++)
				{
					inClient[i].readLine();
				}
				System.out.println(inClient[a].readLine());
			}
			
			
		}
	    scanIn.close();
		for(int i =0; i < nbP; i++)
		{
			outClient[i].close();
			inClient[i].close();
			clientSocket[i].close();
		}
		
		
	}

}