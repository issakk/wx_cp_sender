package com.example.starter.wx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class SendReq {


  @Builder.Default
  private String touser = "@all";

  @Builder.Default
  private String toparty = "@all";

  @Builder.Default
  private String totag = "@all";

  private String msgtype;

  @Builder.Default
  private Long agentid = 1000002L;

  private ContentDTO text;

  private ContentDTO markdown;

  private Integer safe;

  private Integer enableIdTrans;

  private Integer enableDuplicateCheck;

  private Integer duplicateCheckInterval;

  @NoArgsConstructor
  @Data
  public static class ContentDTO {
    private String content;
  }
}
