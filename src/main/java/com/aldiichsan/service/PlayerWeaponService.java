package com.aldiichsan.service;

import com.aldiichsan.model.PlayerWeaponModel;

import java.util.List;

public interface PlayerWeaponService {
    List<PlayerWeaponModel> getAllWeapon();
    int countAllWeapon();
    PlayerWeaponModel createNewWeapon(PlayerWeaponModel playerWeaponModel) throws Exception;
    PlayerWeaponModel updateWeapon(PlayerWeaponModel playerWeaponModel) throws Exception;
    void deleteWeapon(Long id);
}
