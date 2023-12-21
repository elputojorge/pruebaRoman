package xmlJorge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class xmlJorge2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReservaDatos datos = new ReservaDatos();
		ArrayList<String> json = new ArrayList<String>();
		ArrayList<String> xml = new ArrayList<String>();
		Scanner teclado = new Scanner(System.in);

		String archivoReserva;
		String archivoSalida;
		

		while (true) {
			System.out.println(
					"¿Qué archivo de reserva quieres procesar? (Ej. reserva_salon.xml o reserva_salon.json, o escribe 'EXIT' para salir)");
			archivoReserva = teclado.nextLine();
			System.out.println("¿En que formato quieres guardar la salida? (xml o json)");
			archivoSalida = teclado.nextLine();
			

			if (archivoReserva.equals("EXIT")) {
				System.out.println("Saliendo del programa.");
				break; // Sale del bucle

				// ARCHIVO XML
			} else if (archivoReserva.toLowerCase().endsWith(".xml")) {
				System.out.println("Procesando el archivo: " + archivoReserva);
				// DE XML A XML
				if (archivoSalida.equals("xml")) {
					try {

						leerEscribirArchivoXmlXml(archivoReserva, "datos_reserva.xml", datos, xml);
						System.out.println("Procesamiento completado.");
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// DE XML A JSON
				else if (archivoSalida.equals("json")) {
					try {

						leerEscribirArchivoXmlJson(archivoReserva, "datos_reserva.json", datos, json);
						System.out.println("Procesamiento completado.");
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Archivo no válido.");
					break;
				}

				// ARCHIVO JSON
			} else if (archivoReserva.toLowerCase().endsWith(".json")) {
				System.out.println("Procesando el archivo: " + archivoReserva);
				// DE JSON A JSON
				if (archivoSalida.equals("json")) {
					try {

						leerEscribirArchivoJsonJson(archivoReserva, "datos_reserva.json", datos, json);
						System.out.println("Procesamiento completado.");
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				//DE JSON A XML
				else if (archivoSalida.equals("xml")) {
					try {

						leerEscribirArchivoJsonXml(archivoReserva, "datos_reserva.xml", datos, xml);
						System.out.println("Procesamiento completado.");
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("Archivo no válido.");
					break;
				}

				//por si lo escribe mal
			} else {
				System.out.println("Archivo no válido. Debe ser un archivo .xml o .json o escribe 'EXIT' para salir.");
			}

		}
	}

	public static void leerEscribirArchivoXmlJson(String inputFile, String outputFile, ReservaDatos datos,
			ArrayList<String> json) {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

			boolean insideReserva = false;

			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("<reserva>")) {
					insideReserva = true;
					bw.write("{\n");
					bw.write("\"reserva\":{\n");
				} else if (insideReserva) {
					String trimmedLine = line.trim();
					if (esEtiquetaDeseada(trimmedLine)) {

						datos.guardarDatosXml(trimmedLine);

					}
				}
			}

			pasarJson(json, datos);
			for (String string : json) {
				bw.write(string + "\n");
			}

			bw.write("}");
			bw.write("\n}");
			System.out.println("Datos guardados en " + outputFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void leerEscribirArchivoXmlXml(String inputFile, String outputFile, ReservaDatos datos,
			ArrayList<String> xml) {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

			boolean insideReserva = false;

			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("<reserva>")) {
					insideReserva = true;
					bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
					bw.write("<reserva>\n");
				} else if (insideReserva) {
					String trimmedLine = line.trim();
					if (esEtiquetaDeseada(trimmedLine)) {

						datos.guardarDatosXml(trimmedLine);

					}
				}
			}

			pasarXml(xml, datos);
			for (String string : xml) {
				bw.write(string + "\n");
			}

			bw.write("</reserva>");
			System.out.println("Datos guardados en " + outputFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void leerEscribirArchivoJsonJson(String inputFile, String outputFile, ReservaDatos datos,
			ArrayList<String> json) {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

			boolean insideReserva = false;

			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("\"reserva\":")) {
					insideReserva = true;
					bw.write("{\n");
					bw.write("\"reserva\":{\n");
				} else if (insideReserva) {
					String trimmedLine = line.trim();
					if (esEtiquetaDeseada(trimmedLine)) {
						datos.guardarDatosJson(trimmedLine);

					}
				}
			}

			pasarJson(json, datos);
			for (String string : json) {
				bw.write(string + "\n");
			}

			bw.write("}");
			bw.write("\n}");
			System.out.println("Datos guardados en " + outputFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void leerEscribirArchivoJsonXml(String inputFile, String outputFile, ReservaDatos datos,
			ArrayList<String> xml) {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

			boolean insideReserva = false;

			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("\"reserva\":")) {
					insideReserva = true;
					bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
					bw.write("<reserva>\n");
				} else if (insideReserva) {
					String trimmedLine = line.trim();
					if (esEtiquetaDeseada(trimmedLine)) {
						datos.guardarDatosJson(trimmedLine);

					}
				}
			}

			pasarXml(xml, datos);
			for (String string : xml) {
				bw.write(string + "\n");
			}

			bw.write("</reserva>");
			System.out.println("Datos guardados en " + outputFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static boolean esEtiquetaDeseada(String line) {
		return line.startsWith("<nombre>") || line.startsWith("<fechaEvento>") || line.startsWith("<tipo>")
				|| line.startsWith("<asistentes>") || line.startsWith("<tipoCocina>")
				|| line.startsWith("<numeroJornadas>") || line.startsWith("<habitaciones>")
				|| line.startsWith("<tipoMesa>") || line.startsWith("<comensalesMesa>")
				// etiquetas de Json
				|| line.startsWith("\"nombre\"") || line.startsWith("\"fechaEvento\"") || line.startsWith("\"tipo\"")
				|| line.startsWith("\"asistentes\"") || line.startsWith("\"tipoCocina\"")
				|| line.startsWith("\"numeroJornadas\"") || line.startsWith("\"habitaciones\"")
				|| line.startsWith("\"tipoMesa\"") || line.startsWith("\"comensalesMesa\"");
	}

	public static void pasarJson(ArrayList<String> json, ReservaDatos datos) {
		json.add("\"nombre\":\"" + datos.getNombre() + "\",");
		json.add("\"fechaEvento\":\"" + datos.getFechaEvento() + "\",");
		json.add("\"tipo\":\"" + datos.getTipo() + "\",");
		json.add("\"asistentes\":\"" + datos.getAsistentes() + "\",");
		json.add("\"tipoCocina\":\"" + datos.getTipoCocina() + "\",");
		json.add("\"numeroJornadas\":\"" + datos.getNumeroJornadas() + "\",");
		json.add("\"habitaciones\":\"" + datos.getHabitaciones() + "\",");
		json.add("\"tipoMesa\":\"" + datos.getTipoMesa() + "\",");
		json.add("\"comensalesMesa\":\"" + datos.getComenslesMesa() + "\"");
	}

	public static void pasarXml(ArrayList<String> xml, ReservaDatos datos) {
		xml.add("<nombre>" + datos.getNombre() + "</nombre>");
		xml.add("<fechaEvento>" + datos.getFechaEvento() + "</fechaEvento>");
		xml.add("<tipo>" + datos.getTipo() + "</tipo>");
		xml.add("<asistentes>" + datos.getAsistentes() + "</asistentes>");
		xml.add("<tipoCocina>" + datos.getTipoCocina() + "</tipoCocina>");
		xml.add("<numeroJornadas>" + datos.getNumeroJornadas() + "</numeroJornadas>");
		xml.add("<habitaciones>" + datos.getHabitaciones() + "</habitaciones>");
		xml.add("<tipoMesa>" + datos.getTipoMesa() + "</tipoMesa>");
		xml.add("<comensalesMesa>" + datos.getComenslesMesa() + "</comensalesMesa>");
	}

}