package com.example.appBandas.repositorios;

import com.example.appBandas.modelos.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    // Solo traemos las que están activas y ordenadas por fecha
    List<Notificacion> findByIdUsuarioAndActivoTrueOrderByFechaEnvioDesc(Integer idUsuario);
}