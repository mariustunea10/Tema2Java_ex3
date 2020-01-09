package com.dao;

import com.mapper.CarMapper;
import com.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CarDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource= dataSource;
        this.jdbcTemplateObject= new JdbcTemplate(dataSource);
    }


    public void insert(String license_plate,int production_year,String color,int kilometers, String brand)
    {
        String SQL = "insert into cars (licensePlate, `year`, color, kilometers, brand) values (?, ?, ?, ?, ?)";
        jdbcTemplateObject.update( SQL, license_plate,production_year,color,kilometers, brand);
    }

    public void update(int id,String license_plate,int production_year,int color,int kilometers, String brand)
    {
        String SQL = "update cars set licensePlate = ?, `year` = ?, color = ?, kilometers = ?, brand = ? where id = ?";
        jdbcTemplateObject.update(SQL, license_plate,production_year,color,kilometers, id, brand);
    }

    public void delete(String license_plate)
    {
        String SQL = "delete from cars where licensePlate = ?";
        jdbcTemplateObject.update(SQL, license_plate);
    }

    public Car getCar(String license_plate)
    {
        String SQL ="select * from cars where licensePlate = ?";
        Car car = jdbcTemplateObject.queryForObject(SQL,	new	Object[]{license_plate},new CarMapper());
        return car;
    }

    public List<Car> getAllCars()
    {
        String SQL ="select * from cars";
        List <Car> pers =	jdbcTemplateObject.query(SQL,new CarMapper());
        return	pers;
    }

    public	List<Car> getCarsModel(String model)
    {
        String SQL ="select * from cars where brand = ?";
        List <Car> pers =	jdbcTemplateObject.query(SQL,new Object[]{model},new	CarMapper());
        return	pers;
    }

    public	List<Car> getCarsUnder5Years()
    {
        String SQL ="select * from cars where `year` > 2015";
        List <Car> pers =	jdbcTemplateObject.query(SQL,new	CarMapper());
        return	pers;
    }

    public	List<Car> getCarsUnderKm(int km)
    {
        String SQL ="select * from cars where kilometers < "+km;
        List <Car> pers =	jdbcTemplateObject.query(SQL,new	CarMapper());
        return	pers;
    }
}
