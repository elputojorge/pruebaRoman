package xmlJorge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReservaDatos {
	String nombre = null;
	String fechaEvento = null;
	String tipo = null;
	String asistentes = null;
	String tipoCocina = null;
	String numeroJornadas = null;
	String habitaciones = null;
	String tipoMesa = null;
	String comenslesMesa = null;

	public ReservaDatos() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(String asistentes) {
		this.asistentes = asistentes;
	}

	public String getTipoCocina() {
		return tipoCocina;
	}

	public void setTipoCocina(String tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	public String getNumeroJornadas() {
		return numeroJornadas;
	}

	public void setNumeroJornadas(String numeroJornadas) {
		this.numeroJornadas = numeroJornadas;
	}

	public String getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(String habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getTipoMesa() {
		return tipoMesa;
	}

	public void setTipoMesa(String tipoMesa) {
		this.tipoMesa = tipoMesa;
	}

	public String getComenslesMesa() {
		return comenslesMesa;
	}

	public void setComenslesMesa(String comenslesMesa) {
		this.comenslesMesa = comenslesMesa;
	}

	public void guardarDatosXml(String line) {

		if (esEtiquetaDeseada(line)) {

			if (line.startsWith("<nombre>")) {
				this.setNombre(reemplazarXml(line));
			}
			if (line.startsWith("<fechaEvento>")) {
				this.setFechaEvento(reemplazarXml(line));
			}
			if (line.startsWith("<tipo>")) {
				this.setTipo(reemplazarXml(line));
			}
			if (line.startsWith("<asistentes>")) {
				this.setAsistentes(reemplazarXml(line));
			}
			if (line.startsWith("<tipoCocina>")) {
				this.setTipoCocina(reemplazarXml(line));
			}
			if (line.startsWith("<numeroJornadas>")) {
				this.setNumeroJornadas(reemplazarXml(line));
			}
			if (line.startsWith("<habitaciones>")) {
				this.setHabitaciones(reemplazarXml(line));
			}
			if (line.startsWith("<tipoMesa>")) {
				this.setTipoMesa(reemplazarXml(line));
			}
			if (line.startsWith("<comensalesMesa>")) {
				this.setComenslesMesa(reemplazarXml(line));
			}
		}
	}

	public void guardarDatosJson(String line) {

		if (esEtiquetaDeseada(line)) {
			if (line.startsWith("\"nombre\"")) {
				this.setNombre(reemplazarJson(line));
			}
			if (line.startsWith("\"fechaEvento\"")) {
				this.setFechaEvento(reemplazarJson(line));
			}
			if (line.startsWith("\"tipo\"")) {
				this.setTipo(reemplazarJson(line));
			}
			if (line.startsWith("\"asistentes\"")) {
				this.setAsistentes(reemplazarJson(line));
			}
			if (line.startsWith("\"tipoCocina\"")) {
				this.setTipoCocina(reemplazarJson(line));
			}
			if (line.startsWith("\"numeroJornadas\"")) {
				this.setNumeroJornadas(reemplazarJson(line));
			}
			if (line.startsWith("\"habitaciones\"")) {
				this.setHabitaciones(reemplazarJson(line));
			}
			if (line.startsWith("\"tipoMesa\"")) {
				this.setTipoMesa(reemplazarJson(line));
			}
			if (line.startsWith("\"comensalesMesa\"")) {
				this.setComenslesMesa(reemplazarJson(line));
			}
		}
	}

	public static String reemplazarXml(String dato) {

		return dato.replaceAll("<[^>]*>", "").replaceAll("    ", "");

	}
	// \"[^\"]+\": esta parte es para la etiqueta por ejemplo "nombre":
	// el \\s* me quita los espacios
	// \"([^\"]*)\" esta parte ya es del dato
	// ,? puede haber una ,
	// y el $1 pilla el primer valor
	public static String reemplazarJson(String dato) {
		return dato.replaceAll("\"[^\"]+\":\\s*\"([^\"]*)\",?", "$1");
	}

	public static boolean esEtiquetaDeseada(String line) {
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
}
