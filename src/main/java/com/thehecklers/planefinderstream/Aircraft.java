package com.thehecklers.planefinderstream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft {
    private Long id;
    private String callsign, squawk, reg, flightno, route, type, category;

    private int altitude, heading, speed;
    @JsonProperty("vert_rate")
    private int vertRate;
    @JsonProperty("selected_altitude")
    private int selectedAltitude;

    private double lat, lon, barometer;
    @JsonProperty("polar_distance")
    private double polarDistance;
    @JsonProperty("polar_bearing")
    private double polarBearing;

    @JsonProperty("is_adsb")
    private boolean isADSB;
    @JsonProperty("is_on_ground")
    private boolean isOnGround;

    @JsonProperty("last_seen_time")
    private Instant lastSeenTime;
    @JsonProperty("pos_update_time")
    private Instant posUpdateTime;
    @JsonProperty("bds40_seen_time")
    private Instant bds40SeenTime;

    public void setLastSeenTime(long lastSeenTime) {
        this.lastSeenTime = Instant.ofEpochSecond(lastSeenTime);
    }

    public void setPosUpdateTime(long posUpdateTime) {
        this.posUpdateTime = Instant.ofEpochSecond(posUpdateTime);
    }

    public void setBds40SeenTime(long bds40SeenTime) {
        this.bds40SeenTime = Instant.ofEpochSecond(bds40SeenTime);
    }

    // For generation of demo data when no aircraft are within range
    public Aircraft(String callsign, String reg, String flightno, String type,
                    int altitude, int heading, int speed,
                    double lat, double lon) {
        this.callsign = callsign;
        this.reg = reg;
        this.flightno = flightno;
        this.type = type;
        this.altitude = altitude;
        this.heading = heading;
        this.speed = speed;
        this.lat = lat;
        this.lon = lon;
    }
}
