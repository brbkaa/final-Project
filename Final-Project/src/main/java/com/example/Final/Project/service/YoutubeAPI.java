package com.example.Final.Project.service;

import com.example.Final.Project.model.Statistics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
public class YoutubeAPI {
    @Value("${templates.accessible.services.url}")
    private String val;
    private final WebClient webClient = WebClient.create();
    public String viewsCount(String videoId){
        HashMap<String, String> map = new HashMap<>();
        map.put("id", videoId);
        Statistics stats = webClient.get()
                .uri(val, map)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Statistics.class)
                .block();
        System.out.println(stats.viewCount);
        return stats.viewCount;
    }
}
