package br.com.alura.linguagesapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.annotation.DeleteExchange;

@RestController
@RequestMapping("/linguagens")
public class Controller {

    @Autowired
    private Repository repository;

    @GetMapping
    public List<Linguagem> obterLinguagens() {
        return repository.findByOrderByRanking();
    }

    @GetMapping("{id}")
    public Linguagem obterLinguagemPorId(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));        
    }

    @PostMapping
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
        return new ResponseEntity<>(repository.save(linguagem), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public Linguagem atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }        
        linguagem.setId(id);
        return repository.save(linguagem);
    }

    @DeleteMapping("{id}")
    public void excluirLinguagem(@PathVariable String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        
    }
}
