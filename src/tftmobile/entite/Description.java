/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.entite;

/**
 *
 * @author yasmi
 */
public class Description {
    
//    public static final int PERSONNEL   = 0;
//    public static final int PUBLIC   = 1;
// 
//    private int type;
    private String contenu;
 
//    public void setType(String type) {
//     if (type.equals("personnel")) {
//            this.type = PERSONNEL;
//        } else if (type.equals("public")) {
//            this.type = PUBLIC;
//        }else {
//            throw new IllegalArgumentException("unknown PhoneNumber type: " + type);
//        }
//    }
 
//    public int getType() {
//        return type;
//    }
 
    public void setContenu(String contenu) {
            this.contenu = contenu;

    }
 
    public String getContenu() {
        return contenu;
    }
    
}