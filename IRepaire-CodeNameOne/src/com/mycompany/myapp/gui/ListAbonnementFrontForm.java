///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//import com.codename1.capture.Capture;
//import com.codename1.components.FloatingActionButton;
//import com.codename1.components.SpanLabel;
//import com.codename1.components.ToastBar;
//import com.codename1.io.File;
//import com.codename1.io.MultipartRequest;
//import com.codename1.l10n.ParseException;
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
//import com.codename1.ui.util.ImageIO;
//import com.codename1.ui.util.Resources;
//import com.mycompany.myapp.entities.Abonnement;
//import com.mycompany.myapp.services.ServiceMateriel;
//import com.mycompany.myapp.utils.Statics;
//import com.sun.jndi.toolkit.url.Uri;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//import javafx.scene.image.ImageView;
//
///**
// *
// * @author User
// */
//public class ListAbonnementFrontForm extends BaseFrontForm {
//
//    private Object previous;
//    private int bgColor = 0x22252a;
//    private int titleColor = 0xd46973;
//    private int separatorColor = 0xd6d6d6;
//    private final String imageBaseUrl = Statics.BASE_URL + "uploads/";
//    ServiceMateriel es;
//    Resources res;
//    String base64 = "";
//    ArrayList<Abonnement> mList;
//    
//
//    public ListAbonnementFrontForm(Resources resource) {
//        super.addSideMenu(resource);
//        getAllStyles().setBgColor(bgColor);
//        setTitle("List Abonnement");
//        Container uiList = new Container(BoxLayout.y());
//        uiList.setScrollableY(true);
//        this.res = resource;
//
//        es = new ServiceMateriel();
//        mList = es.getAllAbonnement();
//        showList(mList, false);
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_LOCATION_PIN);
//        fab.addActionListener(e ->  {
//           
//                new MapForm(res).show(); // MAP FORM
//           
//        });
//        fab.bindFabToContainer(getContentPane());
//        
//        
//        
//        
//        getToolbar().addSearchCommand(e -> {
//            System.out.println(e.getCommand());
//            String text = (String) e.getSource();
//            if (text == null || text.length() == 0) {
//                removeAll();
//                showList(mList, true);
//            } else {
//                List<Abonnement> searchList = mList
//                        .stream()
//                        .filter(p -> p.getNom().contains(text))
//                        .collect(Collectors.toList());
//                System.out.println(searchList);
//                removeAll();
//                showList(searchList, true);
//            }
//            getContentPane().animateLayout(150);
//
//        });
//    }
//
//    private void showList(List<Abonnement> list, Boolean isSearching) {
//        System.out.println("Showing list: "+list);
//        Container listContainer = new Container(BoxLayout.y());
//        if(!isSearching)
//            setTitle("List Abonnement (" + es.countAbn() + ")");
//
//        for (Abonnement r : list) {
//
//            Container cellContainer = new Container(BoxLayout.x());
//            Container infoContainer = new Container(BoxLayout.y());
//
//            String imageUrl = imageBaseUrl + r.getPhoto();
//            SpanLabel nomLabel = new SpanLabel("Nom: " + r.getNom());
//            
//            SpanLabel categoryLabel = new SpanLabel("Ctégories :" + r.getCategorie());
//            SpanLabel descLabel = new SpanLabel("Description :" + r.getDescription());
//            SpanLabel prixLabel = new SpanLabel("Prix :" + r.getPrix());
//            SpanLabel photoLabel = new SpanLabel("Photo :" + r.getPhoto());
//
//            Image placeholder = Image.createImage(350, 350, 0xbfc9d2);
//            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
//            URLImage background = URLImage.createToStorage(encImage, r.getNom(), imageUrl);
//
//            cellContainer.add(background);
//            infoContainer.add(nomLabel);
//            infoContainer.add(descLabel);
//            infoContainer.add(categoryLabel);
//            infoContainer.add(prixLabel);
//            cellContainer.add(infoContainer);
//
//            Button deleteButton = new Button("Delete");
//            deleteButton.setHidden(true);
//            deleteButton.getAllStyles().setBgColor(0xF36B08);
//            deleteButton.setSize(new Dimension(100, 200));
//            deleteButton.addActionListener(e -> {
//                Dialog alert = new Dialog("Attention");
//                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette abonnement?\nCette action est irréversible!");
//                alert.add(message);
//                Button ok = new Button("Confirmer");
//                Button cancel = new Button(new Command("Annuler"));
//                ok.addActionListener(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent evt) {
//                        es.deleteAbonnement(r.getId());
//
//                        resetUI();
//                        alert.dispose();
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
//            updateButton.setHidden(true);
//            updateButton.setUIID("Link");
//            updateButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
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
//     * @param abn will be used in case of update
//     * @param actionUpdate will be used to differentiate between add/update
//     * actions
//     * 
//     */
//    private void showAddDialog(Abonnement abn, Boolean actionUpdate) {
//       
//        Dialog d = new Dialog("Ajouter un abonnement");
//        int fieldsWidth = 300;
//        Container fieldsContainer = new Container(BoxLayout.y());
//        fieldsContainer.setScrollableY(true);
//        TextField nom = new TextField("", "Nom...", 20, TextArea.ANY);
//        nom.getAllStyles().setFgColor(bgColor);
//        nom.setAlignment(LEFT);
//        TextField prix = new TextField("", "Prix...", 20, TextArea.NUMERIC);
//        prix.getAllStyles().setFgColor(bgColor);
//        prix.setAlignment(LEFT);
//        TextField descrption = new TextField("", "Description...", 20, TextArea.NUMERIC);
//        descrption.getAllStyles().setFgColor(bgColor);
//        descrption.setAlignment(LEFT);
//        TextField category = new TextField("", "Catégorie...", 20, TextArea.NUMERIC);
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
//        Button uploadButton = new Button("Upload");
//        uploadButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                base64 = getImage();
//            }
//        });
//        fieldsContainer
//                .add(nom).add(prix)
//                .add(descrption)
//                .add(category)
//                .add(uploadButton)
//                .add(centered);
//        if (actionUpdate) {
//            nom.setText(abn.getNom());
//            prix.setText("" + abn.getPrix());
//            descrption.setText(abn.getDescription());
//            category.setText(abn.getCategorie());
//        }
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                String nomStr, descStr, prixStr, categoryStr, photoStr;
//                nomStr = nom.getText();
//                descStr = descrption.getText();
//                prixStr = prix.getText();
//                categoryStr = category.getText();
//                photoStr = nomStr + "-image";
//                photoStr = photoStr.replace(" ", "");
//                photoStr += ".png";
//                if (!nomStr.isEmpty() && !descStr.isEmpty() && !prixStr.isEmpty() && !categoryStr.isEmpty() && !photoStr.isEmpty() && !base64.isEmpty()) {
//                    Abonnement abonnement = new Abonnement();
//                    abonnement.setNom(nomStr);
//                    abonnement.setPrix(Float.valueOf(prixStr));
//                    abonnement.setDescription(descStr);
//                    abonnement.setCategorie(categoryStr);
//                    abonnement.setPhoto(photoStr);
//                    es.uploadImage(base64, photoStr);
//                    if (actionUpdate) {
//                        abonnement.setId(abn.getId());
//                        es.modifierAbonnement(abonnement);
//                    } else {
//                        es.ajouterAbonnement(abonnement);
//                    }
//                }
//                resetUI();
//                d.dispose();
//            }
//        });
//
//        d.setLayout(new BorderLayout());
//        d.add(BorderLayout.CENTER, fieldsContainer);
//        d.show();
//    }
//
//    // triggered when file picker is clicked
//    private String getImage() {
//        String base64Image = "";
//        try {
//            int width = Display.getInstance().getDisplayWidth();
//            Image image = Image.createImage(Capture.capturePhoto(width, -1));
//
//            ImageIO imgIO = ImageIO.getImageIO();
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            imgIO.save(image, out, ImageIO.FORMAT_JPEG, 1);
//            byte[] ba = out.toByteArray();
//            base64Image = Base64.getEncoder().encodeToString(ba);
//        } catch (IOException ex) {
//            Logger.getLogger(ListFactureForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return base64Image;
//    }
//
//    // This method will fetch the "abonnement" list from server
//    //and will diplay it into UI (mainly used to refresh the list
//    private void resetUI() {
//        mList = es.getAllAbonnement();
//        removeAll();
//        showList(mList, false);
//    }
//
//    // used to reload the list into the UI (the list could be local)
//    private void resetList(List<Abonnement> list) {
//        removeAll();
//        showList(list, false);
//    }
//}
