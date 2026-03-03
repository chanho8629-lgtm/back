package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.dto.main.MainCardDTO;
import com.app.ggshop.v1.dto.main.MainNotificationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeMapper {
    List<MainCardDTO> selectRecentCards(@Param("limit") int limit);

    int selectActivePostCount();

    int selectNotificationCount(@Param("memberId") Long memberId);

    List<MainNotificationDTO> selectRecentNotifications(@Param("memberId") Long memberId,
                                                        @Param("limit") int limit);
}
