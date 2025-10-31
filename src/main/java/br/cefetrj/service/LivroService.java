package br.cefetrj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.cefetrj.model.Livro;
import br.cefetrj.repository.LivroRepository;

@Service
public class LivroService {
    protected LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro save(Livro entity) {
        return repository.save(entity);
    }

    public Livro update(Livro entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Livro> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Livro> findAll() {

        return repository.findAll();
    }
}
