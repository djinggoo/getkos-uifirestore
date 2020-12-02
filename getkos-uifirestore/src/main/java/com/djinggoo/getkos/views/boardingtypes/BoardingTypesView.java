package com.djinggoo.getkos.views.boardingtypes;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.djinggoo.getkos.views.main.MainView;

@Route(value = "boarding-types", layout = MainView.class)
@PageTitle("Boarding Types")
public class BoardingTypesView extends Div {

    public BoardingTypesView() {
        setId("boarding-types-view");
        add(new Text("Content placeholder"));
    }

}
