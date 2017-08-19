from flask import Flask, jsonify
from model import get_report
from sensors import WeatherSensor


app = Flask(__name__)
sensor = WeatherSensor.build()


@app.route('/weather/report', methods=['GET'])
def get_weather_report():
    return jsonify(get_report(sensor))
