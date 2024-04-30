package com.meres.MeresSpotify.controller.request;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.meres.MeresSpotify.models.Musica;
import com.meres.MeresSpotify.models.Usuario;

import lombok.Data;

@Data
public class PlaylistRequest {
    private UUID id;

    private UUID idUsuario;

    private String nome;

    private List<Musica> musicas = new ArrayList<>();

    private Usuario usuario;
}
