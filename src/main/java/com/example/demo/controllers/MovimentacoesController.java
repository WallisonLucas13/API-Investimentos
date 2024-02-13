package com.example.demo.controllers;

import com.example.demo.Exceptions.NotFoundRegisterException;
import com.example.demo.models.Movimentacoes;
import com.example.demo.dtos.MovimentacoesDto;
import com.example.demo.services.MovimentacoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/movimentacoes")
public class MovimentacoesController {

    @Autowired
    private MovimentacoesService service;

    @PostMapping("salvar")
    public ResponseEntity<String> save(@RequestBody @Valid MovimentacoesDto dto){
        service.save(dto);
        return ResponseEntity.ok("Movimentação de " + dto.operacao() + " Salva!");
    }

    @GetMapping("listarTodas")
    public ResponseEntity<List<Movimentacoes>> listarTodos(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("encontrar/{id}")
    public ResponseEntity<Object> encontrarMovimentacao(@PathVariable("id") Long id){
        try{
            Movimentacoes movimentacoes = service.encontrarMovimentacao(id);
            return ResponseEntity.ok(movimentacoes);
        }
        catch (NotFoundRegisterException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("apagar/{id}")
    public ResponseEntity<String> apagar(@PathVariable("id") Long id){
        try {
            service.apagar(id);
            return ResponseEntity.ok("Movimentação Apagada com Sucesso!");
        }
        catch(NotFoundRegisterException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") Long id,
                                             @RequestBody @Valid MovimentacoesDto dto){
        try {
            Movimentacoes atualizada = service.atualizar(id, dto);
            return ResponseEntity.ok(atualizada);
        }
        catch(NotFoundRegisterException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
