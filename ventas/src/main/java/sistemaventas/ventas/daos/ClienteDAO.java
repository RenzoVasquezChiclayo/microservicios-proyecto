package sistemaventas.ventas.daos;

import org.springframework.stereotype.Repository;

import sistemaventas.ventas.entidad.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer>{


    public Cliente findByRuc(String ruc);



}
