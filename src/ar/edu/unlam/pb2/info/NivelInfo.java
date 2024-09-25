package ar.edu.unlam.pb2.info;

import java.util.Objects;
import ar.edu.unlam.pb2.enumsDatos.*;

public class NivelInfo{
	
	protected NivelesDeEducacion nivel;
	
	public NivelInfo(NivelesDeEducacion nivel) {
		this.nivel = nivel;
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nivel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NivelInfo other = (NivelInfo) obj;
		return nivel == other.nivel;
	}

	@Override
	public String toString() {
		return "NivelInfoDocente [nivel=" + nivel + "]";
	}	
}
