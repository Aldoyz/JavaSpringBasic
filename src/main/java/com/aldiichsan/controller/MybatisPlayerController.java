package com.aldiichsan.controller;

import com.aldiichsan.model.PlayerModel;
import com.aldiichsan.model.PlayerSelectModel;
import com.aldiichsan.service.PlayerService;
import com.aldiichsan.util.ApiResponse;
import com.aldiichsan.util.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

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
        int totalPlayer = playerService.countAllPlayer();
        List<PlayerSelectModel> playerList = playerService.getAllPlayer();
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_FETCHED.getMessage(), totalPlayer, playerList));
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create A New Character Player")
    public ResponseEntity<?> createNewPlayer(@RequestBody PlayerModel playerModel) {
        try {
            PlayerModel player = playerService.createNewPlayer(playerModel);
            return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.CREATED, ResponseMessage.DATA_CREATED.getMessage(), player));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ApiResponse.fail(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update An Existing Character Player")
    public ResponseEntity<?> updatePlayer(@RequestBody PlayerModel playerModel) {
        try {
            PlayerModel player = playerService.updatePlayer(playerModel);
            return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_MODIFIED.getMessage(), player));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ApiResponse.fail(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete One Player")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_DELETED.getMessage()));
    }

    @GetMapping(path = "test-exception/{code}")
    @ApiOperation("Test Exception Handling")
    public void testException(@PathVariable(value = "code") String code) {
        playerService.textException(code);
//        return ResponseEntity.ok().body("Success");
    }
}
