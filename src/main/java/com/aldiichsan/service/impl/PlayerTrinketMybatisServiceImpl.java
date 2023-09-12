package com.aldiichsan.service.impl;

import com.aldiichsan.exception.AlreadyExistsException;
import com.aldiichsan.exception.PlayerTrinketAlreadyExistsException;
import com.aldiichsan.mapper.PlayerTrinketMapper;
import com.aldiichsan.model.PlayerTrinketModel;
import com.aldiichsan.service.PlayerTrinketService;
import com.aldiichsan.util.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerTrinketServiceMybatis")
public class PlayerTrinketMybatisServiceImpl implements PlayerTrinketService {

    private static final Logger log = LoggerFactory.getLogger(PlayerTrinketMybatisServiceImpl.class);

    private final PlayerTrinketMapper playerTrinketMapper;

    public PlayerTrinketMybatisServiceImpl(PlayerTrinketMapper playerTrinketMapper) {
        this.playerTrinketMapper = playerTrinketMapper;
    }

    @Override
    public List<PlayerTrinketModel> getAllTrinket() {
        return playerTrinketMapper.getAllTrinket();
    }

    @Override
    public int countAllTrinket() {
        return playerTrinketMapper.countAllTrinket();
    }

    @Override
    public PlayerTrinketModel createNewTrinket(PlayerTrinketModel playerTrinketModel) {
        try {
            playerTrinketMapper.createNewTrinket(playerTrinketModel);
            log.debug("generated id: {}", playerTrinketModel.getId());
            return playerTrinketModel;
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
    public PlayerTrinketModel updateTrinket(PlayerTrinketModel playerTrinketModel) {
        try {
            playerTrinketMapper.updateTrinket(playerTrinketModel);
            return playerTrinketMapper.findById(playerTrinketModel.getId());
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
    public void deleteTrinket(Long id) {
        playerTrinketMapper.deleteTrinket(id);
    }
}
