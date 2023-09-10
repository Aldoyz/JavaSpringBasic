package com.aldiichsan.service.impl;

import com.aldiichsan.exception.AlreadyExistsException;
import com.aldiichsan.exception.ApiExceptionHandling;
import com.aldiichsan.mapper.PlayerMapper;
import com.aldiichsan.model.PlayerModel;
import com.aldiichsan.model.PlayerSelectModel;
import com.aldiichsan.service.PlayerService;
import com.aldiichsan.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerServiceMybatis")
@Slf4j
public class PlayerMybatisServiceImpl implements PlayerService {

    private final PlayerMapper playerMapper;
    private final ApiExceptionHandling apiExceptionHandling;

    public PlayerMybatisServiceImpl(PlayerMapper playerMapper, ApiExceptionHandling apiExceptionHandling) {
        this.playerMapper = playerMapper;
        this.apiExceptionHandling = apiExceptionHandling;
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
    public PlayerModel createNewPlayer(PlayerModel playerModel) {
        try {
            playerMapper.createNewPlayer(playerModel);
            log.debug("generated id: {}", playerModel.getId());
            return playerModel;
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new AlreadyExistsException(ResponseMessage.DATA_ALREADY_EXISTS.getMessage());
            } else {
                log.error("Error when create a class.", e);
                throw e;
            }
        }
    }

    @Override
    public PlayerModel updatePlayer(PlayerModel playerModel) {
        try {
            playerMapper.updatePlayer(playerModel);
            return playerMapper.findById(playerModel.getId());
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new AlreadyExistsException(ResponseMessage.DATA_ALREADY_EXISTS.getMessage());
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

    @Override
    public void textException(String code) {
        try {
            throw new NullPointerException("FORCED EXCEPTION TEST");
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
        }
    }
}
