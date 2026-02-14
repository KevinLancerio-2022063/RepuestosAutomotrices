package com.kevinlancerio.RepuestosAutomotrices.repository;

import com.kevinlancerio.RepuestosAutomotrices.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {

    boolean existsByEmailEmpleado(String emailEmpleado);

}
