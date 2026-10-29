package com.academico.application.service;
import com.academico.application.event.AcademicoObserver;
import com.academico.application.event.EventoAcademico;
import com.academico.application.event.PublicadorEventos;
import com.academico.application.event.TipoEvento;
import com.academico.application.port.in.EstudianteUseCase;
import com.academico.application.validation.Validador;
import com.academico.application.validation.estudiante.ValidadorCorreoEstudiante;
import com.academico.application.validation.estudiante.ValidadorEstudianteDuplicado;
import com.academico.application.validation.estudiante.ValidadorIdEstudiante;
import com.academico.application.validation.estudiante.ValidadorNombreEstudiante;
import com.academico.domain.model.Estudiante;
//import com.academico.infrastructure.persistence.EstudianteRepositoryJson;
import com.academico.domain.repository.EstudianteRepository;


import java.util.List;
import java.util.Optional;

public class EstudianteService implements EstudianteUseCase {

    private final EstudianteRepository repository;
    // Observer: el servicio publica un evento tras cada operación exitosa
    private final PublicadorEventos publicador = new PublicadorEventos();
    // Chain of Responsibility: validaciones antes de registrar y actualizar
    private final Validador<Estudiante> cadenaRegistro;
    private final Validador<Estudiante> cadenaActualizacion;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;

        // Registro: Id -> Nombre -> Correo -> Duplicado
        this.cadenaRegistro = new ValidadorIdEstudiante();
        cadenaRegistro.enlazar(new ValidadorNombreEstudiante())
                      .enlazar(new ValidadorCorreoEstudiante())
                      .enlazar(new ValidadorEstudianteDuplicado(repository));

        // Actualización: Nombre -> Correo (el ID ya existe)
        this.cadenaActualizacion = new ValidadorNombreEstudiante();
        cadenaActualizacion.enlazar(new ValidadorCorreoEstudiante());
    }

    public void suscribir(AcademicoObserver observador) {
        publicador.suscribir(observador);
    }

    private void notificar(TipoEvento tipo, Estudiante estudiante) {
        publicador.notificar(new EventoAcademico(
                tipo, "Estudiante", estudiante.getId(), estudiante.getNombre()));
    }

    @Override
    public void registrar(Estudiante estudiante) {
        cadenaRegistro.validar(estudiante);
        repository.guardar(estudiante);
        notificar(TipoEvento.REGISTRADO, estudiante);
    }

    @Override
    public List<Estudiante> listar() {
        return repository.listar();
    }

    @Override
    public boolean actualizar(Estudiante estudiante) {
        cadenaActualizacion.validar(estudiante);
        boolean actualizado = repository.actualizar(estudiante);
        if (actualizado) {
            notificar(TipoEvento.ACTUALIZADO, estudiante);
        }
        return actualizado;
    }

    @Override
    public boolean eliminar(int id) {
        Optional<Estudiante> existente = repository.buscarPorId(id);
        boolean eliminado = repository.eliminar(id);
        if (eliminado) {
            existente.ifPresent(e -> notificar(TipoEvento.ELIMINADO, e));
        }
        return eliminado;
    }

}
