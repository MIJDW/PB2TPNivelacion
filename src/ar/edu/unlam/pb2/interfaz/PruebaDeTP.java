package ar.edu.unlam.pb2.interfaz;

import java.util.Scanner;
import ar.edu.unlam.pb2.dominio.*;

public class PruebaDeTP {
	
	private static Scanner teclado = new Scanner(System.in);
	private static Integer opcion = 0;
	private static final Integer diasDelCicloEscolar = 150;
	private static final Integer cantidadDeEvaluaciones = 5;

	public static void main(String[] args) {
		
		String nombreDeLaInstitucion = ingresarString("Ingrese el nombre de la Institucion");
		Integer cantidadDeAlumnoDeLaInstitucion = ingresarInteger("Ingrese la cantidad total de alumnos que tendra la Institucion");
		Institucion institucion = new Institucion(nombreDeLaInstitucion, cantidadDeAlumnoDeLaInstitucion);
		
		do {
			mostrarMenu();
			opcion = ingresarInteger("Elige una de las siguientes opciones");
			Menu menu = Menu.values()[opcion - 1];
			
			switch(menu) {
			case INSTITUCION:
				menuInstitucion(institucion);
				break;
			case NIVEL:
				menuNivel(institucion);
				break;
			case DIVISION:
				menuDivision(institucion);
				break;
			case CORREGIR_EVALUACION:
				corregirEvaluacion(institucion);
				break;
			case ASISTENCIAS:
				asistencias(institucion);
				break;
			case SALIR:
				System.out.println("BYE");
				break;
			}
		}while(opcion != Menu.values().length);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	private static void menuInstitucion(Institucion institucion) {
		do {
			mostrarMenuInstitucion();
			opcion = ingresarInteger("Elige una de las anteriores opciones");
			
			MenuInstitucion menuInstitucion = MenuInstitucion.values()[opcion - 1];
			
			switch(menuInstitucion) {
			case AGREGAR_NIVEL:
				agregarNivelEnInstitucion(institucion);
				break;
			case ELIMINAR_NIVEL:
				eliminarNivelEnInstitucion(institucion);
				break;
			case AGREGAR_ALUMNO:
				agregarAlumnoEnInstitucion(institucion);
				break;
			case ELIMINAR_ALUMNO:
				eliminarALumnoDeInstitucion(institucion);
				break;
			case OBTENER_ALUMNOS_ORDENADOS_POR_EDAD:
				obtenerAlumnosOrdenadosPorEdadDeInstitucion(institucion);
				break;
			case DISTRIBUIR_ALUMNOS_A_NIVELES:
				distribuirAlumnosDeInstitucion(institucion);
				break;
			case AGREGAR_DOCENTE:
				agregarDocenteEnInstitucion(institucion);
				break;
			case ELIMINAR_DOCENTE:
				eliminarDocenteDeInstitucion(institucion);
				break;
			case SALIR:
				mostrar("SALIO DEL MENU DE LA INSTITUCION");
				break;
			}
			
		}while(opcion != MenuInstitucion.values().length);
	}
	
	private static void menuNivel(Institucion institucion) {
		
		Nivel nivel = obtenerNivel(institucion);
		
		do {
			
			mostrarMenuNivel();
			opcion = ingresarInteger("Elige una de las anteriores opciones");
			
			MenuNivel menuNivel = MenuNivel.values()[opcion - 1];
			
			switch(menuNivel) {
			case OBTENER_ALUMNOS_ORDENADOS_POR_EDAD:
				obtenerAlumnosOrdenadosPorEdadDeNivel(nivel);
				break;
			case DISTRIBUIR_ALUMNOS_A_DIVISIONES:
				distribuirAlumnosDelNivel(nivel);
				break;
			case AGREGAR_DOCENTE_A_NIVEL:
				agregarDocenteAlNivel(institucion, nivel);
				break;
			case ELIMINAR_DOCENTE_DEL_NIVEL:
				eliminarDocenteDelNivel(nivel);
				break;
			case SALIR:
				mostrar("SALIO DEL MENU DEL NIVEL");
				break;
			}
			
		}while(opcion != MenuNivel.values().length);
		
	}
	
	private static void menuDivision(Institucion institucion) {
		
		Nivel nivel = obtenerNivel(institucion);
		Division division = obtenerDivision(nivel);
		
		do {
			mostrarMenuDivision();
			opcion = ingresarInteger("Elige una de las anteriores opciones");
			
			MenuDivision menuDivision = MenuDivision.values()[opcion - 1];
			
			switch(menuDivision) {
			case OBTENER_LISTA_DE_ALUMNOS_ORDENADA_POR_NOMBRE:
				obtenerListaDeAlumnosOrdenadaPorNombreDeDivision(division);
				break;
			case AGREGAR_DOCENTE_A_DIVISION:
				agregarDocenteALaDivision(nivel, division);
				break;
			case ELIMINAR_DOCENTE_DE_DIVISION:
				eliminarDocenteDeLaDivision(division);
				break;
			case SALIR:
				mostrar("SALIO DEL MENU DE LA DIVISION");
				break;
			}
			
		}while(opcion != 0);
		
	}
	
	private static void corregirEvaluacion(Institucion institucion) {
		
		Nivel nivel = obtenerNivel(institucion);
		
		if(!nivel.getNivel().equals(NivelesDeEducacion.JARDIN)) {
			
			Division division = obtenerDivision(nivel);
			
			do {
				
				mostrarAlumnos(division.getAlumnos());
				opcion = ingresarInteger("Elige una de las anteriores opciones");
				Alumno alumno = division.getAlumnos()[opcion - 1];
				mostrar(division.toString() + "  -  " + alumno.toString());
				
				Docente docente = null;
				
				do {
					mostrar("Posibles docentes a elegir");
					mostrarDocentesDeNivelQueContenganDivision(nivel, division);
					opcion = ingresarInteger("Elige una de las anteriores opciones o 0 para salir");
					
					if(opcion != 0) {
						docente = division.getDocentes()[opcion - 1];
						if(division.getNivel().equals(NivelesDeEducacion.SECUNDARIA)) {
							
							GradoInfoDocente info = docente.obtenerGradoDeCompetencia(nivel.getNivel(), division.getGrado());
							mostrar(docente.toString());
							mostrarMateriasDelGradoDeCompetenciaDelDocente(info);
							
						}else if(!division.getNivel().equals(NivelesDeEducacion.SECUNDARIA)){
							mostrar(docente.toString());
						}
					}
				}while(opcion != 0);
				
				GradoInfo infoAlumno = alumno.obtenerGrado(division.getNivel(), division.getGrado());
				Portafolio portafolio = null;
				
				if(nivel.getNivel().equals(NivelesDeEducacion.PRIMARIA)) {
					
					portafolio = infoAlumno.obtenerPortafolio(null);
					
				}else if(nivel.getNivel().equals(NivelesDeEducacion.SECUNDARIA)) {
					
					mostrarPortafolios(infoAlumno.getPortafolios());
					opcion = ingresarInteger("Elige una de las anteriores opciones");
					portafolio = infoAlumno.obtenerPortafolio(Materias.values()[opcion - 1]);
					
				}
				do {
					
					mostrarEvaluaciones(portafolio.getEvaluaciones());
					Evaluacion evaluacion = portafolio.obtenerEvaluacionParaCorregir();
					
					String fecha = ingresarStringCorto("Ingrese la fecha de la correcion");
					
					if(evaluacion.agregarDocenteYFecha(docente, fecha)) {
						
						Integer nota = ingresarInteger("Ingrese la nota de la evaluacion");
						evaluacion.corregirEvaluacion(nota);
						mostrar(evaluacion.toString());
						
					}
					
					opcion = ingresarInteger("Ingrese 1 para continuar o 99 para salir");
					
				}while(opcion != 99);
				
				opcion = ingresarInteger("Ingrese 1 para continuar o 0 para salir");
				
			}while(opcion != 0);
		}	
	}
	
	private static void asistencias(Institucion institucion) {
		Nivel nivel = obtenerNivel(institucion);
		Division division = obtenerDivision(nivel);
		do {
			mostrarAlumnos(division.getAlumnos());
			opcion = ingresarInteger("Elige una de las anteriores opciones o 0 para salir");
			
			if(opcion != 0) {
				
				Alumno alumno = division.getAlumnos()[opcion - 1];
				
				do {
					mostrarMenuAsistencias();
					opcion = ingresarInteger("Elige una de las anteriores opciones");
					MenuAsistencias menuAsistencias = MenuAsistencias.values()[opcion - 1];
					
					String fecha = "";
					String situacion = "";
					
					switch(menuAsistencias) {
					case AGREGAR_ASISTENCIA:
						fecha = ingresarStringCorto("Ingresar fecha");
						situacion = ingresarStringCorto("Ingresar ASISTIO o FALTO");
						alumno.agregarAsistencia(fecha, situacion);
						break;
					case ELIMINAR_ASISTENCIA:
						mostrar("Las asistencia con las que cuenta el alumno son:");
						mostrarAsistencias(alumno);
						fecha = ingresarStringCorto("Ingresar fecha");
						alumno.eliminarAsistencia(fecha);
						break;
					case MOSTRAR_ASISTENCIAS:
						mostrar("Las asistencia con las que cuenta el alumno son:");
						mostrarAsistencias(alumno);
						break;
					case SALIR:
						mostrar("Salio de las asistencia del alumno");
						break;
					}
					
				}while(opcion != MenuAsistencias.values().length);	
			}	
		}while(opcion != 0);
	}
	
	private static Nivel obtenerNivel(Institucion institucion) {
		mostrarNivelesDeInstitucion(institucion);
		opcion = ingresarInteger("Elige una de las anteriores opciones");
		Nivel nivel = institucion.obtenerNivel(NivelesDeEducacion.values()[opcion - 1]);
		return nivel;
	}
	
	private static Division obtenerDivision(Nivel nivel) {
		mostrarDivisionesDeNivel(nivel);
		opcion = ingresarInteger("Elige una de las anteriores opciones");
		Division division = null;
		
		if(nivel.getNivel().equals(NivelesDeEducacion.JARDIN)) {
			
			division = nivel.obtenerDivisionDeJardin(nivel.getDivisiones()[opcion - 1].getSala());
			
		}else if(!nivel.getNivel().equals(NivelesDeEducacion.JARDIN)) {
			
			division = nivel.obtenerDivisionDeNivelPrimarioOSecundario(nivel.getDivisiones()[opcion - 1].getGrado());
			
		}
		return division;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	private static Alumno crearAlumno() {
		String nombre = ingresarStringCorto("Ingresar nombre del alumno");
		String apellido = ingresarStringCorto("Ingresar Apellido del alumno");
		Integer edad = ingresarInteger("Ingresar edad del alumno");
		Integer dni = ingresarInteger("Ingresar dni del alumno");
		
		Alumno alumno = null;
		Boolean agrego = Boolean.FALSE;
		
		if(edad >= 2 && edad <= 17) {
			alumno = new Alumno(nombre, apellido, edad, dni, diasDelCicloEscolar, cantidadDeEvaluaciones);
			do {
				mostrarNiveles();
				opcion = ingresarInteger("Ingrese una de las siguientes opciones");
				NivelesDeEducacion nivel = NivelesDeEducacion.values()[opcion - 1];
				
				if(alumno.agregarNivelDeEducacion(nivel)) {
					mostrar("EXITO! EL nivel se agrego");
					Boolean agregoGradoOSala = Boolean.FALSE;
					do {
						if(alumno.poseeElNivelAdecuadoParaAgregarSala()) {
							
							mostrarSalas();
							opcion = ingresarInteger("Ingrese una de las siguientes opciones");
							Salas sala = Salas.values()[opcion - 1];
							
							if(alumno.agregarSala(sala)) {
								mostrar("EXITO! La sala se agrego");
								agregoGradoOSala = Boolean.TRUE;
							}
							
						}else if(alumno.contieneNivelDeEducacion(NivelesDeEducacion.PRIMARIA) || alumno.contieneNivelDeEducacion(NivelesDeEducacion.SECUNDARIA)){
							
							mostrarGrados();
							opcion = ingresarInteger("Ingrese una de las siguiente opciones");
							Grados grado = Grados.values()[opcion - 1];
							
							if(alumno.agregarGrado(nivel, grado)) {
								mostrar("EXITO! El grado se agrego");
								agregoGradoOSala = Boolean.TRUE;
							}else {
								mostrar("ERROR! El grado no se pudo agregar");
							}
						}
					}while(agregoGradoOSala.equals(Boolean.FALSE));
					agrego = Boolean.TRUE;
				}else {
					mostrar("ERROR! No se pudo agregar el nivel");
				}
			}while(agrego.equals(Boolean.FALSE));
		}
		return alumno;
	}
	
	private static Docente crearDocente() {
		String nombre = ingresarStringCorto("Ingresar nombre del docente");
		String apellido = ingresarStringCorto("Ingresar Apellido del docente");
		Integer edad = ingresarInteger("Ingresar edad del docente");
		Integer dni = ingresarInteger("Ingresar dni del docente");
		
		Docente docente = new Docente(nombre, apellido, edad, dni);
		
		if(edad > 22) {
			do {
				mostrar("Competencias de docente:");
				mostrarCompetenciaDeDocente(docente);
				
				mostrarNiveles();
				mostrar("[0]  -  Salir");
				opcion = ingresarInteger("Ingrese una de las siguientes opciones");
				
				if(opcion != 0) {
					
					NivelesDeEducacion nivel = NivelesDeEducacion.values()[opcion - 1];
					
					if(docente.agregarNivelDeCompetencia(nivel)) {
						mostrar("EXITO! El nivel se ha agregado");
					}	
				}
			}while(opcion != 0);
			
			if(docente.contieneNivelDeCompetencia(NivelesDeEducacion.PRIMARIA)) {
				do {
					
					mostrar("Grados de docente:");
					mostrarGradosDeDocente(docente, 0, 6);
					
					mostrarGrados();
					mostrar("[0]  -  Salir");
					opcion = ingresarInteger("Ingrese una de las siguientes opciones");
					
					if(opcion != 0) {
						
						Grados grado = Grados.values()[opcion - 1];
						
						if(docente.agregarGradoDeCompetencia(NivelesDeEducacion.PRIMARIA, grado)) {
							mostrar("EXITO! El grado se ha agregado");
						}
					}	
				}while(opcion != 0);
			}
			
			if(docente.contieneNivelDeCompetencia(NivelesDeEducacion.SECUNDARIA)) {
				do {
					mostrar("Grados de secundaria del docente:");
					mostrarGradosDeDocente(docente, 6, 12);
					
					mostrarGrados();
					mostrar("[0]  -  Salir");
					opcion = ingresarInteger("Ingrese una de las siguientes opciones");
					
					if(opcion != 0) {
						Grados grado = Grados.values()[opcion - 1];
						if(docente.agregarGradoDeCompetencia(NivelesDeEducacion.SECUNDARIA, grado)) {
							mostrar("EXITO! El grado se ha agregado");
						}
						
						do {
							mostrarMaterias();
							mostrar("[99]  -  Salir");
							opcion = ingresarInteger("Ingrese una de las siguientes opciones");
							
							GradoInfoDocente gradoInfo = docente.obtenerGradoDeCompetencia(NivelesDeEducacion.SECUNDARIA, grado);
							
							if(opcion != 99) {
								
								if(gradoInfo.agregarMateria(Materias.values()[opcion - 1])) {
									mostrar("EXITO! La materia se ha agregado");
								}
							}	
						}while(opcion != 99);	
					}
				}while(opcion != 0);
			}		
		}
		return docente;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------
	
	private static Integer ingresarInteger(String mensaje) {
		mostrar(mensaje);
		return teclado.nextInt();
	}
	
	private static String ingresarString(String mensaje) {
		mostrar(mensaje);
		return teclado.nextLine();
	}
	
	private static String ingresarStringCorto(String mensaje) {
		mostrar(mensaje);
		return teclado.next();
	}
	
	private static void mostrar(String mensaje) {
		System.out.println(mensaje);
	}
	
	private static void mostrarMenu() {
		for(Integer i = 0; i < Menu.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + Menu.values()[i]);
		}
	}
	
	private static void mostrarMenuInstitucion() {
		for(Integer i = 0; i < MenuInstitucion.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + MenuInstitucion.values()[i]);
		}
	}
	
	private static void mostrarMenuNivel() {
		for(Integer i = 0; i < MenuNivel.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + MenuNivel.values()[i]);
		}
	}
	
