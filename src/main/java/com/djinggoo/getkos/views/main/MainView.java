package com.djinggoo.getkos.views.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import com.djinggoo.getkos.backend.utils.InitializeFirebaseServer;
import com.google.cloud.firestore.Firestore;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.upload.FinishedEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.djinggoo.getkos.views.cities.CitiesView;
import com.djinggoo.getkos.views.areas.AreasView;
import com.djinggoo.getkos.views.boardingtypes.BoardingTypesView;
import com.vaadin.flow.theme.lumo.Lumo;
import org.apache.coyote.InputBuffer;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport(value = "./styles/views/main/main-view.css", themeFor = "vaadin-app-layout")
@CssImport("./styles/views/main/main-view.css")
@PWA(name = "GetKos UIFirestore", shortName = "GetKos UIFirestore", enableInstallPrompt = false)
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends AppLayout {

    private final Tabs menu;

    public MainView() {
        HorizontalLayout header = createHeader();
        menu = createMenuTabs();
        addToNavbar(createTopBar(header, menu));
    }

    private VerticalLayout createTopBar(HorizontalLayout header, Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.getThemeList().add("dark");
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(header, menu);
        return layout;
    }

    private HorizontalLayout createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setPadding(false);
        header.setSpacing(false);
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setId("header");

        /* logo and name*/
        Image logo = new Image("images/mango.png", "GetKos UIFirestore logo");
        logo.setId("logo");
        header.add(logo);
        header.add(new H1("GetKos UI Firestore"));

        /* upload credentials */
        Button buttonDialog = openDialoUpld();
        buttonDialog.setId("buttondialog");
        header.add(buttonDialog);

//        /* avatar */
//        Image avatar = new Image("images/user.svg", "Avatar");
//        avatar.setId("avatar");
//        header.add(avatar);

        return header;
    }

    private static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.getStyle().set("max-width", "100%");
        tabs.add(getAvailableTabs());
        return tabs;
    }

    private static Tab[] getAvailableTabs() {
        return new Tab[]{createTab("Cities", CitiesView.class), createTab("Areas", AreasView.class),
                createTab("Boarding Types", BoardingTypesView.class)};
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private Button openDialoUpld(){
        Dialog dialog = new Dialog();
        Button buttonOpenDialog = new Button("Upload credentials !");
        Upload upload = uploadCredentials();

        dialog.add(upload);
        buttonOpenDialog.addClickListener(event -> {
            dialog.open();
        });

        return buttonOpenDialog;
    }

    private Upload uploadCredentials(){
//        MemoryBuffer receiverBuffer = new MemoryBuffer();
        FileBuffer receiverBuffer = new FileBuffer();
        Upload upload = new Upload(receiverBuffer);

        upload.addFinishedListener((ComponentEventListener<FinishedEvent>) event -> {
            System.out.println(event.getFileName());
            System.out.println(event.getMIMEType());

            InputStream inputStream = receiverBuffer.getInputStream();

            new InitializeFirebaseServer(inputStream);



//            receiverBuffer.getFileData().getOutputBuffer().
//            receiverBuffer.getFileDescriptor()

//            Scanner myReader = new Scanner(receiverBuffer.getInputStream());
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                System.out.println(data);
//            }

        });

        return upload;
    }
}
