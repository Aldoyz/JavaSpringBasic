package com.aldiichsan.mapper;

import com.aldiichsan.model.PlayerWeaponModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlayerWeaponMapper {
    List<PlayerWeaponModel> getAllWeapon();
    PlayerWeaponModel findById(Long id);
    int countAllWeapon();
    void createNewWeapon(PlayerWeaponModel playerWeaponModel);
    void updateWeapon(PlayerWeaponModel playerWeaponModel);
    void deleteWeapon(Long id);
}
