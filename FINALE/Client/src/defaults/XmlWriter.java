package defaults;
 
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class XmlWriter implements Runnable {
    
    String filename;
    ArrayList coppie,turni,singles;
    Torneo torneo;
    String nome;
    int numTurni,smazzate;
    boolean started,aloneVar;
    
    public XmlWriter(String filename,ArrayList coppie,Torneo torneo){
        this.filename=filename;
        this.coppie=coppie;
        this.torneo=torneo;
        this.turni=torneo.getTurni();
        this.started=torneo.isStarted();
        this.numTurni=torneo.getNumTurni();
        this.aloneVar=torneo.isAlone();
        this.smazzate=torneo.getSmazzate();
    }
    
    public XmlWriter(String filename,ArrayList coppie,String nome,int numTurni,boolean alone,int smazzate){
        this.filename=filename;
        this.coppie=coppie;
        this.nome=nome;
        this.numTurni=numTurni;
        started=false;
        this.aloneVar=alone;
        this.smazzate=smazzate;
    }
    
    public XmlWriter(String filename,ArrayList coppie,ArrayList singles,String nome,int numTurni,boolean alone,int smazzate){
        this(filename,coppie,nome,numTurni,alone,smazzate);
        this.singles=singles;
    }
    
    public XmlWriter(String filename,ArrayList coppie,ArrayList singles,Torneo torneo){
        this(filename,coppie,torneo);
        this.singles=singles;
    }

 
    @Override
    public void run(){
 
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);
                
                
                
                ArrayList tempElementSingles=new SingleList(singles.size());
                
                for(int i=0;i<singles.size();i++){

                    // staff elements
                    Element single = doc.createElement("Single");
                    Single temp=(Single) singles.get(i);
                    rootElement.appendChild(single);

                    // shorten way
                    // staff.setAttribute("id", "1");

                    // firstname elements
                    Element nome = doc.createElement("nome");
                    nome.appendChild(doc.createTextNode(temp.getName()));
                    single.appendChild(nome);

                    Element id = doc.createElement("id");
                    id.appendChild(doc.createTextNode(String.valueOf(temp.getId())));
                    single.appendChild(id);

                    Element tipo = doc.createElement("alone");
                    tipo.appendChild(doc.createTextNode(String.valueOf(temp.isAlone())));
                    single.appendChild(tipo);

                    Element master = doc.createElement("master");
                    master.appendChild(doc.createTextNode(String.valueOf(temp.getMaster())));
                    single.appendChild(master);

                    Element victory = doc.createElement("victory");
                    victory.appendChild(doc.createTextNode(String.valueOf(temp.getVictory())));
                    single.appendChild(victory);
                    
                    Element id_database = doc.createElement("id_database");
                    id_database.appendChild(doc.createTextNode(String.valueOf(temp.getId_database())));
                    single.appendChild(id_database);
                    
                    

                }
                
                
                if(!aloneVar){
                
                
                ArrayList tempElementCoppie=new ArrayList();
                
                for(int i=0;i<coppie.size();i++){

                    // staff elements
                    Element coppia = doc.createElement("Coppia");
                    Coppia temp=(Coppia) coppie.get(i);
                    rootElement.appendChild(coppia);

                    // shorten way
                    // staff.setAttribute("id", "1");

                    // firstname elements
                    Element nome1 = doc.createElement("nome1");
                    nome1.appendChild(doc.createTextNode(String.valueOf(temp.getUno())));
                    coppia.appendChild(nome1);

                    Element nome2 = doc.createElement("nome2");
                    nome2.appendChild(doc.createTextNode(String.valueOf(temp.getDue())));
                    coppia.appendChild(nome2);

                    Element id = doc.createElement("id");
                    id.appendChild(doc.createTextNode(String.valueOf(temp.getId())));
                    coppia.appendChild(id);

                    Element tipo = doc.createElement("tipo");
                    tipo.appendChild(doc.createTextNode(String.valueOf(temp.getType())));
                    coppia.appendChild(tipo);

                    Element master = doc.createElement("master");
                    master.appendChild(doc.createTextNode(String.valueOf(temp.getMastPoints())));
                    coppia.appendChild(master);

                    Element victory = doc.createElement("victory");
                    victory.appendChild(doc.createTextNode(String.valueOf(temp.getVictPoints())));
                    coppia.appendChild(victory);
                    

                }
                
                }
                
                
                
                
                Element torneoNode=doc.createElement("Torneo");
                rootElement.appendChild(torneoNode);

                if(started){ nome=torneo.getNome(); aloneVar=torneo.isAlone(); }

                Element nomeTorneo= doc.createElement("nome");
                nomeTorneo.appendChild(doc.createTextNode(nome));
                torneoNode.appendChild(nomeTorneo);
                
                Element aloneTorneo= doc.createElement("alone");
                aloneTorneo.appendChild(doc.createTextNode(String.valueOf(aloneVar)));
                torneoNode.appendChild(aloneTorneo);


                Element startedElement = doc.createElement("started");
                startedElement.appendChild(doc.createTextNode(String.valueOf(started)));
                torneoNode.appendChild(startedElement);
                
                
                Element numTurniElement = doc.createElement("numturni");
                numTurniElement.appendChild(doc.createTextNode(String.valueOf(numTurni)));
                torneoNode.appendChild(numTurniElement);
                
                Element smazzateElement = doc.createElement("smazzate");
                smazzateElement.appendChild(doc.createTextNode(String.valueOf(smazzate)));
                torneoNode.appendChild(smazzateElement);

                if(started){
                    for(int i=0;i<turni.size();i++){

                        // staff elements
                        Element turno = doc.createElement("Turno");
                        Turno temp=(Turno) turni.get(i);
                        rootElement.appendChild(turno);

                        Element id = doc.createElement("id");
                        id.appendChild(doc.createTextNode(String.valueOf(temp.getId())));
                        turno.appendChild(id);

                        Element tipo = doc.createElement("calcolato");
                        tipo.appendChild(doc.createTextNode(String.valueOf(temp.getCalcolato())));
                        turno.appendChild(tipo);

                        Element numTavoli = doc.createElement("numtavoli");
                        numTavoli.appendChild(doc.createTextNode(String.valueOf(temp.getNumTavoli())));
                        turno.appendChild(numTavoli);

                        if(torneo.isAlone()){
                            createTavoliSingle(temp,doc,turno);
                        } else {
                            createTavoliCoppie(temp,doc,turno);
                        }

                        torneoNode.appendChild(turno);


                    }
                }
                    
                
                
                
                
                
 
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(this.filename));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
    
    
    
    public void createTavoliCoppie(Turno temp,Document doc,Element turno){
        for(int k=0;k<temp.getTavoli().length;k++){
            
            TavoloCoppie tempTavolo=(TavoloCoppie) temp.getTavolo(k);

            Element tavolo = doc.createElement("Tavolo");

            Element idTavolo = doc.createElement("id");
            idTavolo.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getId())));
            tavolo.appendChild(idTavolo);

            Element id_uno = doc.createElement("id_uno");
            id_uno.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getCop1())));
            tavolo.appendChild(id_uno);

            Element id_due = doc.createElement("id_due");
            id_due.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getCop2())));
            tavolo.appendChild(id_due);

            Element pun1 = doc.createElement("pun1");
            pun1.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPun1())));
            tavolo.appendChild(pun1);

            Element pun2 = doc.createElement("pun2");
            pun2.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPun2())));
            tavolo.appendChild(pun2);

            turno.appendChild(tavolo);
        }
    }
    
    
    public void createTavoliSingle(Turno temp,Document doc,Element turno){
        for(int k=0;k<temp.getTavoli().length;k++){
            
            TavoloSingoli tempTavolo=(TavoloSingoli) temp.getTavolo(k);

            Element tavolo = doc.createElement("Tavolo");

            Element idTavolo = doc.createElement("id");
            idTavolo.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getId())));
            tavolo.appendChild(idTavolo);

            Element id_uno = doc.createElement("id_uno");
            id_uno.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPla1())));
            tavolo.appendChild(id_uno);

            Element id_due = doc.createElement("id_due");
            id_due.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPla2())));
            tavolo.appendChild(id_due);
            
            Element id_tre = doc.createElement("id_tre");
            id_tre.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPla3())));
            tavolo.appendChild(id_tre);
            
            Element id_quattro = doc.createElement("id_quattro");
            id_quattro.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPla4())));
            tavolo.appendChild(id_quattro);

            Element pun1 = doc.createElement("pun1");
            pun1.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPun1())));
            tavolo.appendChild(pun1);

            Element pun2 = doc.createElement("pun2");
            pun2.appendChild(doc.createTextNode(String.valueOf(tempTavolo.getPun2())));
            tavolo.appendChild(pun2);

            turno.appendChild(tavolo);
        }
    }
}