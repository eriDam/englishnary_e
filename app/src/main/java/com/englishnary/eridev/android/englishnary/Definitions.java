package com.englishnary.eridev.android.englishnary;

import java.io.Serializable;

/**
 * Created by eridev on 31/01/16.
 */
public class Definitions implements Serializable{
   // private String definitions;
    private String text;
    private String atribution;

    public Definitions( String text, String atribution) {
        //this.definitions = definitions;
        this.text = text;
        this.atribution = atribution;

    }

//    public String getDefinitions() {
//         return definitions;
//     }
//
//     public void setDefinitions(String definitions) {
//
//         this.definitions = definitions;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAtribution() {
        return atribution;
    }

    public void setAtribution(String atribution) {
        this.atribution = atribution;
    }



    @Override
    public String toString() {
        return "Definitions:  " +
                //"definitions='" + definitions + '\n' +
                ", text='" + text + '\n' +
                ", atribution='" + atribution + '\n' +
                '}';
    }
}
