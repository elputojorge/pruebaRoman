package xmlJorge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class xmlJorge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			leerEscribirArchivo("reserva_salon.xml", "datos_reserva.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void leerEscribirArchivo(String inputFile, String outputFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			bw.write("<reservas>\n");

			boolean insideReserva = false;

			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("<reserva>")) {
					insideReserva = true;
					bw.write("<reserva>\n");
				} else if (line.trim().startsWith("</reserva>")) {
					insideReserva = false;
					bw.write("</reserva>\n");
				} else if (insideReserva) {
					String trimmedLine = line.trim();
					if (esEtiquetaDeseada(trimmedLine)) {
						bw.write(line + "\n");
					}
				}
			}

			bw.write("</reservas>");
			System.out.println("Datos guardados en " + outputFile);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static boolean esEtiquetaDeseada(String line) {
		return line.startsWith("<nombre>") || line.startsWith("<fechaEvento>") || line.startsWith("<tipo>")
				|| line.startsWith("<asistentes>") || line.startsWith("<tipoCocina>")
				|| line.startsWith("<numeroJornadas>") || line.startsWith("<habitaciones>")
				|| line.startsWith("<tipoMesa>") || line.startsWith("<comensalesMesa>");
//		public static String reemplazarXml(String dato) {
//
//			return dato.replaceAll("<[^>]*>", "").replaceAll("    ", "");
//
//		}
//
//		public static String reemplazarJson(String dato) {
//			// \"[^\"]+\": esta parte es para la etiqueta por ejemplo "nombre":
//			// el \\s* me quita los espacios
//			// \"([^\"]*)\" esta parte ya es del dato
//			// ,? puede haber una ,
//			// y el $1 pilla el primer valor
//			return dato.replaceAll("\"[^\"]+\":\\s*\"([^\"]*)\",?", "$1");
//		}
	}

}
