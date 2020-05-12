package com.project.myapp.gui;

import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;

public class SignupForm extends Form {
    Form current;

    private Label createSeparator(){
        Label sep = new Label();
        sep.setUIID("Separator");
        //make label visible even without content
        sep.setShowEvenIfBlank(true);
        return sep;
    }


    public SignupForm(Resources theme) {
        current = this;
        setLayout(new BorderLayout());
        Toolbar tb = new Toolbar();
        setToolbar(tb);

        Style iconStyle = getUIManager().getComponentStyle("Title");
        FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,iconStyle,4);
        FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD,iconStyle,4);

        //Inserting the arrow back oprion and the done option in the toolbar

        tb.addCommandToLeftBar("",leftArrow, e -> Log.p("Back Pressed"));
        Command doneCommand = tb.addCommandToRightBar("Done",null, e -> Log.p("Done Pressed"));
        //Setting an UIID for the done option
        tb.findCommandComponent(doneCommand).setUIID("RedCommand");

        // defining the main image of the button or the background image
        Button cameraButton = new Button(theme.getImage("camera.png"));
        // defining that the button is in fact layered and putting the label containing the image on top
        Container cameraLayer = LayeredLayout.encloseIn(
                // defining the image to put on top of where
                // defining an image in a label preserve the size
                new Label(theme.getImage("camera-button.png")), cameraButton
        );

        cameraButton.setUIID("CameraButton");

        Container titleContainer = Container.encloseIn(
                new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER),
                cameraLayer, BorderLayout.CENTER);
        titleContainer.setUIID("TitleContainer");

        TextField firstName = new TextField("", "First Name");
        TextField lastName = new TextField("", "Last Name");
        TextField email = new TextField("", "Email Address", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Choose a Password", 20, TextField.PASSWORD);
        TextField phone = new TextField("", "Phone Number", 20, TextField.PHONENUMBER);
        Label phonePrefix = new Label("+1");
        phonePrefix.setUIID("TextField");

        TableLayout fullNameLayout = new TableLayout(1,3);
        Container fullName = new Container(fullNameLayout);
        fullName.add(fullNameLayout.createConstraint().widthPercentage(49),firstName).
                add(fullNameLayout.createConstraint().widthPercentage(1),createSeparator()).
                add(fullNameLayout.createConstraint().widthPercentage(50),lastName);

        Container fullPhone = TableLayout.encloseIn(3,phonePrefix,createSeparator(),phone);

        Button southButton = new Button("Get started", rightArrow);
        southButton.setTextPosition(Component.LEFT);
        southButton.setUIID("SouthButton");

        Container by = BoxLayout.encloseY(
                fullName,
                createSeparator(),
                email,
                createSeparator(),
                password,
                createSeparator(),
                fullPhone,
                createSeparator()
        );

        by.setScrollableY(true);

        add(BorderLayout.NORTH, titleContainer).
                add(BorderLayout.SOUTH, southButton).
                add(BorderLayout.CENTER, by);
    }
}
