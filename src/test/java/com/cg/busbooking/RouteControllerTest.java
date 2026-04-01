package com.cg.busbooking;
import com.cg.busbooking.controller.RouteController;
import com.cg.busbooking.entity.Route;
import com.cg.busbooking.exception.RouteNotFoundException;
import com.cg.busbooking.service.RouteService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import static org.mockito.Mockito.when;

@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    void testGetRouteBetweenCities_Success() throws Exception {
        List<Route> routes = List.of(new Route(), new Route());
        when(routeService.getRouteBetweenCities("Mumbai", "Pune")).thenReturn(routes);
        mockMvc.perform(get("/api/route/search")
                        .param("source", "Mumbai")
                        .param("destination", "Pune"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetRouteBetweenCities_Failure() throws Exception {
        when(routeService.getRouteBetweenCities("A", "B")).thenThrow(new RouteNotFoundException("No routes"));
        mockMvc.perform(get("/api/route/search")
                        .param("source", "A")
                        .param("destination", "B"))
                .andExpect(status().isNotFound());
    }

}
