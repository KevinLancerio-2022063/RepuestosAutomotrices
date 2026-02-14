package com.kevinlancerio.RepuestosAutomotrices.service;

import com.kevinlancerio.RepuestosAutomotrices.entity.Empleado;
import com.kevinlancerio.RepuestosAutomotrices.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImplements implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImplements(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadosById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws RuntimeException {

        String correo = empleado.getEmailEmpleado().toLowerCase();

        if (!(correo.endsWith("@gmail.com") ||
                correo.endsWith("@outlook.com") ||
                correo.endsWith("@hotmail.com") ||
                correo.endsWith("@yahoo.com"))) {

            throw new IllegalArgumentException(
                    "El correo debe ser gmail, outlook, hotmail o yahoo");
        }

        boolean existe = empleadoRepository
                .existsByNombreEmpleadoAndApellidoEmpleadoAndEmailEmpleado(
                        empleado.getNombreEmpleado(),
                        empleado.getApellidoEmpleado(),
                        empleado.getEmailEmpleado()
                );

        if (existe) {
            throw new IllegalArgumentException("El empleado ya está registrado");
        }

        return empleadoRepository.save(empleado);
    }

    @Override
    public void deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);

    }

    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado) {

        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no se ha encontrado con id: " + id));

        empleadoExistente.setNombreEmpleado(empleado.getNombreEmpleado());
        empleadoExistente.setApellidoEmpleado(empleado.getApellidoEmpleado());
        empleadoExistente.setPuestoEmpleado(empleado.getPuestoEmpleado());
        empleadoExistente.setEmailEmpleado(empleado.getEmailEmpleado());

        return empleadoRepository.save(empleadoExistente);
    }



}

