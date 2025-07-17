package br.edu.iftm.tarefa1.livro.control;

import br.edu.iftm.tarefa1.livro.domain.Livro;
import br.edu.iftm.tarefa1.livro.repository.LivroRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public void cadastrarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
    }

    @GetMapping
    public List<Livro> getLivros(@RequestParam(required = false) String titulo,
                                 @RequestParam(required = false) String autor) {
        if (titulo != null || autor != null) {
            return livroRepository.buscarPorTituloOuAutor(titulo, autor);
        } else {
            return livroRepository.listarTodos();
        }
    }

    @GetMapping("/{isbn}")
    public Livro getLivroByIsbn(@PathVariable("isbn") String isbn) {
        return livroRepository.buscarPorIsbn(isbn);
    }
}
