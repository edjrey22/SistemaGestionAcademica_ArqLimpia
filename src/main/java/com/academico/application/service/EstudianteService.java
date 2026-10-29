package com.academico.application.service;
import com.academico.application.event.AcademicoObserver;
import com.academico.application.event.EventoAcademico;
import com.academico.application.event.PublicadorEventos;
import com.academico.application.event.TipoEvento;
import com.academico.application.port.in.EstudianteUseCase;
import com.academico.domain.model.Estudiante;
//import com.academico.infrastructure.persistence.EstudianteRepositoryJson;
import com.academico.domain.repository.EstudianteRepository;


import java.util.List;
import java.util.Optional;

public class EstudianteService implements EstudianteUseCase {

    private final EstudianteRepository repository;
    // Observer: el servicio publica un evento tras cada operación exitosa
    private final PublicadorEventos publicador = new PublicadorEventos();

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
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
        repository.guardar(estudiante);
        notificar(TipoEvento.REGISTRADO, estudiante);
    }

    @Override
    public List<Estudiante> listar() {
        return repository.listar();
    }

    @Override
    public boolean actualizar(Estudiante estudiante) {
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
