package ru.otus.msa.user.adapter.in.http.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonDeserialize
@JsonSerialize
public class UserDto {
    private String email;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String password;
}
