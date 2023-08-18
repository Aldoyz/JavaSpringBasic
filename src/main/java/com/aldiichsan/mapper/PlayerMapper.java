package com.aldiichsan.mapper;

import com.aldiichsan.model.PlayerModel;
import com.aldiichsan.model.PlayerSelectModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlayerMapper {
    List<PlayerSelectModel> getAllPlayer();
    PlayerModel findById(Long id);
    int countAllPlayer();
    void createNewPlayer(PlayerModel playerModel);
    void updatePlayer(PlayerModel playerModel);
    void deletePlayer(Long id);
}
