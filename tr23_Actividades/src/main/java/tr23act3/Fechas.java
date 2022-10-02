package tr23act3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fechas 
{
	protected static final int PORT = 2000;

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
			//Flujo de salida de datos
			IN = new DataInputStream(socketCliente.getInputStream());
			OUT = new DataOutputStream(socketCliente.getOutputStream());
			OUT.writeUTF("Hola cliente dame dos fechas para comparar (yyyy-MM-dd) ");
			
			String fechaI1 = IN.readUTF();
			String fechaI2 = IN.readUTF();
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				
				Date fecha1 = formato.parse(fechaI1);
				Date fecha2 = formato.parse(fechaI2);
				
				OUT.writeUTF(ComparaFecha(fecha1, fecha2));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			OUT.close();
			socketCliente.close();
			socketServidor.close();
			
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
	
	public static String ComparaFecha(Date fecha1, Date fecha2)
	{
		
		if(fecha1.equals(fecha2)){
		    return "Fecha 1 es igual a fecha2";
		 }else if(fecha1.after(fecha2)){
		    return "Fecha 1 es mayor a fecha2";
		 }else if(fecha1.before(fecha2)){
		    return "Fecha 1 es menor a fecha2";
		 }
		return null;
	}

}