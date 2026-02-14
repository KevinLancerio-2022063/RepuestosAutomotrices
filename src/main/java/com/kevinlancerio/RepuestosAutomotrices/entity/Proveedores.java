package com.kevinlancerio.RepuestosAutomotrices.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "proveedores")
public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @NotBlank(message="El nombre del proveedor no puede estar vacío")
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @NotNull(message="El telefono es obligatorio")
    @Column(name = "telefono_proveedor")
    private Integer telefonoProveedor;

    @NotBlank(message="La dirección no puede estar vacío")
    @Column(name = "direccion")
    private String direccion;

    @NotBlank(message="El correo no puede estar vacía")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|outlook\\.com|hotmail\\.com|yahoo\\.com)$",
            message = "El correo debe ser gmail, outlook, hotmail o yahoo")
    @Column(name = "email_proveedor")
    private String emailProveedor;

    public Proveedores() {
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(Integer telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }
}
