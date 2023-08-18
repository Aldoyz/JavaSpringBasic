package com.aldiichsan.controller;

import com.aldiichsan.model.PlayerModel;
import com.aldiichsan.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/v1/mybatis/player")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "API For Character Player Management", tags = "Player")
public class MybatisPlayerController {

    private final PlayerService playerService;

    public MybatisPlayerController(@Qualifier("playerServiceMybatis") PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("List All Available Character Playeres")
    public ResponseEntity<?> getAllPlayers() {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "All available classes are shown.");
        response.put("total", playerService.countAllPlayer());
        response.put("data", playerService.getAllPlayer());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create A New Character Player")
    public ResponseEntity<?> createNewPlayer(@RequestBody PlayerModel playerModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerModel player = playerService.createNewPlayer(playerModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "New class has been created.");
            response.put("data", player);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update An Existing Character Player")
    public ResponseEntity<?> updatePlayer(@RequestBody PlayerModel playerModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerModel player = playerService.updatePlayer(playerModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Existing class has been updated");
            response.put("data", player);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete One Player")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        playerService.deletePlayer(id);
        response.put("status", HttpStatus.OK.value());
        response.put("message", "The class has been deleted successfully");
        return ResponseEntity.ok().body(response);
    }
}
