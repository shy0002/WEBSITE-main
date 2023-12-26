package com.ayang.website.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shy
 * @date 2023/12/25
 * @description IpDetail
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpDetail implements Serializable {
    private String ip;
    private String isp;
    private String isp_id;
    private String city;
    private String city_id;
    private String county;
    private String county_id;
    private String region;
    private String region_id;

}
