package br.com.somapay.infraestructure.repositoy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FuncionarioRepositoryImpl {


    @PersistenceContext
    private EntityManager manager;


}
