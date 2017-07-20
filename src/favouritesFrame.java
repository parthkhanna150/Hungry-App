
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.Graphics;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vasu
 */
public class favouritesFrame extends javax.swing.JFrame {

    ArrayList<recipe> al = new ArrayList();

    public favouritesFrame() {
        initComponents();
        int h = Toolkit.getDefaultToolkit().getScreenSize().height;
        int w = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.setSize(w, h);
        getContentPane().setBackground(java.awt.Color.PINK);

        setVisible(true);
        try {
            FileInputStream fis = new FileInputStream("Favourite.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String s = "";
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                s += line;
            }
            StringTokenizer st = new StringTokenizer(s, ";");
            while (st.hasMoreTokens()) {
                String sub = st.nextToken();
                StringTokenizer st1 = new StringTokenizer(sub, "~");
                String recipe_id = st1.nextToken();
                String title = st1.nextToken();
                String image_url = st1.nextToken();

                al.add(new recipe(recipe_id, title, image_url));
            }
            singlePanel[] sp = new singlePanel[al.size()];
            favPanel.setSize(w, h);
            int x = 10;
            int y = 10;
            for (int i = 0; i < sp.length; i++) {
                final String id = al.get(i).rID;
                final String head = al.get(i).title;
                final String imgurl = al.get(i).imageUrl;
                sp[i] = new singlePanel();
                sp[i].setBounds(x, y, 200, 200);
                sp[i].lb_title.setText("<html>" + head + "<html>");
                URL imgUrl = new URL(imgurl);
                URLConnection conn2 = imgUrl.openConnection();
                conn2.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
                BufferedImage img = ImageIO.read(conn2.getInputStream());
                BufferedImage tempJPG = resize(img, 200, 200);
                sp[i].lb_icon.setIcon(new ImageIcon(tempJPG));
//
                sp[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            recipeDetail rd = new recipeDetail(head, imgurl, id);
                            rd.setVisible(true);
//
                        }
                    }
                });
                favPanel.add(sp[i]);
                favPanel.repaint();
                if (x < 500) {
                    x += 240;
                } else {
                    x = 10;
                    y += 240;
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param image
     * @param width
     * @param height
     * @return
     */
    public BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
//        System.out.println(bi);
        return bi;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        clearAll = new javax.swing.JButton();
        sp = new javax.swing.JScrollPane();
        favPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("My Favourites");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 20, 240, 50);

        clearAll.setText("Clear all");
        clearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllActionPerformed(evt);
            }
        });
        getContentPane().add(clearAll);
        clearAll.setBounds(1050, 40, 160, 40);

        favPanel.setLayout(null);
        sp.setViewportView(favPanel);

        getContentPane().add(sp);
        sp.setBounds(10, 150, 1250, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllActionPerformed
        // TODO add your handling code here:
        File f = new File("Favourite.txt");
        f.delete();
        al.clear();
        favPanel.removeAll();
        favPanel.repaint();
    }//GEN-LAST:event_clearAllActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(favouritesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(favouritesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(favouritesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(favouritesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new favouritesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearAll;
    private javax.swing.JPanel favPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}