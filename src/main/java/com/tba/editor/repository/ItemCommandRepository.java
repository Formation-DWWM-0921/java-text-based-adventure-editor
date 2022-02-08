package com.tba.editor.repository;

import com.tba.editor.entity.command.ItemCommand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCommandRepository extends JpaRepository<ItemCommand, Integer> { }
