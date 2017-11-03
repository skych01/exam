package code.test5.subject;

import code.test5.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * WeatherData知道如何跟气象站联系，以获得天气数据。
 * 当天气数据有更新时，WeatherData会更新两个公告牌用于展示新的天气数据。
 */
public class WeatherData implements Subject {

    private List<Observer> observers=new ArrayList<Observer>();

    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压
    private List<Float> forecastTemperatures;//未来几天的温度


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notiyf() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    public void measurementsChanged() {
        notiyf();
    }
    public void setMeasurements(float temperature, float humidity,
                                float pressure, List<Float> forecastTemperatures) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.forecastTemperatures = forecastTemperatures;
        measurementsChanged();
    }
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public List<Float> getForecastTemperatures() {
        return forecastTemperatures;
    }

}
