package ar.edu.unlam.pb2.dominio;
import java.util.Objects;
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
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public Materias getMateria() {
		return this.materia;
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
	public Integer getNota() {
		return this.nota;
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
		Evaluacion other = (Evaluacion) obj;
		return Objects.equals(fecha, other.fecha);
	}

	@Override
	public String toString() {
		return "Evaluacion [fecha=" + fecha + ", nivel=" + nivel + ", grado=" + grado + ", materia=" + materia
				 + ", docente=" + docente + ", nota=" + nota + ", aprobado=" + aprobado + "]";
	}
}
