import datetime

from utils import DoNotUpdateMoreOftenThan


REPORT_UPDATE_INTERVAL = datetime.timedelta(seconds=5)


@DoNotUpdateMoreOftenThan(REPORT_UPDATE_INTERVAL)
def get_report(sensors):
    return {
        'temperature': sensors.get_temperature(),
        'humidity':sensors.get_humidity(),
    }
