package com.kevinlancerio.RepuestosAutomotrices.controller;

import com.kevinlancerio.RepuestosAutomotrices.entity.Empleado;
import com.kevinlancerio.RepuestosAutomotrices.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @PostMapping
    public ResponseEntity<?> createEmpleado(@RequestBody Empleado empleado) {

        if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre no puede estar vacío");
        }

        if (empleado.getApellidoEmpleado() == null || empleado.getApellidoEmpleado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El apellido no puede estar vacío");
        }

        if (empleado.getPuestoEmpleado() == null || empleado.getPuestoEmpleado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El puesto no puede estar vacío");
        }

        if (empleado.getEmailEmpleado() == null || empleado.getEmailEmpleado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El correo no puede estar vacío");
        }

        try {
            Empleado nuevo = empleadoService.saveEmpleado(empleado);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpleadoById(@PathVariable Integer id){
        Empleado empleado = empleadoService.getEmpleadosById(id);

        if(empleado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empleado no encontrado");
        }

        return ResponseEntity.ok(empleado);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Integer id,
                                            @RequestBody Empleado empleado) {

        Empleado actualizado = empleadoService.updateEmpleado(id, empleado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id){

        Empleado empleado = empleadoService.getEmpleadosById(id);

        if(empleado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empleado no existe");
        }

        empleadoService.deleteEmpleado(id);
        return ResponseEntity.ok("Empleado eliminado correctamente");
    }


}
