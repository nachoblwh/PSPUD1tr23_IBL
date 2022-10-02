package tr23act2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Calculadora 
{
	protected static final int PORT = 2000 ;

	public static void main(String[] args) 
	{
		ServerSocket socketServidor = null;
		Socket socketCliente = null;
		DataOutputStream OUT = null;
		DataInputStream IN= null;
		
		try {
			socketServidor = new ServerSocket(PORT);
			//espera hasta que el cliente acceda al servidor
			socketCliente = socketServidor.accept();
			//Flujo de datos
			IN = new DataInputStream(socketCliente.getInputStream());
			OUT = new DataOutputStream(socketCliente.getOutputStream());
			OUT.writeUTF("Hola cliente dame dos numeros: ");
			int numero1 = IN.read();
			int numero2 = IN.read();
			OUT.writeUTF("Dime un signo: +, -, *, /");
			char signo = IN.readChar();
			
			OUT.write(Calcula(numero1, numero2, signo));
			IN.read();
			
			
			socketServidor.close();
			socketCliente.close();
			OUT.close();

		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally
		{
			if (OUT != null)
			{
				try 
				{
					OUT.close();
				}
				catch(IOException e) 
				{
					e.printStackTrace();
				}
				
			}
			if (socketCliente != null)
			{
				try 
				{
					socketCliente.close();
				}
				catch(IOException e) 
				{
					e.printStackTrace();
				}
				
			}
			if (socketServidor != null)
			{
				try 
				{
					socketServidor.close();
				}
				catch(IOException e) 
				{
					e.printStackTrace();
				}
				
			}
				
			
		}
	}
	
	
	public static int Calcula(int numero1, int numero2, char signo)
	{
		int res=0;
		switch(signo) {
			
		case '+':
			res=numero1 + numero2;
			System.out.println(res);
			break;
		case '-':
			res=numero1 - numero2;
			System.out.println(res);
			break;
		case '*':
			res=numero1 * numero2;
			System.out.println(res);
			break;
		case '/':
			res=numero1 / numero2;
			System.out.println(res);
			break;
		}
		
		return res;
		
	}
	
}
