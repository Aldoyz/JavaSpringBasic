package com.aldiichsan.service;

import com.aldiichsan.model.PlayerClassModel;

import java.util.List;

public interface PlayerClassService {
    List<PlayerClassModel> getAllClass();
    int countAllClass();
    PlayerClassModel createNewClass(PlayerClassModel playerClassModel) throws Exception;
    PlayerClassModel updateClass(PlayerClassModel playerClassModel) throws Exception;
    void deleteClass(Long id);
}
