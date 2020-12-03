package com.djinggoo.getkos.views.cities;

import com.djinggoo.getkos.backend.entity.City;
import com.djinggoo.getkos.backend.services.CitiesService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.djinggoo.getkos.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;

@Route(value = "cities", layout = MainView.class)
@PageTitle("Cities")
@RouteAlias(value = "", layout = MainView.class)
public class CitiesView extends Div {

    @Autowired
    CitiesService citiesService;

    Grid<City> gridCities = new Grid<>(City.class);
    
    public CitiesView() {
        setId("cities-view");

        /* content */
        configureGrid();

        Div divContent = new Div();
        divContent.add(gridCities);

        add(divContent);
    }

    private void configureGrid(){
        gridCities.setColumns("name", "value");
        gridCities.setItems(simpleData());
    }

    private ArrayList<City> simpleData(){
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Malang", 1));
        cities.add(new City("Yogya", 3));
        cities.add(new City("SBY", 2));

//        return citiesService.getCities();
        return cities;
    }

}
