package com.example.starter;

import com.example.starter.time.CronTrigger;
import com.example.starter.weather.WeatherDTO;
import com.example.starter.wx.SendReq;
import com.example.starter.wx.SendReq.ContentDTO;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class Client extends AbstractVerticle {


  private static Map<Integer, String> dressMap;

  static {
    dressMap = new HashMap<>();
    dressMap.put(1, "夏季着装：轻棉织物制作的短衣、短裙、薄短裙、短裤");
    dressMap.put(2, "夏季着装：棉麻面料的衬衫、薄长裙、薄T恤");
    dressMap.put(3, "春秋过渡装：单层棉麻面料的短套装、T恤衫、薄牛仔衫裤、休闲服、职业套装");
    dressMap.put(4, "春秋过渡装：套装、夹衣、风衣、休闲装、夹克衫、西装、薄毛衣");
    dressMap.put(5, "春秋着装：风衣、大衣、夹大衣、外套、毛衣、毛套装、西装、防寒服");
    dressMap.put(6, "秋冬着装：毛衣、风衣、毛套装、西服套装");
    dressMap.put(7, "冬季着装：棉衣、冬大衣、皮夹克、厚呢外套、呢帽、手套、羽绒服、皮袄");
    dressMap.put(8, "冬季着装：棉衣、冬大衣、皮夹克、厚呢外套、呢帽、手套、羽绒服、皮袄");
  }

  //https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
  private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

  private static final String SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

  //curl "https://api.caiyunapp.com/v2.5/TAkhjf8d1nlSlspN/101.6656,39.2072/daily?dailysteps=1"
  private static final String WEATHER_URL = "https://api.caiyunapp.com/v2.5/DBwo5ikwRApOchc0/113.2498,23.0955/daily";

  private static final String CORP_ID = "wwde9a3771302d7261";

  private static final String CORP_SECRET = "ykgNLkvG3IOE0NoseHuxt6m8RLQFoqTdppVpSoUvpEU";

  private static final Long AGENT_ID = 1000002L;


  private String accessToken = "1";

  private WebClient client;

  private MySQLPool mySQLPool;


  @Override
  public void stop() throws Exception {
    log.info("关闭数据库连接");
    mySQLPool.close();
    super.stop();
  }

  @Override
  public void start() throws Exception {
    mySQLPool = getMySQLPool();

    client = WebClient.create(vertx);
    String cronExpression = "1 1 8,21 * * ? ";
    log.info("程序启动,cron表达式:{}",cronExpression);
    CronTrigger cronTrigger = new CronTrigger(vertx, cronExpression);

    cronTrigger.schedule(h -> {
      requestWeather();
    });


  }

  private MySQLPool getMySQLPool() {
    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
      .setPort(3306)
      .setHost("chillfun.top")
      .setDatabase("lucky_weather")
      .setUser("admin")
      .setPassword("Iss@k0217");

    // Pool options
    PoolOptions poolOptions = new PoolOptions()
      .setMaxSize(5);

    // Create the client pool
    MySQLPool mySQLPool = MySQLPool.pool(vertx, connectOptions, poolOptions);
    return mySQLPool;
  }

  private void requestSend(SendReq seq, String accessToken) {
    client.postAbs(SEND_URL)
      .addQueryParam("access_token", accessToken)
      .sendJson(seq, ar -> {
        if (ar.succeeded()) {
          HttpResponse<Buffer> response = ar.result();
          log.info("Got HTTP response with status " + response.statusCode());
          log.info("response.bodyAsString " + response.bodyAsString());
        } else {
          ar.cause()
            .printStackTrace();
        }
      });
  }

  private void requestWeather() {
    String pattern = "{0},最高气温**{1}**,最低气温**{2}**,平均气温**{3}**";
    if (LocalTime.now()
      .compareTo(LocalTime.of(18, 0)) > 0)
    {
      pattern = "<font color=\\\"warning\\\">今天</font> " + pattern;
    } else {
      pattern = "<font color=\\\"info\\\">明天</font> " + pattern;
    }
    String finalPattern = pattern;
    client.getAbs(WEATHER_URL)
      .addQueryParam("dailysteps", "2")
      .as(BodyCodec.json(WeatherDTO.class))
      .send(ar -> {
        if (ar.succeeded()) {
          StringBuilder sb = new StringBuilder();
          HttpResponse<WeatherDTO> response = ar.result();
          WeatherDTO weatherDTO = response.body();
          log.info("Got HTTP response with status " + response.statusCode());
          log.info("response.bodyAsString " + Json.encode(weatherDTO));
          weatherDTO.getResult()
            .getDaily()
            .getTemperature()
            .stream()
            .filter(t -> filterDate(t.getDate()))
            .forEach(t -> {
              Double max = t.getMax();
              Double min = t.getMin();
              Double avg = t.getAvg();

              sb.append(MessageFormat.format(finalPattern, t.getDate()
                .toLocalDate(), max, min, avg));
              sb.append("\n");
            });
          weatherDTO.getResult()
            .getDaily()
            .getLifeIndex()
            .getDressing()
            .stream()
            .filter(d -> filterDate(d.getDate()))
            .forEach(d -> {
              String desc = d.getDesc();
              String s = dressMap.get(Integer.parseInt(d.getIndex()));
              sb.append(MessageFormat.format("穿衣指数为{0},可以穿{1}", desc, s));

            });

          ContentDTO text = new ContentDTO();
          text.setContent(sb.toString());
          SendReq seq = SendReq.builder()
            .msgtype("markdown")
            .markdown(text)
            .safe(0)
            .enableIdTrans(0)
            .enableDuplicateCheck(0)
            .duplicateCheckInterval(3000)
            .build();
          send(seq);

        } else {
          ar.cause()
            .printStackTrace();
        }
      });
  }

  private Boolean filterDate(LocalDateTime dateTime) {
    LocalDate date = LocalDate.now();
    if (LocalTime.now()
      .compareTo(LocalTime.of(18, 0)) > 0)
    {
      date = date.plusDays(1);
    }
    return dateTime.toLocalDate()
      .compareTo(date) == 0;
  }


  private String requestAccessToken(SendReq req) {
    AtomicReference<String> t = new AtomicReference<>("");
    client.postAbs(ACCESS_TOKEN_URL)
      .addQueryParam("corpid", CORP_ID)
      .addQueryParam("corpsecret", CORP_SECRET)
      .send(ar -> {
        if (ar.succeeded()) {
          HttpResponse<Buffer> response = ar.result();
          log.info("Got HTTP response with status " + response.statusCode());
          log.info("response.bodyAsString " + response.bodyAsString());
          String token = response.bodyAsJsonObject()
            .getString("access_token");
          t.set(token);
          cacheAccessToken(t.get());
          requestSend(req, token);
        } else {
          ar.cause()
            .printStackTrace();
        }
      });
    return t.get();
  }

  private void cacheAccessToken(String s) {
    mySQLPool
      .preparedQuery("INSERT INTO wx_access_token (token, create_time,expires_in) VALUES (?, ?,?)")
      .execute(Tuple.of(s, LocalDateTime.now(), 7200),
        sqlAr -> {
          if (sqlAr.succeeded()) {
            log.info("插入成功 accessToken为：" + s);
            RowSet<Row> rows = sqlAr.result();
          } else {
            System.out.println("Failure: " + sqlAr.cause()
              .getMessage());
          }
        });
  }


  private String send(SendReq req) {


    AtomicReference<String> t = new AtomicReference<>("");
    mySQLPool
      .query("SELECT * FROM wx_access_token ORDER BY id DESC  LIMIT 1")
      .execute(ar -> {
        if (ar.succeeded()) {
          RowSet<Row> result = ar.result();
          log.info("result:{}", result.toString());
          result.forEach(r -> {
            String token = r.getString("token");
            LocalDateTime createTime = r.getLocalDateTime("create_time");
            Integer expiresIn = r.getInteger("expires_in");
            log.info("最新的accessToken为：" + token);
            log.info("创建时间为：" + createTime);
            log.info("过期时间为：" + expiresIn);
            if (createTime.plusSeconds(7200L)
              .compareTo(LocalDateTime.now()) > 0)
            {
              log.info("accessToken未过期，直接返回");
              requestSend(req, token);
            } else {
              log.info("accessToken已过期，重新获取");
              requestAccessToken(req);
            }
          });
        } else {
          System.out.println("Failure: " + ar.cause()
            .getMessage());
        }
        // Now close the pool
      });

    return t.get();
  }


}
