package ar.edu.unlam.pb2.personas;

import java.util.LinkedList;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.info.*;

public class Alumno extends Persona {
	
	private LinkedList<Salas> salasCursadas = new LinkedList<Salas>();
	private LinkedList<Asistencia> asistencias = new LinkedList<Asistencia>();
	private Boolean evaluable;
	
	public Alumno(String nombre, String apellido, Integer edad, Integer dni) {
		super(nombre, apellido, edad, dni);
	}
	
	public LinkedList<Salas> getSalasCursadas() {
		return this.salasCursadas;
	}
	public Boolean getEvaluable() {
		return this.evaluable;
	}
	public LinkedList<Asistencia> getAsistencias() {
		return asistencias;
	}

	public void subirEdad() {
		super.edad++;
	}
	
	private void alumnoEsEvaluable(NivelInfo nivelInfo) {
		if(nivelInfo.getNivel().equals(NivelesDeEducacion.PRIMARIA) || nivelInfo.getNivel().equals(NivelesDeEducacion.SECUNDARIA)){
			this.evaluable = Boolean.TRUE;
		}else {
			this.evaluable = Boolean.FALSE;
		}
	}
	
	private Integer indiceDelNivelDeEducacionMasAlto() {
		Integer indice = -1;
		if(getNiveles().size() > 0) {
			for(NivelInfo nivelInfo : getNiveles()) {
				if(nivelInfo.getNivel().ordinal() > indice) {
					indice = nivelInfo.getNivel().ordinal();
				}
			}
		}
		return indice;	
	}
	
	public Boolean verificarSiAproboNivel(NivelesDeEducacion nivel) {
		Boolean aprobo = Boolean.FALSE;
		Integer contadorDeGrados = 0;
		Integer contadorDeAprobados = 0;
		if(getGrados().size() > 0) {
			for(GradoInfoPrimaria gradoInfoPrimaria : getGrados()) {
				if(gradoInfoPrimaria.getNivel().equals(nivel)) {
					contadorDeGrados++;
					GradoInfoAlumnoPrimaria gradoInfoAlumnoPrimaria = (GradoInfoAlumnoPrimaria) gradoInfoPrimaria;
					gradoInfoAlumnoPrimaria.comprobarSituacionAcademica();
					if(gradoInfoAlumnoPrimaria.getAprobado()) {
						contadorDeAprobados++;
					}
				}
			}
		}
		if(contadorDeGrados == contadorDeAprobados) {
			aprobo = Boolean.TRUE;
			obtenerNivelDeEducacion(nivel).aprobar();
		}
		return aprobo;
	}
	
