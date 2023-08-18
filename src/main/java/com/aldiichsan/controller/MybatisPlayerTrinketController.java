package com.aldiichsan.controller;

import com.aldiichsan.model.PlayerTrinketModel;
import com.aldiichsan.service.PlayerTrinketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/v1/mybatis/player-trinket")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "API For Character Trinket Management", tags = "Player Trinket")
public class MybatisPlayerTrinketController {

    private final PlayerTrinketService playerTrinketService;

    public MybatisPlayerTrinketController(@Qualifier("playerTrinketServiceMybatis") PlayerTrinketService playerTrinketService) {
        this.playerTrinketService = playerTrinketService;
    }

    @GetMapping(path = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("List All Available Character Trinketes")
    public ResponseEntity<?> getAllTrinketes() {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "All available classes are shown.");
        response.put("total", playerTrinketService.countAllTrinket());
        response.put("data", playerTrinketService.getAllTrinket());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create A New Character Trinket")
    public ResponseEntity<?> createNewTrinket(@RequestBody PlayerTrinketModel playerTrinketModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerTrinketModel playerTrinket = playerTrinketService.createNewTrinket(playerTrinketModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "New class has been created.");
            response.put("data", playerTrinket);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update An Existing Character Trinket")
    public ResponseEntity<?> updateTrinket(@RequestBody PlayerTrinketModel playerTrinketModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerTrinketModel playerTrinket = playerTrinketService.updateTrinket(playerTrinketModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Existing class has been updated");
            response.put("data", playerTrinket);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete One Trinket")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        playerTrinketService.deleteTrinket(id);
        response.put("status", HttpStatus.OK.value());
        response.put("message", "The class has been deleted successfully");
        return ResponseEntity.ok().body(response);
    }
}
