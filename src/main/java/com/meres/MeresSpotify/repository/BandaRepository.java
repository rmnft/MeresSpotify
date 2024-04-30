package com.meres.MeresSpotify.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meres.MeresSpotify.models.Banda;

public interface BandaRepository extends JpaRepository<Banda, UUID> {
}
