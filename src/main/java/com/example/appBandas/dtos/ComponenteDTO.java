package com.example.appBandas.dtos;

public class ComponenteDTO {
	private Integer idUsuario;
	private String nombre;
	private String apellidos;
	private String dni;
	private String telefono;
	private String email;
	private String direccion;
	private String rolApp;
	private Boolean activo;
	private String nombreCompleto;
	private String fotoPerfil;
	private String instrumentoYVoz;
	private String cargo;

	public ComponenteDTO() {
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRolApp() {
		return rolApp;
	}

	public void setRolApp(String rolApp) {
		this.rolApp = rolApp;
	}

	// (Mantén los getters/setters que ya tenías de nombreCompleto, fotoPerfil,
	// etc.)
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getInstrumentoYVoz() {
		return instrumentoYVoz;
	}

	public void setInstrumentoYVoz(String instrumentoYVoz) {
		this.instrumentoYVoz = instrumentoYVoz;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}