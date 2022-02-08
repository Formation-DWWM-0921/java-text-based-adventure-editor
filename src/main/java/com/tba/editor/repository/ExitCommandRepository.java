package com.tba.editor.repository;

import com.tba.editor.entity.command.ExitCommand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExitCommandRepository extends JpaRepository<ExitCommand, Integer> { }
