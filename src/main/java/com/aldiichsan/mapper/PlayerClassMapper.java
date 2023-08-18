package com.aldiichsan.mapper;

import com.aldiichsan.model.PlayerClassModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlayerClassMapper {
    List<PlayerClassModel> getAllClass();
    PlayerClassModel findById(Long id);
    int countAllClass();
    void createNewClass(PlayerClassModel playerClassModel);
    void updateClass(PlayerClassModel playerClassModel);
    void deleteClass(Long id);
}
