import java.io.BufferedWriter;
import java.io.IOException;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folosita pentru a afisa informatii despre nod in timpul
 *         serializarii si deserializarii
 *
 */
public class Display {
	/**
	 * Constructor
	 */
	public Display() {
	}

	/**
	 * 
	 * @param node
	 *            nodul a carui informatie va fi afisata
	 * @param writer
	 *            writer-ul cu care se va scrie in fisier
	 * @param tab
	 *            numarul de tab-uri din afisare
	 * @throws IOException
	 *             in caz ca se vor genera erori la scrierea in fisier
	 *             <p>
	 *             Metoda afiseaza informatiile despre nodul din tag-urile de tip
	 *             object: Tipul nodului,Versiunea si ID-ul, punand in prealabil
	 *             un numar corespunzator de tab-uri.
	 */
	public void DisplayObject(Nod node, BufferedWriter writer, int tab) throws IOException {
		for (int i = 0; i < tab; i++)
			writer.write("\t");

		writer.write("<Object class=" + "\"" + node.GetNodeType() + "\"" + " " + "Version=" + "\"" + node.getVersion()
				+ "\"" + " " + "id=" + "\"" + node.getId() + "\"" + ">");

		writer.write("\n");
	}

	/**
	 * 
	 * @param node
	 *            nodul al carui numa va fi afisat
	 * @param writer
	 *            writer-ul cu care se va scrie in fisier
	 * @param tab
	 *            numarul de tab-uri din afisare
	 * @throws IOException
	 *             in caz de exceptii la scriere
	 *             <p>
	 *             metoda afiseaza numele nodului curent intre tag-urile Nume
	 *             si /Nume punand in prealabil un numar corespunzator de
	 *             tab-uri
	 */
	public void DisplayName(Nod node, BufferedWriter writer, int tab) throws IOException {
		for (int i = 0; i < tab; i++)
			writer.write("\t");

		writer.write("<Nume>" + node.getName() + "</Nume>");
		writer.write("\n");
	}

	/**
	 * 
	 * @param node
	 *            nodul a carui lista de adiacenta va fi afisata
	 * @param writer
	 *            writer-ul cu care se scrie in fisier
	 * @param tab
	 *            numar corespunzator de tab-uri
	 * @throws IOException
	 *             in caz ca apar erori de scriere
	 *             <p>
	 *             Metoda verifica cum este implementata lista de adiacenta a
	 *             nodului curent, apeland metoda toString a listei, si deschide
	 *             in mod corespunzator un tag pentru list, set sau vector in
	 *             functie de rezultatul apelului
	 */
	public void DisplayList(Nod node, BufferedWriter writer, int tab) throws IOException {
		for (int i = 0; i < tab; i++)
			writer.write("\t");

		if (node.getAdjacencyList().toString().contentEquals("SET"))
			writer.write("<SET>");
		else if (node.getAdjacencyList().toString().contentEquals("ARRAY"))
			writer.write("<VECTOR>");
		else
			writer.write("<LIST>");

		writer.write("\n");
	}

	/**
	 * 
	 * @param node
	 *            nodul a carui informatie va fi afisata
	 * @param writer
	 *            writer-ul cu care se va scrie in fisier
	 * @param tab
	 *            numarul de tab-uri din afisare
	 * @throws IOException
	 *             in caz ca apar erori la scriere
	 *             <p>
	 *             Metoda afiseaza informatiile despre nod din tag-urile de tip
	 *             Reference: Tipul nodului,Versiunea si ID-ul, prin apelurile
	 *             corespunzatoare de getteri, punand in prealabil un numar
	 *             corespunzator de tab-uri.
	 */
	public void DisplayReferences(Nod node, BufferedWriter writer, int tab) throws IOException {
		for (int j = 0; j < tab; j++)
			writer.write("\t");

		writer.write("<Reference class=" + "\"" + node.GetNodeType() + "\"" + " Version=" + "\"" + node.getVersion()
				+ "\"" + " " + "id=" + "\"" + node.getId() + "\"" + ">");
		writer.write("\n");
	}

