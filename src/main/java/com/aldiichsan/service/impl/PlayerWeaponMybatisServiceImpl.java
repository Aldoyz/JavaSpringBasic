package com.aldiichsan.service.impl;

import com.aldiichsan.exception.AlreadyExistsException;
import com.aldiichsan.exception.PlayerWeaponAlreadyExistsException;
import com.aldiichsan.mapper.PlayerWeaponMapper;
import com.aldiichsan.model.PlayerWeaponModel;
import com.aldiichsan.service.PlayerWeaponService;
import com.aldiichsan.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerWeaponServiceMybatis")
@Slf4j
public class PlayerWeaponMybatisServiceImpl implements PlayerWeaponService {
    private final PlayerWeaponMapper playerWeaponMapper;

    public PlayerWeaponMybatisServiceImpl(PlayerWeaponMapper playerWeaponMapper) {
        this.playerWeaponMapper = playerWeaponMapper;
    }

    @Override
    public List<PlayerWeaponModel> getAllWeapon() {
        return playerWeaponMapper.getAllWeapon();
    }

    @Override
    public int countAllWeapon() {
        return playerWeaponMapper.countAllWeapon();
    }

    @Override
    public PlayerWeaponModel createNewWeapon(PlayerWeaponModel playerWeaponModel) {
        try {
            playerWeaponMapper.createNewWeapon(playerWeaponModel);
            log.debug("generated id: {}", playerWeaponModel.getId());
            return playerWeaponModel;
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
    public PlayerWeaponModel updateWeapon(PlayerWeaponModel playerWeaponModel) {
        try {
            playerWeaponMapper.updateWeapon(playerWeaponModel);
            return playerWeaponMapper.findById(playerWeaponModel.getId());
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
    public void deleteWeapon(Long id) {
        playerWeaponMapper.deleteWeapon(id);
    }
}
