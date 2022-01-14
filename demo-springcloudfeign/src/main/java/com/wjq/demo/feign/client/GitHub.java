package com.wjq.demo.feign.client;

import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * @author wjq
 * @since 2022-01-13
 */
public interface GitHub {

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repository);

    class Contributor {
        String login;
        int contributions;
    }
}
