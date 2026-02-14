package com.kevinlancerio.RepuestosAutomotrices.controller;

import com.kevinlancerio.RepuestosAutomotrices.entity.Proveedores;
import com.kevinlancerio.RepuestosAutomotrices.entity.Repuestos;
import com.kevinlancerio.RepuestosAutomotrices.service.RepuestosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestosController {
    private final RepuestosService repuestosService;

    public RepuestosController(RepuestosService repuestosService) {
        this.repuestosService = repuestosService;
    }

    @GetMapping
    public List<Repuestos> getAllRepuestos() {
        return repuestosService.getAllRepuestos();
    }

    @PostMapping
    public ResponseEntity<?> createRepuesto(@RequestBody Repuestos repuesto){

        if(repuesto.getNombreRepuesto()==null || repuesto.getNombreRepuesto().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El nombre del repuesto no puede estar vacío");
        }

        if(repuesto.getCategoriaRepuesto()==null || repuesto.getCategoriaRepuesto().trim().isEmpty()){
            return ResponseEntity.badRequest().body("La categoría no puede estar vacía");
        }

        if(repuesto.getPrecioCompra()<=0){
            return ResponseEntity.badRequest().body("El precio de compra debe ser mayor a 0");
        }

        if(repuesto.getPrecioVenta()<=0){
            return ResponseEntity.badRequest().body("El precio de venta debe ser mayor a 0");
        }

        if(repuesto.getProveedor()==null || repuesto.getProveedor().getIdProveedor()==null){
            return ResponseEntity.badRequest().body("Debe enviar el id del proveedor");
        }

        try{
            Repuestos nuevo = repuestosService.saveRepuestos(repuesto);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);

        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getRepuestosById(@PathVariable Integer id) {
        Repuestos repuestos = repuestosService.getRepuestosById(id);

        if (repuestos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Repuesto no encontrado");
        }
        return ResponseEntity.ok(repuestos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRepuestos (@PathVariable Integer id, @RequestBody Repuestos repuestos) {

        Repuestos actualizado = repuestosService.updateRepuestos(id, repuestos);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRepuestos (@PathVariable Integer id) {

        Repuestos repuestos = repuestosService.getRepuestosById(id);

        if (repuestos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no existe");
        }

        repuestosService.deleteRepuestos(id);
        return ResponseEntity.ok("Proveedor eliminado correctamente");


    }

}
