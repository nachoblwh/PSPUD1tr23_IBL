package tr23act3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class FechasCliente {
private static final String HOST = "localhost";
	
	public static void main(String[] args)
	{
		Socket socketServidor  = null;
		DataInputStream IN = null;
		DataOutputStream OUT = null;
		Scanner scanner = new Scanner(System.in);
		try
		{
			socketServidor = new Socket(HOST, Fechas.PORT);
			IN = new DataInputStream(socketServidor.getInputStream());
			OUT = new DataOutputStream(socketServidor.getOutputStream());
			System.out.println(IN.readUTF());
			
			String fechaI1 =scanner.nextLine(); 
			OUT.writeUTF(fechaI1);
			
			String fechaF2= scanner.nextLine();
			OUT.writeUTF(fechaF2);
			
			System.out.println(IN.readUTF()); 
			
			OUT.close();
			scanner.close();
			
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

