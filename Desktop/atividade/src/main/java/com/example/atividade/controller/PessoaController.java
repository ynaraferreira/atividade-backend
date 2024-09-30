package com.example.atividade.controller;

import com.example.atividade.dto.PessoaDto;
import com.example.atividade.model.Pessoa;
import com.example.atividade.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository Repository;

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDto Dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(Dto.getNome());
        pessoa.setCpf(Dto.getCpf());
        pessoa.setIdade(Dto.getIdade());
        Repository.save(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = Repository.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = Repository.findById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
