package br.com.sebo.repository;

import br.com.sebo.pojo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    List<Livro> findAllByAno (String ano);
}