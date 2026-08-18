// En enkel drikkelek, der den har blitt standarisert.
// Regler er inne, bruker må selv legge til sitater og bilder dem ønsker selv å bruke
// Steder der du trenger å endre selv for å legge til er kommentert inn i koden

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*; 
import javafx.scene.layout.*; 
import javafx.scene.control.*;  
import javafx.geometry.*;
import javafx.event.ActionEvent;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.util.*;
import java.io.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Drikkelek extends Application {
  // Objektvariabler
  Label hvemSa, reglerL, info, reglerI, reglerII, reglerIII, reglerIV, reglerV, sitat, sitatLabel, svarLabel;
  Button spill, lukkSpill, regler, tilbake, spillFraRegler, sjekkSvar, spillSlutt;  
  Image hovedbilde; // også må du legge til andre bilder du vil her
  ImageView b1, spillBilde, svarBilde;
  Stage vindu;
  Scene hovedScene, regelScene, spillScene, svarScene;
  HashMap<String, ArrayList<String>> sitatMap = new HashMap<>();
  HashMap<String, String> bilder = new HashMap<>();
  String riktigPerson;

  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage vindu) { 
    this.vindu = vindu;

    // Person 1 sitater
    // Du kan copy paste denne for å legge til flere folk
    ArrayList<String> personSitater = new ArrayList<>();
    personSitater.add("");
    sitatMap.put("Person", personSitater);

    // Bilder av personene, legg til URL lenken i mappen din
    bilder.put("Person", "file:///C:/Users/Alana/OneDrive/Pictures/person.jpg");

    // Hovedmeny
    VBox panel = new VBox();
    panel.setAlignment(Pos.CENTER);
    panel.setPadding(new Insets(5));
    panel.setSpacing(10);
    
    // Label hovedmeny
    hvemSa = new Label("Hvem sa dette?");
    hvemSa.setFont(new Font("Orbitron", 30));
    hvemSa.setMaxWidth(Double.MAX_VALUE);
    hvemSa.setAlignment(Pos.CENTER);

    // Bilde hovedmeny
    hovedbilde = new Image("file:C:/Users/Alana/OneDrive/Pictures/image.png");
    b1 = new ImageView(hovedbilde);

    // Knapper hovedmeny
    spill = new Button("Spill!");
    spill.setPrefSize(150, 40);
    spill.setOnAction(e -> behandleKlikk(e));
    lukkSpill = new Button("Lukk spill");
    lukkSpill.setPrefSize(150, 40);
    lukkSpill.setOnAction(e -> behandleKlikk(e));
    regler = new Button("Regler");
    regler.setPrefSize(150, 40);
    regler.setOnAction(e -> behandleKlikk(e));

    HBox knapper = new HBox(10, spill, lukkSpill, regler);
    knapper.setAlignment(Pos.CENTER);
    panel.getChildren().addAll(hvemSa, b1, knapper);
    hovedScene = new Scene(panel, 500, 600);

    // Regler meny
    VBox panel2 = new VBox();
    panel2.setAlignment(Pos.CENTER);
    panel2.setPadding(new Insets(5));
    panel2.setSpacing(5);

    // Regler labels
    reglerL = new Label("Reglene er lette!");
    reglerL.setFont(new Font("Orbitron", 20));
    reglerL.setMaxWidth(Double.MAX_VALUE);
    reglerI = new Label("Alle gjetter på hvem som sa hva!");
    reglerI.setFont(new Font("Orbitron", 18));
    reglerII = new Label("Bommer du, 1 slurk");
    reglerII.setFont(new Font("Orbitron", 18));
    reglerIII = new Label("Gjetter du riktig, del ut 1 slurk");
    reglerIII.setFont(new Font("Orbitron", 18));
    reglerIV = new Label("Du kan velge mellom: ");
    reglerIV.setFont(new Font("Orbitron", 18));
    reglerV = new Label("Personer du kan gjette på");
    reglerV.setFont(new Font("Orbitron", 16));
    reglerL.setAlignment(Pos.CENTER);

    // Regler knapper
    tilbake = new Button("Tilbake!");
    tilbake.setPrefSize(150, 40);
    tilbake.setOnAction(e -> behandleKlikk(e));
    spillFraRegler = new Button("Spill!");
    spillFraRegler.setPrefSize(150, 40);
    spillFraRegler.setOnAction(e -> behandleKlikk(e));

    panel2.getChildren().addAll(reglerL, reglerI, reglerII, reglerIII, reglerIV, reglerV, tilbake, spillFraRegler);
    regelScene = new Scene(panel2, 500, 600);

    // Spill meny 
    VBox panel3 = new VBox();
    panel3.setAlignment(Pos.CENTER);
    panel3.setPadding(new Insets(5));
    panel3.setSpacing(5);

    // Sitatlabel design
    sitat = new Label("Hvem sa dette: ");
    sitat.setFont(new Font("Orbitron", 20));
    sitatLabel = new Label("");
    sitatLabel.setFont(new Font("Orbitron", 18));
    sitatLabel.setWrapText(true);
    sitatLabel.setMaxWidth(450);
    sitatLabel.setTextAlignment(TextAlignment.CENTER);  
    sitatLabel.setAlignment(Pos.CENTER);
    sitatLabel.setMaxWidth(Double.MAX_VALUE);        
    spillBilde = new ImageView(); 

    // Spill knapper
    spillSlutt = new Button("Slutt spill!");
    spillSlutt.setOnAction(e -> behandleKlikk(e));
    spillSlutt.setPrefSize(200, 40);
    
    sjekkSvar = new Button("Sjekk svaret");
    sjekkSvar.setOnAction(e -> behandleKlikk(e));
    sjekkSvar.setPrefSize(200, 40);
    
    // Scene design
    panel3.getChildren().addAll(sitat, sitatLabel, sjekkSvar, spillSlutt);
    spillScene = new Scene(panel3, 500, 600);


    // Svar scene
    VBox panel4 = new VBox();
    panel4.setAlignment(Pos.CENTER);
    panel4.setPadding(new Insets(5));
    panel4.setSpacing(10);

    svarLabel = new Label("");
    svarLabel.setFont(new Font("Orbitron", 20));
    svarLabel.setAlignment(Pos.CENTER);
    svarLabel.setMaxWidth(Double.MAX_VALUE);

    svarBilde = new ImageView();
    svarBilde.setFitWidth(200);
    svarBilde.setFitHeight(200);
    svarBilde.setPreserveRatio(true); 

    Button nesteSitat = new Button("Neste sitat");
    nesteSitat.setOnAction(e -> {
    trekkTilfeldig();
    vindu.setScene(spillScene);
});
    HBox bildeBox = new HBox(svarBilde);
    bildeBox.setAlignment(Pos.CENTER); 
    panel4.getChildren().addAll(svarLabel, bildeBox, nesteSitat); 
    svarScene = new Scene(panel4, 500, 600);


    // Vindu info
    vindu.setTitle("Drikkelek");
    vindu.setResizable(false); 
    vindu.setScene(hovedScene); 
    vindu.show();
  }

  // Metoder 
  public void trekkTilfeldig() {
    if (sitatMap.isEmpty()) {
        sitatLabel.setText("Ingen flere sitater!");
        return;
    }
    Random rand = new Random();
    // Velger tilfeldig person
    ArrayList<String> personer = new ArrayList<>(sitatMap.keySet());
    String person = personer.get(rand.nextInt(personer.size()));
    // Velger sitat fra personen
    ArrayList<String> personSitater = sitatMap.get(person);
    String valgtSitat = personSitater.get(rand.nextInt(personSitater.size()));

    sitatLabel.setText(valgtSitat);
      

    riktigPerson = person;
}
  public void behandleKlikk(ActionEvent event) {
    if (event.getSource() == spill) { 
      trekkTilfeldig();
      vindu.setScene(spillScene);
    } else if (event.getSource() == regler) { 
      vindu.setScene(regelScene);
    } else if (event.getSource() == tilbake) { 
      vindu.setScene(hovedScene);
    } else if (event.getSource() == spillFraRegler) { 
      trekkTilfeldig();
      vindu.setScene(spillScene);
    } else if (event.getSource() == sjekkSvar) { 
    System.out.println("Riktig person: " + riktigPerson);
    System.out.println("Bildesti: " + bilder.get(riktigPerson));
    Image img = new Image(bilder.get(riktigPerson));
    System.out.println("Bilde feilet: " + img.isError());
    System.out.println("Bilde bredde: " + img.getWidth());
    svarLabel.setText(riktigPerson + " sa dette!");
    svarBilde.setImage(img);
    vindu.setScene(svarScene);
    } else if (event.getSource() == lukkSpill) { 
      Platform.exit();
    } else if (event.getSource() == spillSlutt) { 
      Platform.exit();
    }
  }
}
