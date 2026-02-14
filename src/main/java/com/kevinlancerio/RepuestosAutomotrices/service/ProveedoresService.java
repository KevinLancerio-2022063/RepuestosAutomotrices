package com.kevinlancerio.RepuestosAutomotrices.service;

import com.kevinlancerio.RepuestosAutomotrices.entity.Proveedores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProveedoresService {

    List<Proveedores> getAllProveedores();
    Proveedores getProveedoresById(Integer id);
    Proveedores saveProveedores (Proveedores proveedores) throws RuntimeException;
    Proveedores updateProveedores (Integer id, Proveedores proveedores);
    void deleteProveedores (Integer id);


}
