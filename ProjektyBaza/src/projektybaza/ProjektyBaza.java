
package projektybaza;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableColumnModel;
import static projektybaza.Frame.tabela;

public class ProjektyBaza {
    static Connection conn = null;
    static ResultSet rs = null;
    static String querry;
    static ArrayList data = new ArrayList();
    static String[][] table;
    

//    Metoda łącząca z Bazą Damych
    public static void StartConnect() throws SQLException, IOException{
        String path = new java.io.File(".").getCanonicalPath();
        System.out.println(path);
        String url = "jdbc:sqlite:" + path+"\\src\\projektybaza\\Projekty.db";
        conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");
    }
    
//        Metoda rozłączająca z Bazą
    public static void EndConnect() throws SQLException{
        conn.close();
    }
    
//        Metoda zlicza ilość rekordów w tabeli
    public static int countRecords() throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rss = stmt.executeQuery("select * From Projekty ");
        int size = 0;
        while (rss.next()) size++;
        return size;
    }


    public static void setTable(String orderBy) throws SQLException {
        table = getData(orderBy);
    }
    
    // metoda zapisująza tabelę z bazy do tablicy dwuwymiarowej=====================\\
    public static String[][] getData(String orderBy) throws SQLException{
        String[][] row = new String[countRecords()][4];
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery("select * From Projekty  Order By " + orderBy);
        int i = 0;
        while(rs.next()){
            row[i][0] = rs.getString("Projekt_id");
            row[i][1] = rs.getString("Nazwa");
            row[i][2] = rs.getString("Adres");
            row[i][3] = rs.getString("Opis");
            i++;
        }
        return row;
    }
    
    
    //==========================Dodanie danych do tabeli =========================================\\
    public static void addData(String id,String nazwa,String adres,String opis) throws SQLException{
        Statement stmt = conn.createStatement();
        stmt.executeQuery("select * From Projekty Order By Projekt_id");
        stmt.executeUpdate("Insert into Projekty values( '" + id + "','" + nazwa + "', '" + adres + " ','" + opis + "')");
    }
    //=============Modyfikacja danych w tabeli ===============================\\]
    public static void updateData (String id,String nazwa,String adres,String opis) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("Update Projekty Set nazwa  = '" + nazwa + "', adres = '" + adres + "',opis = '" + opis + "' where Projekt_id='" + id + "'" );
    }
    
    
    //============================ Usuwanie rekordów z bazy po ID==================\\
    public static void removeData(String index) throws SQLException{
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("Delete From Projekty Where Projekt_id = " + index);
    }
    
    //============Sprawdzanie ile jest elementów w bazie. Przydatne przy autoinkrementacji ID =======\\
    public static int isEmptyId() throws SQLException{
        int idTest = 1;
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery("select * From Projekty  Order By Projekt_id");
        while (rs.next()){
            if(idTest != Integer.parseInt(rs.getString(1))) return idTest;
            else idTest++;
        }
        return idTest;
    }
    
    
    //==========Metoda odświeżająca wyświietlaną tablicę ===========================\\
    public static void updateTable(String orderBy) throws SQLException{
        ProjektyBaza.setTable(orderBy);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * From Projekty Order By Projekt_id");
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            ProjektyBaza.table,
            new String [] {
                "ID", "Nazwa", "Adres", "Opis"
            }));
        TableColumnModel columnModel = tabela.getColumnModel();
            columnModel.getColumn(0).setMaxWidth(30);
            columnModel.getColumn(1).setMaxWidth(150);
            columnModel.getColumn(2).setMaxWidth(240);
            columnModel.getColumn(2).setPreferredWidth(200);
    }
    
}
