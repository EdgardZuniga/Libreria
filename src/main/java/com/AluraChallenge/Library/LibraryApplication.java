package com.AluraChallenge.Library;

import com.AluraChallenge.Library.Books.DataBooksTitle;
import com.AluraChallenge.Library.Model.AuthorModel;
import com.AluraChallenge.Library.Model.BookModel;
import com.AluraChallenge.Library.Options.OptionsMenu;
import com.AluraChallenge.Library.Repository.IAuthorRepository;
import com.AluraChallenge.Library.Repository.IBookRepository;
import com.AluraChallenge.Library.Services.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner{

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IAuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}


	@Override
	public void run(String... args){
		boolean finish = false;

		while (!finish){
			Scanner scanner = new Scanner(System.in);

			System.out.println(OptionsMenu.MenuMessage());
			var respuesta = scanner.nextLine();

            switch (respuesta) {
                case "1" :
                    Scanner scannerTitle = new Scanner(System.in);

                    Console.log("Ingrese el Nombre del Libro.");
                    var res = scannerTitle.nextLine();

                    DataBooksTitle books = OptionsMenu.SearchBook(res);

                    if (books.result().isEmpty()) Console.log("No encontrado");
                    else{
                        var firstBook = books.result().get(0);
                        BookModel bookModel = new BookModel(firstBook);
                        Console.log(bookModel.getBook());

                        AuthorModel authorModel = new AuthorModel(
                                bookModel.getId(), bookModel.getBirthYear(), bookModel.getDeathYear(), bookModel.getAuthorName()
                        );

                        Scanner scannerSave = new Scanner(System.in);
                        Console.log(OptionsMenu.SaveBookMessage());
                        String resSave = scannerSave.nextLine();

                        if (resSave.equals("1")) {
                            Optional<BookModel> existingBook = bookRepository.findById(bookModel.getId());

                            if (existingBook.isPresent()) {
                                Console.log("El libro ya está guardado en la base de datos.");
                            } else {
                                try {
                                    bookRepository.save(bookModel);

                                    Optional<AuthorModel> existingAuthor = authorRepository
                                            .findByName(authorModel.getName());

                                    Console.log("-------------------------------------------------------------");
                                    Console.log("Su libro: '" + bookModel.getTitle() + "' a sido guardado en el registro.");
                                    Console.log("-------------------------------------------------------------");

                                    if (existingAuthor.isEmpty() && !Objects.equals(authorModel.getName(), "Desconocido"))
                                        authorRepository.save(authorModel);
                                }catch (Exception e){
                                    System.out.println("Ha fallado");
                                    throw new RuntimeException(e.getMessage());

                                }
                            }
                        }else Console.log("El libro a sido Descartado.");

                        boolean programContinue = OptionsMenu.ProgramContinue();
                        if (!programContinue) finish = true;
                    }

                	break;
                case "2" :
                    List<BookModel> listBooks = bookRepository.findAll();
                    listBooks.forEach(el -> Console.log(el.getBook()));

                    Console.log("Libros Guardados en Registro: " + (long) listBooks.size());

                    boolean programContinue2 = OptionsMenu.ProgramContinue();
                    if (!programContinue2) finish = true;
                    break;

                case "3" :
                    List<AuthorModel> listAuthors = authorRepository.findAll();
                    listAuthors.forEach(el -> Console.log(el.getAuthor()));

                    boolean programContinue3 = OptionsMenu.ProgramContinue();
                    if (!programContinue3) finish = true;
                    break;

                case "4" :
                    Console.log("""
                            Escriba un el año para encontrar el Autor.
                            ---------------------------------------------------
                            Se necesita un rango: ej 1500 a 1600.
                            Digite Primero en que año comienza el rango.
                            ---------------------------------------------------
                            Digite su respuesta ->
                            """);

                    Scanner inScanner = new Scanner(System.in);
                    Scanner outScanner = new Scanner(System.in);

                    try {
                        String first = inScanner.nextLine();

                        Console.log("Digite el año donde termina el rango.");
                        String second = outScanner.nextLine();

                        int firstYear;
                        int lastYear;

                        try {
                            firstYear = Integer.parseInt(first);
                            lastYear = Integer.parseInt(second);
                        } catch (NumberFormatException e) {
                            Console.log("Solo se aceptan números como respuesta.");
                            break;
                        }
                        if (firstYear == lastYear) lastYear++;
                        if (firstYear < lastYear){
                            List<AuthorModel> birthAuthors = authorRepository.findAll();
                            int counter = 0;

                            for (AuthorModel el : birthAuthors) {
                                if (el.getBirthYear() >= firstYear) {
                                    counter++;
                                    Console.log(el.getAuthor());
                                }
                            }
                            if (counter == 0) Console.log("No se encontró ningún Autor.");
                                else
                                    Console.log("Autores vivos dentro de los años " + firstYear + " a " + lastYear);

                        }else
                            Console.log("El rango de: " + firstYear + " Hasta " + lastYear + " no tiene sentido alguno.");

                    } catch (Exception e) {
                        Console.log("Ocurrió un error inesperado: " + e.getMessage());
                    }
                    boolean programContinue4 = OptionsMenu.ProgramContinue();
                    if (!programContinue4) finish = true;
                    break;

                case "5" :
                    Console.log("""
                                En que idioma desea filtrar los libros?
                                ---------------------------------------------------
                                es (Español)
                                en (Ingles)
                                ---------------------------------------------------
                                Digite su respuesta ->
                                """);

                    Scanner lan = new Scanner(System.in);
                    String languageSelected = lan.nextLine();

                    int counter = 0;
                    if (languageSelected.equals("es") || languageSelected.equals("en")) {
                        List<BookModel> authorsList = bookRepository.findAll();
                        for (BookModel el : authorsList) {
                            Set<String> languagesSet = new HashSet<>(el.getLanguages());

                            if (languagesSet.contains(languageSelected)) {
                                Console.log(el.getBook());
                                counter++;
                            }
                        }
                    } else {
                        Console.log("No se encontró esa opción.");
                    }

                    if (counter == 0) {
                        Console.log("No se encontró ningún libro.");
                    }

                    boolean programContinue5 = OptionsMenu.ProgramContinue();
                    if (!programContinue5) finish = true;
                    break;

                case "6" :
                    Console.log("Gracias Por Usar Nuestro Programa.");
                    finish = true;
                    break;

                default:
                    Console.log("No se encontró esa Opción.");
                    break;
            }
		}
	}
}
