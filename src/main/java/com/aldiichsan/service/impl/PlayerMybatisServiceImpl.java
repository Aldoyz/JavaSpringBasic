package com.aldiichsan.service.impl;

import com.aldiichsan.exception.PlayerAlreadyExistsException;
import com.aldiichsan.mapper.PlayerMapper;
import com.aldiichsan.model.PlayerModel;
import com.aldiichsan.model.PlayerSelectModel;
import com.aldiichsan.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerServiceMybatis")
@Slf4j
public class PlayerMybatisServiceImpl implements PlayerService {
    private final PlayerMapper playerMapper;

    public PlayerMybatisServiceImpl(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    @Override
    public List<PlayerSelectModel> getAllPlayer() {
        return playerMapper.getAllPlayer();
    }

    @Override
    public int countAllPlayer() {
        return playerMapper.countAllPlayer();
    }

    @Override
    public PlayerModel createNewPlayer(PlayerModel playerModel) throws Exception {
        try {
            playerMapper.createNewPlayer(playerModel);
            log.debug("generated id: {}", playerModel.getId());
            return playerModel;
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new PlayerAlreadyExistsException("Player already exists!");
            } else {
                log.error("Error when create a class.", e);
                throw e;
            }
        }
    }

    @Override
    public PlayerModel updatePlayer(PlayerModel playerModel) throws Exception {
        try {
            playerMapper.updatePlayer(playerModel);
            return playerMapper.findById(playerModel.getId());
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new PlayerAlreadyExistsException("Player already exists!");
            } else {
                log.error("Error when updating a class.", e);
                throw e;
            }
        }
    }

    @Override
    public void deletePlayer(Long id) {
        playerMapper.deletePlayer(id);
    }
}
