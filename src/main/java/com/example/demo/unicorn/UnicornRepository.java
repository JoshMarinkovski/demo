package com.example.demo.unicorn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnicornRepository extends JpaRepository<Unicorn, Long> {}