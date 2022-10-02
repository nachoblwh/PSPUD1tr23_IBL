package tr23act2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CalculadoraCliente {

	private static final String HOST = "localhost";
	
	public static void main(String[] args)
	{
		Socket socketServidor  = null;
		DataInputStream IN = null;
		DataOutputStream OUT = null;
		Scanner scanner = new Scanner(System.in);
		try
		{
			socketServidor = new Socket(HOST, Calculadora.PORT);
			IN = new DataInputStream(socketServidor.getInputStream());
			OUT = new DataOutputStream(socketServidor.getOutputStream());
			System.out.println(IN.readUTF());
			
			int numero1 =scanner.nextInt(); 
			OUT.write(numero1);
			
			int numero2= scanner.nextInt();
			OUT.write(numero2);
			
			System.out.println(IN.readUTF());
			char signo= scanner.next().charAt(0);
			OUT.writeChar(signo);
			
			System.out.println(IN.read()); 
			
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

