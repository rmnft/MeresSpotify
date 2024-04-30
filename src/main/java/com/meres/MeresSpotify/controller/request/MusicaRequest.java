package com.meres.MeresSpotify.controller.request;

import java.util.UUID;

import com.meres.MeresSpotify.models.Banda;

import lombok.Data;

@Data
public class MusicaRequest {
    private UUID id;
    private String nome;
    private Integer duracao;
    private Banda banda;

}
