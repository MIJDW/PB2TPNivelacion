package ar.edu.unlam.pb2.establecimiento;

import java.util.ArrayList;

import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.personas.*;

public class NivelJardin extends EstructuraEducativaNivel{

	public NivelJardin() {
		super.nivel = NivelesDeEducacion.JARDIN;
		agregarDivisiones();
	}
	
	private void agregarDivisiones() {
		for(Salas sala : Salas.values()) {
			DivisionJardin divisionJardin = new DivisionJardin(sala);
			if(!getDivisiones().contains(divisionJardin)) {
				super.divisiones.add(divisionJardin);
			}
		}
	}
	
	public DivisionJardin obtenerDivisionJardin(Salas sala) {
		DivisionJardin divisionJardin = (DivisionJardin)getDivisiones().get(sala.ordinal());
		return divisionJardin;
	}
	
	private ArrayList<Alumno> obtenerAlumnosParaCombinar(){
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		for(EstructuraEducativaDivisiones division : getDivisiones()) {
			if(division.getAlumnos().size() < 10) {
				for(Alumno alumno : division.getAlumnos()) {
					alumnos.add(alumno);
				}
			}
			DivisionJardin divisionJardin = (DivisionJardin) division;
			divisionJardin.limpiarListaAlumno();
		}
		return alumnos;
	}
	
	private ArrayList<Docente> obtenerDocenteParaCombinar(){
		ArrayList<Docente> docentes = new ArrayList<Docente>();
		for(EstructuraEducativaDivisiones division : getDivisiones()) {
			if(division.getAlumnos().size() < 10) {
				for(Docente docente : division.getDocentes()) {
					docentes.add(docente);
				}
			}
			DivisionJardin divisionJardin = (DivisionJardin) division;
			divisionJardin.limpiarListaDocente();
		}
		return docentes;
	}
	
	private ArrayList<Integer> numerosAleatorios(Integer longitud){
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		while(numeros.size() < longitud) {
			Double numD = Math.random() * longitud;
			Integer numI = numD.intValue();
			if(!numeros.contains(numI)) {
				numeros.add(numI);
			}	
		}
		return numeros;
	}
	
	public void juntarAlumnosDeSalasConMenosDeDiezAlumnos() {
		ArrayList<Alumno> alumnos = obtenerAlumnosParaCombinar();
		Integer contador = 0;
		ArrayList<Docente> docentes = obtenerDocenteParaCombinar();
		ArrayList<Integer> numeros = numerosAleatorios(docentes.size());
		Integer contador2 = 0;
		for(EstructuraEducativaDivisiones division : getDivisiones()) {
			DivisionJardin divisionJardin = (DivisionJardin) division;
			if(divisionJardin.getAlumnos().size() == 0) {
				for(Integer i = 0; i < DivisionJardin.CANTIDAD_MAXIMA_DE_ALUMNOS; i++) {
					if(divisionJardin.getAlumnos().size() < DivisionJardin.CANTIDAD_MAXIMA_DE_ALUMNOS) {
						if(contador < alumnos.size()) {
							Alumno alumno = alumnos.get(contador);
							if(divisionJardin.agregarAlumnoAJardinParaCombinar(alumno)) {
								contador++;
							}
						}
					}
				}
				for(Integer i = 0; i < DivisionJardin.cantidadMaximaDeDocentes; i++) {
					if(divisionJardin.getDocentes().size() < DivisionJardin.getCantidadMaximaDeDocentes()) {
						if(contador2 < docentes.size()) {
							Integer numero = numeros.get(contador2);
							Docente docente = docentes.get(numero);
							if(divisionJardin.agregarDocente(docente)) {
								contador2++;
							}
						}
					}
				}
			}
		}
	}
}
