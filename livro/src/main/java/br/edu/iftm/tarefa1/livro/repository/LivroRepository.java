package br.edu.iftm.tarefa1.livro.repository;

import br.edu.iftm.tarefa1.livro.domain.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Repository;

@Repository
public class LivroRepository {
    private List<Livro> livros = new ArrayList<>();

    public void save(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> listarTodos() {
        return livros;
    }

    public List<Livro> buscarPorTituloOuAutor(String titulo, String autor) {
        return livros.stream().filter(l -> 
            (titulo == null || l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) &&
            (autor == null || l.getAutor().toLowerCase().contains(autor.toLowerCase()))
        ).collect(Collectors.toList());
    }

    public Livro buscarPorIsbn(String isbn) {
        return livros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
}
