package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    // Create a Router
    Router router = Router.router(vertx);

    router.route()
      .handler(BodyHandler.create());

    // Mount the handler for all incoming requests at every path and HTTP method
    router.route()
      .handler(context -> {
        // Get the address of the request
        String address = context.request()
          .connection()
          .remoteAddress()
          .toString();
        // Get the query parameter "name"


        // Write a json response
        context.json(
          new JsonObject()
            .put("address", address)
            .put("body", context.getBodyAsJson()
              .encode())
        );
      });

    // Create the HTTP server
    vertx.createHttpServer()
      // Handle every request using the router
      .requestHandler(router)
      // Start listening
      .listen(8888)
      // Print the port
      .onSuccess(server ->
        System.out.println(
          "HTTP server started on port " + server.actualPort()
        )
      );
  }
}
