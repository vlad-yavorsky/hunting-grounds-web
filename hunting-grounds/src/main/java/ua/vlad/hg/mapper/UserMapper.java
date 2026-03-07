package ua.vlad.hg.mapper;

import org.mapstruct.Mapper;
import ua.vlad.hg.entity.User;
import ua.vlad.hg.dto.UserDto;

@Mapper
public interface UserMapper {

    User toEntity(UserDto user);

}
