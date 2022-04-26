/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.jeya0023.game;
import cst8218.jeya0023.entity.Sprite;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Ajithyugan Jeyakumar
 */
@Startup
@Singleton
public class SpriteGame {

    private Integer xSize = 500;
    private Integer ySize = 500;
    private List<Sprite> spriteList;
   

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private GameSession session;


    
    private void Bouncing(Sprite sprite)
    {
    
        if(sprite.getX() < 0 && sprite.getxSpeed() < 0)
        {
            sprite.bounceLeft();
        }
        if(sprite.getX() > xSize && sprite.getxSpeed() > 0)
        {
            sprite.bounceRight();
        }
        if(sprite.getY() < 0 && sprite.getySpeed() < 0)
        {
            sprite.bounceUp();
        }
        if(sprite.getY() > ySize && sprite.getySpeed() > 0)
        {
            sprite.bounceDown();
        }
    }
    @PostConstruct
    public void go()
    {
        new Thread (new Runnable(){ @Override 
        
        public void run (){ 
        while (true){ spriteList = session.findAll(); 
        for ( Sprite sprite : spriteList){ 
            sprite.move(); 
            Bouncing( sprite);
            session.edit(sprite);
        
        } 
        try {Thread.sleep(100); }
        catch( InterruptedException ie) { ie.printStackTrace();   }   }   }
        
        }   )
                .start();
    }
    
}
