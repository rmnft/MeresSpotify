package com.meres.MeresSpotify.controller.request;

import java.util.List;
import java.util.UUID;

import com.meres.MeresSpotify.models.Plano;
import com.meres.MeresSpotify.models.Usuario;

import lombok.Data;

@Data
public class AssinaturaRequest {
    private UUID id;
    private UUID idUsuario;
    private boolean ativo;
    private Plano plano;
    private List<Usuario> usuarios;
}
