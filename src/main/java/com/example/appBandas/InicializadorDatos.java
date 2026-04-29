package com.example.appBandas;

import com.example.appBandas.modelos.AsignacionBorla;
import com.example.appBandas.modelos.AsignacionBorlaId;
import com.example.appBandas.modelos.Asistencia;
import com.example.appBandas.modelos.AsistenciaId;
import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Contrato;
import com.example.appBandas.modelos.Cuota;
import com.example.appBandas.modelos.Evento;
import com.example.appBandas.modelos.GaleriaMultimedia;
import com.example.appBandas.modelos.Insignia;
import com.example.appBandas.modelos.Instrumento;
import com.example.appBandas.modelos.InstrumentoVoz;
import com.example.appBandas.modelos.InstrumentoVozId;
import com.example.appBandas.modelos.Inventario;
import com.example.appBandas.modelos.LecturaAnuncio;
import com.example.appBandas.modelos.LecturaAnuncioId;
import com.example.appBandas.modelos.Liquidacion;
import com.example.appBandas.modelos.LogSistema;
import com.example.appBandas.modelos.Mantenimiento;
import com.example.appBandas.modelos.Marcha;
import com.example.appBandas.modelos.Partitura;
import com.example.appBandas.modelos.PasajeroVehiculo;
import com.example.appBandas.modelos.PasajeroVehiculoId;
import com.example.appBandas.modelos.Prestamo;
import com.example.appBandas.modelos.RolCargo;
import com.example.appBandas.modelos.Seccion;
import com.example.appBandas.modelos.TablonAnuncio;
import com.example.appBandas.modelos.Transaccion;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.modelos.UsuarioCargo;
import com.example.appBandas.modelos.UsuarioCargoId;
import com.example.appBandas.modelos.UsuarioInsignia;
import com.example.appBandas.modelos.UsuarioInsigniaId;
import com.example.appBandas.modelos.UsuarioInstrumento;
import com.example.appBandas.modelos.UsuarioInstrumentoId;
import com.example.appBandas.modelos.VehiculoCompartido;
import com.example.appBandas.modelos.Votacion;
import com.example.appBandas.modelos.Voto;
import com.example.appBandas.modelos.VotoId;
import com.example.appBandas.modelos.Voz;
import com.example.appBandas.repositorios.AsignacionBorlaRepository;
import com.example.appBandas.repositorios.AsistenciaRepository;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.ContratoRepository;
import com.example.appBandas.repositorios.CuotaRepository;
import com.example.appBandas.repositorios.EventoRepository;
import com.example.appBandas.repositorios.GaleriaMultimediaRepository;
import com.example.appBandas.repositorios.InsigniaRepository;
import com.example.appBandas.repositorios.InstrumentoRepository;
import com.example.appBandas.repositorios.InstrumentoVozRepository;
import com.example.appBandas.repositorios.InventarioRepository;
import com.example.appBandas.repositorios.LecturaAnuncioRepository;
import com.example.appBandas.repositorios.LiquidacionRepository;
import com.example.appBandas.repositorios.LogRepository;
import com.example.appBandas.repositorios.MantenimientoRepository;
import com.example.appBandas.repositorios.MarchaRepository;
import com.example.appBandas.repositorios.PartituraRepository;
import com.example.appBandas.repositorios.PasajeroVehiculoRepository;
import com.example.appBandas.repositorios.PrestamoRepository;
import com.example.appBandas.repositorios.RolCargoRepository;
import com.example.appBandas.repositorios.SeccionRepository;
import com.example.appBandas.repositorios.TablonAnuncioRepository;
import com.example.appBandas.repositorios.TransaccionRepository;
import com.example.appBandas.repositorios.UsuarioCargoRepository;
import com.example.appBandas.repositorios.UsuarioInsigniaRepository;
import com.example.appBandas.repositorios.UsuarioInstrumentoRepository;
import com.example.appBandas.repositorios.UsuarioRepository;
import com.example.appBandas.repositorios.VehiculoCompartidoRepository;
import com.example.appBandas.repositorios.VotacionRepository;
import com.example.appBandas.repositorios.VotoRepository;
import com.example.appBandas.repositorios.VozRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase de configuración encargada de rellenar la base de datos
 * con información de prueba la primera vez que se ejecuta la aplicación.
 * Atrapa las entidades guardadas para asegurar la generación de IDs.
 * Las inserciones se realizan en orden de dependencia para respetar las 
 * restricciones de clave foránea.
 */
@Configuration
public class InicializadorDatos {

