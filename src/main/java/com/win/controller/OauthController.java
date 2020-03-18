package com.win.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/oauth")
public class OauthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthController.class);

    @GetMapping("/login")
    public ResponseEntity<JsonNode> retrieveToken(@RequestParam(value = "code") final String pAuthCode) {
        ObjectMapper lObjectMapper = new ObjectMapper();
        JsonNode lResponseJSON = null;

        final String lCredentials = "first-client:first_password";
        String lEncodedCredentials = new String(Base64.encodeBase64(lCredentials.getBytes()));

        RestTemplate lRestTemplate = new RestTemplate();

        String lAccessTokenUrl = "http://localhost:8081/oauth/token";
        lAccessTokenUrl += "?code=" + pAuthCode;
        lAccessTokenUrl += "&grant_type=authorization_code";

        HttpHeaders lHeaders = new HttpHeaders();
        lHeaders.add("Authorization", "Basic " + lEncodedCredentials);

        HttpEntity<String> lRequestEntity = new HttpEntity<String>(lHeaders);

        final ResponseEntity<String> lResponseEntity = lRestTemplate.exchange(lAccessTokenUrl, HttpMethod.POST,
                lRequestEntity, String.class);

        LOGGER.info("Status Code " + lResponseEntity.getStatusCode());
        LOGGER.info("Body " + lResponseEntity.getBody());
       
        try {
            lResponseJSON = lObjectMapper.readTree(lResponseEntity.getBody());
        } catch (JsonProcessingException lJsonProcessingException) {
            LOGGER.error("JsonProcessingException ", lJsonProcessingException);
        }
        return new ResponseEntity<JsonNode>(lResponseJSON, HttpStatus.ACCEPTED);
    }

}