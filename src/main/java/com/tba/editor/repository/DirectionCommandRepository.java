package com.tba.editor.repository;

import com.tba.editor.entity.command.DirectionCommand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionCommandRepository extends JpaRepository<DirectionCommand, Integer> { }
