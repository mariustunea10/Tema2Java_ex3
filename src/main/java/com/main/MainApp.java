package com.main;


import com.dao.CarDAO;
import com.model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String licensePlate, model, color;
        int productionYear,kilometers;

        ApplicationContext context =new ClassPathXmlApplicationContext("Beans_2_3.xml");
        CarDAO carDao =(CarDAO)context.getBean("opJDBC");

        int Choice=9;
        boolean loop_condition = true;
        List<Car> cars;

        while (loop_condition)
        {
            System.out.println("1.Add Car");
            System.out.println("2.Delete Car");
            System.out.println("3.Search for Car");
            System.out.println("4.List Cars");
            System.out.println("5.Search after model");
            System.out.println("6.Cars under 100.000km");
            System.out.println("7.Cars under 5 years old");
            System.out.println("0.To exit");
            System.out.print("Pick:");

            try{
                Choice = Integer.parseInt(br.readLine());
            }catch(NumberFormatException nfe){
                System.err.println("Invalid Format!");
            }

            switch(Choice)
            {
                case 0:
                    loop_condition=false;
                    break;
                case 1:
                    System.out.println("Car license plate:");
                    licensePlate = br.readLine();
                    System.out.println("Production year:");
                    productionYear = Integer.parseInt(br.readLine());
                    System.out.println("Color:");
                    color = br.readLine();
                    System.out.println("Km:");
                    kilometers= Integer.parseInt(br.readLine());
                    System.out.println("Model:");
                    model = br.readLine();

                    carDao.insert(licensePlate,productionYear,color,kilometers, model);
                    break;
                case 2:
                    System.out.println("Car license plate:");
                    licensePlate = br.readLine();
                    carDao.delete(licensePlate);

                    break;
                case 3:
                    System.out.println("Car license plate:");
                    licensePlate = br.readLine();
                    Car searchedCar = carDao.getCar(licensePlate);
                    System.out.println(searchedCar.toString());

                    break;
                case 4:
                    cars = carDao.getAllCars();
                    for (Car p : cars)
                    {
                        System.out.println(p);
                    }
                    break;
                case 5:
                    System.out.println("Car model:");
                    model = br.readLine();
                    cars = carDao.getCarsModel(model);
                    for (Car p : cars)
                    {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    cars = carDao.getCarsUnderKm(100000);
                    for (Car p : cars)
                    {
                        System.out.println(p);
                    }
                    break;
                case 7:
                    cars = carDao.getCarsUnder5Years();
                    for (Car p : cars)
                    {
                        System.out.println(p);
                    }
                    break;
                default:  System.err.println("Invalid! Pick again!"); break;
            }

        }

    }


}
