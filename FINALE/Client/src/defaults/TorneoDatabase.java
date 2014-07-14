/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package defaults;

/**
 *
 * @author Giorgio
 */
public class TorneoDatabase {
    
    int id;
    String name,name_file;
    
    public TorneoDatabase(int id,String name){
        this.id=id;
        this.name=name;
    }
    
    public TorneoDatabase(int id,String name,String name_file){
        this.id=id;
        this.name=name;
        this.name_file=name_file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_file() {
        return name_file;
    }

    public void setName_file(String name_file) {
        this.name_file = name_file;
    }
    
    
    
}
