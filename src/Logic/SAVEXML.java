package Logic;
import java.beans.XMLEncoder;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.*;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.annotation.*;

public class SAVEXML {
    GameEngine Game = GameEngine.getGameInstance();
    SaveModel s = Game.Get_Save_object();
    boolean write_Before;
void File_Save(){

    DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder icBuilder;
    try

    {
        icBuilder = icFactory.newDocumentBuilder();
        Document doc = icBuilder.newDocument();

        Element mainRootElement;
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/Logic/file.xml", false));

        mainRootElement = doc.createElementNS("alyelshwahy@yahoo.com","Data");

            doc.appendChild(mainRootElement);


            mainRootElement.appendChild(createUser(doc, s.Type, s.right, s.left, s.Boat, s.score, s.isLeft, s.choosenShip,
                    s.choosenFarmer,s.choosenHerbAnimal,s.choosenCarnAnimal,s.choosenPlants));



        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);


        StreamResult file = new StreamResult(bw);
        transf.transform(source, file);


    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
    private static Node createUser(Document doc, int type, List<ICrosser> Right, List<ICrosser>left,
                                   List<ICrosser> boat, int scores, boolean isLeft, SHIP choosenShip, FARMER choosenFarmer,
                                   HERBANIMAL choosenHerb, CARNANIMAL choosenCarn, PLANTS choosenPlants) {

        Element user = doc.createElement("user1");

        user.setAttribute("STORYCASE", Integer.toString(type));
        if(type==1) {
            try {
                for (int i = 0; i < Right.size(); i++) {
                    user.appendChild(createUserElement(doc, "ListRight", Right.get(i).getLabelToBeShown()));
                }

            } catch (NullPointerException e) {
                System.out.println("NULLLL1");
            }
            try {
                for (int i = 0; i < left.size(); i++) {
                    user.appendChild(createUserElement(doc, "ListLeft", left.get(i).getLabelToBeShown()));
                }
            } catch (NullPointerException e) {
                System.out.println("NULLLL2");
            }
            try {
                //  user.appendChild(createUserElement(doc, "ListBoats", boat.toString()));
                for (int i = 0; i < boat.size(); i++) {
                    user.appendChild(createUserElement(doc, "ListBoat", boat.get(i).getLabelToBeShown()));
                }
            } catch (NullPointerException e) {
                System.out.println("NULLLL3");
                user.appendChild(createUserElement(doc, "ListBoat", "empty"));
            }

            if(choosenShip!=null)
                user.appendChild(createUserElement(doc, "choosenShip", String.valueOf(choosenShip)) );
            if(choosenFarmer!=null)
                user.appendChild(createUserElement(doc, "choosenFarmer", String.valueOf(choosenFarmer)) );
            if(choosenHerb!=null)
                user.appendChild(createUserElement(doc, "choosenHerb", String.valueOf(choosenHerb)) );
            if(choosenCarn!=null)
                user.appendChild(createUserElement(doc, "choosenCarn", String.valueOf(choosenCarn)) );
            if(choosenPlants!=null)
                user.appendChild(createUserElement(doc, "choosenPlants", String.valueOf(choosenPlants)) );

        }
        else if(type==2){
            try {
                for (int i = 0; i < Right.size(); i++) {
                    user.appendChild(createUserElement(doc, "ListRight",
                            Double.toString(Right.get(i).getWeight())));
                }
            } catch (NullPointerException e) {
                System.out.println("NULLLL1");
            }
            try {
//            user.appendChild(createUserElement(doc, "ListLeft", left.toString()));
                for (int i = 0; i < left.size(); i++) {
                    user.appendChild(createUserElement(doc, "ListLeft",
                            Double.toString(left.get(i).getWeight())));
                }
            } catch (NullPointerException e) {
                System.out.println("NULLLL2");
            }
            try {
                //  user.appendChild(createUserElement(doc, "ListBoats", boat.toString()));
                for (int i = 0; i < boat.size(); i++) {
                    user.appendChild(createUserElement(doc, "ListBoat",
                            Double.toString(boat.get(i).getWeight())));
                }
            } catch (NullPointerException e) {
                System.out.println("NULLLL3");
                user.appendChild(createUserElement(doc, "ListBoat", "empty"));
            }
            if(choosenShip!=null)
                user.appendChild(createUserElement(doc, "choosenShip", String.valueOf(choosenShip)) );
            if(choosenHerb!=null)
                user.appendChild(createUserElement(doc, "choosenHerb", String.valueOf(choosenHerb)) );
        }
        try {
            user.appendChild(createUserElement(doc, "Scores", Integer.toString(scores)));
        }
        catch (NullPointerException e){
            System.out.println("NULLLL4");
        }
       // user.appendChild(createUserElement(doc, "Scores", Integer.toString(scores)));
        if(isLeft) {
            user.appendChild(createUserElement(doc, "ISLEFT","true"));
        }
        else
        {
            user.appendChild(createUserElement(doc, "ISLEFT","false"));
        }


        return user;
    }
    private static Node createUserElement(Document doc, String name,
                                          String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }





//    File file = new File("D:\\Downloads\\RiverCrosser--master\\RiverCrosser--master\\src\\Logic\\file.xml");
//        FileOutputStream fos = new FileOutputStream(new File("D:\\\\Downloads\\\\RiverCrosser--master\\\\RiverCrosser--master\\\\src\\\\Logic\\\\file.xml"));
//        XMLEncoder encode = new XMLEncoder(fos);
//        encode.writeObject(Game);
//        encode.close();
//        fos.close();


}


