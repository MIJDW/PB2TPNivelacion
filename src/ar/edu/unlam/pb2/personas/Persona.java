package ar.edu.unlam.pb2.personas;
import java.util.LinkedList;
import java.util.Objects;

import ar.edu.unlam.pb2.info.*;
public class Persona {
	
	protected LinkedList<NivelInfo> niveles = new LinkedList<NivelInfo>();
	protected LinkedList<GradoInfoPrimaria> grados = new LinkedList<GradoInfoPrimaria>();
	protected String nombre;
	protected String apellido;
	protected Integer edad;
	protected Integer dni;
	
	public Persona(String nombre, String apellido, Integer edad, Integer dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}
	public String getApellido() {
		return this.apellido;
	}
	public Integer getEdad() {
		return this.edad;
	}
	public Integer getDni() {
		return this.dni;
	}
	public LinkedList<NivelInfo> getNiveles() {
		return this.niveles;
	}
	public LinkedList<GradoInfoPrimaria> getGrados() {
		return this.grados;
	}
	
	protected Boolean agregarNivel(NivelInfo info) {
		if(!getNiveles().contains(info)) {
			return this.niveles.add(info);
		}
		return Boolean.FALSE;
	}
	
	protected Boolean eliminarNivel(NivelInfo info) {
		if(getNiveles().contains(info)) {
			return this.niveles.remove(info);
		}
		return Boolean.FALSE;
	}
	
	protected Boolean agregarGrado(GradoInfoPrimaria grado) {
		if(!getGrados().contains(grado)) {
			return this.grados.add(grado);
		}
		return Boolean.FALSE;
	}
	
	protected Boolean eliminarGrado(GradoInfoPrimaria grado) {
		if(getGrados().contains(grado)) {
			return this.grados.remove(grado);
		}
		return Boolean.FALSE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", dni=" + dni + "]";
	}
	
}
