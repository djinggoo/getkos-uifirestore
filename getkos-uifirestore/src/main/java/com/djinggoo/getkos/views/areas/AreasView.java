package com.djinggoo.getkos.views.areas;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.djinggoo.getkos.views.main.MainView;

@Route(value = "areas", layout = MainView.class)
@PageTitle("Areas")
public class AreasView extends Div {

    public AreasView() {
        setId("areas-view");
        add(new Text("Content placeholder"));
    }

}
