package com.kevinlancerio.RepuestosAutomotrices.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Repuestos")
public class Repuestos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repuesto")
    private Integer idRepuesto;

    @Column(name = "nombre_repuesto")
    private String nombreRepuesto;

    @Column(name = "categoria_repuesto")
    private String categoriaRepuesto;

    @Column(name = "precio_compra")
    private double precioCompra;

    @Column(name = "precio_venta")
    private double precioVenta;

    @ManyToOne
    @JoinColumn(name="id_proveedor", nullable=false)
    private Proveedores proveedor;

    public Repuestos () {

    }

    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }

    public String getCategoriaRepuesto() {
        return categoriaRepuesto;
    }

    public void setCategoriaRepuesto(String categoriaRepuesto) {
        this.categoriaRepuesto = categoriaRepuesto;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Proveedores getProveedor() { return proveedor; }
    public void setProveedor(Proveedores proveedor) { this.proveedor = proveedor; }
}