	private static void mostrarMenuDivision() {
		for(Integer i = 0; i < MenuDivision.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + MenuDivision.values()[i]);
		}
	}
	
	private static void mostrarMenuAsistencias() {
		for(Integer i = 0; i < MenuAsistencias.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + MenuAsistencias.values()[i]);
		}
	}
	
	private static void mostrarNiveles() {
		for(Integer i = 0; i < NivelesDeEducacion.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + NivelesDeEducacion.values()[i]);
		}
	}
	
	private static void mostrarSalas() {
		for(Integer i = 0; i < Salas.values().length;i++) {
			mostrar("[" + (i + 1) + "]  -  " + Salas.values()[i]);
		}
	}
	
	private static void mostrarGrados() {
		for(Integer i = 0; i < Grados.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + Grados.values()[i]);
		}
	}
	
	private static void mostrarMaterias() {
		for(Integer i = 0; i < Materias.values().length; i++) {
			mostrar("[" + (i + 1) + "]  -  " + Materias.values()[i]);
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private static void agregarNivelEnInstitucion(Institucion institucion) {
		do {
			mostrarNivelesDeInstitucion(institucion);
			mostrar("");
			mostrarNiveles();
			mostrar("[" + 0 + "]  -  " + "SALIR");
			
			opcion = ingresarInteger("Elija una de las siguientes opciones");
			
			if(opcion != 0) {
				NivelesDeEducacion nivel = NivelesDeEducacion.values()[opcion - 1];
				
				if(institucion.agregarNivel(nivel)) {
					mostrar("EXITO! El nivel se ha agregado");
				}else {
					mostrar("ERROR! El nivel ya ha sido agregado");
				}
			}		
		}while(opcion != 0);
	}
	
	private static void eliminarNivelEnInstitucion(Institucion institucion) {
		do {
			mostrarNivelesDeInstitucion(institucion);
			mostrar("[" + 0 + "]  -  " + "SALIR");
			mostrar("");
			
			opcion = ingresarInteger("Elija una de las siguientes opciones");
			
			if(opcion != 0) {
				NivelesDeEducacion nivel = NivelesDeEducacion.values()[opcion - 1];
				
				if(institucion.eliminarNivel(nivel)) {
					mostrar("EXITO! El nivel se ha eliminado");
				}
			}
			
		}while(opcion != 0);
	}
	
	private static void agregarAlumnoEnInstitucion(Institucion institucion) {
		do {
			mostrarAlumnosDeInstitucion(institucion);
			opcion = ingresarInteger("[1]  -  Continuar" + "\n" + "[0]  -  Salir");
			
			if(opcion == 1) {
				Alumno alumno = crearAlumno();
				if(alumno != null) {
					if(institucion.agregarAlumno(alumno)) {
						mostrar("EXITO! El alumno se agrego");
					}
				}else {
					mostrar("ERROR! No se pudo agregar el alumno");
				}
			}	
		}while(opcion != 0);
	}
	
	private static void eliminarALumnoDeInstitucion(Institucion institucion) {
		do {
			mostrarAlumnosDeInstitucion(institucion);
			opcion = ingresarInteger("Elija el alumno que desea eliminar o 0 para salir");
			
			if(opcion != 0) {
				Alumno alumno = institucion.obtenerAlumno(institucion.getAlumnos()[opcion - 1].getDni());
				if(institucion.eliminarAlumno(alumno)) {
					mostrar("EXITO! Se elimino el alumno");
				}
			}	
		}while(opcion != 0);
	}
	
	private static void obtenerAlumnosOrdenadosPorEdadDeInstitucion(Institucion institucion) {
		Alumno[] alumnos = institucion.obtenerAlumnosOrdenadosPorEdad();
		mostrarAlumnos(alumnos);
	}
	
	private static void distribuirAlumnosDeInstitucion(Institucion institucion) {
		institucion.distribuirAlumnosANiveles();
		mostrarAlumnosDeLosNivelesDeLaInstitucion(institucion);
	}
	
	private static void agregarDocenteEnInstitucion(Institucion institucion) {
		do {
			mostrarDocentesDeInstitucion(institucion);
			
			Docente docente = crearDocente();
			if(institucion.agregarDocente(docente)) {
				mostrar("EXITO! El docente se agrego");
			}
			
			opcion = ingresarInteger("[1]  -  Continuar" + "\n" + "[99]  -  Salir");
				
		}while(opcion !=99);
	}
	
	private static void eliminarDocenteDeInstitucion(Institucion institucion) {
		do {
			mostrarDocentesDeInstitucion(institucion);
			opcion = ingresarInteger("Elija el docente que desea eliminar o 0 para salir");
			
			if(opcion != 0) {
				Docente docente = institucion.obtenerDocente(institucion.getDocentes()[opcion - 1].getDni());
				if(institucion.eliminarDocente(docente)) {
					mostrar("EXITO! Se elimino el docente");
				}
			}	
		}while(opcion != 0);
	}
	
	private static void obtenerAlumnosOrdenadosPorEdadDeNivel(Nivel nivel) {
		Alumno[] alumnos = nivel.obtenerAlumnosOrdenadosPorEdad();
		mostrarAlumnos(alumnos);
	}
	
	private static void distribuirAlumnosDelNivel(Nivel nivel) {
		nivel.distribuirAlumnosADivisiones();
		mostrarAlumnosDeLasDivisionesDeLNivel(nivel);
	}
	
	private static void agregarDocenteAlNivel(Institucion institucion, Nivel nivel) {
		do {
			mostrarDocentesDeInstitucionQueContenganNivel(institucion, nivel);
			opcion = ingresarInteger("Elija el docente que desea agregar al nivel o 0 para salir");
			
			if(opcion != 0) {
				
				Docente docente = institucion.obtenerDocente(institucion.getDocentes()[opcion - 1].getDni());
				if(nivel.agregarDocente(docente)) {
					mostrar("EXITO! El docente se agrego al nivel");
					mostrar("Los docentes del nivel son");
					mostrarDocentesDelNivel(nivel);
				}
			}
		}while(opcion != 0);
	}
	
	private static void eliminarDocenteDelNivel(Nivel nivel) {
		do {
			mostrarDocentesDelNivel(nivel);
			opcion = ingresarInteger("Elija el docente que desea eliminar del nivel o 0 para salir");
			
			if(opcion != 0) {
				Docente docente = nivel.obtenerDocente(nivel.getDocentes()[opcion - 1].getDni());
				if(nivel.eliminarDocente(docente)) {
					mostrar("EXITO! Se elimino el docente del nivel");
				}
			}
		}while(opcion != 0);
	}
	
	private static void obtenerListaDeAlumnosOrdenadaPorNombreDeDivision(Division division) {
		Alumno[] alumnos = division.obtenerListaOrdenadaDeAlumnosPorNombre();
		mostrarAlumnos(alumnos);
	}
	
	private static void agregarDocenteALaDivision(Nivel nivel, Division division) {
		do {	
			mostrarDocentesDeNivelQueContenganDivision(nivel, division);
			opcion = ingresarInteger("Elija el docente que desea agregar a la division o 0 para salir");
			
			if(opcion != 0) {
				
				Docente docente = nivel.obtenerDocente(nivel.getDocentes()[opcion - 1].getDni());
				
				if(!division.getNivel().equals(NivelesDeEducacion.SECUNDARIA)) {
					
					if(division.agregarDocente(docente)) {
						mostrar("EXITO! El docente se agrego a la division");
						mostrar("Los docentes de la division son");
						mostrarDocentesDeDivision(division);
						
					}
					
				}else if(division.getNivel().equals(NivelesDeEducacion.SECUNDARIA)){
					
					GradoInfoDocente info = docente.obtenerGradoDeCompetencia(nivel.getNivel(), division.getGrado());
					do {
						
						mostrarMateriasDelGradoDeCompetenciaDelDocente(info);
						opcion = ingresarInteger("Elija el docente que desea agregar al nivel o 99 para salir");
						
						if(opcion != 99) {
							if(division.agregarDocenteDeSecundariaSegunMateria(docente, Materias.values()[opcion - 1])) {
								mostrar("EXITO! El docente se agrego a la division");
								mostrar("Los docentes de la division son");
								mostrarDocentesDeDivisionDeSecundariaConMaterias(division);
							}
						}
					}while(opcion != 99);
				}
			}
		}while(opcion != 0);
	}
	
	private static void eliminarDocenteDeLaDivision(Division division) {
		do {
			mostrarDocentesDeDivision(division);
			opcion = ingresarInteger("Elija el docente que desea agregar al nivel o 0 para salir");
			
			if(opcion != 0) {
				
				Docente docente = division.obtenerDocente(division.getDocentes()[opcion - 1].getDni());
				
				if(!division.getNivel().equals(NivelesDeEducacion.SECUNDARIA)) {
					if(division.eliminarDocente(docente)) {
						mostrar("EXITO! Se elimino el docente de la division");
					}	
				}else if(division.getNivel().equals(NivelesDeEducacion.SECUNDARIA)) {
					
					 do {
						 mostrarDocentesDeDivisionDeSecundariaConMaterias(division);
						 opcion = ingresarInteger("Elija el docente que desea agregar al nivel o 99 para salir");
						 
						 if(division.eliminarDocenteDeSecundariaSegunMateria(Materias.values()[opcion - 1])) {
							 mostrar("EXITO! Se elimino el docente de la materia"); 
						 }
					 }while(opcion != 99);
				}
			}
		}while(opcion != 0);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private static void mostrarNivelesDeInstitucion(Institucion institucion) {
		for(Integer i = 0; i < institucion.getNiveles().length; i++) {
			if(institucion.getNiveles()[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + institucion.getNiveles()[i].toString());
			}
		}
	}
	
	private static void mostrarAlumnosDeInstitucion(Institucion institucion) {
		mostrarAlumnos(institucion.getAlumnos());
	}
	
	private static void mostrarAlumnos(Alumno[] alumnos) {
		for(Integer i = 0; i < alumnos.length; i++) {
			if(alumnos[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + alumnos[i].toString());
			}
		}
	}
	
	private static void mostrarAlumnosDeLosNivelesDeLaInstitucion(Institucion institucion) {
		for(Integer i = 0; i < institucion.getNiveles().length; i++) {
			if(institucion.getNiveles()[i] != null) {
				mostrar(institucion.getNiveles()[i].toString());
				mostrarAlumnos(institucion.getNiveles()[i].getAlumnos());
			}
		}
	}
	
	private static void mostrarAlumnosDeLasDivisionesDeLNivel(Nivel nivel) {
		for(Integer i = 0; i < nivel.getDivisiones().length; i++) {
			mostrar(nivel.getDivisiones()[i].toString());
			mostrarAlumnos(nivel.getDivisiones()[i].getAlumnos());
		}
	}
	
	private static void mostrarCompetenciaDeDocente(Docente docente) {
		for(Integer i = 0; i < docente.getNivelDeCompetencia().length; i++) {
			if(docente.getNivelDeCompetencia()[i] != null) {
				mostrar(docente.getNivelDeCompetencia()[i].toString());
			}
		}
	}
	
	private static void mostrarGradosDeDocente(Docente docente, Integer inicio, Integer fin) {
		for(Integer i = inicio; i < fin; i++) {
			if(docente.getGradosDeCompetencia()[i] != null) {
				mostrar(docente.getGradosDeCompetencia()[i].toString());
			}
		}
	}
	
	private static void mostrarDocentesDeInstitucion(Institucion institucion) {
		for(Integer i = 0; i < institucion.getDocentes().length; i++) {
			if(institucion.getDocentes()[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + institucion.getDocentes()[i].toString());
			}
		}
	}
	
	private static void mostrarDocentesDeInstitucionQueContenganNivel(Institucion institucion, Nivel nivel) {
		for(Integer i = 0; i < institucion.getDocentes().length; i++) {
			if(institucion.getDocentes()[i] != null) {
				if(institucion.getDocentes()[i].contieneNivelDeCompetencia(nivel.getNivel())) {
					mostrar("[" + (i + 1) + "]  -  " + institucion.getDocentes()[i].toString());
				}
			}
		}
	}
	
	private static void mostrarDocentesDeNivelQueContenganDivision(Nivel nivel, Division division) {
		for(Integer i = 0; i < nivel.getDocentes().length; i++) {
			if(nivel.getDocentes()[i] != null) {
				if(nivel.getNivel().equals(NivelesDeEducacion.JARDIN)) {
					if(nivel.getDocentes()[i].contieneNivelDeCompetencia(NivelesDeEducacion.JARDIN)) {
						mostrar("[" + (i + 1) + "]  -  " + nivel.getDocentes()[i].toString());
					}
				}else {
					if(nivel.getDocentes()[i].contieneGradoDeCompetencia(nivel.getNivel(), division.getGrado())) {
						mostrar("[" + (i + 1) + "]  -  " + nivel.getDocentes()[i].toString());
					}
				}
			}
		}
	}
	
	private static void mostrarDocentesDelNivel(Nivel nivel) {
		for(Integer i = 0; i < nivel.getDocentes().length; i++) {
			if(nivel.getDocentes()[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + nivel.getDocentes()[i].toString());
			}
		}
	}
	
	private static void mostrarDivisionesDeNivel(Nivel nivel) {
		for(Integer i = 0; i < nivel.getDivisiones().length; i++) {
			if(nivel.getDivisiones()[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + nivel.getDivisiones()[i].toString());
			}
		}
	}
	
	private static void mostrarDocentesDeDivision(Division division) {
		for(Integer i = 0; i < division.getDocentes().length; i++) {
			if(division.getDocentes()[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + division.getDocentes()[i].toString());
			}
		}
	}
	
	private static void mostrarDocentesDeDivisionDeSecundariaConMaterias(Division division) {
		for(Integer i = 0; i < division.getDocentes().length; i++) {
			if(division.getDocentes()[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + division.getDocentes()[i].toString() + "  -  " + Materias.values()[i]);
			}
		}
	}
		
	private static void mostrarMateriasDelGradoDeCompetenciaDelDocente(GradoInfoDocente info) {
		for(Integer i = 0; i < info.getMaterias().length; i++) {
			if(info.getMaterias()[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + info.getMaterias()[i]);
			}
		}
	}
	
	private static void mostrarPortafolios(Portafolio[] portafolios) {
		for(Integer i = 0; i < portafolios.length; i++) {
			if(portafolios[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + portafolios[i].toString());
			}
		}
	}
	
	private static void mostrarEvaluaciones(Evaluacion[] evaluaciones) {
		for(Integer i = 0; i < evaluaciones.length; i++) {
			if(evaluaciones[i] != null) {
				mostrar("[" + (i + 1) + "]  -  " + evaluaciones[i].toString());
			}
		}
	}
	
	private static void mostrarAsistencias(Alumno alumno) {
		for(Integer i = 0; i < alumno.getAsistencias().length; i++) {
			for(Integer j = 0; j < alumno.getAsistencias()[i].length; j++) {
				if(alumno.getAsistencias()[i][j] != null) {
					if(j == alumno.getAsistencias()[i].length - 1) {
						mostrar(alumno.getAsistencias()[i][0] + "  -  " + alumno.getAsistencias()[i][1]);
					}
				}
			}
		}
	}
	
}
