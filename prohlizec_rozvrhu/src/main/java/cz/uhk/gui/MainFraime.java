package cz.uhk.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import cz.uhk.data.URLLoader;
import cz.uhk.data.kolekce.Budovy;
import cz.uhk.data.kolekce.Mistnosti;
import cz.uhk.data.kolekce.RozvrhoveAkce;

public class MainFraime extends JFrame {

    private JToolBar toolBar;
    private JTable tabAkce;
    private JScrollPane scTable;

    private JComboBox<String> jcBudovy;
    private JComboBox<String> jcMistnosti;
    private JButton nactiBtn;
    private JButton zobrazRozvrhBtn;

    private String budova;
    private String mistnost;
    private TabulkaAkci modelTabulkyAkci;

    private RozvrhoveAkce nactenaData;

    public MainFraime() {
        setTitle("Prohlížeč rozvrhu místností");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createToolBar();

        initData();

        modelTabulkyAkci = new TabulkaAkci();
        tabAkce = new JTable(modelTabulkyAkci);
        scTable = new JScrollPane(tabAkce);
        add(scTable, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void createToolBar() {
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        add(toolBar, BorderLayout.NORTH);

        // https://stag-demo.uhk.cz/ws/services/rest2/mistnost/getBudovy?outputFormat=JSON
        // zkrBudovy // ber jen ^[A-Z]$
        jcBudovy = new JComboBox<>();
        toolBar.add(jcBudovy);
        jcBudovy.addActionListener((a) -> {
            JComboBox cb = (JComboBox) a.getSource();
            budova = (String)cb.getSelectedItem();
        });

        // https://stag-demo.uhk.cz/ws/services/rest2/mistnost/getMistnostiInfo?zkrBudovy=J&outputFormat=JSON
        // cisloMistnosti // ber jen ^[A-Z][0-9]+$
        jcMistnosti = new JComboBox<>();
        toolBar.add(jcMistnosti);
        jcMistnosti.addActionListener((a) -> {
            JComboBox cb = (JComboBox) a.getSource();
            mistnost = (String) cb.getSelectedItem();
        });

        // https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=%25&budova=J&mistnost=J1&outputFormat=JSON
        nactiBtn = new JButton("Nacti");
        toolBar.add(nactiBtn);

        nactiBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    
                    nactenaData = URLLoader.getInstace()
                    .nactiDataZJsonZdroje(
                            "rozvrhy/getRozvrhByMistnost?semestr=%25&budova=" + budova + "&mistnost=" + mistnost + "&outputFormat=JSON",
                        RozvrhoveAkce.class);

                        modelTabulkyAkci.setAkce(nactenaData.getRozvrhovaAkce());

                        scTable.revalidate();
                        scTable.repaint();
                }
            }       
        });

        zobrazRozvrhBtn = new JButton("Zobraz rozvrh");
        toolBar.add(zobrazRozvrhBtn);
        zobrazRozvrhBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {                
                if (e.getButton() == MouseEvent.BUTTON1) {
                    RozvrhFrame rozvrhFrame = new RozvrhFrame(budova, mistnost, nactenaData.getRozvrhovaAkce());

                    rozvrhFrame.setVisible(true);
                }
            }
        });
    }

    private void initData() {

        List<String> nacteneBudovy = URLLoader.getInstace()
            .nactiDataZJsonZdroje("mistnost/getBudovy?outputFormat=JSON", Budovy.class)
            .prevedBudovyNaStringy();

        jcBudovy.setModel(new DefaultComboBoxModel<>(
            nacteneBudovy
                .toArray(new String[nacteneBudovy.size()])
        ));

        List<String> nacteneMistnosti = URLLoader.getInstace()
            .nactiDataZJsonZdroje("mistnost/getMistnostiInfo?outputFormat=JSON", Mistnosti.class)
            .prevedMistnostiNaStringy();

        jcMistnosti.setModel(new DefaultComboBoxModel<>(
            nacteneMistnosti
                .toArray(new String[nacteneMistnosti.size()])
        ));
    }
}
