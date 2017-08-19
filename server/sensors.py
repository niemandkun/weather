from os import path


SYS_SENSOR_PATH = '/sys/bus/iio/devices/iio:device0/'

HUMIDITY = path.join(SYS_SENSOR_PATH, 'in_humidityrelative_input')
TEMPERATURE = path.join(SYS_SENSOR_PATH, 'in_temp_input')


class Sensor:
    def __init__(self, sensor_device):
        self.__sensor_dev = sensor_device

    def measure(self):
        return self.__sensor_dev.read.strip()

    def dispose(self):
        self.__sensor_dev.close()

    @staticmethod
    def of(sensor_name):
        return Sensor(open(sensor_name))


class MockSensor:
    def __init__(self):
        self.is_disposed = False

    def measure(self):
        if self.is_disposed:
            raise Exception()
        return 42

    def dispose(self):
        self.is_disposed = True


class WeatherSensor:
    def __init__(self, temp, humidity):
        self.__temp = temp
        self.__humidity = humidity

    def get_temperature(self):
        return self.__temp.measure()

    def get_humidity(self):
        return self.__humidity.measure()

    def dispose(self):
        self.__temp.dispose()
        self.__humidity.dispose()

    @staticmethod
    def mock():
        return WeatherSensor(MockSensor(), MockSensor())

    @staticmethod
    def build():
        return WeatherSensor(Sensor.of(TEMPERATURE), Sensor.of(HUMIDITY))
