package com.kevinlancerio.RepuestosAutomotrices.repository;

import com.kevinlancerio.RepuestosAutomotrices.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores,Integer> {

    boolean existsByEmailProveedor(String emailProveedor);

}
