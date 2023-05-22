package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.application.entities.Teste;

@RepositoryRestResource(collectionResourceRel = "teste", path = "testes")
public interface TesteRepository extends PagingAndSortingRepository<Teste, Long> {

    /**
     * Método que retorna uma lista de clientes fazendo a busca pelo nome
     passado como parâmetro.
     *
     * @param name
     * @return lista de clientes
     */
    List<Teste> findByNome(@Param("name") String name);

    /**
     * Método que retorna o cliente com apenas seu nome fazendo a busca
     com o id passado como parâmetro.
     *
     * @param id
     * @return cliente do id passado como parâmetro.
     */
    @Query("SELECT c.nome FROM Teste c where c.id = :id")
    Teste findNomeById(@Param("id") Integer id);

    /**
     * Método que retorna uma lista de clientes fazendo a busca pelo nome passado
     como parâmetro e ordenando os
     * clientes pelo nome.
     *
     * @param name
     * @return lista de clientes
     */
    List<Teste> findByNomeOrderByNome(@Param("name") String name);
}