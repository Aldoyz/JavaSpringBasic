package com.aldiichsan.mapper;

import com.aldiichsan.model.PlayerTrinketModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlayerTrinketMapper {
    List<PlayerTrinketModel> getAllTrinket();
    PlayerTrinketModel findById(Long id);
    int countAllTrinket();
    void createNewTrinket(PlayerTrinketModel playerTrinketModel);
    void updateTrinket(PlayerTrinketModel playerTrinketModel);
    void deleteTrinket(Long id);
}
