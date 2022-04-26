/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.jeya0023.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ajithyugan Jeyakumar
 */
@Entity
@XmlRootElement
public class Sprite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer x;
    private Integer y;
    private Integer xSpeed;
    private Integer ySpeed;



    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(Integer xSpeed) {
        this.xSpeed = xSpeed;
    }

    public Integer getySpeed() {
        return ySpeed;
    }

    public void setySpeed(Integer ySpeed) {
        this.ySpeed = ySpeed;
    }
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void move()
    {
        x+=xSpeed;
        y+=ySpeed;
    }
    
    public void bounceUp()
    {
        ySpeed = - ySpeed;
    }
        
    
    public void bounceDown()
    {
        ySpeed = - ySpeed;
    }
    
    public void bounceLeft()
    {
        xSpeed = - xSpeed;
    }
        
    
    public void bounceRight()
    {
        xSpeed = - xSpeed;
    }
    
    public Sprite upDateInDB(Sprite sprite ){
            if( sprite.x == null){
                
            sprite.x = this.x;
            }
             if( sprite.xSpeed == null){
            sprite.xSpeed = this.xSpeed;
             }
              if( sprite.y == null){
            sprite.y= this.y;
              }
               if( sprite.ySpeed == null){
            sprite.ySpeed = this.ySpeed;
               }
               
            return sprite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprite)) {
            return false;
        }
        Sprite other = (Sprite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.jeya0023.entity.Sprite[ id=" + id + " ]";
    }
    
}
