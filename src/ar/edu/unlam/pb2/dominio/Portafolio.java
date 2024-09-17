package ar.edu.unlam.pb2.dominio;
import java.util.ArrayList;
public class Portafolio {

	private NivelesDeEducacion nivel;
	private Grados grado;
	private Materias materia;
	private ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();

	public Portafolio(NivelesDeEducacion nivel, Grados grado, Materias materia) {
		this.nivel = nivel;
		this.grado = grado;
		this.materia = materia;
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public Materias getMateria() {
		return this.materia;
	}
	public ArrayList<Evaluacion> getEvaluaciones() {
		return this.evaluaciones;
	}

	public Boolean agregarEvaluacion(Evaluacion evaluacion) {
		if(evaluacion.getNivel().equals(this.nivel) && evaluacion.getGrado().equals(this.grado)) {
			if((this.materia != null && evaluacion.getMateria().equals(this.materia)) || (this.materia == null && evaluacion.getMateria() == null)) {
				if(!this.evaluaciones.contains(evaluacion) && evaluacion.getDocente() != null && evaluacion.getNota() != null) {
					return this.evaluaciones.add(evaluacion);
				}
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "Portafolio [nivel=" + nivel + ", grado=" + grado + ", materia=" + materia + "]";
	}
}
