package com.app.BrzFinances.entity.mapper;

import com.app.BrzFinances.entity.BrzUser;
import com.app.BrzFinances.entity.dto.BrzUserRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface BrzUserMapper {

    BrzUser updateUserFromDto(BrzUser brzUser, BrzUserRequestDto brzUserRequestDto);
}
