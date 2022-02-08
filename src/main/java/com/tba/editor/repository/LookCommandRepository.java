package com.tba.editor.repository;

import com.tba.editor.entity.command.LookCommand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LookCommandRepository extends JpaRepository<LookCommand, Integer> { }
