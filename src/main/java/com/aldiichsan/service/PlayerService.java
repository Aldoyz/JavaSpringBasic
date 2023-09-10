package com.aldiichsan.service;

import com.aldiichsan.model.PlayerModel;
import com.aldiichsan.model.PlayerSelectModel;

import java.util.List;

public interface PlayerService {
    List<PlayerSelectModel> getAllPlayer();
    int countAllPlayer();
    PlayerModel createNewPlayer(PlayerModel playerModel) throws Exception;
    PlayerModel updatePlayer(PlayerModel playerModel) throws Exception;
    void deletePlayer(Long id);
    void textException(String code);
}
