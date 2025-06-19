package com.neontech.sistema_escolar.controller;

import com.neontech.sistema_escolar.model.Curso;
import com.neontech.sistema_escolar.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.salvar(curso);
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodosCursos() {
        List<Curso> cursos = cursoService.listarTodos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Curso>> listarCursosAtivos() {
        List<Curso> cursosAtivos = cursoService.listarAtivos();
        return new ResponseEntity<>(cursosAtivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(curso -> new ResponseEntity<>(curso, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            Curso cursoAtualizado = cursoService.salvar(curso);
            return new ResponseEntity<>(cursoAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Curso> alterarStatusCurso(@PathVariable Long id, @RequestParam boolean ativo) {
        try {
            Curso cursoAtualizado = cursoService.alterarStatus(id, ativo);
            return new ResponseEntity<>(cursoAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        try {
            cursoService.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict se houver matrículas
        }
    }
}