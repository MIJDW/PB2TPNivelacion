package ar.edu.unlam.pb2.info;
import java.util.Objects;

import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.personas.*;

public class MateriaOcupada {
	
	private Materias materia;
	private Docente docente;
	
	public MateriaOcupada(Docente docente, Materias materia) {
		this.docente = docente;
		this.materia = materia;
	}

	public Materias getMateria() {
		return materia;
	}
	public Docente getDocente() {
		return docente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(materia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MateriaOcupada other = (MateriaOcupada) obj;
		return materia == other.materia;
	}

	@Override
	public String toString() {
		return "MateriaOcupada [materia=" + materia + ", docente=" + docente + "]";
	}
}
