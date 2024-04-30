package com.meres.MeresSpotify.controller.request;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class PlanoRequest {
    private UUID id;
    private String nome;
    private Double preco;
    private List<AssinaturaRequest> assinaturas;
}
