package com.meres.MeresSpotify.controller.request;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class BandaRequest {
    private UUID id;
    private String nome;
    private String descricao;
    private String backDrop;
    private List<MusicaRequest> musicas;
}
