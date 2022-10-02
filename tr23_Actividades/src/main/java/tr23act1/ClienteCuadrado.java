package tr23act1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteCuadrado 
{
	private static final String HOST = "localhost";
	
	public static void main(String[] args) 
	{
		
			Socket socketServidor  = null;
			DataInputStream IN = null;
			DataOutputStream OUT = null;
			Scanner scanner = new Scanner(System.in);
			int numero;
			try
			{
				socketServidor = new Socket(HOST, Cuadrado.PORT);
				IN = new DataInputStream(socketServidor.getInputStream());
				System.out.println(IN.readUTF());
				
				OUT = new DataOutputStream(socketServidor.getOutputStream());
				numero= scanner.nextInt();
				OUT.write(numero);
				
				System.out.println(IN.read()); 
				
				scanner.close();
				OUT.close();
				
			}
			catch (UnknownHostException unknownException)
			{
				unknownException.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}finally
			{
				try
				{
					IN.close();
				}catch(IOException ex)
				{
					ex.printStackTrace();
				}
				
				try
				{
					socketServidor.close();
				}catch(IOException ex)
				{
					ex.printStackTrace();		
				}
			}
	}	
}

