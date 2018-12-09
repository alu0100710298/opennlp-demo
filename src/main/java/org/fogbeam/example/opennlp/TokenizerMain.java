
package org.fogbeam.example.opennlp;


import java.io.*;
import java.util.*;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{

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

	public static String LeerFichero (String aux){
		System.out.println(" ");
		System.out.println("Introduce el nombre del fichero: ");
		System.out.println("Si quiere salir puelse 's'");
		return aux;
	}
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
