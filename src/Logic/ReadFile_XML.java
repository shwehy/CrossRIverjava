package Logic;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public class ReadFile_XML {

    SaveModel Model;
    int type;
    public void Read() throws IOException, SAXException, ParserConfigurationException {
        File xmlFile = new File("src/Logic/file.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();


        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("user1");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("STORYCASE");
                if (Integer.valueOf(uid)==1){
                    this.type =1;
                }
                else
                    this.type =2;

                NodeList right = elem.getElementsByTagName("ListRight");
                for (int j=0;j<right.getLength();j++){
                    System.out.println("right is : "+right.item(j).getTextContent());


                }
                NodeList left = elem.getElementsByTagName("ListLeft");
                for (int j=0;j<left.getLength();j++){
                    System.out.println("left is : "+left.item(j).getTextContent());
                }
                NodeList boat = elem.getElementsByTagName("ListBoat");
                for (int j=0;j<boat.getLength();j++){
                    System.out.println("boat is : "+boat.item(j).getTextContent());
                }


                Node node4 = elem.getElementsByTagName("Scores").item(0);
                String scores = node4.getTextContent();

                System.out.printf("User id: %s%n", uid);

                System.out.printf("Scores: %s%n", scores);

//

            }
        }
    }
}
//    public void Read() throws ParserConfigurationException, IOException, SAXException, InvocationTargetException {
//
//        File xmlFile = new File("RiverCrosser--master/src/Logic/file.xml");
//        BufferedReader br = new BufferedReader(new FileReader("D:\\Downloads\\RiverCrosser--master\\RiverCrosser--master\\src\\Logic\\file.xml"));
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = factory.newDocumentBuilder();
//        Document doc = dBuilder.parse(String.valueOf(br));
//
//        doc.getDocumentElement().normalize();
//
//        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
//
//        NodeList nList = doc.getElementsByTagName("user");
//
//        for (int i = 0; i < nList.getLength(); i++) {
//
//            Node nNode = nList.item(i);
//
//            System.out.println("\nCurrent Element: " + nNode.getNodeName());
//
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                Element elem = (Element) nNode;
//
//                String uid = elem.getAttribute("id");
//
//                Node node1 = elem.getElementsByTagName("ListRight").item(0);
//                String fname = node1.getTextContent();
//
//                Node node2 = elem.getElementsByTagName("ListLeft").item(0);
//                String lname = node2.getTextContent();
//
//                Node node3 = elem.getElementsByTagName("ListBoat").item(0);
//                String occup = node3.getTextContent();
//                Node node4 = elem.getElementsByTagName("Scores").item(0);
//                String scores = node4.getTextContent();
//
//                System.out.printf("User id: %s%n", uid);
//                System.out.printf("ListRight: %s%n", fname);
//                System.out.printf("LIstLeft: %s%n", lname);
//                System.out.printf("Listboat: %s%n", occup);
//                System.out.printf("Scores: %s%n", scores);
//
//
//            }
//        }

//    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    DocumentBuilder dBuilder = factory.newDocumentBuilder();
//    Document doc = dBuilder.parse(xmlFile);
//
//        doc.getDocumentElement().normalize();
//
//        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
//
//    NodeList nList = doc.getElementsByTagName("user");
//
//        for (int i = 0; i < nList.getLength(); i++) {
//
//        Node nNode = nList.item(i);
//
//        System.out.println("\nCurrent Element: " + nNode.getNodeName());
//
//
//    }
//}
