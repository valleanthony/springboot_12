package com.example.springboot_12;

import org.springframework.data.repository.CrudRepository;

public interface ActorRepo extends CrudRepository<Actor,Long> {
}
