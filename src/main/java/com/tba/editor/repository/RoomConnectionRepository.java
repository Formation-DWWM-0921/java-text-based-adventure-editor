package com.tba.editor.repository;

import com.tba.editor.entity.Room;
import com.tba.editor.entity.RoomConnection;
import com.tba.editor.entity.command.DirectionCommand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomConnectionRepository extends JpaRepository<RoomConnection, Integer> {
    public RoomConnection findByFromRoomAndDirection(Room fromRoom, DirectionCommand direction);
}
