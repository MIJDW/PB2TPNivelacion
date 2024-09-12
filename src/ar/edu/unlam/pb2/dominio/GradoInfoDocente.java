package ar.edu.unlam.pb2.dominio;

import java.util.Arrays;

public class GradoInfoDocente {
	
	private NivelesDeEducacion nivel;
	private Grados grado;
	private Materias[] materias;
	
	public GradoInfoDocente(NivelesDeEducacion nivel, Grados grado) {
		this.nivel = nivel;
		this.grado = grado;
		iniciarMaterias();
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public Materias[] getMaterias() {
		return this.materias;
	}
	
	private void iniciarMaterias() {
		if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			this.materias = new Materias[Materias.values().length];
		}
	}
	
	public Boolean contieneMateria(Materias materia) {
		Boolean contiene = Boolean.FALSE;
		if(this.materias != null) {
			for(Integer i = 0; i < this.materias.length && !contiene; i++) {
				if(this.materias[i] != null) {
					if(this.materias[i].equals(materia)) {
						contiene = Boolean.TRUE;
					}
				}	
			}
		}
		return contiene;
	}
	
	public Boolean agregarMateria(Materias materia) {
		Boolean agrego = Boolean.FALSE;
		if(this.materias != null) {
			if(!contieneMateria(materia)) {
				this.materias[materia.ordinal()] = materia;
				agrego = Boolean.TRUE;
			}
		}
		return agrego;
	}
	
	public Boolean eliminarMateria(Materias materia) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneMateria(materia)) {
			this.materias[materia.ordinal()] = null;
		}
		return eliminar;
	}

	@Override
	public String toString() {
		return "GradoInfoDocente [nivel=" + nivel + ", grado=" + grado + ", materias=" + Arrays.toString(materias)
				+ "]";
	}
	
}
