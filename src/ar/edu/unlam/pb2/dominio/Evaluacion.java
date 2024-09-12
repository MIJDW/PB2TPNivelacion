package ar.edu.unlam.pb2.dominio;

public class Evaluacion {
	
	private String fecha;
	private NivelesDeEducacion nivel;
	private Grados grado;
	private Materias materia;
	
	private Docente docente;
	
	private Integer nota;
	private Boolean aprobado;
	
	public Evaluacion(NivelesDeEducacion nivel, Grados grado, Materias materia) {
		this.nivel = nivel;
		this.grado = grado;
		this.materia = materia;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	public Docente getDocente() {
		return this.docente;
	}
	public Boolean getAprobado() {
		return this.aprobado;
	}
	
	public Boolean agregarDocenteYFecha(Docente docente, String fecha) {
		Boolean agrego = Boolean.FALSE;
		if(this.docente == null && this.fecha == null) {
			this.docente = docente;
			this.fecha = fecha;
			agrego = Boolean.TRUE;
		}
		return agrego;
	}
	
	public void corregirEvaluacion(Integer nota) {
		if(this.docente != null) {
			this.nota = nota;
			if(nota >= 7) {
				this.aprobado = Boolean.TRUE;
			}else {
				this.aprobado = Boolean.FALSE;
			}
		}
	}

	@Override
	public String toString() {
		return "Evaluacion [fecha=" + fecha + ", nivel=" + nivel + ", grado=" + grado + ", materia=" + materia
				 + ", docente=" + docente + ", nota=" + nota + ", aprobado=" + aprobado + "]";
	}
	
}
