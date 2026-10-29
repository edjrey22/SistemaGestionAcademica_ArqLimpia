package com.academico.infrastructure.adapter.in;

import com.academico.domain.repository.CursoRepository;
import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryJson;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryPostgres;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositoryJson;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositoryPostgres;

public class RepositoryFactory {

    private RepositoryFactory(){
        //evitar instancia de fabrica
    }

    public static EstudianteRepository crearEstudianteRepository(){
        return switch (tipoAlmacenamiento()){
            case "JSON" -> new EstudianteRepositoryJson();
            case "POSTGRESQL" -> new EstudianteRepositoryPostgres();
            default -> throw new IllegalArgumentException(
                    "Tipo de almacenamiento no soportado: " + tipoAlmacenamiento());
        };
    }

    public static CursoRepository crearCursoRepository(){
        return switch (tipoAlmacenamiento()){
            case "JSON" -> new CursoRepositoryJson();
            case "POSTGRESQL" -> new CursoRepositoryPostgres();
            default -> throw new IllegalArgumentException(
                    "Tipo de almacenamiento no soportado: " + tipoAlmacenamiento());
        };
    }

    private static String tipoAlmacenamiento(){
        return System.getenv().getOrDefault("STORAGE_TYPE", "JSON").trim().toUpperCase();
    }
}
