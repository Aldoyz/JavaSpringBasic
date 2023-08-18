package com.aldiichsan.service;

import com.aldiichsan.model.PlayerTrinketModel;

import java.util.List;

public interface PlayerTrinketService {
    List<PlayerTrinketModel> getAllTrinket();
    int countAllTrinket();
    PlayerTrinketModel createNewTrinket(PlayerTrinketModel playerTrinketModel) throws Exception;
    PlayerTrinketModel updateTrinket(PlayerTrinketModel playerTrinketModel) throws Exception;
    void deleteTrinket(Long id);
}
