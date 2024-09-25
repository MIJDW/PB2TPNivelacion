package ar.edu.unlam.pb2.personas;

import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.info.*;

public class Docente extends Persona{
	
	public Docente(String nombre, String apellido, Integer edad, Integer dni) {
		super(nombre, apellido, edad, dni);
	}
	
	public Boolean agregarNivelDeCompetencia(NivelesDeEducacion nivel) {
		NivelInfo nivelInfo = new NivelInfo(nivel);
		return agregarNivel(nivelInfo);
	}
	
	public Boolean eliminarNivelDeCompetencia(NivelesDeEducacion nivel) {
		NivelInfo nivelInfo = new NivelInfo(nivel);
		return eliminarNivel(nivelInfo);
	}
	
	public Boolean agregarGradoDeCompetencia(GradoInfoPrimaria grado) {
		for(NivelInfo nivel : getNiveles()) {
			if(nivel.getNivel().equals(grado.getNivel())) {
				return agregarGrado(grado);
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarGradoDeCompetencia(GradoInfoPrimaria grado) {
		return eliminarGrado(grado);
	}
	
	public GradoInfoPrimaria obtenerGradoDeCompetencia(NivelesDeEducacion nivel, Grados grado) {
		GradoInfoPrimaria gradoInfoPrimaria = null;
		for(GradoInfoPrimaria gradoInfo : getGrados()) {
			if(gradoInfo.getNivel().equals(nivel) && gradoInfo.getGrado().equals(grado)) {
				gradoInfoPrimaria = gradoInfo;
			}
		}
		return gradoInfoPrimaria;
	}
	
}
