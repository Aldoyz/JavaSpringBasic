package com.aldiichsan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerSelectModel {
    Long id;
    String playerName;
    String className;
    String weaponName;
    String trinketName;
}
