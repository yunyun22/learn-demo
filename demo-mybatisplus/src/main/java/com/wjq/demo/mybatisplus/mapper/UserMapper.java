package com.wjq.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjq.demo.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JWANG1
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 批量更新数据
     *
     * @param ids ids
     * @return 影响数量
     */
    int batchUpdate(@Param("list") List<Integer> ids,@Param("age") Integer age);
}
