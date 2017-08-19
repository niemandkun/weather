from os import path


SYS_SENSOR_PATH = '/sys/bus/iio/devices/iio:device0/'

HUMIDITY = path.join(SYS_SENSOR_PATH, 'in_humidityrelative_input')
TEMPERATURE = path.join(SYS_SENSOR_PATH, 'in_temp_input')


class Sensor:
    def __init__(self, sensor_device):
        self.__sensor_dev = sensor_device

    def measure(self):
        with open(self.__sensor_dev) as dev:
            return dev.read().strip()


class MockSensor:
    def measure(self):
        return 42


class WeatherSensor:
    def __init__(self, temp, humidity):
        self.__temp = temp
        self.__humidity = humidity

    def get_temperature(self):
        return self.__temp.measure()

    def get_humidity(self):
        return self.__humidity.measure()

    @staticmethod
    def mock():
        return WeatherSensor(MockSensor(), MockSensor())

    @staticmethod
    def build():
        return WeatherSensor(Sensor(TEMPERATURE), Sensor(HUMIDITY))
