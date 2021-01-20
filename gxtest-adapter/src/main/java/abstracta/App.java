package abstracta;

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
        
        System.out.println( "Hello World!" );

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


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document newDoc = dBuilder.newDocument();

            Element rootElement = newDoc.createElement("ns2:test-suite");
            newDoc.appendChild(rootElement);
            Attr attr = newDoc.createAttribute("xmlns:ns2");
            attr.setValue("urn:model.allure.qatools.yandex.ru");
            rootElement.setAttributeNode(attr);
            Attr startAttr = newDoc.createAttribute("start");
            startAttr.setValue("1000");
            rootElement.setAttributeNode(startAttr);
            Attr stopAttr = newDoc.createAttribute("stop");
            stopAttr.setValue("1050");
            rootElement.setAttributeNode(stopAttr);
            Element suiteNameElement = newDoc.createElement("name");
            suiteNameElement.appendChild(newDoc.createTextNode("A name for the test"));
            rootElement.appendChild(suiteNameElement);
            Element suiteTitleElement = newDoc.createElement("title");
            suiteTitleElement.appendChild(newDoc.createTextNode("A title for the test"));
            rootElement.appendChild(suiteTitleElement);

            Element testCasesElement = newDoc.createElement("test-cases");
            rootElement.appendChild(testCasesElement);


            Element testCaseElement = newDoc.createElement("test-case");
            testCasesElement.appendChild(testCaseElement);
            Attr startAttr1 = newDoc.createAttribute("start");
            startAttr1.setValue("1000");
            testCaseElement.setAttributeNode(startAttr1);
            Attr statusAttr = newDoc.createAttribute("status");
            statusAttr.setValue("passed");
            testCaseElement.setAttributeNode(statusAttr);
            Attr stopAttr1 = newDoc.createAttribute("stop");
            stopAttr1.setValue("1050");
            testCaseElement.setAttributeNode(stopAttr1);
            Element nameElement = newDoc.createElement("name");
            nameElement.appendChild(newDoc.createTextNode("A name for the test"));
            testCaseElement.appendChild(nameElement);
            Element titleElement = newDoc.createElement("title");
            titleElement.appendChild(newDoc.createTextNode("A title for the test"));
            testCaseElement.appendChild(titleElement);

            Element labelsElement = newDoc.createElement("labels");
            testCaseElement.appendChild(labelsElement);
            // Element languageLabelElement = newDoc.createElement("label");
            // Attr nameAttr1 = newDoc.createAttribute("name");
            // nameAttr1.setValue("language");
            // languageLabelElement.setAttributeNode(nameAttr1);
            // Attr valueAttr1 = newDoc.createAttribute("value");
            // valueAttr1.setValue("javascript");
            // languageLabelElement.setAttributeNode(valueAttr1);
            // labelsElement.appendChild(languageLabelElement);
            // Element frameworkLabelElement = newDoc.createElement("label");
            // Attr nameAttr2 = newDoc.createAttribute("name");
            // nameAttr2.setValue("framework");
            // frameworkLabelElement.setAttributeNode(nameAttr2);
            // Attr valueAttr2 = newDoc.createAttribute("value");
            // valueAttr2.setValue("wdio");
            // frameworkLabelElement.setAttributeNode(valueAttr2);
            // labelsElement.appendChild(frameworkLabelElement);
            // Element threadLabelElement = newDoc.createElement("label");
            // Attr nameAttr3 = newDoc.createAttribute("name");
            // nameAttr3.setValue("thread");
            // threadLabelElement.setAttributeNode(nameAttr3);
            // Attr valueAttr3 = newDoc.createAttribute("value");
            // valueAttr3.setValue("0-0");
            // threadLabelElement.setAttributeNode(valueAttr3);
            // labelsElement.appendChild(threadLabelElement);

            Element paramsElement = newDoc.createElement("parameters");
            testCaseElement.appendChild(paramsElement);
            Element browserParamElement = newDoc.createElement("parameter");
            Attr kindAttr = newDoc.createAttribute("kind");
            kindAttr.setValue("argument");
            browserParamElement.setAttributeNode(kindAttr);
            Attr nameAttr = newDoc.createAttribute("name");
            nameAttr.setValue("browser");
            browserParamElement.setAttributeNode(nameAttr);
            Attr valueAttr = newDoc.createAttribute("value");
            valueAttr.setValue("chrome-87.0.4280.141");
            browserParamElement.setAttributeNode(valueAttr);
            paramsElement.appendChild(browserParamElement);

            Element stepsElement = newDoc.createElement("steps");
            testCaseElement.appendChild(stepsElement);

            Element attachmentsElement = newDoc.createElement("attachments");
            testCaseElement.appendChild(attachmentsElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("testresults/1234-testsuite.xml"));
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);  
        } catch (Exception e) {
            // <test-case start='1610983306771' status='passed' stop='1610983320814'>
            //     <name>"before all" hook</name>
            //     <title>"before all" hook</title>
            //     <labels>
            //         <label name='language' value='javascript'/>
            //         <label name='framework' value='wdio'/>
            //         <label name='thread' value='0-0'/>
            //     </labels>
            //     <parameters>
            //         <parameter kind='argument' name='browser' value='chrome-87.0.4280.141'/>
            //     </parameters>
            //     <steps/>
            //     <attachments/>
            // </test-case>
            //TODO: handle exception
        }
    }

    public class TestSuite {
        private int             start;
        private int             stop;
        private String          name;
        private ArrayList<Test> tests = new ArrayList<>();
    }

    public class Test {
        private int start;
        private int stop;
        private String name;
        private String title;
        private String status;
        private String browser;
    }
}
