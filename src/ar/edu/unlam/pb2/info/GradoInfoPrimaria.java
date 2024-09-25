package ar.edu.unlam.pb2.info;
import java.util.Objects;
import ar.edu.unlam.pb2.enumsDatos.*;

public class GradoInfoPrimaria{
	
	protected NivelesDeEducacion nivel;
	protected Grados grado;
	
	public GradoInfoPrimaria(Grados grado) {
		this.nivel = NivelesDeEducacion.PRIMARIA;
		this.grado = grado;
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(grado, nivel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradoInfoPrimaria other = (GradoInfoPrimaria) obj;
		return grado == other.grado && nivel == other.nivel;
	}

	@Override
	public String toString() {
		return "GradoInfoPrimaria [nivel=" + nivel + ", grado=" + grado + "]";
	}
}
