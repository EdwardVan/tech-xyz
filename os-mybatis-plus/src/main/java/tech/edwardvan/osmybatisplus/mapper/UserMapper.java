package tech.edwardvan.osmybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tech.edwardvan.osmybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author EdwardVan
 * @since 2020-03-31
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from demo_user ${ew.customSqlSegment}")
    List<User> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);

    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper wrapper);
}