	public Boolean elAlumnoEstaDentroDelRangoDeEdad(NivelesDeEducacion nivel) {
		Boolean dentro = Boolean.FALSE;
		if(nivel.equals(NivelesDeEducacion.JARDIN)) {
			if(this.edad >= 2 && this.edad <= 5) {
				dentro = Boolean.TRUE;
			}
		}else if(nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			if(this.edad >= 6 && this.edad <= 11) {
				dentro = Boolean.TRUE;
			}
		}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			if(this.edad >= 12 && this.edad <= 17) {
				dentro = Boolean.TRUE;
			}
		}
		return dentro;
	}
	
	public Boolean agregarNivelDeEducacion(NivelesDeEducacion nivel) {
		NivelInfoAlumno nivelInfoAlumno = new NivelInfoAlumno(nivel);
		if(elAlumnoEstaDentroDelRangoDeEdad(nivel)) {
			if(getNiveles().size() > 0 && nivel.ordinal() == 2) {
				if(verificarSiAproboNivel(NivelesDeEducacion.values()[nivel.ordinal()-1])) {
					return super.agregarNivel(nivelInfoAlumno);
				}
			}else if(nivel.ordinal() > indiceDelNivelDeEducacionMasAlto()) {
				return super.agregarNivel(nivelInfoAlumno);
			}
			alumnoEsEvaluable(nivelInfoAlumno);
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarNivelEducacion(NivelesDeEducacion nivel) {
		NivelInfoAlumno nivelInfoAlumno = new NivelInfoAlumno(nivel);
		return super.eliminarNivel(nivelInfoAlumno);
	}
	
	public NivelInfoAlumno obtenerNivelDeEducacion(NivelesDeEducacion nivel) {
		NivelInfoAlumno nivelInfoAlumno = null;
		for(NivelInfo nivelInfo : getNiveles()) {
			if(nivelInfo.getNivel().equals(nivel)) {
				nivelInfoAlumno = (NivelInfoAlumno) nivelInfo;
			}
		}
		return nivelInfoAlumno;
	}
	
	public Boolean agregarSala(Salas sala) {
		for(NivelInfo nivelInfo : getNiveles()) {
			if(nivelInfo.getNivel().equals(NivelesDeEducacion.JARDIN)) {
				if(!this.salasCursadas.contains(sala)) {
					return this.salasCursadas.add(sala);
				}
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarSala(Salas sala) {
		if(this.salasCursadas.contains(sala)) {
			return this.salasCursadas.remove(sala);
		}
		return Boolean.FALSE;
	}
	
	private Integer edadDelAlumnoMinimaParaAgregarGrado(GradoInfoPrimaria grado) {
		Integer edad = 0;
		if(grado.getNivel().equals(NivelesDeEducacion.PRIMARIA)) {
			edad = grado.getGrado().ordinal() + 6;
		}else if(grado.getNivel().equals(NivelesDeEducacion.SECUNDARIA)) {
			edad = grado.getGrado().ordinal() + 12;
		}
		return edad;
	}
	
	public Boolean alumnoCumpleConEdadParaAgregarGrado(GradoInfoPrimaria grado) {
		Boolean cumple = Boolean.FALSE;
		if(edadDelAlumnoMinimaParaAgregarGrado(grado) <= super.edad && (edadDelAlumnoMinimaParaAgregarGrado(grado) + 2) >= super.edad) {
			cumple = Boolean.TRUE;
		}
		return cumple;
	}
	
	private Integer indiceDeGradoCursadoMasAlto(GradoInfoPrimaria grado) {
		Integer indice = -1;
		for(GradoInfoPrimaria gradoInfoPrimaria : getGrados()) {
			if(indiceDelNivelDeEducacionMasAlto().equals(grado.getNivel().ordinal())) {
				if(indice < gradoInfoPrimaria.getGrado().ordinal()) {
					indice = gradoInfoPrimaria.getGrado().ordinal();
				}
			}
		}
		return indice;
	}
	
	private Boolean aproboGradoAnterior(GradoInfoPrimaria grado) {
		Boolean aproboAnterior = Boolean.FALSE;
		for(GradoInfoPrimaria gradoInfoPrimaria : getGrados()) {
			if(indiceDelNivelDeEducacionMasAlto().equals(grado.getNivel().ordinal())) {
				if(indiceDeGradoCursadoMasAlto(grado).equals(gradoInfoPrimaria.getGrado().ordinal())) {
					GradoInfoAlumnoPrimaria gradoInfoAlumnoPrimaria = (GradoInfoAlumnoPrimaria) gradoInfoPrimaria;
					if(gradoInfoAlumnoPrimaria.getAprobado()) {
						aproboAnterior = Boolean.TRUE;
					}
				}
			}
		}
		return aproboAnterior;	
	}
	
	public Boolean agregarGradoParaCursar(GradoInfoPrimaria grado) {
		if(alumnoCumpleConEdadParaAgregarGrado(grado)) {
			if(getGrados().size() == 0 || (aproboGradoAnterior(grado) && (indiceDeGradoCursadoMasAlto(grado) + 1) == grado.getGrado().ordinal() )) {
				return agregarGrado(grado);
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarGradoParaCursar(GradoInfoPrimaria grado) {
		return eliminarGrado(grado);
	}
	
	public GradoInfoPrimaria obtenerGradoEnCurso(NivelesDeEducacion nivel, Grados grado) {
		GradoInfoPrimaria gradoInfoPrimaria = null;
		for(GradoInfoPrimaria gradoInfo : getGrados()) {
			if(gradoInfo.getNivel().equals(nivel) && gradoInfo.getGrado().equals(grado)) {
				gradoInfoPrimaria = gradoInfo;
			}
		}
		return gradoInfoPrimaria;
	}
	
	public Boolean agregarAsistencia(String fecha, OpcionesDeAsistencia asistenciaOpcion) {
		Asistencia asistencia = new Asistencia(fecha, asistenciaOpcion);
		if(!this.asistencias.contains(asistencia)) {
			return this.asistencias.add(asistencia);
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarAsistencia(String fecha) {
		return this.asistencias.remove(obtenerAsistencia(fecha));
	}
	
	public Asistencia obtenerAsistencia(String fecha) {
		Asistencia buscada = null;
		for(Asistencia asistencia : getAsistencias()) {
			if(asistencia.getFecha().equalsIgnoreCase(fecha)) {
				buscada = asistencia;
			}
		}
		return buscada;
	}
}
