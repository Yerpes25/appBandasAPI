package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.dtos.ComponenteDTO;
import com.example.appBandas.modelos.CredencialesLogin;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.servicios.EstadisticasDuenoServicio;
import com.example.appBandas.servicios.UsuarioServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar las peticiones HTTP de los usuarios.
 * Esta clase actúa como la API para que la aplicación de Android pueda
 * registrar músicos, iniciar sesión y obtener sus datos en formato JSON.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

	@GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioServicio.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody CredencialesLogin credenciales) {
        
        Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorEmail(credenciales.getCorreo());
        
        if (usuario.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        
        
        if (usuario.get().getPassword().equals(credenciales.getClave())) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(401).build();
        }
    }
    
    @GetMapping("/nombre-por-email/{email}")
    public ResponseEntity<String> obtenerNombrePorEmail(@PathVariable String email) {
        return usuarioServicio.obtenerUsuarioPorEmail(email)
                .map(usuario -> ResponseEntity.ok(usuario.getNombre()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario nuevoUsuario) {
        Usuario usuarioGuardado = usuarioServicio.guardarUsuario(nuevoUsuario);
        return ResponseEntity.ok(usuarioGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }
    
    /**
     * Cuenta el total de usuarios que hay guardados en la base de datos.
     * * @return El numero total de usuarios.
     */
    @GetMapping("/total-usuarios")
    public long obtenerTotalUsuarios() {
			return usuarioServicio.obtenerTodosLosUsuariosPorNumero();
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario datosNuevos) {
        Optional<Usuario> usuarioExistente = usuarioServicio.obtenerUsuarioPorId(id);
        
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            
            // Campos básicos
            if (datosNuevos.getNombre() != null) usuario.setNombre(datosNuevos.getNombre());
            if (datosNuevos.getApellidos() != null) usuario.setApellidos(datosNuevos.getApellidos());
            if (datosNuevos.getDni() != null) usuario.setDni(datosNuevos.getDni());
            if (datosNuevos.getEmail() != null) usuario.setEmail(datosNuevos.getEmail());
            if (datosNuevos.getTelefono() != null) usuario.setTelefono(datosNuevos.getTelefono());
            if (datosNuevos.getDireccion() != null) usuario.setDireccion(datosNuevos.getDireccion());
            
            // Campos específicos de la banda
            if (datosNuevos.getContactEmerg() != null) usuario.setContactEmerg(datosNuevos.getContactEmerg());
            if (datosNuevos.getTallaCamisa() != null) usuario.setTallaCamisa(datosNuevos.getTallaCamisa());
            if (datosNuevos.getTallaChaqueta() != null) usuario.setTallaChaqueta(datosNuevos.getTallaChaqueta());
            if (datosNuevos.getTallaPantalon() != null) usuario.setTallaPantalon(datosNuevos.getTallaPantalon());
            if (datosNuevos.getTallaGorra() != null) usuario.setTallaGorra(datosNuevos.getTallaGorra());
            
            // Otros campos
            if (datosNuevos.getBiografia() != null) usuario.setBiografia(datosNuevos.getBiografia());
            if (datosNuevos.getFotoPerfil() != null) usuario.setFotoPerfil(datosNuevos.getFotoPerfil());
            if (datosNuevos.getPassword() != null) usuario.setPassword(datosNuevos.getPassword());
            
            // Guardamos el usuario con todos los cambios aplicados
            return ResponseEntity.ok(usuarioServicio.guardarUsuario(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/banda/{idBanda}")
    public ResponseEntity<List<Usuario>> obtenerPorBanda(@PathVariable Integer idBanda) {
        return ResponseEntity.ok(usuarioServicio.obtenerUsuariosPorBanda(idBanda));
    }
    
    @GetMapping("/banda/{idBanda}/detalles")
    public ResponseEntity<List<ComponenteDTO>> obtenerDetallesPorBanda(@PathVariable Integer idBanda) {
        return ResponseEntity.ok(usuarioServicio.obtenerDetallesComponentes(idBanda));
    }
}