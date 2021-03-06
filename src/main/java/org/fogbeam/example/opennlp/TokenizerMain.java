
package org.fogbeam.example.opennlp;


import java.io.*;
import java.util.*;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Clase TokernizerMain del proyecto OpenNLP
 * Procesamiento de uno o más ficheros, sepranado en tokens y almacenadolos en un fichero
 * @author Sergio Moreno Martín - alu0100710298
 */
public class TokenizerMain {
	/**
	 * Obtienes los token que forman el un fichero de entrada
	 * @param pw
	 * @param b
	 * @param tokenizer
	 * @throws IOException
	 */
	public static void Tokenizer(PrintWriter pw, BufferedReader b, Tokenizer tokenizer)throws IOException{
		String cadena;
		while((cadena = b.readLine())!=null){
			String[] tokens = tokenizer.tokenize(cadena);
			for(String token : tokens){
				pw.println(token);
				pw.flush();
			}
		}
	}

	/**
	 * Método para mostrar las instrucciones al usuario
	 * @param aux
	 * @return
	 */
	public static String LeerFichero (String aux){
		System.out.println(" ");
		System.out.println("Introduce el nombre del fichero: ");
		System.out.println("Si quiere salir puelse 's'");
		return aux;
	}

	/**
	 * Main del programa.
	 * Se inicializan las variables y se llaman a las funciones LeerFichero y Tokenizer
	 * @param args
	 * @throws Exception
	 */
	public static void main( String[] args ) throws Exception
	{

		//InputStream modelIn = new FileInputStream( "models/en-token.bin" );


		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );

		try {
			TokenizerModel model = new TokenizerModel(modelIn);

			Tokenizer tokenizer = new TokenizerME(model);

			Scanner s = new Scanner(System.in);
			String aux = null;
			FileWriter fichero;
			PrintWriter pw;
			fichero = new FileWriter("fichero.txt");
			pw = new PrintWriter(fichero);

			while (!Objects.equals(aux, "s")) {
				LeerFichero(aux);
				aux = s.nextLine();
				File archivo = new File(aux);
				FileReader f = new FileReader(archivo);
				BufferedReader b = new BufferedReader(f);

				Tokenizer(pw, b, tokenizer);
				pw.println("\n --- OTRO FICHERO ---\n");
				b.close();
			}

			pw.close();
			s.close();
		}

		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{


			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
}
