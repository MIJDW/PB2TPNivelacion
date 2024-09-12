package ar.edu.unlam.pb2.dominio;

public class Portafolio {
	
	private NivelesDeEducacion nivel;
	private Grados grado;
	private Materias materia;
	
	private Evaluacion[] evaluaciones;

	public Portafolio(NivelesDeEducacion nivel, Grados grado, Materias materia, Integer cantidadDeEvaluaciones) {
		this.nivel = nivel;
		this.grado = grado;
		this.materia = materia;
		this.evaluaciones = new Evaluacion[cantidadDeEvaluaciones];
		iniciarEvaluaciones();
	}
	
	public Materias getMateria() {
		return this.materia;
	}
	public Evaluacion[] getEvaluaciones() {
		return this.evaluaciones;
	}
	
	private void iniciarEvaluaciones() {
		for(Integer i = 0; i < this.evaluaciones.length; i++) {
			if(this.evaluaciones[i] == null) {
				this.evaluaciones[i] = new Evaluacion(this.nivel, this.grado, this.materia);
			}
		}
	}
	
	public Evaluacion obtenerEvaluacionParaCorregir() {
		Evaluacion evaluacion = null;
		Boolean obtuvo = Boolean.FALSE;
		for(Integer i = 0; i < this.evaluaciones.length && !obtuvo; i++) {
			if(this.evaluaciones[i].getFecha() == null && this.evaluaciones[i].getDocente() == null) {
				evaluacion = this.evaluaciones[i];
				obtuvo = Boolean.TRUE;
			}
		}
		return evaluacion;
	}

	@Override
	public String toString() {
		return "Portafolio [nivel=" + nivel + ", grado=" + grado + ", materia=" + materia + "]";
	}
	
}
