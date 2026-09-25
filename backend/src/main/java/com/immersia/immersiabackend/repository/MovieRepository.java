package com.immersia.immersiabackend.repository;

import com.immersia.immersiabackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository <Movie ,Long> {

}
