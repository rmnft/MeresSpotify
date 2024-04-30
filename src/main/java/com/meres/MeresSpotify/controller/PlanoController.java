package com.meres.MeresSpotify.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.meres.MeresSpotify.controller.request.PlanoRequest;
import com.meres.MeresSpotify.models.Plano;
import com.meres.MeresSpotify.repository.PlanoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    @Autowired
    private PlanoRepository repository;

    @GetMapping
    public List<Plano> obter(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Plano> criarPlano(@Valid @RequestBody PlanoRequest request) {
        Plano plano = new Plano();
        plano.setNome(request.getNome());
        plano.setPreco(request.getPreco());
        this.repository.save(plano);

        return new ResponseEntity(plano, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Plano> obterPlano(@PathVariable UUID id) {
        return this.repository.findById(id).map(item -> {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePlano(@PathVariable UUID id){
        Optional<Plano> optPlano = this.repository.findById(id);

        //Caso não ache o usuário, retornar um 404
        if (optPlano.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Plano plano = optPlano.get();

        repository.delete(plano);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Plano> updatePlani(@PathVariable UUID id, @RequestBody Plano planoNovo) {
        Optional<Plano> optPlano = this.repository.findById(id);

        //Caso não ache o usuário, retornar um 404
        if (optPlano.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Plano plano = optPlano.get();
        if(planoNovo.getNome() != null && !planoNovo.getNome().isEmpty()){
            plano.setNome(planoNovo.getNome());
        }
        if (planoNovo.getPreco() != null && !planoNovo.getPreco().isNaN()) {
            plano.setPreco(planoNovo.getPreco());
        }

        repository.save(plano);

        return new ResponseEntity(plano, HttpStatus.OK);
    }


}
