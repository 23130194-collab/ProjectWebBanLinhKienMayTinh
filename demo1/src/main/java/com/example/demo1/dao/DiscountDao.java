package com.example.demo1.dao;

import com.example.demo1.model.Discount;
import org.jdbi.v3.core.statement.StatementContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;

public class DiscountDao extends DatabaseDao {

    public static class DiscountMapper implements RowMapper<Discount> {
        @Override
        public Discount map(ResultSet rs, StatementContext ctx) throws SQLException {
            Discount discount = new Discount();
            discount.setId(rs.getInt("id"));
            discount.setDiscountValue(rs.getDouble("discountValue"));
            discount.setStartTime(rs.getTimestamp("startTime"));
            discount.setEndTime(rs.getTimestamp("endTime"));
            return discount;
        }
    }

    /**
     * Thêm một mục giảm giá mới vào cơ sở dữ liệu và trả về ID được tạo tự động.
     * @param discount Đối tượng Discount chứa thông tin cần thêm.
     * @return ID của mục giảm giá vừa được tạo.
     */
    public int addDiscountAndReturnId(Discount discount) {
        return get().withHandle(handle -> {
            String sql = "INSERT INTO discounts (discount_value, start_date, end_date) VALUES (:discountValue, :startDate, :endDate)";
            return handle.createUpdate(sql)
                    .bindBean(discount)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
        });
    }
}
