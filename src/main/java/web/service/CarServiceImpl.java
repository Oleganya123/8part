package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final List<Car> cars;

    public CarServiceImpl() {
        cars = new ArrayList<>();
        cars.add(new Car("NIVA", 2025, "Red"));
        cars.add(new Car("BMW", 2017, "Blue"));
        cars.add(new Car("Audi", 2021, "Black"));
        cars.add(new Car("LADA", 2014, "White"));
        cars.add(new Car("Honda", 2022, "Silver"));
    }

    @Override
    public List<Car> getCars(int count) {
        return cars.subList(0, Math.min(count, cars.size()));
    }
}

