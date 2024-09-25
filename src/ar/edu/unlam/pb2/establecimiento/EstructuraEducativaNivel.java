package ar.edu.unlam.pb2.establecimiento;
import java.util.ArrayList;
import java.util.Objects;

import ar.edu.unlam.pb2.enumsDatos.*;

public class EstructuraEducativaNivel {
	
	protected NivelesDeEducacion nivel;
	protected ArrayList<EstructuraEducativaDivisiones> divisiones = new ArrayList<EstructuraEducativaDivisiones>();
	
	public EstructuraEducativaNivel() {
	}

	public NivelesDeEducacion getNivel() {
		return nivel;
	}
	public ArrayList<EstructuraEducativaDivisiones> getDivisiones() {
		return divisiones;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nivel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstructuraEducativaNivel other = (EstructuraEducativaNivel) obj;
		return nivel == other.nivel;
	}

	@Override
	public String toString() {
		return "EstructuraEducativaNivel [nivel=" + nivel + "]";
	}
}
