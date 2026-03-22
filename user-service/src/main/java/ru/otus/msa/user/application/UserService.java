package ru.otus.msa.user.application;

import org.springframework.data.domain.Page;
import ru.otus.msa.user.adapter.in.http.dto.UserDto;
import ru.otus.msa.user.adapter.out.db.entity.User;

import java.util.UUID;

public interface UserService {
    Page<User> getAll();

    User create(UserDto user);

    User delete(UUID id);
}
