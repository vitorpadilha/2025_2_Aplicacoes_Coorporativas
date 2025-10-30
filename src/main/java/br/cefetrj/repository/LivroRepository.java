package br.cefetrj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import br.cefetrj.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>, JpaSpecificationExecutor<Livro> {

}
