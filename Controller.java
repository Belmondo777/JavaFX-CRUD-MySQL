package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import sample.database.DatabaseConnection;
import sample.models.Muz;
import sample.models.MuzZena;
import sample.models.Zena;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TableView tableView_muz;
    public TableColumn tableColumn_id;
    public TableColumn tableColumn_ime;
    public TableColumn tableColumn_prezime;
    public TableColumn tableColumn_godiste;
    public TableColumn tableColumn_zenaId;
    public TextField tf_ime_muza;
    public TextField tf_prezime_muza;
    public TextField tf_godiste_muza;
    public Button btn_dodaj_muza;
    public ComboBox cb_zena;
    public TextField tf_ime_zene;
    public TextField tf_godiste_zene;
    public TextField tf_prezime_zene;
    public Button btn_dodaj_zenu;
    public Button btn_izbrisi_muza;
    public TableView tv_muz_zena;
    public TableColumn tc_muzId;
    public TableColumn tc_muzIme;
    public TableColumn tc_muzPrezime;
    public TableColumn tc_muzGodiste;
    public TableColumn tc_muzZena;
    public TableColumn tc_zenaId;
    public TableColumn tc_zenaIme;
    public TableColumn tc_zenaPrezime;
    public TableColumn tc_zenaGodiste;
    private ObservableList<Muz> observableListMuz;
    private ObservableList<Zena> observableListZena;
    private ObservableList<MuzZena> observableListMuzZena;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView_muz.setEditable(true);
        tableView_muz.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Connection connection = new DatabaseConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM muz");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("muz_id");
                String ime = resultSet.getString("muz_ime");
                String prezime = resultSet.getString("muz_prezime");
                long godiste = resultSet.getLong("muz_godiste");
                int zena_id = resultSet.getInt("zena_id");

                Muz muz = new Muz(id, ime, prezime, godiste, zena_id);;

                tableColumn_id.setCellValueFactory(new PropertyValueFactory<Muz, Integer>("id"));

                tableColumn_ime.setCellValueFactory(new PropertyValueFactory<Muz, String>("ime"));
                tableColumn_ime.setCellFactory(TextFieldTableCell.forTableColumn());

                tableColumn_prezime.setCellValueFactory(new PropertyValueFactory<Muz, String>("prezime"));
                tableColumn_prezime.setCellFactory(TextFieldTableCell.forTableColumn());

                tableColumn_godiste.setCellValueFactory(new PropertyValueFactory<Muz, Long>("godiste"));
                tableColumn_godiste.setCellFactory(TextFieldTableCell.<Muz, Long>forTableColumn(new LongStringConverter()));
                ;
                tableColumn_zenaId.setCellValueFactory(new PropertyValueFactory<Muz, Integer>("zena_id"));
                tableColumn_zenaId.setCellFactory(TextFieldTableCell.<Muz, Integer>forTableColumn(new IntegerStringConverter()));

                observableListMuz = FXCollections.observableArrayList();
                observableListMuz.add(muz);
                tableView_muz.getItems().addAll(observableListMuz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM zena");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("zena_id");
                String ime = resultSet.getString("zena_ime");
                String prezime = resultSet.getString("zena_prezime");
                long godiste = resultSet.getLong("zena_godiste");

                Zena zena = new Zena();
                zena.setId(id);
                zena.setIme(ime);
                zena.setPrezime(prezime);
                zena.setGodiste(godiste);

                observableListZena = FXCollections.observableArrayList();
                observableListZena.add(zena);
                cb_zena.getItems().addAll(observableListZena);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableColumn_ime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Muz, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Muz, String> event) {
                Muz ime = event.getTableView().getItems().get(event.getTablePosition().getRow());
                String staroIme = event.getOldValue();
                ime.setIme(event.getNewValue());

                Connection connection = new DatabaseConnection().getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE muz SET muz_ime = ? WHERE muz_id = ?");
                    preparedStatement.setString(1, ime.getIme());
                    preparedStatement.setInt(2, ime.getId());

                    boolean uspesnost = false;

                    if (!uspesnost) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("CESTITAMO!");
                        alert.setContentText("Uspesno ste izmenili ime korisnika! " + "\n" + "Staro ime: " + staroIme + "\n" + "Novo ime: " + ime.getIme());
                        alert.showAndWait();
                        preparedStatement.execute();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("GRESKA!");
                        alert.setContentText("Doslo je do greske pri menjanju imena!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        tableColumn_prezime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Muz, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Muz, String> event) {
                Muz prezime = event.getTableView().getItems().get(event.getTablePosition().getRow());
                String staroPrezime = event.getOldValue();
                prezime.setPrezime(event.getNewValue());

                Connection connection = new DatabaseConnection().getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE muz SET muz_prezime = ? WHERE muz_id = ?");
                    preparedStatement.setString(1, prezime.getPrezime());
                    preparedStatement.setInt(2, prezime.getId());

                    boolean uspesnost = false;

                    if (!uspesnost) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("CESTITAMO!");
                        alert.setContentText("Uspesno ste izmenili prezime korisnika! " + "\n" + "Staro prezime: " + staroPrezime + "\n" + "Novo prezime: " + prezime.getPrezime());
                        alert.showAndWait();
                        preparedStatement.execute();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("GRESKA!");
                        alert.setContentText("Doslo je do greske pri menjanju prezimena!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        tableColumn_godiste.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Muz, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Muz, Long> event) {
                Muz godiste = event.getTableView().getItems().get(event.getTablePosition().getRow());
                long staroGodiste = event.getOldValue();
                godiste.setGodiste(event.getNewValue());

                Connection connection = new DatabaseConnection().getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE muz SET muz_godiste = ? WHERE muz_id = ?");
                    preparedStatement.setLong(1, godiste.getGodiste());
                    preparedStatement.setInt(2, godiste.getId());

                    boolean uspesnost = false;

                    if (!uspesnost) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("CESTITAMO!");
                        alert.setContentText("Uspesno ste izmenili godiste korisnika! " + "\n" + "Staro godiste: " + staroGodiste + "\n" + "Novo godiste: " + godiste.getGodiste());
                        alert.showAndWait();
                        preparedStatement.execute();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("GRESKA!");
                        alert.setContentText("Doslo je do greske pri menjanju godista!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        tableColumn_zenaId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Muz, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Muz, Integer> event) {
                Muz zena_id = event.getTableView().getItems().get(event.getTablePosition().getRow());
                int bivsaZena = event.getOldValue();
                zena_id.setZena_id(event.getNewValue());

                Connection connection = new DatabaseConnection().getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE muz SET zena_id = ? WHERE muz_id = ?");
                    preparedStatement.setInt(1, zena_id.getZena_id());
                    preparedStatement.setInt(2, zena_id.getId());

                    boolean uspesnost = false;

                    if (!uspesnost) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("CESTITAMO!");
                        alert.setContentText("Uspesno ste promenili zenu! " + "\n" + "Bivsa zena: " + bivsaZena + "\n" + "Nova riba: " + zena_id.getZena_id());
                        alert.showAndWait();
                        preparedStatement.execute();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Dijalog");
                        alert.setHeaderText("GRESKA!");
                        alert.setContentText("Niste uspeli da promenite zenu!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Connection connection1 = new DatabaseConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection1.prepareStatement("SELECT * FROM muz INNER JOIN zena ON muz.zena_id=zena.zena_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int muz_id = resultSet.getInt("muz_id");
                String muz_ime = resultSet.getString("muz_ime");
                String muz_prezime = resultSet.getString("muz_prezime");
                long muz_godiste = resultSet.getLong("muz_godiste");
                int muz_zena = resultSet.getInt("zena_id");
                int zena_id = resultSet.getInt("zena_id");
                String zena_ime = resultSet.getString("zena_ime");
                String zena_prezime = resultSet.getString("zena_prezime");
                long zena_godiste = resultSet.getLong("zena_godiste");

                MuzZena muzZena = new MuzZena(muz_id, muz_ime, muz_prezime, muz_godiste, muz_zena, zena_id, zena_ime, zena_prezime, zena_godiste);

                tc_muzId.setCellValueFactory(new PropertyValueFactory<MuzZena, Integer>("muz_id"));
                tc_muzIme.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("muz_ime"));
                tc_muzPrezime.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("muz_prezime"));
                tc_muzGodiste.setCellValueFactory(new PropertyValueFactory<MuzZena, Long>("muz_godiste"));
                tc_muzZena.setCellValueFactory(new PropertyValueFactory<MuzZena, Integer>("muz_zena"));
                tc_zenaId.setCellValueFactory(new PropertyValueFactory<MuzZena, Integer>("zena_id"));
                tc_zenaIme.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("zena_ime"));
                tc_zenaPrezime.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("zena_prezime"));
                tc_zenaGodiste.setCellValueFactory(new PropertyValueFactory<MuzZena, Long>("zena_godiste"));

                observableListMuzZena = FXCollections.observableArrayList();
                observableListMuzZena.add(muzZena);
                tv_muz_zena.getItems().addAll(observableListMuzZena);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClick(MouseEvent mouseEvent) {
        String ime = tf_ime_muza.getText();
        String prezime = tf_prezime_muza.getText();
        String godiste = tf_godiste_muza.getText();
        Zena zena = (Zena) cb_zena.getSelectionModel().getSelectedItem();
        String str = "" + zena;
        String[] razdvojen = str.split(" ");
        String prvi = razdvojen[0];
        int zena_id = Integer.parseInt(prvi);

        if (ime.isEmpty() || prezime.isEmpty() || godiste.isEmpty() || cb_zena.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dijalog");
            alert.setHeaderText("NAPOMENA!");
            alert.setContentText("Text Field-ovi i Combo Box ne smeju biti prazni!");
            alert.showAndWait();
        } else {
            Connection connection = new DatabaseConnection().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO muz (muz_ime, muz_prezime, muz_godiste, zena_id) VALUES (?, ?, ?, ?)");
                preparedStatement.setString(1, ime);
                preparedStatement.setString(2, prezime);
                preparedStatement.setLong(3, Long.parseLong(godiste));
                preparedStatement.setInt(4, zena_id);

                boolean uspesnost = false;

                if (!uspesnost) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Dijalog");
                    alert.setHeaderText("CESTITAMO!");
                    alert.setContentText("Uspesno ste dodali korisnika! " + "\n" + "Korisnik: " + ime + " " + prezime + " (" + godiste + ")");
                    alert.showAndWait();
                    preparedStatement.execute();
                    tf_ime_muza.clear();
                    tf_prezime_muza.clear();
                    tf_godiste_muza.clear();
                    cb_zena.getSelectionModel().clearSelection();
                    readAllMen();
                    readAllMenAndWomen();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Dijalog");
                    alert.setHeaderText("GRESKA!");
                    alert.setContentText("Doslo je do greske pri dodavanju korisnika!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickZena(MouseEvent mouseEvent) {
        String ime = tf_ime_zene.getText();
        String prezime = tf_prezime_zene.getText();
        String godiste = tf_godiste_zene.getText();

        if (ime.isEmpty() || prezime.isEmpty() || godiste.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dijalog");
            alert.setHeaderText("NAPOMENA!");
            alert.setContentText("Text Field ne sme biti prazan!");
            alert.showAndWait();
        } else {
            Connection connection = new DatabaseConnection().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO zena (zena_ime, zena_prezime, zena_godiste) VALUES (?, ?, ?)");
                preparedStatement.setString(1, ime);
                preparedStatement.setString(2, prezime);
                preparedStatement.setLong(3, Long.parseLong(godiste));

                boolean uspesnost = false;

                if (!uspesnost) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Dijalog");
                    alert.setHeaderText("CESTITAMO!");
                    alert.setContentText("Uspesno ste dodali korisnika! " + "\n" + "Korisnik: " + ime + " " + prezime + " (" + godiste + ")");
                    alert.showAndWait();
                    preparedStatement.execute();
                    tf_ime_zene.clear();
                    tf_prezime_zene.clear();
                    tf_godiste_zene.clear();
                    readAllWomen();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void izbrisiMuza(MouseEvent mouseEvent) {
        ObservableList<Muz> allItems, singleItem;
        allItems = tableView_muz.getItems();
        singleItem = tableView_muz.getSelectionModel().getSelectedItems();
        int id = 0;
        for (Muz m : singleItem) {
            id = m.getId();
            allItems.remove(m);
        }
        Connection connection = new DatabaseConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM muz WHERE muz_id = ?");
            preparedStatement.setInt(1, id);

            boolean uspesnost = false;

            if (!uspesnost) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Dijalog");
                alert.setHeaderText("CESTITAMO!");
                alert.setContentText("Uspesno ste izbrisali korisnika!");
                alert.showAndWait();
                preparedStatement.execute();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Dijalog");
                alert.setHeaderText("GRESKA!");
                alert.setContentText("Greska pri brisanju korisnika!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAllMen() {
        observableListMuz.clear();
        tableView_muz.getItems().clear();
        Connection connection = new DatabaseConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM muz");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("muz_id");
                String ime = resultSet.getString("muz_ime");
                String prezime = resultSet.getString("muz_prezime");
                long godiste = resultSet.getLong("muz_godiste");
                int zena_id = resultSet.getInt("zena_id");

                Muz muz = new Muz(id, ime, prezime, godiste, zena_id);;

                tableColumn_id.setCellValueFactory(new PropertyValueFactory<Muz, Integer>("id"));
                tableColumn_ime.setCellValueFactory(new PropertyValueFactory<Muz, String>("ime"));
                tableColumn_prezime.setCellValueFactory(new PropertyValueFactory<Muz, String>("prezime"));
                tableColumn_godiste.setCellValueFactory(new PropertyValueFactory<Muz, Long>("godiste"));
                tableColumn_zenaId.setCellValueFactory(new PropertyValueFactory<Muz, Integer>("zena_id"));

                observableListMuz = FXCollections.observableArrayList();
                observableListMuz.add(muz);
                tableView_muz.getItems().addAll(observableListMuz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAllWomen() {
        observableListZena.clear();
        cb_zena.getItems().clear();
        Connection connection = new DatabaseConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM zena");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("zena_id");
                String ime = resultSet.getString("zena_ime");
                String prezime = resultSet.getString("zena_prezime");
                long godiste = resultSet.getLong("zena_godiste");

                Zena zena = new Zena();
                zena.setId(id);
                zena.setIme(ime);
                zena.setPrezime(prezime);
                zena.setGodiste(godiste);

                ObservableList<Zena> observableList = FXCollections.observableArrayList();
                observableList.add(zena);
                cb_zena.getItems().addAll(observableList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAllMenAndWomen() {
        observableListMuzZena.clear();
        tv_muz_zena.getItems().clear();
        Connection connection1 = new DatabaseConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection1.prepareStatement("SELECT * FROM muz INNER JOIN zena ON muz.zena_id=zena.zena_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int muz_id = resultSet.getInt("muz_id");
                String muz_ime = resultSet.getString("muz_ime");
                String muz_prezime = resultSet.getString("muz_prezime");
                long muz_godiste = resultSet.getLong("muz_godiste");
                int muz_zena = resultSet.getInt("zena_id");
                int zena_id = resultSet.getInt("zena_id");
                String zena_ime = resultSet.getString("zena_ime");
                String zena_prezime = resultSet.getString("zena_prezime");
                long zena_godiste = resultSet.getLong("zena_godiste");

                MuzZena muzZena = new MuzZena(muz_id, muz_ime, muz_prezime, muz_godiste, muz_zena, zena_id, zena_ime, zena_prezime, zena_godiste);

                tc_muzId.setCellValueFactory(new PropertyValueFactory<MuzZena, Integer>("muz_id"));
                tc_muzIme.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("muz_ime"));
                tc_muzPrezime.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("muz_prezime"));
                tc_muzGodiste.setCellValueFactory(new PropertyValueFactory<MuzZena, Long>("muz_godiste"));
                tc_muzZena.setCellValueFactory(new PropertyValueFactory<MuzZena, Integer>("muz_zena"));
                tc_zenaId.setCellValueFactory(new PropertyValueFactory<MuzZena, Integer>("zena_id"));
                tc_zenaIme.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("zena_ime"));
                tc_zenaPrezime.setCellValueFactory(new PropertyValueFactory<MuzZena, String>("zena_prezime"));
                tc_zenaGodiste.setCellValueFactory(new PropertyValueFactory<MuzZena, Long>("zena_godiste"));

                observableListMuzZena = FXCollections.observableArrayList();
                observableListMuzZena.add(muzZena);
                tv_muz_zena.getItems().addAll(observableListMuzZena);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
