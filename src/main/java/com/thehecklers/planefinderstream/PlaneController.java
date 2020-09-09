package com.thehecklers.planefinderstream;

import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Controller
public class PlaneController {
    private final PlaneFinderService pfService;

    public PlaneController(PlaneFinderService pfService) {
        this.pfService = pfService;
    }

    @ResponseBody
    @GetMapping("/aircraftlist")
    public List<Aircraft> getCurrentAircraftList() {
        return pfService.getAircraftList();
    }

    @ResponseBody
    @GetMapping("/aircraft")
    public Flux<Aircraft> getCurrentAircraftFlux() throws IOException {
        return pfService.getAircraftFlux().log();
    }

    @ResponseBody
    @GetMapping(value = "/aircraftstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Aircraft> getCurrentAircraftStream() throws IOException {
        return pfService.getAircraftFlux().concatWith(
                Flux.interval(Duration.ofSeconds(1))
                        .flatMap(l -> pfService.getAircraftFlux()));
    }

    @MessageMapping("pingpong")
    public Mono<Pong> pingPong(Ping ping) {
        System.out.println("    >>> Incoming.");
        return Mono.just(new Pong(ping.getRequest() + " pong!"));
    }

    @MessageMapping("acstream")
    public Flux<Aircraft> getCurrentACStream() throws IOException {
        return pfService.getAircraftFlux().concatWith(
                Flux.interval(Duration.ofSeconds(1))
                        .flatMap(l -> pfService.getAircraftFlux()));
    }
}

