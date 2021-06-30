package com.misaka.yuban.dao.mapper;

import com.misaka.yuban.common.dao.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
    @Insert("insert into log (user_id,url, request, response, exception, time_cost) " +
            "values (#{log.userId},#{log.url}, #{log.request}, #{log.response}, " +
            "#{log.exception}, #{log.timeCost})")
    void insert(@Param("log") Log log);

    @Delete("DELETE FROM log WHERE created_at <= DATE_FORMAT( NOW( ) - INTERVAL 7 DAY , '%Y-%m-%d')")
    void schduleDelete();
}
