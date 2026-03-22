package ru.otus.msa.user.adapter.out.db;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.msa.user.adapter.in.http.dto.UserDto;
import ru.otus.msa.user.adapter.out.db.entity.User;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {
        UUID.class
})
public interface RegisterUserMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    User map(UserDto registerUserDto);

}
