package com.aldiichsan.service.impl;

import com.aldiichsan.exception.PlayerClassAlreadyExistsException;
import com.aldiichsan.mapper.PlayerClassMapper;
import com.aldiichsan.model.PlayerClassModel;
import com.aldiichsan.service.PlayerClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerClassServiceMybatis")
@Slf4j
public class PlayerClassMybatisServiceImpl implements PlayerClassService {
    private final PlayerClassMapper playerClassMapper;

    public PlayerClassMybatisServiceImpl(PlayerClassMapper playerClassMapper) {
        this.playerClassMapper = playerClassMapper;
    }

    @Override
    public List<PlayerClassModel> getAllClass() {
        return playerClassMapper.getAllClass();
    }

    @Override
    public int countAllClass() {
        return playerClassMapper.countAllClass();
    }

    @Override
    public PlayerClassModel createNewClass(PlayerClassModel playerClassModel) throws Exception {
        try {
            playerClassMapper.createNewClass(playerClassModel);
            log.debug("generated id: {}", playerClassModel.getId());
            return playerClassModel;
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new PlayerClassAlreadyExistsException("Class already exists!");
            } else {
                log.error("Error when create a class.", e);
                throw e;
            }
        }
    }

    @Override
    public PlayerClassModel updateClass(PlayerClassModel playerClassModel) throws Exception {
        try {
            playerClassMapper.updateClass(playerClassModel);
            return playerClassMapper.findById(playerClassModel.getId());
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new PlayerClassAlreadyExistsException("Class already exists!");
            } else {
                log.error("Error when updating a class.", e);
                throw e;
            }
        }
    }

    @Override
    public void deleteClass(Long id) {
        playerClassMapper.deleteClass(id);
    }
}
