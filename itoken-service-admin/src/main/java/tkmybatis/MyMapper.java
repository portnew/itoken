package tkmybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author 张宏运
 * @Date  2019-05-13 20:22
 *
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}