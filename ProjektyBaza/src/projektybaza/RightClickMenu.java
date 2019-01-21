
package projektybaza;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

//================== klasa tworząca menu pod prawym przyciskiem myszy ============\\



class RightClickMenu extends JPopupMenu {
    JMenuItem anItem;
    int row;
    
    public RightClickMenu(int row){
        this.row = row;
        JMenuItem edit = new JMenuItem("Edytuj");
        add(edit);
        JMenuItem remove = new JMenuItem("Usuń");
        add(remove);
        JMenuItem copy = new JMenuItem("Kopiuj Adres");
        add(copy);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editClick(evt);
            }
        });
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delateClick(evt);
            }
        });
        copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyClick(evt);
            }
        });
    }
    
    
    //====================== obsługa opcji Edytuj====================\\
    private void editClick(java.awt.event.ActionEvent evt) {
        int row = Frame.row;
        UpdateFrame up = new UpdateFrame(row);
        up.setLocationRelativeTo(this);
        up.setVisible(true);
    }
    
    
        //=========== obsługa opcji Kopiuj Adres przydatnej do uruchamiania projektów ====================\\
    private void copyClick(java.awt.event.ActionEvent evt){
        StringSelection stringSelection = new StringSelection(ProjektyBaza.table[Frame.row][2]);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
     
    //====================== obsługa opcji Usuń====================\\
    private void delateClick(java.awt.event.ActionEvent evt) {
        int row =  Frame.row;
        DelateWin del = new DelateWin(row);
        del.setLocationRelativeTo(this);
        del.setVisible(true);
    }
}