package com.my;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyEumeTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
//    Logger logger = Logger.getLogger(MyEumeTypeHandler.class);

    private final Class<E> type;
    private final E[] enums;

    public MyEumeTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.type = type;
            this.enums = (E[]) type.getEnumConstants();
            if (this.enums == null) {
                throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
            }
        }
    }

    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }

    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return this.enums[i];
            } catch (Exception var5) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + this.type.getSimpleName() + " by ordinal value.", var5);
            }
        }
    }

    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return this.enums[i];
            } catch (Exception var5) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + this.type.getSimpleName() + " by ordinal value.", var5);
            }
        }
    }

    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            try {
                return this.enums[i];
            } catch (Exception var5) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + this.type.getSimpleName() + " by ordinal value.", var5);
            }
        }
    }
}
