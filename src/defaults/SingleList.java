/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

import exception.ErroreNonSingle;
import static defaults.TavoloSingoli.checkSingle;
import java.util.ArrayList;

/**
 *
 * @author playrom
 */
public class SingleList extends ArrayList{
    
    public SingleList(){
        super();
    }
    
    public SingleList(int dimensione){
        super(dimensione);
    }
    
    public Single findSingle(int id){
        for(int i=0;i<this.size();i++){
            Single temp=(Single) this.get(i);
            try{
                checkSingle(temp);
                if(temp.getId()==id){
                    return temp;
                }
            }catch(ErroreNonSingle e){
                System.out.println("Errore non single");
            }
        }
        
        return null;
    }
    
    @Override
    public Single get(int i){
        Single ret=(Single) super.get(i);
        return ret;
    }
}
