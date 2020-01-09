package com.mapper;

import com.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car>
{
    public Car mapRow(ResultSet rs, int	rowNum) throws SQLException
    {
        Car car = new Car();
        car.setId(rs.getInt(	"id"));
        car.setLicensePlate(rs.getString("licensePlate"));
        car.setYear(rs.getInt("year"));
        car.setColor(rs.getString("color"));
        car.setKilometers(rs.getInt("kilometers"));
        car.setBrand(rs.getString("brand"));

        return car;
    }
}
