package com.example.nobsv2.catfact;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.nobsv2.Query;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {

    private final RestTemplate restTemplate;
    private final String url = "https://catfact.ninja/fact";
    private final String MAX_LENGTH = "max_length";

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("null")
    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
        // Sets up URL with query string parameters
        URI uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam(MAX_LENGTH, input)
            .build()
            .toUri();
        
        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CatFactResponse> response = restTemplate
                    .exchange(uri, HttpMethod.GET, entity, CatFactResponse.class);

        CatFactDTO factDTO = new CatFactDTO(response.getBody().getFact());
        return ResponseEntity.ok(factDTO);
    }
    
}
