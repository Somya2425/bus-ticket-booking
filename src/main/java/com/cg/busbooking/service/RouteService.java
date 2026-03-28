package com.cg.busbooking.service;
import com.cg.busbooking.entity.Route;

import java.util.List;
public interface RouteService {
    List<Route> getRouteBetweenCities(String source, String destination);
}
