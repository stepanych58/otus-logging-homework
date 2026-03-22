package ru.otus.msa.user.adapter.out.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.msa.user.adapter.in.http.dto.UserDto;
import ru.otus.msa.user.adapter.out.db.entity.User;
import ru.otus.msa.user.adapter.out.db.repository.UserRepository;
import ru.otus.msa.user.application.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RegisterUserMapper registerUserMapper;

    @Override
    public Page<User> getAll() {
        return new PageImpl<>(userRepository.findAll());
    }

    @Override
    @Transactional
    public User create(UserDto createUserRequest) {
        User userEntity = registerUserMapper.map(createUserRequest);
        log.warn("test warn log");
        log.error("test error log");
        log.info("test info log");
        return userRepository.save(userEntity);
    }


    @Override
    public User delete(UUID id) {
        User user = userRepository.findById(id)
                .orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

}