    @Bean
    public CommandLineRunner poblarBaseDeDatos(
            BandaRepository bandaRepo,
            SeccionRepository seccionRepo,
            VozRepository vozRepo,
            RolCargoRepository cargoRepo,
            InsigniaRepository insigniaRepo,
            UsuarioRepository usuarioRepo,
            InstrumentoRepository instrumentoRepo,
            EventoRepository eventoRepo,
            InventarioRepository inventarioRepo,
            MarchaRepository marchaRepo,
            ContratoRepository contratoRepo,
            TablonAnuncioRepository anuncioRepo,
            VotacionRepository votacionRepo,
            CuotaRepository cuotaRepo,
            PrestamoRepository prestamoRepo,
            LiquidacionRepository liquidacionRepo,
            PartituraRepository partituraRepo,
            MantenimientoRepository mantenimientoRepo,
            GaleriaMultimediaRepository galeriaRepo,
            VehiculoCompartidoRepository vehiculoCompartidoRepo,
            LecturaAnuncioRepository lecturaAnuncioRepo,
            PasajeroVehiculoRepository pasajeroVehiculoRepo,
            AsignacionBorlaRepository asignacionBorlaRepo,
            UsuarioInsigniaRepository usuarioInsigniaRepo,
            UsuarioCargoRepository usuarioCargoRepo,
            InstrumentoVozRepository instrumentoVozRepo,
            UsuarioInstrumentoRepository usuarioInstrumentoRepo,
            AsistenciaRepository asistenciaRepo,
            VotoRepository votoRepo,
            TransaccionRepository transaccionRepo,
            LogRepository logRepo
    ) {
        return args -> {
            if (bandaRepo.count() == 0) {
                System.out.println("⏳ INICIANDO CARGA DE DATOS DE PRUEBA COMPLETA...");

                // =========================================================
                // BLOQUE 1: ENTIDADES INDEPENDIENTES (Sin dependencias foráneas)
                // =========================================================
             
                // 1. INSERCIONES DE BANDAS
                Banda banda1 = new Banda();
                banda1.setNombre("Agrupación Musical Santa Cecilia");
                banda1.setIdentificadorBanda("BANDA-001");
                banda1.setFotoPortada("https://servidor.com/portada_cecilia.jpg");
                banda1.setColorPrimario("#FF0000");
                banda1.setfFundacion(LocalDate.of(1990, 5, 12));
                banda1.setUltimaConexion(LocalDate.now());
                banda1.setDireccion("Torreperogil, calle obispo Basulto N79");
                banda1.setTwitter("https://x.com/?lang=es");
                banda1.setInstagram("https://www.instagram.com/");
                banda1.setYoutube("https://www.youtube.com/?app=desktop&hl=es");
                banda1.setActivo(true);
                banda1 = bandaRepo.save(banda1);

                Banda banda2 = new Banda();
                banda2.setNombre("Banda de Cornetas Rosario");
                banda2.setIdentificadorBanda("BANDA-002");
                banda2.setFotoPortada("https://servidor.com/portada_rosario.jpg");
                banda2.setColorPrimario("#00FF00");
                banda2.setfFundacion(LocalDate.of(2005, 3, 20));
                banda2.setUltimaConexion(LocalDate.now());
                banda2.setDireccion("Torreperogil, calle obispo Basulto N79");
                banda2.setTwitter("https://x.com/?lang=es");
                banda2.setInstagram("https://www.instagram.com/");
                banda2.setYoutube("https://www.youtube.com/?app=desktop&hl=es");
                banda2.setActivo(true);
                banda2 = bandaRepo.save(banda2);

                Banda banda3 = new Banda();
                banda3.setNombre("Asociación Filarmónica Las Nieves");
                banda3.setIdentificadorBanda("BANDA-003");
                banda3.setFotoPortada("https://servidor.com/portada_nieves.jpg");
                banda3.setColorPrimario("#0000FF");
                banda3.setfFundacion(LocalDate.of(2010, 8, 15));
                banda3.setUltimaConexion(LocalDate.now());
                banda3.setDireccion("Torreperogil, calle obispo Basulto N79");
                banda3.setTwitter("https://x.com/?lang=es");
                banda3.setInstagram("https://www.instagram.com/");
                banda3.setYoutube("https://www.youtube.com/?app=desktop&hl=es");
                banda3.setActivo(true);
                banda3 = bandaRepo.save(banda3);

                // 2. INSERCIONES DE SECCIÓN
                Seccion seccion1 = new Seccion();
                seccion1.setNombre("Viento Metal");
                seccion1 = seccionRepo.save(seccion1);

                Seccion seccion2 = new Seccion();
                seccion2.setNombre("Viento Madera");
                seccion2 = seccionRepo.save(seccion2);

                Seccion seccion3 = new Seccion();
                seccion3.setNombre("Percusión");
                seccion3 = seccionRepo.save(seccion3);

                // 3. INSERCIONES DE VOZ
                Voz voz1 = new Voz();
                voz1.setNombre("1º Alta");
                voz1 = vozRepo.save(voz1);

                Voz voz2 = new Voz();
                voz2.setNombre("1º Fuerte");
                voz2 = vozRepo.save(voz2);

                Voz voz3 = new Voz();
                voz3.setNombre("2º Voz");
                voz3 = vozRepo.save(voz3);

                // 4. INSERCIONES DE ROL CARGO
                RolCargo cargo1 = new RolCargo();
                cargo1.setNombre("Director Musical");
                cargo1.setTipoCargo("Junta Directiva");
                cargo1.setLimitePersonas(1);
                cargo1 = cargoRepo.save(cargo1);

                RolCargo cargo2 = new RolCargo();
                cargo2.setNombre("Tesorero");
                cargo2.setTipoCargo("Junta Directiva");
                cargo2.setLimitePersonas(1);
                cargo2 = cargoRepo.save(cargo2);

                RolCargo cargo3 = new RolCargo();
                cargo3.setNombre("Vocal de Uniformes");
                cargo3.setTipoCargo("Apoyo");
                cargo3.setLimitePersonas(3);
                cargo3 = cargoRepo.save(cargo3);

                // 5. INSERCIONES DE INSIGNIAS
                Insignia insignia1 = new Insignia();
                insignia1.setNombre("Asistencia Perfecta");
                insignia1.setDescripcion("Otorgada por asistir a todos los ensayos durante un mes consecutivo.");
                insignia1.setMeta(10);
                insignia1 = insigniaRepo.save(insignia1);

                Insignia insignia2 = new Insignia();
                insignia2.setNombre("Veterano");
                insignia2.setDescripcion("Otorgada por cumplir 5 años ininterrumpidos en la banda.");
                insignia2.setMeta(5);
                insignia2 = insigniaRepo.save(insignia2);

                Insignia insignia3 = new Insignia();
                insignia3.setNombre("Solista Destacado");
                insignia3.setDescripcion("Otorgada por interpretar un solo principal en un concierto oficial.");
                insignia3.setMeta(1);
                insignia3 = insigniaRepo.save(insignia3);

                // 6. INSERCIONES DE LOGS DE SISTEMA
                LogSistema log1 = new LogSistema("INFO", "UsuarioControlador", "Inicio de sesión exitoso", "Usuario ID: Temporal");
                log1 = logRepo.save(log1);

                LogSistema log2 = new LogSistema("WARN", "EventoServicio", "Evento modificado a menos de 24h", "Evento ID: Temporal");
                log2 = logRepo.save(log2);

                LogSistema log3 = new LogSistema("ERROR", "InventarioServicio", "Intento de préstamo sin stock", "Artículo QR: Temporal");
                log3 = logRepo.save(log3);


                // =========================================================
                // BLOQUE 2: ENTIDADES DE PRIMER NIVEL (Dependen del Bloque 1)
                // =========================================================

                // 7. INSERCIONES DE USUARIOS (Depende de Banda)
                Usuario usuario1 = new Usuario();
                usuario1.setBanda(banda1);
                usuario1.setNombre("Carlos");
                usuario1.setApellidos("García Pérez");
                usuario1.setDni("12345678A");
                usuario1.setTelefono("600111222");
                usuario1.setEmail("c");
                usuario1.setfNacimiento(LocalDate.of(1995, 2, 10));
                usuario1.setDireccion("Calle Mayor 1");
                usuario1.setfAlta(LocalDate.now());
                usuario1.setActivo(true);
                usuario1.setFotoPerfil("foto_carlos.jpg");
                usuario1.setBiografia("Trompetista principal de la agrupación.");
                usuario1.setPassword("1234");
                usuario1.setContactEmerg("600333444");
                usuario1.setTallaChaqueta("M");
                usuario1.setTallaPantalon("40");
                usuario1.setTallaGorra("58");
                usuario1.setTallaCamisa("M");
                usuario1.setRolApp("Musico");
                usuario1.setUltimoAcceso(LocalDateTime.now());
                usuario1.setAprobado(true);
                usuario1 = usuarioRepo.save(usuario1);

                Usuario usuario2 = new Usuario();
                usuario2.setBanda(banda1);
                usuario2.setNombre("Laura");
                usuario2.setApellidos("Martínez Ruiz");
                usuario2.setDni("87654321B");
                usuario2.setTelefono("611223344");
                usuario2.setEmail("dueño");
                usuario2.setfNacimiento(LocalDate.of(1998, 7, 22));
                usuario2.setDireccion("Avenida Constitución 5");
                usuario2.setfAlta(LocalDate.now());
                usuario2.setActivo(true);
                usuario2.setFotoPerfil("foto_laura.jpg");
                usuario2.setBiografia("Banderín oficial de la banda.");
                usuario2.setPassword("1234");
                usuario2.setContactEmerg("611556677");
                usuario2.setTallaChaqueta("S");
                usuario2.setTallaPantalon("38");
                usuario2.setTallaGorra("56");
                usuario2.setTallaCamisa("S");
                usuario2.setRolApp("Dueño");
                usuario2.setUltimoAcceso(LocalDateTime.now());
                usuario2.setAprobado(true);
                usuario2 = usuarioRepo.save(usuario2);

                Usuario usuario3 = new Usuario();
                usuario3.setBanda(banda2);
                usuario3.setNombre("Miguel");
                usuario3.setApellidos("López Gómez");
                usuario3.setDni("11223344C");
                usuario3.setTelefono("622334455");
                usuario3.setEmail("admin");
                usuario3.setfNacimiento(LocalDate.of(1990, 11, 5));
                usuario3.setDireccion("Plaza España 3");
                usuario3.setfAlta(LocalDate.now());
                usuario3.setActivo(true);
                usuario3.setFotoPerfil("foto_miguel.jpg");
                usuario3.setBiografia("Director musical.");
                usuario3.setPassword("1234");
                usuario3.setContactEmerg("622667788");
                usuario3.setTallaChaqueta("L");
                usuario3.setTallaPantalon("44");
                usuario3.setTallaGorra("60");
                usuario3.setTallaCamisa("L");
                usuario3.setRolApp("Administrador");
                usuario3.setUltimoAcceso(LocalDateTime.now());
                usuario3.setAprobado(true);
                usuario3 = usuarioRepo.save(usuario3);

                // 8. INSERCIONES DE EVENTOS (Depende de Banda)
                Evento evento1 = new Evento();
                evento1.setBanda(banda1);
                evento1.setTipo("Ensayo");
                evento1.setfHora(LocalDateTime.of(2026, 5, 15, 20, 30));
                evento1.setDireccion("Local de Ensayo, Nave 1");
                evento1.setLatitud(37.3890);
                evento1.setLongitud(-5.9844);
                evento1.setTitulo("Ensayo General Corpus");
                evento1.setHoraFin("22:30");
                evento1.setRequiereConf(true);
                evento1 = eventoRepo.save(evento1);

                Evento evento2 = new Evento();
                evento2.setBanda(banda1);
                evento2.setTipo("Concierto");
                evento2.setfHora(LocalDateTime.of(2026, 6, 20, 19, 0));
                evento2.setDireccion("Teatro Principal");
                evento2.setLatitud(37.3860);
                evento2.setLongitud(-5.9925);
                evento2.setTitulo("Concierto de Verano");
                evento2.setHoraFin("21:00");
                evento2.setRequiereConf(true);
                evento2 = eventoRepo.save(evento2);

                Evento evento3 = new Evento();
                evento3.setBanda(banda2);
                evento3.setTipo("Procesion");
                evento3.setfHora(LocalDateTime.of(2026, 4, 10, 17, 30));
                evento3.setDireccion("Parroquia de San Pedro");
                evento3.setLatitud(37.3915);
                evento3.setLongitud(-5.9870);
                evento3.setTitulo("Salida Procesional");
                evento3.setHoraFin("23:30");
                evento3.setRequiereConf(true);
                evento3 = eventoRepo.save(evento3);

                // 9. INSERCIONES DE CONTRATOS (Depende de Banda)
                Contrato contrato1 = new Contrato();
                contrato1.setBanda(banda1);
                contrato1.setCliente("Hermandad de la Macarena");
                contrato1.setfFirma(LocalDate.of(2026, 1, 15));
                contrato1.setImporteTotal(3500.0);
                contrato1.setHorasFirmadas(6.5);
                contrato1 = contratoRepo.save(contrato1);

                Contrato contrato2 = new Contrato();
                contrato2.setBanda(banda1);
                contrato2.setCliente("Ayuntamiento de Sevilla");
                contrato2.setfFirma(LocalDate.of(2026, 2, 10));
                contrato2.setImporteTotal(1200.0);
                contrato2.setHorasFirmadas(2.0);
                contrato2 = contratoRepo.save(contrato2);

                Contrato contrato3 = new Contrato();
                contrato3.setBanda(banda2);
                contrato3.setCliente("Hermandad de la Estrella");
                contrato3.setfFirma(LocalDate.of(2026, 3, 5));
                contrato3.setImporteTotal(2800.0);
                contrato3.setHorasFirmadas(5.0);
                contrato3 = contratoRepo.save(contrato3);

                // 10. INSERCIONES DE INVENTARIO (Depende de Banda)
                Inventario inventario1 = new Inventario();
                inventario1.setBanda(banda1);
                inventario1.setNombre("Trompeta Yamaha");
                inventario1.setTipo("Instrumento");
                inventario1.setDescripcion("Trompeta dorada en Si bemol");
                inventario1.setCodigoQr("QR-INST-001");
                inventario1.setEstado("Disponible");
                inventario1.setStock(3);
                inventario1 = inventarioRepo.save(inventario1);

                Inventario inventario2 = new Inventario();
                inventario2.setBanda(banda1);
                inventario2.setNombre("Chaqueta de Gala");
                inventario2.setTipo("Uniforme");
                inventario2.setDescripcion("Chaqueta azul marino talla L");
                inventario2.setCodigoQr("QR-UNIF-002");
                inventario2.setEstado("Prestado");
                inventario2.setStock(1);
                inventario2 = inventarioRepo.save(inventario2);

                Inventario inventario3 = new Inventario();
                inventario3.setBanda(banda2);
                inventario3.setNombre("Clarinete Buffet");
                inventario3.setTipo("Instrumento");
                inventario3.setDescripcion("Clarinete de madera ébano");
                inventario3.setCodigoQr("QR-INST-003");
                inventario3.setEstado("En Reparación");
                inventario3.setStock(2);
                inventario3 = inventarioRepo.save(inventario3);


                // 11. INSERCIONES DE TABLÓN DE ANUNCIOS (Depende de Banda)
                TablonAnuncio anuncio1 = new TablonAnuncio();
                anuncio1.setBanda(banda1);
                anuncio1.setTitulo("Ensayo General Suspendido");
                anuncio1.setMensaje("Debido a las fuertes lluvias, el ensayo previsto para hoy queda cancelado.");
                anuncio1 = anuncioRepo.save(anuncio1);

                TablonAnuncio anuncio2 = new TablonAnuncio();
                anuncio2.setBanda(banda1);
                anuncio2.setTitulo("Reparto de partituras");
                anuncio2.setMensaje("Este viernes se entregarán las partituras físicas de la nueva marcha al terminar el ensayo.");
                anuncio2 = anuncioRepo.save(anuncio2);

                TablonAnuncio anuncio3 = new TablonAnuncio();
                anuncio3.setBanda(banda2);
                anuncio3.setTitulo("Reunión Extraordinaria");
                anuncio3.setMensaje("Reunión obligatoria para la sección de percusión.");
                anuncio3 = anuncioRepo.save(anuncio3);

                // 12. INSERCIONES DE MARCHA (Depende de Banda)
                Marcha marcha1 = new Marcha();
                marcha1.setBanda(banda1);
                marcha1.setNombre("La Pasión");
                marcha1.setAutor("Manuel Alejandro");
                marcha1.setTiempo("Lento");
                marcha1.setfMontaje(LocalDate.of(2023, 1, 15));
                marcha1.setCategoria("Clásica");
                marcha1.setEstado("Montada");
                marcha1 = marchaRepo.save(marcha1);

                Marcha marcha2 = new Marcha();
                marcha2.setBanda(banda1);
                marcha2.setNombre("Silencio Blanco");
                marcha2.setAutor("Julio Páez");
                marcha2.setTiempo("Medio");
                marcha2.setfMontaje(LocalDate.of(2024, 10, 5));
                marcha2.setCategoria("Fúnebre");
                marcha2.setEstado("En montaje");
                marcha2 = marchaRepo.save(marcha2);

                Marcha marcha3 = new Marcha();
                marcha3.setBanda(banda2);
                marcha3.setNombre("Mi Esperanza");
                marcha3.setAutor("Desconocido");
                marcha3.setTiempo("Rápido");
                marcha3.setfMontaje(LocalDate.now());
                marcha3.setCategoria("Alegre");
                marcha3.setEstado("Nueva");
                marcha3 = marchaRepo.save(marcha3);

                // 13. INSERCIONES DE TRANSACCIÓN (Depende de Banda)
                Transaccion trans1 = new Transaccion();
                trans1.setBanda(banda1);
                trans1.setDescripcion("Cobro Ayuntamiento Semana Santa");
                trans1.setCantidad(4500.0);
                trans1.setCategoria("Actuación");
                trans1.setFecha(LocalDate.of(2026, 4, 15));
                trans1.setTipo("Ingreso");
                trans1 = transaccionRepo.save(trans1);

                Transaccion trans2 = new Transaccion();
                trans2.setBanda(banda1);
                trans2.setDescripcion("Compra de varas para trompa");
                trans2.setCantidad(-150.0);
                trans2.setCategoria("Instrumentos");
                trans2.setFecha(LocalDate.of(2026, 3, 10));
                trans2.setTipo("Gasto");
                trans2 = transaccionRepo.save(trans2);

                Transaccion trans3 = new Transaccion();
                trans3.setBanda(banda2);
                trans3.setDescripcion("Pago cuotas mensuales socios");
                trans3.setCantidad(850.0);
                trans3.setCategoria("Cuotas");
                trans3.setFecha(LocalDate.of(2026, 5, 1));
                trans3.setTipo("Ingreso");
                trans3 = transaccionRepo.save(trans3);

                // 14. INSERCIONES DE INSTRUMENTOS (Depende de Sección)
                Instrumento inst1 = new Instrumento();
                inst1.setNombre("Trompeta");
                inst1.setSeccion(seccion1);
                inst1.setEsViento(true);
                inst1 = instrumentoRepo.save(inst1);

                Instrumento inst2 = new Instrumento();
                inst2.setNombre("Trombón");
                inst2.setSeccion(seccion1);
                inst2.setEsViento(true);
                inst2 = instrumentoRepo.save(inst2);

                Instrumento inst3 = new Instrumento();
                inst3.setNombre("Clarinete");
                inst3.setSeccion(seccion2);
                inst3.setEsViento(true);
                inst3 = instrumentoRepo.save(inst3);

                // 15. INSERCIONES DE VOTACIÓN (Depende de Banda)
                Votacion votacion1 = new Votacion();
                votacion1.setBanda(banda1);
                votacion1.setTitulo("Elección de marchas para el próximo concierto");
                votacion1.setfLimite(LocalDateTime.now().plusDays(7));
                votacion1.setActiva(true);
                votacion1 = votacionRepo.save(votacion1);

                Votacion votacion2 = new Votacion();
                votacion2.setBanda(banda1);
                votacion2.setTitulo("Aprobación del nuevo diseño de uniforme");
                votacion2.setfLimite(LocalDateTime.now().minusDays(5));
                votacion2.setActiva(false);
                votacion2 = votacionRepo.save(votacion2);

                Votacion votacion3 = new Votacion();
                votacion3.setBanda(banda2);
                votacion3.setTitulo("Elección de repertorio de Semana Santa");
                votacion3.setfLimite(LocalDateTime.now().plusDays(14));
                votacion3.setActiva(true);
                votacion3 = votacionRepo.save(votacion3);


                // =========================================================
                // BLOQUE 3: ENTIDADES DE SEGUNDO NIVEL (Dependen del Bloque 2)
                // =========================================================

                // 16. INSERCIONES DE CUOTAS (Depende de Usuario)
                Cuota cuota1 = new Cuota();
                cuota1.setUsuario(usuario1);
                cuota1.setMes(5);
                cuota1.setAnio(2026);
                cuota1.setImporte(15.0);
                cuota1.setEstado("Pagada");
                cuota1 = cuotaRepo.save(cuota1);

                Cuota cuota2 = new Cuota();
                cuota2.setUsuario(usuario2);
                cuota2.setMes(5);
                cuota2.setAnio(2026);
                cuota2.setImporte(15.0);
                cuota2.setEstado("Pendiente");
                cuota2 = cuotaRepo.save(cuota2);

                Cuota cuota3 = new Cuota();
                cuota3.setUsuario(usuario3);
                cuota3.setMes(5);
                cuota3.setAnio(2026);
                cuota3.setImporte(20.0);
                cuota3.setEstado("Pagada");
                cuota3 = cuotaRepo.save(cuota3);

                // 17. INSERCIONES DE ASIGNACION DE BORLAS (Depende de Usuario)
                AsignacionBorla asignacion1 = new AsignacionBorla();
                asignacion1.setId(new AsignacionBorlaId(usuario2.getIdUsuario(), usuario1.getIdUsuario()));
                asignacion1.setAbanderado(usuario2);
                asignacion1.setBorla(usuario1);
                asignacion1 = asignacionBorlaRepo.save(asignacion1);

                AsignacionBorla asignacion2 = new AsignacionBorla();
                asignacion2.setId(new AsignacionBorlaId(usuario2.getIdUsuario(), usuario3.getIdUsuario()));
                asignacion2.setAbanderado(usuario2);
                asignacion2.setBorla(usuario3);
                asignacion2 = asignacionBorlaRepo.save(asignacion2);

                AsignacionBorla asignacion3 = new AsignacionBorla();
                asignacion3.setId(new AsignacionBorlaId(usuario1.getIdUsuario(), usuario3.getIdUsuario()));
                asignacion3.setAbanderado(usuario1);
                asignacion3.setBorla(usuario3);
                asignacion3 = asignacionBorlaRepo.save(asignacion3);

                // 18. INSERCIONES DE ASISTENCIA (Depende de Usuario y Evento)
                Asistencia asistencia1 = new Asistencia();
                asistencia1.setId(new AsistenciaId(usuario1.getIdUsuario(), evento1.getIdEvento()));
                asistencia1.setUsuario(usuario1);
                asistencia1.setEvento(evento1);
                asistencia1.setEstado("Asiste");
                asistencia1.setObservacion("Llega puntual");
                asistencia1 = asistenciaRepo.save(asistencia1);

                Asistencia asistencia2 = new Asistencia();
                asistencia2.setId(new AsistenciaId(usuario2.getIdUsuario(), evento1.getIdEvento()));
                asistencia2.setUsuario(usuario2);
                asistencia2.setEvento(evento1);
                asistencia2.setEstado("Falta");
                asistencia2.setObservacion("Motivos laborales");
                asistencia2 = asistenciaRepo.save(asistencia2);

                Asistencia asistencia3 = new Asistencia();
                asistencia3.setId(new AsistenciaId(usuario3.getIdUsuario(), evento3.getIdEvento()));
                asistencia3.setUsuario(usuario3);
                asistencia3.setEvento(evento3);
                asistencia3.setEstado("Justificado");
                asistencia3.setObservacion("Enfermedad leve");
                asistencia3 = asistenciaRepo.save(asistencia3);

                // 19. INSERCIONES DE GALERIA MULTIMEDIA (Depende de Evento)
                GaleriaMultimedia galeria1 = new GaleriaMultimedia();
                galeria1.setEvento(evento1);
                galeria1.setRutaArchivo("https://servidor.com/multimedia/ensayo1.jpg");
                galeria1.setTipo("Imagen");
                galeria1 = galeriaRepo.save(galeria1);

                GaleriaMultimedia galeria2 = new GaleriaMultimedia();
                galeria2.setEvento(evento2);
                galeria2.setRutaArchivo("https://servidor.com/multimedia/concierto_video.mp4");
                galeria2.setTipo("Video");
                galeria2 = galeriaRepo.save(galeria2);

                GaleriaMultimedia galeria3 = new GaleriaMultimedia();
                galeria3.setEvento(evento3);
                galeria3.setRutaArchivo("https://servidor.com/multimedia/procesion_audio.mp3");
                galeria3.setTipo("Audio");
                galeria3 = galeriaRepo.save(galeria3);

                // 20. INSERCIONES DE MANTENIMIENTO (Depende de Inventario)
                Mantenimiento mant1 = new Mantenimiento();
                mant1.setArticulo(inventario3);
                mant1.setfAviso(LocalDate.now());
                mant1.setDescripcion("Cambio de zapatillas y limpieza general.");
                mant1 = mantenimientoRepo.save(mant1);

                Mantenimiento mant2 = new Mantenimiento();
                mant2.setArticulo(inventario1);
                mant2.setfAviso(LocalDate.of(2026, 2, 15));
                mant2.setDescripcion("Abolladura en la campana, requiere desabollar.");
                mant2 = mantenimientoRepo.save(mant2);

                Mantenimiento mant3 = new Mantenimiento();
                mant3.setArticulo(inventario2);
                mant3.setfAviso(LocalDate.of(2026, 4, 10));
                mant3.setDescripcion("Falta un botón dorado en la manga derecha.");
                mant3 = mantenimientoRepo.save(mant3);

                // 21. INSERCIONES DE LIQUIDACIÓN (Depende de Usuario y Contrato)
                Liquidacion liq1 = new Liquidacion();
                liq1.setUsuario(usuario1);
                liq1.setContrato(contrato1);
                liq1.setImporteAPagar(50.0);
                liq1.setfPago(LocalDate.now());
                liq1 = liquidacionRepo.save(liq1);

                Liquidacion liq2 = new Liquidacion();
                liq2.setUsuario(usuario2);
                liq2.setContrato(contrato1);
                liq2.setImporteAPagar(50.0);
                liq2.setfPago(LocalDate.now());
                liq2 = liquidacionRepo.save(liq2);

                Liquidacion liq3 = new Liquidacion();
                liq3.setUsuario(usuario3);
                liq3.setContrato(contrato3);
                liq3.setImporteAPagar(75.5);
                liq3.setfPago(LocalDate.of(2026, 3, 10));
                liq3 = liquidacionRepo.save(liq3);

                // 22. INSERCIONES DE INSTRUMENTO_VOZ (Depende de Instrumento y Voz)
                InstrumentoVoz instVoz1 = new InstrumentoVoz();
                instVoz1.setId(new InstrumentoVozId(inst1.getIdInstrumento(), voz1.getIdVoz()));
                instVoz1.setInstrumento(inst1);
                instVoz1.setVoz(voz1);
                instVoz1 = instrumentoVozRepo.save(instVoz1);

                InstrumentoVoz instVoz2 = new InstrumentoVoz();
                instVoz2.setId(new InstrumentoVozId(inst1.getIdInstrumento(), voz2.getIdVoz()));
                instVoz2.setInstrumento(inst1);
                instVoz2.setVoz(voz2);
                instVoz2 = instrumentoVozRepo.save(instVoz2);

                InstrumentoVoz instVoz3 = new InstrumentoVoz();
                instVoz3.setId(new InstrumentoVozId(inst3.getIdInstrumento(), voz1.getIdVoz()));
                instVoz3.setInstrumento(inst3);
                instVoz3.setVoz(voz1);
                instVoz3 = instrumentoVozRepo.save(instVoz3);

                // 23. INSERCIONES DE LECTURAS DE ANUNCIOS (Depende de Usuario y TablonAnuncio)
                LecturaAnuncio lectura1 = new LecturaAnuncio();
                lectura1.setId(new LecturaAnuncioId(usuario1.getIdUsuario(), anuncio1.getIdAnuncios()));
                lectura1.setUsuario(usuario1);
                lectura1.setAnuncio(anuncio1);
                lectura1.setfLectura(LocalDateTime.now().minusDays(1));
                lectura1 = lecturaAnuncioRepo.save(lectura1);

                LecturaAnuncio lectura2 = new LecturaAnuncio();
                lectura2.setId(new LecturaAnuncioId(usuario2.getIdUsuario(), anuncio1.getIdAnuncios()));
                lectura2.setUsuario(usuario2);
                lectura2.setAnuncio(anuncio1);
                lectura2.setfLectura(LocalDateTime.now().minusHours(5));
                lectura2 = lecturaAnuncioRepo.save(lectura2);

                LecturaAnuncio lectura3 = new LecturaAnuncio();
                lectura3.setId(new LecturaAnuncioId(usuario3.getIdUsuario(), anuncio3.getIdAnuncios()));
                lectura3.setUsuario(usuario3);
                lectura3.setAnuncio(anuncio3);
                lectura3.setfLectura(LocalDateTime.now());
                lectura3 = lecturaAnuncioRepo.save(lectura3);

                // 24. INSERCIONES DE PARTITURA (Depende de Marcha)
                Partitura part1 = new Partitura();
                part1.setMarcha(marcha1);
                part1.setRutaPDF("/partituras/la_pasion_trompeta1.pdf");
                part1.setRutaAudio("/audios/la_pasion.mp3");
                part1 = partituraRepo.save(part1);

                Partitura part2 = new Partitura();
                part2.setMarcha(marcha1);
                part2.setRutaPDF("/partituras/la_pasion_bajos.pdf");
                part2.setRutaAudio("/audios/la_pasion_bajos_guia.mp3");
                part2 = partituraRepo.save(part2);

                Partitura part3 = new Partitura();
                part3.setMarcha(marcha2);
                part3.setRutaPDF("/partituras/silencio_blanco_general.pdf");
                part3.setRutaAudio("/audios/silencio_blanco.mp3");
                part3 = partituraRepo.save(part3);

                // 25. INSERCIONES DE PRÉSTAMO (Depende de Usuario e Inventario)
                Prestamo prestamo1 = new Prestamo();
                prestamo1.setUsuario(usuario1);
                prestamo1.setInventario(inventario1);
                prestamo1.setfSalida(LocalDate.of(2025, 9, 1));
                prestamo1.setfDevolucion(null); 
                prestamo1 = prestamoRepo.save(prestamo1);

                Prestamo prestamo2 = new Prestamo();
                prestamo2.setUsuario(usuario2);
                prestamo2.setInventario(inventario2);
                prestamo2.setfSalida(LocalDate.of(2026, 1, 10));
                prestamo2.setfDevolucion(null);
                prestamo2 = prestamoRepo.save(prestamo2);

                Prestamo prestamo3 = new Prestamo();
                prestamo3.setUsuario(usuario3);
                prestamo3.setInventario(inventario3);
                prestamo3.setfSalida(LocalDate.of(2026, 2, 5));
                prestamo3.setfDevolucion(LocalDate.of(2026, 3, 20)); 
                prestamo3 = prestamoRepo.save(prestamo3);

                // 26. INSERCIONES DE VEHÍCULO COMPARTIDO (Depende de Evento y Usuario)
                VehiculoCompartido vehiculo1 = new VehiculoCompartido();
                vehiculo1.setEvento(evento1);
                vehiculo1.setUsuario(usuario1);
                vehiculo1.setPlazasTotales(5);
                vehiculo1.setPlazasDisponibles(3);
                vehiculo1 = vehiculoCompartidoRepo.save(vehiculo1);

                VehiculoCompartido vehiculo2 = new VehiculoCompartido();
                vehiculo2.setEvento(evento2);
                vehiculo2.setUsuario(usuario2);
                vehiculo2.setPlazasTotales(4);
                vehiculo2.setPlazasDisponibles(1);
                vehiculo2 = vehiculoCompartidoRepo.save(vehiculo2);

                VehiculoCompartido vehiculo3 = new VehiculoCompartido();
                vehiculo3.setEvento(evento1);
                vehiculo3.setUsuario(usuario3);
                vehiculo3.setPlazasTotales(7);
                vehiculo3.setPlazasDisponibles(6); 
                vehiculo3 = vehiculoCompartidoRepo.save(vehiculo3);

                // 27. INSERCIONES DE VOTO (Depende de Usuario y Votacion)
                Voto voto1 = new Voto();
                voto1.setId(new VotoId(usuario1.getIdUsuario(), votacion1.getIdVotacion()));
                voto1.setUsuario(usuario1);
                voto1.setVotacion(votacion1);
                voto1.setOpcion("Marcha Clásica");
                voto1 = votoRepo.save(voto1);

                Voto voto2 = new Voto();
                voto2.setId(new VotoId(usuario2.getIdUsuario(), votacion1.getIdVotacion()));
                voto2.setUsuario(usuario2);
                voto2.setVotacion(votacion1);
                voto2.setOpcion("Marcha Alegre");
                voto2 = votoRepo.save(voto2);

                Voto voto3 = new Voto();
                voto3.setId(new VotoId(usuario3.getIdUsuario(), votacion1.getIdVotacion()));
                voto3.setUsuario(usuario3);
                voto3.setVotacion(votacion1);
                voto3.setOpcion("Abstención");
                voto3 = votoRepo.save(voto3);

                // 28. INSERCIONES DE USUARIO CARGO (Depende de Usuario y RolCargo)
                UsuarioCargo usuCargo1 = new UsuarioCargo();
                usuCargo1.setId(new UsuarioCargoId(usuario1.getIdUsuario(), cargo1.getIdCargo()));
                usuCargo1.setUsuario(usuario1);
                usuCargo1.setCargo(cargo1);
                usuCargo1 = usuarioCargoRepo.save(usuCargo1);

                UsuarioCargo usuCargo2 = new UsuarioCargo();
                usuCargo2.setId(new UsuarioCargoId(usuario2.getIdUsuario(), cargo2.getIdCargo()));
                usuCargo2.setUsuario(usuario2);
                usuCargo2.setCargo(cargo2);
                usuCargo2 = usuarioCargoRepo.save(usuCargo2);

                UsuarioCargo usuCargo3 = new UsuarioCargo();
                usuCargo3.setId(new UsuarioCargoId(usuario3.getIdUsuario(), cargo3.getIdCargo()));
                usuCargo3.setUsuario(usuario3);
                usuCargo3.setCargo(cargo3);
                usuCargo3 = usuarioCargoRepo.save(usuCargo3);

                // 29. INSERCIONES DE USUARIO INSIGNIA (Depende de Usuario e Insignia)
                UsuarioInsignia usuInsignia1 = new UsuarioInsignia();
                usuInsignia1.setId(new UsuarioInsigniaId(usuario1.getIdUsuario(), insignia1.getIdInsignia()));
                usuInsignia1.setUsuario(usuario1);
                usuInsignia1.setInsignia(insignia1);
                usuInsignia1.setfObtencion(LocalDate.now().minusMonths(2));
                usuInsignia1 = usuarioInsigniaRepo.save(usuInsignia1);

                UsuarioInsignia usuInsignia2 = new UsuarioInsignia();
                usuInsignia2.setId(new UsuarioInsigniaId(usuario2.getIdUsuario(), insignia2.getIdInsignia()));
                usuInsignia2.setUsuario(usuario2);
                usuInsignia2.setInsignia(insignia2);
                usuInsignia2.setfObtencion(LocalDate.now().minusDays(15));
                usuInsignia2 = usuarioInsigniaRepo.save(usuInsignia2);

                UsuarioInsignia usuInsignia3 = new UsuarioInsignia();
                usuInsignia3.setId(new UsuarioInsigniaId(usuario3.getIdUsuario(), insignia1.getIdInsignia()));
                usuInsignia3.setUsuario(usuario3);
                usuInsignia3.setInsignia(insignia1);
                usuInsignia3.setfObtencion(LocalDate.now().minusDays(1));
                usuInsignia3 = usuarioInsigniaRepo.save(usuInsignia3);


                // =========================================================
                // BLOQUE 4: ENTIDADES DE TERCER NIVEL (Dependen del Bloque 3)
                // =========================================================

                // 30. INSERCIONES DE PASAJERO VEHÍCULO (Depende de Usuario y VehiculoCompartido)
                PasajeroVehiculo pasajero1 = new PasajeroVehiculo();
                pasajero1.setId(new PasajeroVehiculoId(usuario1.getIdUsuario(), vehiculo1.getIdVehiculo()));
                pasajero1.setUsuario(usuario1);
                pasajero1.setVehiculo(vehiculo1);
                pasajero1 = pasajeroVehiculoRepo.save(pasajero1);

                PasajeroVehiculo pasajero2 = new PasajeroVehiculo();
                pasajero2.setId(new PasajeroVehiculoId(usuario2.getIdUsuario(), vehiculo1.getIdVehiculo()));
                pasajero2.setUsuario(usuario2);
                pasajero2.setVehiculo(vehiculo1);
                pasajero2 = pasajeroVehiculoRepo.save(pasajero2);

                PasajeroVehiculo pasajero3 = new PasajeroVehiculo();
                pasajero3.setId(new PasajeroVehiculoId(usuario3.getIdUsuario(), vehiculo2.getIdVehiculo()));
                pasajero3.setUsuario(usuario3);
                pasajero3.setVehiculo(vehiculo2);
                pasajero3 = pasajeroVehiculoRepo.save(pasajero3);

                // 31. INSERCIONES DE USUARIO INSTRUMENTO (Depende de Usuario e InstrumentoVoz)
                UsuarioInstrumento usuInst1 = new UsuarioInstrumento();
                usuInst1.setId(new UsuarioInstrumentoId(usuario1.getIdUsuario(), inst1.getIdInstrumento(), voz1.getIdVoz()));
                usuInst1.setUsuario(usuario1);
                usuInst1.setInstrumentoVoz(instVoz1);
                usuInst1.setEsSolista(true);
                usuInst1.setEsMaza(false);
                usuInst1 = usuarioInstrumentoRepo.save(usuInst1);

                UsuarioInstrumento usuInst2 = new UsuarioInstrumento();
                usuInst2.setId(new UsuarioInstrumentoId(usuario2.getIdUsuario(), inst1.getIdInstrumento(), voz2.getIdVoz()));
                usuInst2.setUsuario(usuario2);
                usuInst2.setInstrumentoVoz(instVoz2);
                usuInst2.setEsSolista(false);
                usuInst2.setEsMaza(false);
                usuInst2 = usuarioInstrumentoRepo.save(usuInst2);

                UsuarioInstrumento usuInst3 = new UsuarioInstrumento();
                usuInst3.setId(new UsuarioInstrumentoId(usuario3.getIdUsuario(), inst3.getIdInstrumento(), voz1.getIdVoz()));
                usuInst3.setUsuario(usuario3);
                usuInst3.setInstrumentoVoz(instVoz3);
                usuInst3.setEsSolista(false);
                usuInst3.setEsMaza(true);
                usuInst3 = usuarioInstrumentoRepo.save(usuInst3);
                
                System.out.println("✅ DATOS DE PRUEBA CARGADOS CORRECTAMENTE EN LA BASE DE DATOS");
            } else {
                System.out.println("⚡ LA BASE DE DATOS YA TIENE DATOS. SE OMITE LA CARGA INICIAL.");
            }
        };
    }
}