package com.AluraChallenge.Library.Options;

import com.AluraChallenge.Library.Books.DataBooksTitle;
import com.AluraChallenge.Library.Services.ConnectionAPI;
import com.AluraChallenge.Library.Services.Console;

import java.util.Scanner;


public class OptionsMenu {
    public static DataBooksTitle SearchBook(String titleBook){
		String SearchTitle = "https://gutendex.com/books?search=%20" + titleBook.replace(' ', '+');
		String response = ConnectionAPI.getDataAPI(SearchTitle);

		ConvertResponse converter = new ConvertResponse();
		var converted = converter.DataConverter(response, DataBooksTitle.class);

        return converted;
    }
    public static String MenuMessage(){
        String menuMessage = """
				------------------------------------------------                 
				|                *BIBLIOTECA*                  |
				------------------------------------------------
				| Que Acción desea Realizar?                   |
				------------------------------------------------
				| 1-Buscar Libro por su Titulo.                |
				| 2-Listar Libros Registrados.                 |
				| 3-Listar Autores Registrados.                |
				| 4-Listar Autores Vivos en Determinado año.   |
				| 5-Listar Libros Por Idioma.                  |
				| 6-Salir Del Programa.                        |
				------------------------------------------------
				Escriba su Respuesta ->
				""";
        return menuMessage;
    }
    public static String SaveBookMessage(){
        return """
                Desea guardar el libro en su registro?
                   1- SI                           
                   2- No
                
                Cualquier otra respuesta sera tomada como "NO".  
                """;
    }
    public static boolean ProgramContinue(){
        Scanner scannerEnd = new Scanner(System.in);
        String message = """
                Desea seguir usando nuestro Programa?
                   1- SI                           
                   2- No
                
                Cualquier otra respuesta sera tomada como "NO".  
                """;

        Console.log(message);
        String response = scannerEnd.nextLine();

        if (response.equals("1")) return true;
        else return false;
    }
}
