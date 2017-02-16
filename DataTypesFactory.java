/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folosita pentru a facilita instantierea listelor de adiacenta
 *         <p>
 *         Vom folosi design pattern-ul de factory si de singleton
 */
public class DataTypesFactory {
	/**
	 * Pentru a avea o singura instanta de factory
	 */
	private static DataTypesFactory instance = null;

	/**
	 * Constructor privat, pentru a nu putea fi apelat constructorul default
	 */
	private DataTypesFactory() {
	}

	/**
	 * 
	 * @return o instanta de factory
	 */
	public static DataTypesFactory getInstance() {
		/**
		 * Daca factory-ul nu a fost instantiat pana acum, instantiaza-l
		 */
		if (instance == null) {
			instance = new DataTypesFactory();
		}
		/**
		 * Altfel, returneaza instanta deja existenta
		 */
		return instance;
	}

	/**
	 * 
	 * @param listType
	 *            un string care reprezinta tipul de lista pe adiacenta pe care
	 *            il dorim
	 * @return instanta ceruta listei a listei
	 */
	public DataTypes getDataType(String listType) {
		/*
		 * In functie de string-ul primit, returneaza un vector, o lista sau un
		 * set
		 */
		switch (listType) {
		case "SET":
			return new MySet();
		case "ARRAY":
			return new Array();
		case "LIST":
			return new List();
		default:
			break;
		}
		return null;
	}
}
