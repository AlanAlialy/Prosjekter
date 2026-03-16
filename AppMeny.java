import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

public class AppMeny implements ActionListener {

    private JFrame frame;
    private int antClick = 0;
    private JLabel valg;
    private JLabel teller;
    private JButton blackjack;
    private JButton musikk;
    private JButton lotto;
    private JButton clicker;

    public AppMeny() {
        
        frame = new JFrame(); // Lager "rammen" rundt
        
        // Lager knapper og tekst her
        blackjack = new JButton("Spill Blackjack        (Ikke klart)");
        musikk = new JButton("Finn ny musikk");
        lotto = new JButton("Spill lotto                    (Ikke klart)");
        clicker = new JButton("Clicker teller"); 
        valg = new JLabel("Velg ønsket program");
        teller = new JLabel("Antall ganger knappen er klikket: 0");
        
        // Knapper instillinger
        clicker.addActionListener(this); // Sjekker om den faktisk blir trykket på eller ikke
        clicker.setActionCommand("CLICKER"); // Gjør det faktisk mulig å bruke switch case

        blackjack.addActionListener(this);
        blackjack.setActionCommand("BLACKJACK");

        musikk.addActionListener(this);
        musikk.setActionCommand("MUSIKK");

        lotto.addActionListener(this);
        lotto.setActionCommand("LOTTO");


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        
        // Legger til knapper
        panel.add(valg);
        panel.add(new JLabel("")); // Usynlig element for at de skal starte på ny rad pent
        panel.add(blackjack);
        panel.add(musikk);
        panel.add(lotto);
        panel.add(clicker);

        // Velger farge på knapper her 
        blackjack.setBackground(Color.RED);
        blackjack.setOpaque(true);
        blackjack.setBorderPainted(false);

        // Fikser på teller tekst posisjon
        JPanel tekstPanel = new JPanel();
        tekstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tekstPanel.add(teller);

        // Frame instillinger
        frame.add(panel, BorderLayout.CENTER);      // Setter det midt inni grensesnittet
        frame.add(tekstPanel, BorderLayout.SOUTH);

        // Instillingene til selvet grensesnittet
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Hva den skal gjøre når det stenges
        frame.setTitle("Hovedmeny");
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null); // Får det til å dukke opp i midten av skjermen
        frame.setVisible(true); // Denne må være til slutt

        } 

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String kommando = e.getActionCommand();

            switch (kommando) {
            case "CLICKER":
                antClick++;
                teller.setText("Antall ganger knappen er klikket: " + antClick);
                break;

            case "BLACKJACK":
               // new BlackJackSpill();
                frame.dispose();
                break; 
            
            case "MUSIKK":
                try {
                    // Sjekker først om datamaskinen støtter å åpne nettleseren fra Java
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        
                        Desktop.getDesktop().browse(new URI("https://music.youtube.com/"));
                        
                    } else {
                        System.out.println("Datamaskinen støtter ikke denne handlingen.");
                    }
                } catch (IOException | URISyntaxException ex) {
                    System.out.println("Klarte ikke å åpne lenken!");
                    ex.printStackTrace();
                }
                break;    
        }
    }
    public static void main(String[] args) {
        new AppMeny();
        
        


    }
}
