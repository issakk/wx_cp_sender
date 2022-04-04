package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class Client extends AbstractVerticle {

  //https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
  private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

  private static final String SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

  private static final String CORP_ID = "wwde9a3771302d7261";

  private static final String CORP_SECRET = "ykgNLkvG3IOE0NoseHuxt6m8RLQFoqTdppVpSoUvpEU";

  private static final Long AGENT_ID = 1000002L;


  @Override
  public void start() throws Exception {
    WebClient client = WebClient.create(vertx);


    client.postAbs(ACCESS_TOKEN_URL)
      .addQueryParam("corpid", CORP_ID)
      .addQueryParam("corpsecret", CORP_SECRET)
      .send(ar -> {
        if (ar.succeeded()) {
          HttpResponse<Buffer> response = ar.result();
          System.out.println("Got HTTP response with status " + response.statusCode());
          System.out.println("response.bodyAsString " + response.bodyAsString());
        } else {
          ar.cause()
            .printStackTrace();
        }
      });
  }
}
