package com.app.ggshop.v1.common.enumeration;

import com.app.ggshop.v1.common.enumeration.BoardFilter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class BoardFilterTypeHandler extends BaseTypeHandler<BoardFilter> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BoardFilter parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public BoardFilter getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return fromValue(value);
    }

    @Override
    public BoardFilter getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return fromValue(value);
    }

    @Override
    public BoardFilter getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return fromValue(value);
    }

    private BoardFilter fromValue(String value) {
        if (value == null) return null;
        for (BoardFilter filter : BoardFilter.values()) {
            if (filter.getValue().equals(value)) {
                return filter;
            }
        }
        throw new IllegalArgumentException("Unknown BoardFilter: " + value);
    }
}