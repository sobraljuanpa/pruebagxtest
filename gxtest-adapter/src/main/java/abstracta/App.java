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
                
                Test       test = new Test();
                
                String   status = element.getElementsByTagName("Result").item(0).getTextContent();
                int     elapsed = Integer.parseInt(element.getElementsByTagName("Elapsed").item(0).getTextContent());
                String     name = element.getElementsByTagName("Name").item(0).getTextContent();
                String    title = element.getElementsByTagName("NameAndParams").item(0).getTextContent();
                 
                if(! status.equalsIgnoreCase("OK")){
                    status = "failed";
                } 
                else{
                    status = "passed";
                }

                test.setStatus(status);
                test.setStart(0);
                test.setStop(elapsed);
                test.setName(name);
                test.setTitle(title);
                test.setBrowser(browser);
                
                System.out.println(test.toString());
                tests.add(test);
            }

            Element testSuiteElement = (Element) sourceSuite;
            TestSuite testSuite = new TestSuite(
                0,
                Integer.parseInt(testSuiteElement.getElementsByTagName("ElapsedTime").item(0).getTextContent()),
                testSuiteElement.getElementsByTagName("Name").item(0).getTextContent(),
                tests);

            DocumentBuilder auxDocBuilder = dbFactory.newDocumentBuilder();
            Document               output = auxDocBuilder.newDocument();

            Element rootElement = output.createElement("ns2:test-suite");
            output.appendChild(rootElement);
            Attr attr = output.createAttribute("xmlns:ns2");
            attr.setValue("urn:model.allure.qatools.yandex.ru");
            rootElement.setAttributeNode(attr);
            Attr startAttr = output.createAttribute("start");
            startAttr.setValue(String.valueOf(testSuite.getStart()));
            rootElement.setAttributeNode(startAttr);
            Attr stopAttr = output.createAttribute("stop");
            stopAttr.setValue(String.valueOf(testSuite.getStop()));
            rootElement.setAttributeNode(stopAttr);
            Element suiteNameElement = output.createElement("name");
            suiteNameElement.appendChild(output.createTextNode(testSuite.getName()));
            rootElement.appendChild(suiteNameElement);
            Element suiteTitleElement = output.createElement("title");
            suiteTitleElement.appendChild(output.createTextNode("A title for the test"));
            rootElement.appendChild(suiteTitleElement);

            Element testCasesElement = output.createElement("test-cases");
            rootElement.appendChild(testCasesElement);

            for (int i = 0; i < testSuite.getTests().size(); i++) {
                Test auxTest = tests.get(i);

                Element testCaseElement = output.createElement("test-case");
                testCasesElement.appendChild(testCaseElement);
                Attr startAttr1 = output.createAttribute("start");
                startAttr1.setValue(String.valueOf(auxTest.getStart()));
                testCaseElement.setAttributeNode(startAttr1);
                Attr statusAttr = output.createAttribute("status");
                statusAttr.setValue(auxTest.getStatus());
                testCaseElement.setAttributeNode(statusAttr);
                Attr stopAttr1 = output.createAttribute("stop");
                stopAttr1.setValue(String.valueOf(auxTest.getStop()));
                testCaseElement.setAttributeNode(stopAttr1);
                Element nameElement = output.createElement("name");
                nameElement.appendChild(output.createTextNode(auxTest.getName()));
                testCaseElement.appendChild(nameElement);
                Element titleElement = output.createElement("title");
                titleElement.appendChild(output.createTextNode(auxTest.getTitle()));
                testCaseElement.appendChild(titleElement);

                Element labelsElement = output.createElement("labels");
                testCaseElement.appendChild(labelsElement);

                Element paramsElement = output.createElement("parameters");
                testCaseElement.appendChild(paramsElement);
                Element browserParamElement = output.createElement("parameter");
                Attr kindAttr = output.createAttribute("kind");
                kindAttr.setValue("argument");
                browserParamElement.setAttributeNode(kindAttr);
                Attr nameAttr = output.createAttribute("name");
                nameAttr.setValue("browser");
                browserParamElement.setAttributeNode(nameAttr);
                Attr valueAttr = output.createAttribute("value");
                valueAttr.setValue(auxTest.getBrowser());
                browserParamElement.setAttributeNode(valueAttr);
                paramsElement.appendChild(browserParamElement);

                Element stepsElement = output.createElement("steps");
                testCaseElement.appendChild(stepsElement);

                Element attachmentsElement = output.createElement("attachments");
                testCaseElement.appendChild(attachmentsElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(output);
            StreamResult result = new StreamResult(new File("testresults/12346-testsuite.xml"));
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
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