package com.aldiichsan.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    // Success Messages
    DATA_CREATED("Data successfully created."),
    DATA_FETCHED("Data(s) successfully fetched."),
    DATA_MODIFIED("Data successfully modified."),
    DATA_DELETED("Data successfully deleted."),

    // Fail Messages
    DATA_NOT_FOUND("Data not found."),
    NULL_DATA("Null data is not allowed."),
    OUT_OF_BOUNDS("Access an array of data at an invalid index."),
    DATA_ALREADY_EXISTS("Data already exists."),
    MISSING_PARAMETER("Some required parameter(s) are missing for this request."),
    CANT_CONNECT_DB("Could not connect to database.");

    private final String message;

}
