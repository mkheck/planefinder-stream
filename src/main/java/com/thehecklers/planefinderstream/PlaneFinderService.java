package com.thehecklers.planefinderstream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneFinderService {
    private URL acURL;
    private final ObjectMapper om;
    private final List<Aircraft> acList;

    @SneakyThrows
    public PlaneFinderService() {
        acURL = new URL("http://192.168.1.193/ajax/aircraft");
        om = new ObjectMapper();
        acList = new ArrayList<>();
    }

    public Flux<Aircraft> getAircraft() {
        JsonNode aircraftNodes = null;
        try {
            aircraftNodes = om.readTree(acURL)
                    .get("aircraft");
        } catch (IOException e) {
            e.printStackTrace();
        }

        acList.clear();
        if (null != aircraftNodes) {
            aircraftNodes.iterator()
                    .forEachRemaining(this::saveAircraft);

            return Flux.fromIterable(acList);
        } else {
            //return Flux.just(new Aircraft());
            return Flux.empty();
        }
    }

    private void saveAircraft(JsonNode node) {
        Aircraft ac = null;
        try {
            ac = om.treeToValue(node, Aircraft.class);
            acList.add(ac);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
