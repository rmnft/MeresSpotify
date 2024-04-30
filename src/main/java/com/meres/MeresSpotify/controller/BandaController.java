package com.meres.MeresSpotify.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meres.MeresSpotify.controller.request.BandaRequest;
import com.meres.MeresSpotify.controller.request.MusicaRequest;
import com.meres.MeresSpotify.models.Banda;
import com.meres.MeresSpotify.models.Musica;
import com.meres.MeresSpotify.repository.BandaRepository;
import com.meres.MeresSpotify.repository.MusicaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/banda")
public class BandaController {
    @Autowired
    private BandaRepository repository;

    @Autowired
    private MusicaRepository musicaRepository;

    @PostMapping
    public ResponseEntity<Banda> criar(@Valid @RequestBody BandaRequest request) {
        Banda banda = new Banda();
        banda.setNome(request.getNome());
        banda.setDescricao(request.getDescricao());
        banda.setBackDrop(request.getBackDrop());
        this.repository.save(banda);

        for (MusicaRequest item : request.getMusicas()) {
            Musica musica = new Musica();
            musica.setBanda(banda);
            musica.setNome(item.getNome());
            musica.setId(UUID.randomUUID());
            musica.setDuracao(item.getDuracao());

            banda.getMusicas().add(musica);

            this.musicaRepository.save(musica);
        }

        return new ResponseEntity<>(banda, HttpStatus.CREATED);

    }

    @GetMapping
    public List<Banda> obter() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Banda> obter(@PathVariable("id") UUID id) {
        return this.repository.findById(id).map(item -> {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/musica")
    public ResponseEntity<Banda> associarMusica(@PathVariable("id") UUID id, @Valid @RequestBody MusicaRequest request) {
        Optional<Banda> optBanda = this.repository.findById(id);

        if (optBanda.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Banda banda = optBanda.get();

        Musica musica = new Musica();
        musica.setNome(request.getNome());
        musica.setDuracao(request.getDuracao());
        musica.setBanda(banda);
        banda.getMusicas().add(musica);

        this.musicaRepository.save(musica);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}/musica")
    public ResponseEntity<List<Musica>> obterMusicas(@PathVariable("id") UUID id){
        return this.repository.findById(id).map(item -> {
            return new ResponseEntity<>(item.getMusicas(), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
