/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.MultipartRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Command;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.entities.Materiel;
import com.mycompany.myapp.services.ServiceFacture;
import com.mycompany.myapp.services.ServiceMateriel;
import com.mycompany.myapp.utils.Statics;
import com.sun.jndi.toolkit.url.Uri;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class ListFactureForm extends BaseForm {

    private Object previous;
    private int bgColor = 0x22252a;
    private int titleColor = 0xd46973;
    private int separatorColor = 0xd6d6d6;
    private final String imageBaseUrl = Statics.BASE_URL + "uploads/";
    ServiceFacture es;
    Resources res;
    String base64 = "";
    ArrayList<Facture> mList;

    public ListFactureForm(Resources resource) {
        super.addSideMenu(resource);
        getAllStyles().setBgColor(bgColor);
        setTitle("List Facture");
        Container uiList = new Container(BoxLayout.y());
        uiList.setScrollableY(true);
        this.res = resource;

        es = new ServiceFacture();
        mList = es.getAllFacture();
        showList(mList, false);
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener(e -> showAddDialog(new Facture(), false));
        fab.bindFabToContainer(getContentPane());
        //Recherche
        getToolbar().addSearchCommand(e -> {
            System.out.println(e.getCommand());
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                removeAll();
                showList(mList, true);
            } else {
                List<Facture> searchList = mList
                        .stream()
                        .filter(p -> p.getMat_id().getType_piece().contains(text))
                        .collect(Collectors.toList());
                System.out.println(searchList);
                removeAll();
                showList(searchList, true);
            }
            getContentPane().animateLayout(150);//refresh

        });
    }

    public void showList(List<Facture> list, Boolean isSearching) {
       
       ServiceMateriel sm = new ServiceMateriel();
        System.out.println("Showing list: "+list);
        Container listContainer = new Container(BoxLayout.y());
        if(!isSearching)
            
            setTitle("List Facture (" + list.size() + ")");

        for (Facture r : list) {

            Container cellContainer = new Container(BoxLayout.x());
            Container infoContainer = new Container(BoxLayout.y());

            //String imageUrl = imageBaseUrl + r.getPhoto();
            SpanLabel nomLabel = new SpanLabel("Nom: " + r.getMat_id().getNom());
            SpanLabel typeLabel = new SpanLabel("Type: " + r.getMat_id().getType_piece());
            SpanLabel probLabel = new SpanLabel("Probleme: " + r.getMat_id().getProbleme());
            SpanLabel distLabel = new SpanLabel("Date de distribution: " + r.getMat_id().getDate_dist().substring(6, 16));
            //SpanLabel prix = new SpanLabel("Prix totale: " + r.getPrix());
            
            

            Image placeholder = Image.createImage(350, 350, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            //URLImage background = URLImage.createToStorage(encImage, r.getMat_id().getNom(), "/*imageUrl*/");

            //cellContainer.add(background);
            infoContainer.add(nomLabel);
            infoContainer.add(typeLabel);
            infoContainer.add(probLabel);
            infoContainer.add(distLabel);
            
            cellContainer.add(infoContainer);

            Button deleteButton = new Button("Delete");
            deleteButton.getAllStyles().setBgColor(0xF36B08);
            deleteButton.setSize(new Dimension(100, 200));
            deleteButton.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette facture?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        es.deleteFacture(r.getId());

                        resetUI();
                        alert.dispose();
                    }

                }
                );
                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
            });

            Container buttonContainer = new Container(BoxLayout.x());
            Button updateButton = new Button("modifier");
            updateButton.setUIID("Link");
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    showAddDialog(r, true);
                }
            });
            buttonContainer.add(deleteButton).add(updateButton);
            Container centered = FlowLayout.encloseCenterMiddle(buttonContainer);
            infoContainer.add(centered);
            System.out.println("");
            listContainer.add(cellContainer);
            Container separator = new Container();
            separator.setHeight(2);
            separator.getAllStyles().setBgColor(separatorColor);
            listContainer.add(separator);

        }
        add(listContainer);
    }

    /**
     * This method will take as parameter the "" entity and
     * actionUpdate Boolean if the actionUpdate is true, the dialog fields will
     * be filled with the entity info, else the fields will be empty
     *
     * @param abn will be used in case of update
     * @param actionUpdate will be used to differentiate between add/update
     * actions
     * 
     */
    private void showAddDialog(Facture facture, Boolean actionUpdate) {
       
        Dialog d = new Dialog("Ajouter une Facture");
        int fieldsWidth = 300;
        Container fieldsContainer = new Container(BoxLayout.y());
        fieldsContainer.setScrollableY(true);
       
        TextField typepiece = new TextField("", "Type de la piece...", 20, TextArea.NUMERIC);
        typepiece.getAllStyles().setFgColor(bgColor);
        typepiece.setAlignment(LEFT);
        TextField prix = new TextField("", "Le Prix totale...", 20, TextArea.NUMERIC);
        prix.getAllStyles().setFgColor(bgColor);
        prix.setAlignment(LEFT);
        
        TextField probleme = new TextField("", "Probleme...", 20, TextArea.NUMERIC);
        probleme.getAllStyles().setFgColor(bgColor);
        probleme.setAlignment(LEFT);
//        TextField dateDist = new TextField("", "Date de Distribution...", 20, TextArea.NUMERIC);
//        dateDist.getAllStyles().setFgColor(bgColor);
//        dateDist.setAlignment(LEFT);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        String buttonText = actionUpdate ? "Modifier" : "Ajouter";
        Button button = new Button(buttonText);
        button.getAllStyles().setBgColor(0x218838);

        Button cancleButton = new Button("Cancel");
        cancleButton.setUIID("Link");
        cancleButton.addActionListener(e -> d.dispose());
        cancleButton.getAllStyles().setFgColor(bgColor);
        Container buttonContainer = new Container(BoxLayout.x());
        buttonContainer.add(button).add(cancleButton);
        Container centered = FlowLayout.encloseCenterMiddle(buttonContainer);
        //Button uploadButton = new Button("Upload");
        //uploadButton.addActionListener(new ActionListener() {
           
        
        ServiceMateriel service = new ServiceMateriel();
        ArrayList<Materiel> materielList = service.getAllMateriel();
        Picker materielPicker = getMaterielPicker(materielList);
        if (actionUpdate) {
            materielPicker.setSelectedStringIndex(facture.getMat_id().getId());
        }
        fieldsContainer
                .add(materielPicker)
                .add(prix)
             
                .add(centered);
        if (actionUpdate) {
            //nom.setText(abn.getMat_id().getNom());
//            typepiece.setText("" + abn.getMat_id().getType_piece());
//            probleme.setText(abn.getMat_id().getProbleme());
//            datePicker.setSelectedString(abn.getMat_id().getDate_dist().substring(10, 16));
            materielPicker.setSelectedString(facture.getMat_id().getNom());
            prix.setText(facture.getPrix()+"");
            
            
        }
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nomStr, descStr, prixStr, categoryStr, photoStr,matStr,prixTot;
                //nomStr = nom.getText();
                descStr = typepiece.getText();
                prixStr = probleme.getText();
                //prixTot = prix.getText();
                
                categoryStr = datePicker.getText();
                matStr = materielPicker.getText();
                //photoStr = nomStr + "-image";
                //photoStr = photoStr.replace(" ", " ");
                //photoStr += ".png";
                if (!descStr.isEmpty() && !prixStr.isEmpty() && !categoryStr.isEmpty() /*&& !photoStr.isEmpty() && !base64.isEmpty()*/) {
                    Facture fact = new Facture();
                    Materiel mat = new Materiel();
                    
//                    
//                    mat.setNom(matStr);
//                    mat.setProbleme((prixStr));
//                    mat.setType_piece(descStr);
//                    mat.setDate_dist(categoryStr);
                    //mat.setNom(mat);
                    //abonnement.setPhoto(photoStr);
                    //es.uploadImage(base64, photoStr);
                    
                    //fact.setPrix(Double.valueOf(prixTot));
                    if (actionUpdate) {
                        fact.setId(facture.getId());
                        es.modifierFacture(fact);
                    } else {
                        es.ajouterFacture(fact);
                    }
                }
                resetUI();
                d.dispose();
            }
        });

        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, fieldsContainer);
        d.show();
    }

    // triggered when file picker is clicked
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
     private Picker getMaterielPicker(ArrayList<Materiel> materielList) {
        Picker picker = new Picker();
        ArrayList<String> namesList = getNamesList(materielList);

        picker.setStrings(namesList.toArray(new String[0]));
        picker.setSelectedStringIndex(0);
        return picker;
    }

    private ArrayList<String> getNamesList(ArrayList<Materiel> list) {
        ArrayList<String> namesList = new ArrayList();
        list.forEach((materiel) -> {
            namesList.add(materiel.getNom());
        });

        return namesList;
    }

    // This method will fetch the "abonnement" list from server
    //and will diplay it into UI (mainly used to refresh the list
    private void resetUI() {
        mList = es.getAllFacture();
        removeAll();
        showList(mList, false);
    }

    // used to reload the list into the UI (the list could be local)
    private void resetList(List<Facture> list) {
        removeAll();
        showList(list, false);
    }
}
