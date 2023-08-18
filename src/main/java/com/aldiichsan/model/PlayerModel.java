package com.aldiichsan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerModel {
    Long id;
    String playerName;
    Long classId;
    String className;
    Long weaponId;
    String weaponName;
    Long trinketId;
    String trinketName;
}
