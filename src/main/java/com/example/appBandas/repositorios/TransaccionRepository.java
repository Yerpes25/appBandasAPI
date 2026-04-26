package com.example.appBandas.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Transaccion;

/**
 * Repositorio para gestionar las operaciones de persistencia de las transacciones economicas.
 */
@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    
    List<Transaccion> findByBanda_IdBandaOrderByFechaDesc(Integer idBanda);

    @Query("SELECT COALESCE(SUM(t.cantidad), 0.0) FROM Transaccion t WHERE t.banda.idBanda = :idBanda")
    Double obtenerBalanceTotal(Integer idBanda);

    @Query("SELECT COALESCE(SUM(t.cantidad), 0.0) FROM Transaccion t WHERE t.banda.idBanda = :idBanda AND t.tipo = 'Ingreso' AND t.fecha >= :desde")
    Double sumarIngresosRecientes(Integer idBanda, LocalDate desde);

    @Query("SELECT COALESCE(SUM(t.cantidad), 0.0) FROM Transaccion t WHERE t.banda.idBanda = :idBanda AND t.tipo = 'Gasto' AND t.fecha >= :desde")
    Double sumarGastosRecientes(Integer idBanda, LocalDate desde);
}