	/**
	 * 
	 * @param node
	 *            nodul a carui lista de adiacenta va fi afisata
	 * @param writer
	 *            writer-ul cu care se scrie in fisier
	 * @param tab
	 *            numar corespunzator de tab-uri
	 * @throws IOException
	 *             in caz ca apar erori de scriere
	 *             <p>
	 *             Metoda verifica cum este implementata lista de adiacenta a
	 *             nodului curent, apeland metoda toString a listei, si inchide
	 *             in mod corespunzator un tag pentru list, set sau vector in
	 *             functie de rezultatul apelului
	 */
	public void CloseListTags(Nod node, BufferedWriter writer, int tab) throws IOException {
		for (int i = 0; i < tab; i++)
			writer.write("\t");

		if (node.getAdjacencyList().toString().contentEquals("SET"))
			writer.write("</SET>");
		else if (node.getAdjacencyList().toString().contentEquals("ARRAY"))
			writer.write("</VECTOR>");
		else
			writer.write("</LIST>");

	}

	/**
	 * 
	 * @param node
	 *            nodul a carui informatie urmeaza sa fie afisata
	 * @param deserialize_writer
	 *            writer-ul cu care se vascrie in fisier
	 * @param a
	 *            vector cu setarile nodurilor de tip NodA
	 * @param b
	 *            vector cu setarile nodurilor de tip NodB
	 * @param c
	 *            vector cu setarile nodurilor de tip NodC
	 * @param k
	 *            pozitia curenta din vector
	 * @throws IOException
	 *             in caz ca se genereaza exceptii la scrierea in fisier
	 *             <p>
	 *             Metoda afiseaza mesajul de "Ok cast" si informatiile nodului
	 *             in functie de tipul acestuia (Tip, Nume, versiunea pecedenta
	 *             si versiunea curenta)
	 */
	public void DisplayOKCast(Nod node, BufferedWriter deserialize_writer, int[] a, int[] b, int[] c, int k)
			throws IOException {
		if (node.GetNodeType().contentEquals("NodA")) {
			deserialize_writer.write("OK cast " + node.GetNodeType() + " " + node.getName() + " " + "from Version="
					+ "\"" + a[k - 1] + "\"" + " to Version=" + "\"" + a[k] + "\"");
		} else if (node.GetNodeType().contentEquals("NodB")) {
			deserialize_writer.write("OK cast " + node.GetNodeType() + " " + node.getName() + " " + "from Version="
					+ "\"" + b[k - 1] + "\"" + " to Version=" + "\"" + b[k] + "\"");
		} else {
			deserialize_writer.write("OK cast " + node.GetNodeType() + " " + node.getName() + " " + "from Version="
					+ "\"" + c[k - 1] + "\"" + " to Version=" + "\"" + c[k] + "\"");
		}
	}

	/**
	 * 
	 * @param node
	 *            nodul a carui informatie urmeaza sa fie afisata
	 * @param deserialize_writer
	 *            writer-ul cu care se vascrie in fisier
	 * @param a
	 *            vector cu setarile nodurilor de tip NodA
	 * @param b
	 *            vector cu setarile nodurilor de tip NodB
	 * @param c
	 *            vector cu setarile nodurilor de tip NodC
	 * @param k
	 *            pozitia curenta din vector
	 * @throws IOException
	 *             in caz ca se genereaza exceptii la scrierea in fisier
	 *             <p>
	 *             Metoda afiseaza mesajul de "Fail cast" si informatiile
	 *             nodului in functie de tipul acestuia (Tip, Nume, versiunea
	 *             pecedenta si versiunea curenta)
	 */
	public void DisplayFailCast(Nod node, BufferedWriter deserialize_writer, int[] a, int[] b, int[] c, int k)
			throws IOException {
		if (node.GetNodeType().contentEquals("NodA")) {
			deserialize_writer.write("Fail cast " + node.GetNodeType() + " " + node.getName() + " " + "from Version="
					+ "\"" + a[k - 1] + "\"" + " to Version=" + "\"" + a[k] + "\"");
		} else if (node.GetNodeType().contentEquals("NodB")) {
			deserialize_writer.write("Fail cast " + node.GetNodeType() + " " + node.getName() + " " + "from Version="
					+ "\"" + b[k - 1] + "\"" + " to Version=" + "\"" + b[k] + "\"");
		} else {
			deserialize_writer.write("Fail cast " + node.GetNodeType() + " " + node.getName() + " " + "from Version="
					+ "\"" + c[k - 1] + "\"" + " to Version=" + "\"" + c[k] + "\"");
		}
	}

}
