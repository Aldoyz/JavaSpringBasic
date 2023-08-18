package com.aldiichsan.controller;

import com.aldiichsan.model.PlayerClassModel;
import com.aldiichsan.service.PlayerClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/v1/mybatis/player-class")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "API For Character Class Management", tags = "Player Class")
public class MybatisPlayerClassController {

    private final PlayerClassService playerClassService;

    public MybatisPlayerClassController(@Qualifier("playerClassServiceMybatis") PlayerClassService playerClassService) {
        this.playerClassService = playerClassService;
    }

    @GetMapping(path = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("List All Available Character Classes")
    public ResponseEntity<?> getAllClasses() {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "All available classes are shown.");
        response.put("total", playerClassService.countAllClass());
        response.put("data", playerClassService.getAllClass());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create A New Character Class")
    public ResponseEntity<?> createNewClass(@RequestBody PlayerClassModel playerClassModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerClassModel playerClass = playerClassService.createNewClass(playerClassModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "New class has been created.");
            response.put("data", playerClass);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update An Existing Character Class")
    public ResponseEntity<?> updateClass(@RequestBody PlayerClassModel playerClassModel) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        try {
            PlayerClassModel playerClass = playerClassService.updateClass(playerClassModel);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Existing class has been updated");
            response.put("data", playerClass);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete One Class")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        playerClassService.deleteClass(id);
        response.put("status", HttpStatus.OK.value());
        response.put("message", "The class has been deleted successfully");
        return ResponseEntity.ok().body(response);
    }
}
