package com.example.starter;

import com.example.starter.time.CronTrigger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client extends AbstractVerticle {

  //https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
  private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

  private static final String SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

  private static final String CORP_ID = "wwde9a3771302d7261";

  private static final String CORP_SECRET = "ykgNLkvG3IOE0NoseHuxt6m8RLQFoqTdppVpSoUvpEU";

  private static final Long AGENT_ID = 1000002L;

  private String accessToken;


  @Override
  public void start() throws Exception {
    WebClient client = WebClient.create(vertx);
    CronTrigger cronTrigger = new CronTrigger(vertx, "0 0/1 * * * ?");
    HttpRequest<Buffer> httpRequest = client.postAbs(ACCESS_TOKEN_URL)
      .addQueryParam("corpid", CORP_ID)
      .addQueryParam("corpsecret", CORP_SECRET);
    // cronTrigger.schedule(h -> {
    //   request(httpRequest);
    // });
    requestAccessToken(httpRequest);

  }

  private void requestAccessToken(HttpRequest<Buffer> httpRequest) {
    httpRequest
      .send(ar -> {
        if (ar.succeeded()) {
          HttpResponse<Buffer> response = ar.result();
          log.info("Got HTTP response with status " + response.statusCode());
          log.info("response.bodyAsString " + response.bodyAsString());
          accessToken = response.bodyAsJsonObject()
            .getString("access_token");
        } else {
          ar.cause()
            .printStackTrace();
        }
      });
  }
}
