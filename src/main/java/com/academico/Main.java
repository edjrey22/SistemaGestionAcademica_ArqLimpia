package com.academico;

import com.academico.application.port.in.EstudianteUseCase;
import com.academico.application.port.in.CursoUseCase;
import com.academico.application.service.CursoService;
import com.academico.application.service.EstudianteService;
import com.academico.domain.repository.CursoRepository;
import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryPostgres;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositoryPostgres;
import com.academico.presentation.CursoUI;
import com.academico.presentation.EstudianteUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //agregar
        // infraestructura: basta cambiar esta línea para elegir el adaptador
        // EstudianteRepository repository=new EstudianteRepositoryJson();
        EstudianteRepository repository=new EstudianteRepositoryPostgres();

        //aplicacion
        EstudianteUseCase service=new EstudianteService(repository);

        // presentacion
        EstudianteUI estudianteUI=new EstudianteUI(service);

        CursoRepository cursoRepository = new CursoRepositoryPostgres();
        CursoUseCase cursoService = new CursoService(cursoRepository);
        CursoUI cursoUI = new CursoUI(cursoService);


        Scanner sc = new Scanner(System.in);

        int opcion;

        try {
            do {

                System.out.println("\n=== SISTEMA DE GESTIÓN ACADÉMICA ===");
                System.out.println("1. Gestionar estudiantes");
                System.out.println("2. Gestionar cursos");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = sc.nextInt();

                switch (opcion) {

                    case 1:
                        estudianteUI.mostrarMenu(sc);
                        break;

                    case 2:
                        cursoUI.mostrarMenu(sc);
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        } catch (IllegalStateException e) {
            // Errores del adaptador de salida (por ejemplo, la base de datos)
            System.out.println("Error: " + e.getMessage());
        }
    }
}
