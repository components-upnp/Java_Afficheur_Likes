/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irit.display;

import com.irit.upnp.PageService;
import com.irit.upnp.ReceiveLikeService;
import org.fourthline.cling.model.meta.LocalService;

import java.util.HashMap;

/**
 *
 * @author mkostiuk
 */
public class Fenetre extends javax.swing.JFrame {

    private LocalService<PageService> pageService;
    private LocalService<ReceiveLikeService> receiveLikeService;
    private String pageCourante = "0";
    private HashMap<String,String> likes;

    /**
     * Creates new form Fenetre
     */
    public Fenetre(LocalService<PageService> p, LocalService<ReceiveLikeService> r) {

        initComponents();
        pageService = p;
        receiveLikeService = r;

        pageService.getManager().getImplementation().getPropertyChangeSupport().addPropertyChangeListener(
                evt -> {
                    if (evt.getPropertyName().equals("page")) {
                        pageCourante = (String) evt.getNewValue();
                        refreshView();
                    }
                }
        );

        receiveLikeService.getManager().getImplementation().getPropertyChangeSupport().addPropertyChangeListener(
                evt -> {
                    if (evt.getPropertyName().equals("likes")) {
                        likes = (HashMap<String,String>) evt.getNewValue();
                        System.out.println(likes);
                        refreshView();
                    }
                }
        );
    }

    public void refreshView() {
        afficheurNbLikes.setText(likes.get(pageCourante));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLike = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        afficheurNbLikes = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelLike.setText("Nombre de likes :");

        afficheurNbLikes.setEditable(false);
        jScrollPane1.setViewportView(afficheurNbLikes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLike)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLike))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane afficheurNbLikes;
    private javax.swing.JLabel jLabelLike;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}