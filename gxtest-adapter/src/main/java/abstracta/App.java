package abstracta;

import abstracta.Test;
import abstracta.TestSuite;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class App 
{
    public static void main( String[] args )
    {
        try {
            File                   inputFile = new File("testresults/TestResults.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder         dBuilder = dbFactory.newDocumentBuilder();
            Document               sourceDoc = dBuilder.parse(inputFile);    
        
            sourceDoc.getDocumentElement().normalize();
            Node                 sourceSuite = sourceDoc.getElementsByTagName("TestCase").item(0);
            NodeList          sourceTestList = sourceDoc.getElementsByTagName("Item");
            ArrayList<Test>            tests = new ArrayList<>();

            for (int i = 0; i < sourceTestList.getLength(); i++) {
                String  browser = ((Element) sourceSuite).getElementsByTagName("Browser").item(0).getTextContent();
                Node       node = sourceTestList.item(i);
                Element element = (Element) node;
                Test       test;
                String   status = element.getElementsByTagName("Result").item(0).getTextContent();
                
                if(! status.equalsIgnoreCase("OK")){
                    status = "failed";
                } 
                else{
                    status = "passed";
                }
                
                if (i == 0) {
                    test = new Test(
                                0,
                                Integer.parseInt(element.getElementsByTagName("Elapsed").item(0).getTextContent()),
                                element.getElementsByTagName("Name").item(0).getTextContent(),
                                element.getElementsByTagName("NameAndParams").item(0).getTextContent(),
                                status,
                                browser);    
                }
                else{
                    test = new Test(
                                0,
                                Integer.parseInt(element.getElementsByTagName("Elapsed").item(0).getTextContent()),
                                element.getElementsByTagName("Name").item(0).getTextContent(),
                                element.getElementsByTagName("NameAndParams").item(0).getTextContent(),
                                status,
                                browser);
                }
                
                tests.add(test);
            }

            Element testSuiteElement = (Element) sourceSuite;
            TestSuite testSuite = new TestSuite(
                0,
                Integer.parseInt(testSuiteElement.getElementsByTagName("ElapsedTime").item(0).getTextContent()),
                testSuiteElement.getElementsByTagName("Name").item(0).getTextContent(),
                tests);

        } catch (Exception e) {
            //TODO: handle exception
        }
    
        try {
            // File inputFile = new File("testresults/TestResults.xml");
            // DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Document doc = dBuilder.parse(inputFile);
            // doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            //NodeList nList = doc.getElementsByTagName("Item");
            // System.out.println("----------------------------");
            // System.out.println("there are " + nList.getLength() + " tests in the document");


            // DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Document newDoc = dBuilder.newDocument();

            // Element rootElement = newDoc.createElement("ns2:test-suite");
            // newDoc.appendChild(rootElement);
            // Attr attr = newDoc.createAttribute("xmlns:ns2");
            // attr.setValue("urn:model.allure.qatools.yandex.ru");
            // rootElement.setAttributeNode(attr);
            // Attr startAttr = newDoc.createAttribute("start");
            // startAttr.setValue("1000");
            // rootElement.setAttributeNode(startAttr);
            // Attr stopAttr = newDoc.createAttribute("stop");
            // stopAttr.setValue("1050");
            // rootElement.setAttributeNode(stopAttr);
            // Element suiteNameElement = newDoc.createElement("name");
            // suiteNameElement.appendChild(newDoc.createTextNode("A name for the test"));
            // rootElement.appendChild(suiteNameElement);
            // Element suiteTitleElement = newDoc.createElement("title");
            // suiteTitleElement.appendChild(newDoc.createTextNode("A title for the test"));
            // rootElement.appendChild(suiteTitleElement);

            // Element testCasesElement = newDoc.createElement("test-cases");
            // rootElement.appendChild(testCasesElement);


            // Element testCaseElement = newDoc.createElement("test-case");
            // testCasesElement.appendChild(testCaseElement);
            // Attr startAttr1 = newDoc.createAttribute("start");
            // startAttr1.setValue("1000");
            // testCaseElement.setAttributeNode(startAttr1);
            // Attr statusAttr = newDoc.createAttribute("status");
            // statusAttr.setValue("passed");
            // testCaseElement.setAttributeNode(statusAttr);
            // Attr stopAttr1 = newDoc.createAttribute("stop");
            // stopAttr1.setValue("1050");
            // testCaseElement.setAttributeNode(stopAttr1);
            // Element nameElement = newDoc.createElement("name");
            // nameElement.appendChild(newDoc.createTextNode("A name for the test"));
            // testCaseElement.appendChild(nameElement);
            // Element titleElement = newDoc.createElement("title");
            // titleElement.appendChild(newDoc.createTextNode("A title for the test"));
            // testCaseElement.appendChild(titleElement);

            // Element labelsElement = newDoc.createElement("labels");
            // testCaseElement.appendChild(labelsElement);

            // Element paramsElement = newDoc.createElement("parameters");
            // testCaseElement.appendChild(paramsElement);
            // Element browserParamElement = newDoc.createElement("parameter");
            // Attr kindAttr = newDoc.createAttribute("kind");
            // kindAttr.setValue("argument");
            // browserParamElement.setAttributeNode(kindAttr);
            // Attr nameAttr = newDoc.createAttribute("name");
            // nameAttr.setValue("browser");
            // browserParamElement.setAttributeNode(nameAttr);
            // Attr valueAttr = newDoc.createAttribute("value");
            // valueAttr.setValue("chrome-87.0.4280.141");
            // browserParamElement.setAttributeNode(valueAttr);
            // paramsElement.appendChild(browserParamElement);

            // Element stepsElement = newDoc.createElement("steps");
            // testCaseElement.appendChild(stepsElement);

            // Element attachmentsElement = newDoc.createElement("attachments");
            // testCaseElement.appendChild(attachmentsElement);

            // TransformerFactory transformerFactory = TransformerFactory.newInstance();
            // Transformer transformer = transformerFactory.newTransformer();
            // DOMSource source = new DOMSource(newDoc);
            // StreamResult result = new StreamResult(new File("testresults/1234-testsuite.xml"));
            // transformer.transform(source, result);

            // StreamResult consoleResult = new StreamResult(System.out);
            // transformer.transform(source, consoleResult);  
        } catch (Exception e) {
        }

    }

}