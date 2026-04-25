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
            TransaccionRepository transaccionRepo
    ) {
        return args -> {
            if (bandaRepo.count() == 0) {
                System.out.println("⏳ INICIANDO CARGA DE DATOS DE PRUEBA COMPLETA...");

                // 1. TABLAS MAESTRAS (Atrapamos el ID al guardar)
                Banda banda = new Banda();
                banda.setNombre("Agrupación Musical Santa Cecilia");
                // Sustituimos el CIF por el nuevo identificador
                banda.setIdentificadorBanda("BANDA-STACECILIA-001");
                banda.setFotoPortada("https://miservidor.com/fotos/portada_santacecilia.jpg");
                banda.setColorPrimario("#0D47A1");
                banda.setfFundacion(LocalDate.of(1995, 10, 24));
                banda.setFechaRegistro(LocalDate.now()); 
                banda.setUltimaConexion(LocalDate.now().minusMonths(7));
                banda = bandaRepo.save(banda);
                
                Banda banda1 = new Banda();
                banda1.setNombre("Rosario de linares");
                banda1.setIdentificadorBanda("BANDA-CORNETAS-002");
                banda1.setFotoPortada("https://miservidor.com/fotos/portada_santacecilia.jpg");
                banda1.setColorPrimario("#0D47A1");
                banda1.setfFundacion(LocalDate.of(1980, 10, 24));
                banda.setFechaRegistro(LocalDate.now()); 
                banda.setUltimaConexion(LocalDate.now().minusMonths(5));
                banda1 = bandaRepo.save(banda1);
                
                Banda banda2 = new Banda();
                banda2.setNombre("La pasión");
                banda2.setIdentificadorBanda("BANDA-AGRUPACION-003");
                banda2.setFotoPortada("https://miservidor.com/fotos/portada_santacecilia.jpg");
                banda2.setColorPrimario("#0D47A1");
                banda2.setfFundacion(LocalDate.of(2000, 10, 24));
                banda.setFechaRegistro(LocalDate.now()); 
                banda.setUltimaConexion(LocalDate.now().minusMonths(7));
                banda2 = bandaRepo.save(banda2);

                Seccion seccion = new Seccion();
                seccion.setNombre("Viento Metal");
                seccion = seccionRepo.save(seccion);

                Voz voz = new Voz();
                voz.setNombre("Primera Voz");
                voz = vozRepo.save(voz);

                RolCargo cargo = new RolCargo();
                cargo.setNombre("Director Musical");
                cargo.setTipoCargo("Junta Directiva");
                cargo.setLimitePersonas(1);
                cargo = cargoRepo.save(cargo);

                Insignia insignia = new Insignia();
                insignia.setNombre("Asistencia Perfecta");
                insignia.setDescripcion("Acudir a todos los ensayos de un mes");
                insignia.setMeta(10);
                insignia = insigniaRepo.save(insignia);

                // 2. TABLAS SECUNDARIAS
                Usuario usuario0 = new Usuario();
                usuario0.setNombre("Carlos");
                usuario0.setApellidos("García Pérez");
                usuario0.setDni("26538505R");
                usuario0.setEmail("dueño");
                usuario0.setPassword("1234");
                usuario0.setTelefono("600123456");
                usuario0.setRolApp("Dueño"); // Marcado como Dueño
                usuario0.setAprobado(true);  // Aprobado por defecto para pruebas
                usuario0.setFotoPerfil("");
                usuario0.setActivo(true);
                usuario0.setBanda(banda); 
                usuario0 = usuarioRepo.save(usuario0);
                
                Usuario usuario1 = new Usuario();
                usuario1.setNombre("admin");
                usuario1.setApellidos("admin");
                usuario1.setDni("12345678Z");
                usuario1.setEmail("admin");
                usuario1.setPassword("1234");
                usuario1.setTelefono("600123456");
                usuario1.setRolApp("Administrador"); // Marcado como Administrador
                usuario1.setAprobado(true);  // Aprobado por defecto para pruebas
                usuario1.setFotoPerfil("");
                usuario1.setActivo(true);
                usuario1.setBanda(null); 
                usuario1 = usuarioRepo.save(usuario1);

                Usuario usuario2 = new Usuario();
                usuario2.setNombre("Laura");
                usuario2.setApellidos("Martínez Ruiz");
                usuario2.setDni("87654321B");
                usuario2.setEmail("laura@email.com");
                usuario2.setPassword("mypassword123");
                usuario2.setTelefono("611223344");
                usuario2.setRolApp("Musico");
                usuario2.setAprobado(true);  // Aprobado por defecto para pruebas
                usuario2.setFotoPerfil("");
                usuario1.setActivo(true);
                usuario2.setBanda(banda);
                usuario2 = usuarioRepo.save(usuario2);

                Instrumento instrumento = new Instrumento();
                instrumento.setNombre("Trompeta");
                instrumento.setEsViento(true);
                instrumento.setSeccion(seccion);
                instrumento = instrumentoRepo.save(instrumento);

                Evento evento = new Evento();
                evento.setTipo("Ensayo General");
                evento.setDireccion("Local de la Banda, Nave 4");
                evento.setfHora(LocalDateTime.of(2026, 3, 23, 21, 0));
                evento.setBanda(banda);
                evento = eventoRepo.save(evento);

                Inventario inventario = new Inventario();
                inventario.setTipo("Uniforme");
                inventario.setDescripcion("Chaqueta de Gala (Talla M)");
                inventario.setCodigoQr("QR-UNI-001");
                inventario.setEstado("Disponible");
                inventario.setStock(15);
                inventario.setBanda(banda);
                inventario = inventarioRepo.save(inventario);

                Marcha marcha = new Marcha();
                marcha.setNombre("La Pasión");
                marcha.setAutor("Manuel Alejandro");
                marcha.setBanda(banda);
                marcha = marchaRepo.save(marcha);

                Contrato contrato = new Contrato();
                contrato.setCliente("Hermandad de la Macarena");
                contrato.setfFirma(LocalDate.of(2026, 1, 15));
                contrato.setImporteTotal(3500.0);
                contrato.setHorasFirmadas(6.5);
                contrato.setBanda(banda);
                contrato = contratoRepo.save(contrato);

                TablonAnuncio anuncio = new TablonAnuncio();
                anuncio.setTitulo("Reunión de Componentes");
                anuncio.setMensaje("El viernes tras el ensayo tendremos una reunión importante.");
                anuncio.setRequiereConf(true);
                anuncio.setBanda(banda);
                anuncio = anuncioRepo.save(anuncio);

                Votacion votacion = new Votacion();
                votacion.setTitulo("Elección de la tela para el nuevo uniforme");
                votacion.setfLimite(LocalDateTime.of(2026, 4, 1, 23, 59, 59));
                votacion.setActiva(true);
                votacion.setBanda(banda);
                votacion = votacionRepo.save(votacion);

                VehiculoCompartido vehiculo = new VehiculoCompartido();
                vehiculo.setEvento(evento);
                vehiculo.setUsuario(usuario1); 
                vehiculo.setPlazasTotales(4);
                vehiculo.setPlazasDisponibles(3);
                vehiculo = vehiculoCompartidoRepo.save(vehiculo);

                // 3. TABLAS FINALES (Simples)
                Cuota cuota = new Cuota();
                cuota.setMes(3);
                cuota.setAnio(2026);
                cuota.setImporte(15.0);
                cuota.setEstado("Pagada");
                cuota.setUsuario(usuario1);
                cuotaRepo.save(cuota);

                Prestamo prestamo = new Prestamo();
                prestamo.setfSalida(LocalDate.of(2026, 3, 1));
                prestamo.setInventario(inventario);
                prestamo.setUsuario(usuario1);
                prestamoRepo.save(prestamo);

                Liquidacion liquidacion = new Liquidacion();
                liquidacion.setImporteAPagar(35.50);
                liquidacion.setfPago(LocalDate.of(2026, 4, 10));
                liquidacion.setContrato(contrato);
                liquidacion.setUsuario(usuario1);
                liquidacionRepo.save(liquidacion);

                Partitura partitura = new Partitura();
                partitura.setRutaPDF("https://tuservidor.com/pdf/lapasion_trompeta1.pdf");
                partitura.setRutaAudio("https://tuservidor.com/audio/lapasion.mp3");
                partitura.setMarcha(marcha);
                partituraRepo.save(partitura);

                Mantenimiento mantenimiento = new Mantenimiento();
                mantenimiento.setArticulo(inventario);
                mantenimiento.setfAviso(LocalDate.now());
                mantenimiento.setDescripcion("Falta un botón en la chaqueta de gala.");
                mantenimientoRepo.save(mantenimiento);

                GaleriaMultimedia galeria = new GaleriaMultimedia();
                galeria.setEvento(evento);
                galeria.setRutaArchivo("https://miservidor.com/fotos/ensayo1.jpg");
                galeria.setTipo("Imagen");
                galeriaRepo.save(galeria);

                // 4. TABLAS CON CLAVES COMPUESTAS (Relaciones N a M)
                LecturaAnuncio lectura = new LecturaAnuncio();
                lectura.setId(new LecturaAnuncioId(usuario1.getIdUsuario(), anuncio.getIdAnuncios()));
                lectura.setUsuario(usuario1);
                lectura.setAnuncio(anuncio);
                lectura.setfLectura(LocalDateTime.now());
                lecturaAnuncioRepo.save(lectura);

                PasajeroVehiculo pasajero = new PasajeroVehiculo();
                pasajero.setId(new PasajeroVehiculoId(usuario2.getIdUsuario(), vehiculo.getIdVehiculo()));
                pasajero.setUsuario(usuario2); 
                pasajero.setVehiculo(vehiculo);
                pasajeroVehiculoRepo.save(pasajero);

                AsignacionBorla asignacionBorla = new AsignacionBorla();
                asignacionBorla.setId(new AsignacionBorlaId(usuario1.getIdUsuario(), usuario2.getIdUsuario()));
                asignacionBorla.setAbanderado(usuario1); 
                asignacionBorla.setBorla(usuario2);      
                asignacionBorlaRepo.save(asignacionBorla);

                UsuarioInsignia usuarioInsignia = new UsuarioInsignia();
                usuarioInsignia.setId(new UsuarioInsigniaId(usuario1.getIdUsuario(), insignia.getIdInsignia()));
                usuarioInsignia.setUsuario(usuario1);
                usuarioInsignia.setInsignia(insignia);
                usuarioInsignia.setfObtencion(LocalDate.now());
                usuarioInsigniaRepo.save(usuarioInsignia);

                UsuarioCargo usuarioCargo = new UsuarioCargo();
                usuarioCargo.setId(new UsuarioCargoId(usuario1.getIdUsuario(), cargo.getIdCargo()));
                usuarioCargo.setUsuario(usuario1);
                usuarioCargo.setCargo(cargo);
                usuarioCargoRepo.save(usuarioCargo);

                InstrumentoVoz instrumentoVoz = new InstrumentoVoz();
                instrumentoVoz.setId(new InstrumentoVozId(instrumento.getIdInstrumento(), voz.getIdVoz()));
                instrumentoVoz.setInstrumento(instrumento);
                instrumentoVoz.setVoz(voz);
                instrumentoVoz = instrumentoVozRepo.save(instrumentoVoz);

                UsuarioInstrumento usuarioInstrumento = new UsuarioInstrumento();
                usuarioInstrumento.setId(new UsuarioInstrumentoId(usuario1.getIdUsuario(), instrumento.getIdInstrumento(), voz.getIdVoz()));
                usuarioInstrumento.setUsuario(usuario1);
                usuarioInstrumento.setInstrumentoVoz(instrumentoVoz);
                usuarioInstrumento.setEsSolista(true);
                usuarioInstrumento.setEsMaza(false);
                usuarioInstrumentoRepo.save(usuarioInstrumento);

                Asistencia asistencia = new Asistencia();
                asistencia.setId(new AsistenciaId(usuario1.getIdUsuario(), evento.getIdEvento()));
                asistencia.setUsuario(usuario1);
                asistencia.setEvento(evento);
                asistencia.setEstado("Asiste");
                asistencia.setObservacion("Llegaré 5 minutos tarde");
                asistenciaRepo.save(asistencia);

                Voto voto = new Voto();
                voto.setId(new VotoId(usuario1.getIdUsuario(), votacion.getIdVotacion()));
                voto.setUsuario(usuario1);
                voto.setVotacion(votacion);
                voto.setOpcion("Tela transpirable azul marino");
                votoRepo.save(voto);
                
                Transaccion transaccion = new Transaccion();
                transaccion.setBanda(banda);
                transaccion.setDescripcion("Contrato: " + contrato.getCliente());
                transaccion.setCantidad(contrato.getImporteTotal());
                transaccion.setTipo("Ingreso");
                transaccion.setCategoria("Actuación");
                transaccion.setFecha(contrato.getfFirma());
                transaccionRepo.save(transaccion);
                
                System.out.println("✅ DATOS DE PRUEBA CARGADOS CORRECTAMENTE EN LA BASE DE DATOS");
            } else {
                System.out.println("⚡ LA BASE DE DATOS YA TIENE DATOS. SE OMITE LA CARGA INICIAL.");
            }
        };
    }
}