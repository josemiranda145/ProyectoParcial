/* QueryDatabase.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class QueryDatabase {
	
	final static String PersistenceUnit = "Sample";

	public static void main(String[] args) throws Throwable {
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		EntityTransaction entityTransaction;
		InputStreamReader stream;
		BufferedReader reader;
		String line;
		boolean quit;

		entityManagerFactory = Persistence.createEntityManagerFactory(PersistenceUnit);
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();

		stream = new InputStreamReader(System.in);
		reader = new BufferedReader(stream);

		do {
			line = readLine(reader);
			quit = interpretLine(line, entityTransaction, entityManager);
		} while (!quit);
	}
	
	private static String readLine(BufferedReader reader) throws Throwable {
		StringBuilder result;
		String line;
		
		result = new StringBuilder();
		do {
			line = reader.readLine();
			line = StringUtils.trim(line);
			result.append(line);
			result.append(' ');
		} while (line != null && !line.endsWith(";"));
		
		if (line != null && line.endsWith(";") && result.length() >= 2)
			result.deleteCharAt(result.length() - 2);
		
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	private static boolean interpretLine(String line, EntityTransaction entityTransaction, EntityManager entityManager) {
		boolean result;
		String command;
		Query query;
		List<Object> objects;
		int affected;

		result = false;
		if (!StringUtils.isBlank(line)) {
			try {
				command = StringUtils.trim(line);
				command = StringUtils.substringBefore(command, " ");
				switch (command) {
					case "quit":
						result = true;
						System.out.println("Bye, bye!");
						break;
					case "begin": 
						entityTransaction.begin();
						System.out.println("Transaction started");
						break;
					case "commit":
						entityTransaction.commit();
						System.out.println("Transaction committed");
						break;
					case "rollback": 
						entityTransaction.rollback();
						System.out.println("Transaction rollbacked");
						break;
					case "update":
					case "delete": 
						query = entityManager.createQuery(line);
						affected = query.executeUpdate();
						printAffected(affected);
						break;
					case "select": 
						query = entityManager.createQuery(line);
						objects = (List<Object>)query.getResultList();						
						printResultList(objects);
						break;
					default:
						System.err.println("Command not understood");
				}
			} catch (Throwable oops) {					
				oops.printStackTrace(System.err);
			}
		}
		
		return result;
	}

	private static void printAffected(int affected) {
		System.out.println(String.format("%d objects affected", affected));
	}

	private static void printResultList(List<Object> result) {
		System.out.println(String.format("%d objects found", result.size()));		
		for (Object obj : result) {
			printObject(obj);
			System.out.println();
		}
	}
	
	private static void printObject(Object obj) {		
		if (isPrimitive(obj))
			printPrimitive(obj);
		else if (isArray(obj))
			printArray(obj);
		else 
			printOther(obj);
	}

	private static void printPrimitive(Object obj) {
		System.out.print(obj);
	}
	
	private static void printArray(Object obj) {
		String comma;
		
		System.out.print("[");
		comma = "";
		for (Object subObj : (Object[]) obj) {
			System.out.print(comma);
			printObject(subObj);
			comma = ", ";
		}
		System.out.print("]");
	}
	

	private static void printOther(Object obj) {
		String text;
		
		text = ReflectionToStringBuilder.toString(obj);
		System.out.print(text);		
	}
	
	private static boolean isPrimitive(Object obj) {
		boolean result;
		
		result = (obj instanceof String || obj instanceof Number || obj instanceof Character || obj instanceof Boolean);
		return result;
	}

	private static boolean isArray(Object obj) {
		boolean result;
		
		result = (obj instanceof Object[]);
		
		return result;
	}
	
}
