package ar.edu.unlam.pb2.info;

import java.util.Objects;

import ar.edu.unlam.pb2.enumsDatos.*;

public class Asistencia {
	
	private String fecha;
	private OpcionesDeAsistencia asistencia;
	
	public Asistencia(String fecha, OpcionesDeAsistencia asistencia) {
		this.fecha = fecha;
		this.asistencia = asistencia;
	}

	public String getFecha() {
		return fecha;
	}
	public OpcionesDeAsistencia getAsistencia() {
		return asistencia;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asistencia other = (Asistencia) obj;
		return Objects.equals(fecha, other.fecha);
	}

	@Override
	public String toString() {
		return "Asistencia [fecha=" + fecha + ", asistencia=" + asistencia + "]";
	}
}
