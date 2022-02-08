package com.tba.editor.repository;

import com.tba.editor.entity.command.Command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> { }
