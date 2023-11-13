package com.user_match.usermatch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AccessController {
    
    @Value("${instagram.clientId}")
    private String client_id;

    @Value("${instagram.clientSecret}")
    private String client_secret;

    @PostMapping("insta_access_token")
    @ResponseBody
    @CrossOrigin(origins = "https://127.0.0.1:5173/")
    public ResponseEntity<Map<String, String>> getAccessToken(@RequestBody Map<String, String> request){
        try{
            String code = request.get("code");

            // ---------- Short Live Token--------------
            String url = "https://api.instagram.com/oauth/access_token";
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("client_id", client_id);
            requestBody.add("client_secret", client_secret);
            requestBody.add("grant_type","authorization_code");
            requestBody.add("redirect_uri", "https://127.0.0.1:5173/");
            requestBody.add("code", code);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate template = new RestTemplate();
            ResponseEntity<Map> response = template.postForEntity(url, requestEntity, Map.class);
            
            // ------ Long Live Token -----------
            String longLiveUrl = "https://graph.instagram.com/access_token";
            String accessToken = response.getBody().get("access_token").toString();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(longLiveUrl)
                    .queryParam("grant_type", "ig_exchange_token")
                    .queryParam("client_secret", client_secret)
                    .queryParam("access_token", accessToken);

            RestTemplate longLiveTemplate = new RestTemplate();
            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> longLiveRequestEntity = new HttpEntity<>(headers1);

            ResponseEntity<Map> longLiveTokenResponse = longLiveTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    longLiveRequestEntity,
                    Map.class
            );
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("access_token", longLiveTokenResponse.getBody().get("access_token").toString());
            responseBody.put("user_id", response.getBody().get("user_id").toString());
            responseBody.put("token_type", longLiveTokenResponse.getBody().get("token_type").toString());
            responseBody.put("expires_in", longLiveTokenResponse.getBody().get("expires_in").toString());
            return ResponseEntity.status(response.getStatusCode()).body(responseBody);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("refresh_token")
    @ResponseBody
    @CrossOrigin(origins = "https://127.0.0.1:5173/")
    public ResponseEntity<Map<String, String>> getrefreshToken(@RequestBody Map<String, String> request){
        try{
            String url = "https://graph.instagram.com/refresh_access_token";
            String access_token = request.get("access_token");

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("grant_type", "ig_refresh_token").queryParam("access_token", access_token);

            HttpHeaders headers1 = new HttpHeaders();
            headers1.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> RequestEntity = new HttpEntity<>(headers1);

            RestTemplate template = new RestTemplate();
            ResponseEntity<Map> response = template.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                RequestEntity,
                Map.class
            );

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("access_token", response.getBody().get("access_token").toString());
            responseBody.put("token_type", response.getBody().get("token_type").toString());
            responseBody.put("expires_in", response.getBody().get("expires_in").toString());
            return ResponseEntity.status(response.getStatusCode()).body(responseBody);
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(400).body(null);
        }
        

    }
}
