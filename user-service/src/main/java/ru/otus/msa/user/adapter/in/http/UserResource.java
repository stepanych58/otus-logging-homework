package ru.otus.msa.user.adapter.in.http;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.msa.user.adapter.in.http.dto.UserDto;
import ru.otus.msa.user.adapter.out.db.entity.User;
import ru.otus.msa.user.application.UserService;

import java.util.UUID;

@Tag(name = "user-service CRUD", description = "API to work with user data")
@RestController
@RequestMapping("/user-service/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserResource {

    private final UserService userService;

    @GetMapping
    public PagedModel<User> getAll() {
        return new PagedModel<>(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto user) {
        User result = userService.create(user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public User delete(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable UUID id) {
        return userService.delete(id);
    }
}
