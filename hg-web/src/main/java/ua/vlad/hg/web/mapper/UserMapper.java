package ua.vlad.hg.web.mapper;

import org.mapstruct.Mapper;
import ua.vlad.hg.core.entity.User;
import ua.vlad.hg.web.dto.UserDto;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto user);

}
