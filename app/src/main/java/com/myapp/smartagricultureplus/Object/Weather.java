package com.myapp.smartagricultureplus.Object;

import java.util.List;

public class Weather {

    /**
     * reason : 查询成功!
     * result : {"city":"成都","realtime":{"temperature":"9","humidity":"72","info":"多云","wid":"01","direct":"北风","power":"0级","aqi":"42"},"future":[{"date":"2020-11-25","temperature":"7/11℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2020-11-26","temperature":"7/12℃","weather":"多云转阴","wid":{"day":"01","night":"02"},"direct":"持续无风向"},{"date":"2020-11-27","temperature":"7/12℃","weather":"阴","wid":{"day":"02","night":"02"},"direct":"持续无风向"},{"date":"2020-11-28","temperature":"7/12℃","weather":"阴","wid":{"day":"02","night":"02"},"direct":"持续无风向"},{"date":"2020-11-29","temperature":"7/11℃","weather":"阴转小雨","wid":{"day":"02","night":"07"},"direct":"持续无风向"}]}
     * error_code : 0
     */

    private String reason;
    private ResultDTO result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public class ResultDTO {
        /**
         * city : 成都
         * realtime : {"temperature":"9","humidity":"72","info":"多云","wid":"01","direct":"北风","power":"0级","aqi":"42"}
         * future : [{"date":"2020-11-25","temperature":"7/11℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2020-11-26","temperature":"7/12℃","weather":"多云转阴","wid":{"day":"01","night":"02"},"direct":"持续无风向"},{"date":"2020-11-27","temperature":"7/12℃","weather":"阴","wid":{"day":"02","night":"02"},"direct":"持续无风向"},{"date":"2020-11-28","temperature":"7/12℃","weather":"阴","wid":{"day":"02","night":"02"},"direct":"持续无风向"},{"date":"2020-11-29","temperature":"7/11℃","weather":"阴转小雨","wid":{"day":"02","night":"07"},"direct":"持续无风向"}]
         */

        private String city;
        private RealtimeDTO realtime;
        private List<FutureDTO> future;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public RealtimeDTO getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeDTO realtime) {
            this.realtime = realtime;
        }

        public List<FutureDTO> getFuture() {
            return future;
        }

        public void setFuture(List<FutureDTO> future) {
            this.future = future;
        }

        public class RealtimeDTO {
            /**
             * temperature : 9
             * humidity : 72
             * info : 多云
             * wid : 01
             * direct : 北风
             * power : 0级
             * aqi : 42
             */

            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getWid() {
                return wid;
            }

            public void setWid(String wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }
        }

        public class FutureDTO {
            /**
             * date : 2020-11-25
             * temperature : 7/11℃
             * weather : 多云
             * wid : {"day":"01","night":"01"}
             * direct : 持续无风向
             */

            private String date;
            private String temperature;
            private String weather;
            private WidDTO wid;
            private String direct;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WidDTO getWid() {
                return wid;
            }

            public void setWid(WidDTO wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public class WidDTO {
                /**
                 * day : 01
                 * night : 01
                 */

                private String day;
                private String night;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getNight() {
                    return night;
                }

                public void setNight(String night) {
                    this.night = night;
                }
            }
        }
    }
}
