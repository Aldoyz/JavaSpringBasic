package com.aldiichsan.controller;

import com.aldiichsan.model.PlayerWeaponModel;
import com.aldiichsan.service.PlayerWeaponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/v1/mybatis/player-weapon")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "API For Character Weapon Management", tags = "Player Weapon")
public class MybatisPlayerWeaponController {

    private final PlayerWeaponService playerWeaponService;

    public MybatisPlayerWeaponController(@Qualifier("playerWeaponServiceMybatis") PlayerWeaponService playerWeaponService) {
        this.playerWeaponService = playerWeaponService;
    }

    @GetMapping(path = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("List All Available Character Weapons")
    public ResponseEntity<?> getAllWeapons() {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "All available weapons are shown.");
        response.put("total", playerWeaponService.countAllWeapon());
        response.put("data", playerWeaponService.getAllWeapon());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create A New Character Weapon")
    public ResponseEntity<?> createNewWeapon(@RequestBody PlayerWeaponModel playerWeaponModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerWeaponModel playerWeapon = playerWeaponService.createNewWeapon(playerWeaponModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "New weapon has been created.");
            response.put("data", playerWeapon);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update An Existing Character Weapon")
    public ResponseEntity<?> updateWeapon(@RequestBody PlayerWeaponModel playerWeaponModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerWeaponModel playerWeapon = playerWeaponService.updateWeapon(playerWeaponModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Existing weapon has been updated");
            response.put("data", playerWeapon);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete One Weapon")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        playerWeaponService.deleteWeapon(id);
        response.put("status", HttpStatus.OK.value());
        response.put("message", "The weapon has been deleted successfully");
        return ResponseEntity.ok().body(response);
    }
}
