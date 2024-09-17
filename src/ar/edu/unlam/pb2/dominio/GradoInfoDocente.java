package ar.edu.unlam.pb2.dominio;
import java.util.LinkedList;
public class GradoInfoDocente {
	
	private NivelesDeEducacion nivel;
	private Grados grado;
	private LinkedList<Materias> materias = new LinkedList<Materias>();
	
	public GradoInfoDocente(NivelesDeEducacion nivel, Grados grado) {
		this.nivel = nivel;
		this.grado = grado;
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public LinkedList<Materias> getMaterias() {
		return this.materias;
	}
	
	public Boolean agregarMateria(Materias materia) {
		if(!this.materias.contains(materia) && this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
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
		return "GradoInfoDocente [nivel=" + nivel + ", grado=" + grado + "]";
	}
}
