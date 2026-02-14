package com.kevinlancerio.RepuestosAutomotrices.service;

import com.kevinlancerio.RepuestosAutomotrices.entity.Proveedores;
import com.kevinlancerio.RepuestosAutomotrices.repository.ProveedoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresServiceImplements implements ProveedoresService{

    private final ProveedoresRepository proveedoresRepository;

    public ProveedoresServiceImplements(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;

    }

    @Override
    public List <Proveedores> getAllProveedores() {
        return proveedoresRepository.findAll();
    }

    @Override
    public Proveedores getProveedoresById(Integer id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedores saveProveedores(Proveedores proveedor) {

        String correo = proveedor.getEmailProveedor().toLowerCase();

        if(!(correo.endsWith("@gmail.com") ||
                correo.endsWith("@outlook.com") ||
                correo.endsWith("@hotmail.com") ||
                correo.endsWith("@yahoo.com"))){

            throw new IllegalArgumentException("El correo debe ser gmail, outlook, hotmail o yahoo");
        }

        boolean existe = proveedoresRepository.existsByEmailProveedor(proveedor.getEmailProveedor());

        if(existe){
            throw new IllegalArgumentException("Ya existe un proveedor con ese correo");
        }

        return proveedoresRepository.save(proveedor);
    }

    @Override
    public void deleteProveedores (Integer id) {
        proveedoresRepository.deleteById(id);
    }

    @Override
    public Proveedores updateProveedores(Integer id, Proveedores proveedor) {

        Proveedores proveedorExistente = proveedoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no se ha encontrado con este id: " + id));

        proveedorExistente.setNombreProveedor(proveedor.getNombreProveedor());
        proveedorExistente.setTelefonoProveedor(proveedor.getTelefonoProveedor());
        proveedorExistente.setDireccion(proveedor.getDireccion());
        proveedorExistente.setEmailProveedor(proveedor.getEmailProveedor());

        return proveedoresRepository.save(proveedorExistente);

    }
}
