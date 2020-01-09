package com.main;

import com.dao.CarDAO;
import com.model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AppUI {

    private JPanel rootPanel;
    private JList carList;
    private JButton addButton;
    private JButton removeButton;
    private JButton searchButton;
    private JTextField searchLicensePlateT;
    private JButton seeAllCarsButton;
    private JButton noOfCarsButton;
    private JTextField brandText;
    private JButton carsUnderMileageButton;
    private JTextField kmTreshTextField;
    private JButton statsForCarsUnderButton;
    private JTextField licensePlateT;
    private JTextField brandT;
    private JTextField colorT;
    private JTextField kmT;
    private JTextField yearT;
    private JTextField searchResult;
    private JLabel noOfCarsL;
    private JLabel statsT;

    private String licensePlate, model, color;
    private int productionYear,kilometers;
    private CarDAO carDao;

    private DefaultListModel<Car> carListModel;

    public AppUI(){
        JFrame jframe = new JFrame("APP2");
        jframe.setContentPane(rootPanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        ApplicationContext context =new ClassPathXmlApplicationContext("Beans_2_3.xml");
        carDao =(CarDAO)context.getBean("opJDBC");

        System.out.println("Start App.");
        jframe.setVisible(true);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Car license plate:");
                licensePlate = licensePlateT.getText();
                System.out.println("Production year:");
                productionYear = Integer.parseInt(yearT.getText());
                System.out.println("Color:");
                color = colorT.getText();
                System.out.println("Km:");
                kilometers= Integer.parseInt(kmT.getText());
                System.out.println("Model:");
                model = brandT.getText();

                carDao.insert(licensePlate,productionYear,color,kilometers, model);
            }
        });


        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Car license plate:");
                Car car = (Car)carList.getSelectedValue();
                carDao.delete(car.getLicensePlate());
            }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Car license plate:");
                licensePlate = searchLicensePlateT.getText();
                Car searchedCar = carDao.getCar(licensePlate);
                System.out.println(searchedCar.toString());
                searchResult.setText(searchedCar.toString());
            }
        });
        seeAllCarsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //carListModel = new DefaultListModel<Car>();
                carListModel = new DefaultListModel<>();
                ListModel l = carList.getModel();
                List<Car> cars = carDao.getAllCars();
                for (Car p : cars)
                {
                    carListModel.addElement(p);
                }

                carList.setModel(carListModel);
            }
        });
        noOfCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Car model:");
                model = brandText.getText();
                List<Car> cars = carDao.getCarsModel(model);
                noOfCarsL.setText(cars.size()+"");
            }
        });
        carsUnderMileageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Car> carsUnder = carDao.getCarsUnderKm(Integer.parseInt(kmTreshTextField.getText()));
                List<Car> cars = carDao.getAllCars();

                int stat = (int)(0.5f + ((100f * carsUnder.size()) / cars.size()));
                statsT.setText(stat+"%");
            }
        });
        statsForCarsUnderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Car> cars = carDao.getCarsUnder5Years();
                carListModel = new DefaultListModel<>();
                for (Car p : cars)
                {
                    carListModel.addElement(p);
                }

                carList.setModel(carListModel);
            }
        });
    }

}
