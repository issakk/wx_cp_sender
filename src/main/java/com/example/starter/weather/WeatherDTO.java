package com.example.starter.weather;

import com.example.starter.time.LocalDateTimeExtDeserializer;
import com.example.starter.time.LocalDateTimeExtSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class WeatherDTO {

  @JsonProperty("status")
  private String status;

  @JsonProperty("api_version")
  private String apiVersion;

  @JsonProperty("api_status")
  private String apiStatus;

  @JsonProperty("lang")
  private String lang;

  @JsonProperty("unit")
  private String unit;

  @JsonProperty("tzshift")
  private Integer tzshift;

  @JsonProperty("timezone")
  private String timezone;

  @JsonProperty("server_time")
  private Integer serverTime;

  @JsonProperty("location")
  private List<Double> location;

  @JsonProperty("result")
  private ResultDTO result;

  @NoArgsConstructor
  @Data
  public static class ResultDTO {
    @JsonProperty("daily")
    private DailyDTO daily;

    @JsonProperty("primary")
    private Integer primary;

    @NoArgsConstructor
    @Data
    public static class DailyDTO {
      @JsonProperty("status")
      private String status;

      @JsonProperty("astro")
      private List<AstroDTO> astro;

      @JsonProperty("precipitation")
      private List<PrecipitationDTO> precipitation;

      @JsonProperty("temperature")
      private List<TemperatureDTO> temperature;

      @JsonProperty("wind")
      private List<WindDTO> wind;

      @JsonProperty("humidity")
      private List<HumidityDTO> humidity;

      @JsonProperty("cloudrate")
      private List<CloudrateDTO> cloudrate;

      @JsonProperty("pressure")
      private List<PressureDTO> pressure;

      @JsonProperty("visibility")
      private List<VisibilityDTO> visibility;

      @JsonProperty("dswrf")
      private List<DswrfDTO> dswrf;

      @JsonProperty("air_quality")
      private AirQualityDTO airQuality;

      @JsonProperty("skycon")
      private List<SkyconDTO> skycon;

      @JsonProperty("skycon_08h_20h")
      private List<Skycon08h20hDTO> skycon08h20h;

      @JsonProperty("skycon_20h_32h")
      private List<Skycon20h32hDTO> skycon20h32h;

      @JsonProperty("life_index")
      private LifeIndexDTO lifeIndex;

      @NoArgsConstructor
      @Data
      public static class AirQualityDTO {
        @JsonProperty("aqi")
        private List<AqiDTO> aqi;

        @JsonProperty("pm25")
        private List<Pm25DTO> pm25;

        @NoArgsConstructor
        @Data
        public static class AqiDTO {
          @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

          @JsonProperty("max")
          private MaxDTO max;

          @JsonProperty("avg")
          private AvgDTO avg;

          @JsonProperty("min")
          private MinDTO min;

          @NoArgsConstructor
          @Data
          public static class MaxDTO {
            @JsonProperty("chn")
            private Integer chn;

            @JsonProperty("usa")
            private Integer usa;
          }

          @NoArgsConstructor
          @Data
          public static class AvgDTO {
            @JsonProperty("chn")
            private Integer chn;

            @JsonProperty("usa")
            private Integer usa;
          }

          @NoArgsConstructor
          @Data
          public static class MinDTO {
            @JsonProperty("chn")
            private Integer chn;

            @JsonProperty("usa")
            private Integer usa;
          }
        }

        @NoArgsConstructor
        @Data
        public static class Pm25DTO {
          @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

          @JsonProperty("max")
          private Integer max;

          @JsonProperty("avg")
          private Integer avg;

          @JsonProperty("min")
          private Integer min;
        }
      }

      @NoArgsConstructor
      @Data
      public static class LifeIndexDTO {
        @JsonProperty("ultraviolet")
        private List<UltravioletDTO> ultraviolet;

        @JsonProperty("carWashing")
        private List<CarWashingDTO> carWashing;

        @JsonProperty("dressing")
        private List<DressingDTO> dressing;

        @JsonProperty("comfort")
        private List<ComfortDTO> comfort;

        @JsonProperty("coldRisk")
        private List<ColdRiskDTO> coldRisk;

        @NoArgsConstructor
        @Data
        public static class UltravioletDTO {
          @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

          @JsonProperty("index")
          private String index;

          @JsonProperty("desc")
          private String desc;
        }

        @NoArgsConstructor
        @Data
        public static class CarWashingDTO {
          @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

          @JsonProperty("index")
          private String index;

          @JsonProperty("desc")
          private String desc;
        }

        @NoArgsConstructor
        @Data
        public static class DressingDTO {
          @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

          @JsonProperty("index")
          private String index;

          @JsonProperty("desc")
          private String desc;
        }

        @NoArgsConstructor
        @Data
        public static class ComfortDTO {
          @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

          @JsonProperty("index")
          private String index;

          @JsonProperty("desc")
          private String desc;
        }

        @NoArgsConstructor
        @Data
        public static class ColdRiskDTO {
          @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

          @JsonProperty("index")
          private String index;

          @JsonProperty("desc")
          private String desc;
        }
      }

      @NoArgsConstructor
      @Data
      public static class AstroDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("sunrise")
        private SunriseDTO sunrise;

        @JsonProperty("sunset")
        private SunsetDTO sunset;

        @NoArgsConstructor
        @Data
        public static class SunriseDTO {
          @JsonProperty("time")
          private String time;
        }

        @NoArgsConstructor
        @Data
        public static class SunsetDTO {
          @JsonProperty("time")
          private String time;
        }
      }

      @NoArgsConstructor
      @Data
      public static class PrecipitationDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private Integer max;

        @JsonProperty("min")
        private Integer min;

        @JsonProperty("avg")
        private Integer avg;
      }

      @NoArgsConstructor
      @Data
      public static class TemperatureDTO {
        //2021-12-29T00:00+08:00
        @JsonProperty("date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private Double max;

        @JsonProperty("min")
        private Double min;

        @JsonProperty("avg")
        private Double avg;
      }

      @NoArgsConstructor
      @Data
      public static class WindDTO {
          @JsonProperty("date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private MaxDTO max;

        @JsonProperty("min")
        private MinDTO min;

        @JsonProperty("avg")
        private AvgDTO avg;

        @NoArgsConstructor
        @Data
        public static class MaxDTO {
          @JsonProperty("speed")
          private Double speed;

          @JsonProperty("direction")
          private Double direction;
        }

        @NoArgsConstructor
        @Data
        public static class MinDTO {
          @JsonProperty("speed")
          private Double speed;

          @JsonProperty("direction")
          private Integer direction;
        }

        @NoArgsConstructor
        @Data
        public static class AvgDTO {
          @JsonProperty("speed")
          private Double speed;

          @JsonProperty("direction")
          private Double direction;
        }
      }

      @NoArgsConstructor
      @Data
      public static class HumidityDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private Double max;

        @JsonProperty("min")
        private Double min;

        @JsonProperty("avg")
        private Double avg;
      }

      @NoArgsConstructor
      @Data
      public static class CloudrateDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private Double max;

        @JsonProperty("min")
        private Integer min;

        @JsonProperty("avg")
        private Double avg;
      }

      @NoArgsConstructor
      @Data
      public static class PressureDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private Double max;

        @JsonProperty("min")
        private Double min;

        @JsonProperty("avg")
        private Double avg;
      }

      @NoArgsConstructor
      @Data
      public static class VisibilityDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private Double max;

        @JsonProperty("min")
        private Double min;

        @JsonProperty("avg")
        private Double avg;
      }

      @NoArgsConstructor
      @Data
      public static class DswrfDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("max")
        private Double max;

        @JsonProperty("min")
        private Integer min;

        @JsonProperty("avg")
        private Double avg;
      }

      @NoArgsConstructor
      @Data
      public static class SkyconDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("value")
        private String value;
      }

      @NoArgsConstructor
      @Data
      public static class Skycon08h20hDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("value")
        private String value;
      }

      @NoArgsConstructor
      @Data
      public static class Skycon20h32hDTO {
        @JsonProperty("date")

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'+'08:00")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime date;

        @JsonProperty("value")
        private String value;
      }
    }
  }
}
