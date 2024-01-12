///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//import com.codename1.components.FloatingActionButton;
//import com.codename1.components.SpanLabel;
//import com.codename1.components.ToastBar;
//import com.codename1.l10n.ParseException;
//import com.codename1.l10n.SimpleDateFormat;
//import com.codename1.ui.Button;
//import com.codename1.ui.Command;
//import com.codename1.ui.Component;
//import static com.codename1.ui.Component.LEFT;
//import com.codename1.ui.Container;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.Display;
//import com.codename1.ui.EncodedImage;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Image;
//import com.codename1.ui.Label;
//import com.codename1.ui.TextArea;
//import com.codename1.ui.TextField;
//import com.codename1.ui.URLImage;
//import com.codename1.ui.events.ActionEvent;
//import static com.codename1.ui.events.ActionEvent.Type.Command;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.geom.Dimension;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.layouts.FlowLayout;
//import com.codename1.ui.plaf.Style;
//import com.codename1.ui.spinner.Picker;
//import com.codename1.ui.util.Resources;
//import com.mycompany.myapp.entities.Abonnement;
//import com.mycompany.myapp.entities.Offre;
//import com.mycompany.myapp.services.ServiceMateriel;
//import com.mycompany.myapp.services.ServiceFacture;
//import com.mycompany.myapp.utils.Statics;
//import java.time.LocalDate;
//import com.codename1.ui.plaf.StyleParser.StyleInfo;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//import javafx.scene.control.DatePicker;
//import javafx.scene.image.ImageView;
//
///**
// *
// * @author User
// */
//public class ListOffreFrontForm extends BaseFrontForm {
//
//    private Object previous;
//    private int bgColor = 0x22252a;
//    private int titleColor = 0xd46973;
//    private int separatorColor = 0xd6d6d6;
//    private final String imageBaseUrl = Statics.BASE_URL + "uploads/";
//    ServiceFacture es;
//    Resources res;
//
//    public ListOffreFrontForm(Resources resource) throws ParseException {
//        super.addSideMenu(resource);
//        getAllStyles().setBgColor(bgColor);
//        setTitle("Liste des offres");
//        Container uiList = new Container(BoxLayout.y());
//        uiList.setScrollableY(true);
//        this.res = resource;
//
//        es = new ServiceFacture();
//        showList();
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.addActionListener(e -> {
//           
//                new MapForm(res); // MAP Form
//        
//        });
//        fab.bindFabToContainer(getContentPane());
//
//    }
//
//    private void showList() throws ParseException {
//
//        ArrayList<Offre> list = es.getAllOffre();
//        Container listContainer = new Container(BoxLayout.y());
//
//        for (Offre r : list) {
//
//            Container cellContainer = new Container(BoxLayout.x());
//            Container infoContainer = new Container(BoxLayout.y());
//
//            //  String imageUrl = imageBaseUrl + r.getPhoto();
//            
//            StyleInfo si = new StyleInfo ();
//            SpanLabel nomLabel = new SpanLabel("Nom de l'offre :" + r.getNom());
//            SpanLabel nomAbLabel = new SpanLabel("Nom de l'abonnement :" + r.getAbonnement().getNom());
//            SpanLabel descLabel = new SpanLabel("Description :" + r.getCategorie());
//            SpanLabel prixLabel = new SpanLabel("Délai :" + (r.getDelai()).substring(6, 25));
//            //SpanLabel photoLabel = new SpanLabel("id abonnement :" + r.getNom_abonnement());
//
//            Image placeholder = Image.createImage(350, 350, 0xbfc9d2);
//            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
//            //URLImage background = URLImage.createToStorage(encImage, r.getNom(), imageUrl);
//
//            //  cellContainer.add(background);
//            infoContainer.add(nomLabel);
//            infoContainer.add(nomAbLabel);
//            infoContainer.add(descLabel);
//            // infoContainer.add(categoryLabel);
//            infoContainer.add(prixLabel);
//            cellContainer.add(infoContainer);
//
//            Button deleteButton = new Button("Delete");
//            deleteButton.getAllStyles().setBgColor(0xF36B08);
//            deleteButton.setSize(new Dimension(100, 200));
//            deleteButton.setVisible(false);
//            deleteButton.addActionListener(e -> {
//                Dialog alert = new Dialog("Attention");
//                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette abonnement?\nCette action est irréversible!");
//                alert.add(message);
//                Button ok = new Button("Confirmer");
//                Button cancel = new Button(new Command("Annuler"));
//                ok.addActionListener(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent evt) {
//                        es.deleteOffre(r.getId());
//
//                        alert.dispose();
//                        removeAll();
//                        try {
//                            showList();
//                        } catch (ParseException ex) {
//                            Logger.getLogger(ListOffreFrontForm.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        getContentPane().animateLayout(150);
//                    }
//
//                }
//                );
//                alert.add(cancel);
//                alert.add(ok);
//                alert.showDialog();
//            });
//
//            Container buttonContainer = new Container(BoxLayout.x());
//            Button updateButton = new Button("modifier");
//            updateButton.setUIID("Link");
//            updateButton.setVisible(false);
//            updateButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    System.out.println("CLICKED!!");
//                    showAddDialog(r, true);
//                }
//            });
//            buttonContainer.add(deleteButton).add(updateButton);
//            Container centered = FlowLayout.encloseCenterMiddle(buttonContainer);
//            infoContainer.add(centered);
//            System.out.println("");
//            listContainer.add(cellContainer);
//            Container separator = new Container();
//            separator.setHeight(2);
//            separator.getAllStyles().setBgColor(separatorColor);
//            listContainer.add(separator);
//
//        }
//        add(listContainer);
//    }
//
//    /**
//     * This method will take as parameter the "abonnement" entity and
//     * actionUpdate Boolean if the actionUpdate is true, the dialog fields will
//     * be filled with the entity info, else the fields will be empty
//     *
//     * @param offre will be used in case of update
//     * @param actionUpdate will be used to differentiate between add/update
//     * actions
//     */
//    private void showAddDialog(Offre offre, Boolean actionUpdate) {
//        Dialog d = new Dialog("Ajouter un offre");
//        int fieldsWidth = 300;
//        Container fieldsContainer = new Container(BoxLayout.y());
//        fieldsContainer.setScrollableY(true);
//        TextField nom = new TextField("", "Nom...", 20, TextArea.ANY);
//        nom.getAllStyles().setFgColor(bgColor);
//        nom.setAlignment(LEFT);
//        TextField description = new TextField("", "Description...", 20, TextArea.NUMERIC);
//        description.getAllStyles().setFgColor(bgColor);
//        description.setAlignment(LEFT);
//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);
//
//        TextField category = new TextField("", "nom...", 20, TextArea.NUMERIC);
//        category.getAllStyles().setFgColor(bgColor);
//        category.setAlignment(LEFT);
//        String buttonText = actionUpdate ? "Modifier" : "Ajouter";
//        Button button = new Button(buttonText);
//        button.getAllStyles().setBgColor(0x218838);
//
//        Button cancleButton = new Button("Cancel");
//        cancleButton.setUIID("Link");
//        cancleButton.addActionListener(e -> d.dispose());
//        cancleButton.getAllStyles().setFgColor(bgColor);
//        Container buttonContainer = new Container(BoxLayout.x());
//        buttonContainer.add(button).add(cancleButton);
//        Container centered = FlowLayout.encloseCenterMiddle(buttonContainer);
//
//        ServiceMateriel service = new ServiceMateriel();
//        ArrayList<Abonnement> abonnementsList = service.getAllAbonnement();
//        Picker abonnementPicker = getAbonnementPicker(abonnementsList);
//        if (actionUpdate) {
//            abonnementPicker.setSelectedString(offre.getAbonnement().getNom());
//        }
//        fieldsContainer
//                .add(nom)
//                .add(description)
//                .add(abonnementPicker)
//                .add(datePicker)
//                .add(centered);
//
//        if (actionUpdate) {
//            nom.setText(offre.getNom());
//            description.setText("" + offre.getCategorie());
//        }
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                String nomStr, descStr, categoryStr, photoStr;
//                Date prixStr;
//                Abonnement selectedAbonnement;
//                if (actionUpdate) {
//                    selectedAbonnement = offre.getAbonnement();
//                } else {
//                    selectedAbonnement = abonnementsList.get(abonnementPicker.getSelectedStringIndex());
//                }
//                nomStr = nom.getText();
//                descStr = description.getText();
//                prixStr = datePicker.getDate();
//                //Controle saisie
//                if (!nomStr.isEmpty() && !descStr.isEmpty()) {
//                    //Offre offre = new Offre();
//
//                    offre.setNom(nomStr);
//                    offre.setDelai((String) prixStr.toString());
//                    offre.setCategorie(descStr);
//                    offre.setAbonnement(selectedAbonnement);
//                    if (actionUpdate) {
//                        offre.setId(offre.getId());
//                        es.modifierOffre(offre);
//
//                        removeAll();
//                        try {
//                            showList();
//                        } catch (ParseException ex) {
//                            Logger.getLogger(ListOffreFrontForm.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        getContentPane().animateLayout(150);
//                    } else {
//                        es.ajouterOffre(offre);
//                        removeAll();
//                        try {
//                            showList();
//                        } catch (ParseException ex) {
//                            Logger.getLogger(ListOffreFrontForm.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        getContentPane().animateLayout(150);
//                    }
//                }
//                d.dispose();
//            }
//        });
//
//        d.setLayout(new BorderLayout());
//        d.add(BorderLayout.CENTER, fieldsContainer);
//        d.show();
//    }
//
//    private Picker getAbonnementPicker(ArrayList<Abonnement> abonnementsList) {
//        Picker picker = new Picker();
//        ArrayList<String> namesList = getNamesList(abonnementsList);
//
//        picker.setStrings(namesList.toArray(new String[0]));
//        picker.setSelectedStringIndex(0);
//        return picker;
//    }
//
//    private ArrayList<String> getNamesList(ArrayList<Abonnement> list) {
//        ArrayList<String> namesList = new ArrayList();
//        list.forEach((abonnement) -> {
//            namesList.add(abonnement.getNom());
//        });
//
//        return namesList;
//    }
//    // This method will fetch the "abonnement" list from server
//    //and will diplay it into UI (mainly used to refresh the list
//
//}
