/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movingballsfx;

import javafx.scene.paint.Color;

/**
 *
 * @author erik
 */
public enum Role {
    READER(Color.RED),WRITER(Color.BLUE);
    
    private Color color;
    
    private Role(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return this.color;
    }
}    

