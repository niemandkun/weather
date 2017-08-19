from datetime import datetime


class DoNotUpdateMoreOftenThan:
    def __init__(self, interval):
        self.interval = interval
        self.invalidation_stamp = datetime.utcnow()
        self.cached_value = None

    def __call__(self, fun):
        def cached(*args, **kwargs):
            current_time = datetime.utcnow()
            fresh = current_time < self.invalidation_stamp
            if not fresh:
                self.cached_value = fun(*args, **kwargs)
                self.invalidation_stamp = current_time + self.interval
            return self.cached_value
        return cached
