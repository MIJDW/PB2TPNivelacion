package ar.edu.unlam.pb2.info;

import java.util.LinkedList;
import ar.edu.unlam.pb2.enumsDatos.*;

public class GradoInfoDocenteSecundaria extends GradoInfoPrimaria {
	
	protected LinkedList<Materias> materias = new LinkedList<Materias>();
	
	public GradoInfoDocenteSecundaria(Grados grado) {
		super(grado);
		super.nivel = NivelesDeEducacion.SECUNDARIA;
	}

	public LinkedList<Materias> getMaterias() {
		return this.materias;
	}
	
	public Boolean agregarMateria(Materias materia) {
		if(!this.materias.contains(materia)) {
			return this.materias.add(materia);
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarMateria(Materias materia) {
		if(this.materias.contains(materia)) {
			return this.materias.remove(materia);
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "GradoInfoDocenteSecundaria [materias=" + materias + ", nivel=" + nivel + ", grado=" + grado + "]";
	}
}
