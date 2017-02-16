import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 * 		Clasa aceasta contine metoda main pentru testare. Se deschide
 *         fisierul de input, se creeaza un nou graf si se apeleaza metoda
 *         CreateGrap, din clasaConstructGraph, in care se va citit din fisierul
 *         de input si sevor aplica operatiile specificate asupra grafului .
 */

public class TestGraph {
	public static void main(String[] args) throws IOException {
		Graph graph = new Graph();
		FileReader file = new FileReader(args[0]);
		ConstructGraph creator = new ConstructGraph();
		creator.CreateGraph(file, graph);
		file.close();
	}
}
