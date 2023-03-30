package br.com.alura.linguagesapi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Linguagem, String> {

    List<Linguagem> findByOrderByRanking();
}