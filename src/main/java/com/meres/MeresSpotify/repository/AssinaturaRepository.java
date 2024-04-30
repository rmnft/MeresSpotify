package com.meres.MeresSpotify.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meres.MeresSpotify.models.Assinatura;

public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {
}
