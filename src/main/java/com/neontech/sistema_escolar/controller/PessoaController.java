package com.neontech.sistema_escolar.controller;

import com.neontech.sistema_escolar.model.Pessoa;
import com.neontech.sistema_escolar.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.salvar(pessoa);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> buscarPessoaPorCpf(@PathVariable String cpf) {
        return pessoaService.buscarPorCpf(cpf)
                .map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaAtualizada = pessoaService.salvar(pessoa);
            return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
