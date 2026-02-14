package com.kevinlancerio.RepuestosAutomotrices.controller;

import com.kevinlancerio.RepuestosAutomotrices.entity.Empleado;
import com.kevinlancerio.RepuestosAutomotrices.entity.Proveedores;
import com.kevinlancerio.RepuestosAutomotrices.service.ProveedoresService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")

public class ProveedoresController {
    private final ProveedoresService proveedoresService;
    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping
    public List<Proveedores> getAllProveedores() {
        return proveedoresService.getAllProveedores();
    }

    @PostMapping
    public ResponseEntity<?> createProveedor(@RequestBody Proveedores proveedor){

        if(proveedor.getNombreProveedor()==null || proveedor.getNombreProveedor().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El nombre del proveedor no puede estar vacío");
        }

        if(proveedor.getTelefonoProveedor()==null){
            return ResponseEntity.badRequest().body("El teléfono es obligatorio");
        }

        if(String.valueOf(proveedor.getTelefonoProveedor()).length()<8){
            return ResponseEntity.badRequest().body("El teléfono debe tener 8 dígitos");
        }

        if(proveedor.getDireccion()==null || proveedor.getDireccion().trim().isEmpty()){
            return ResponseEntity.badRequest().body("La dirección no puede estar vacía");
        }

        if(proveedor.getEmailProveedor()==null || proveedor.getEmailProveedor().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El correo no puede estar vacío");
        }

        try{
            Proveedores nuevo = proveedoresService.saveProveedores(proveedor);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);

        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProveedoresById(@PathVariable Integer id) {
        Proveedores proveedores = proveedoresService.getProveedoresById(id);

        if (proveedores == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado");
        }
        return ResponseEntity.ok(proveedores);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProveedores (@PathVariable Integer id, @RequestBody Proveedores proveedores) {

        Proveedores actualizado = proveedoresService.updateProveedores(id, proveedores);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedores (@PathVariable Integer id) {

        Proveedores proveedores = proveedoresService.getProveedoresById(id);

        if (proveedores == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no existe");
        }

        proveedoresService.deleteProveedores(id);
        return ResponseEntity.ok("Proveedor eliminado correctamente");

    }




}
