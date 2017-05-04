package ru.spbau.devdays.clionvalgrind.parser;

import com.google.protobuf.TextFormat;
import ru.spbau.devdays.clionvalgrind.parser.errors.Error;
import ru.spbau.devdays.clionvalgrind.parser.errors.ErrorNode;
import ru.spbau.devdays.clionvalgrind.parser.errors.ErrorsHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.spbau.devdays.clionvalgrind.parser.exception.ParserException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private static ErrorNode stackNode(Node node, String what) {

        NodeList frames = node.getChildNodes();
        List<String> dirList = new ArrayList<String>();
        List<String> funcList = new ArrayList<String>();
        List<Integer> lineNumber = new ArrayList<Integer>();

        for (int k = 0; k < frames.getLength(); ++k) {
            NodeList frameChild = frames.item(k).getChildNodes();
            for (int l = 0; l < frameChild.getLength(); ++l) {
                Node frameNode = frameChild.item(l);

                if (frameNode.getNodeType() == Node.COMMENT_NODE
                        || frameNode.getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                if (frameNode.getNodeName().equals("obj")) {
                    dirList.add(frameNode.getTextContent());
                    lineNumber.add(-1);
                    continue;
                }
                if (frameNode.getNodeName().equals("fn")) {
                    funcList.add(frameNode.getTextContent());
                    continue;
                }
                if (frameNode.getNodeName().equals("dir")) {
                    dirList.set(dirList.size() - 1, frameNode.getTextContent());
                    continue;
                }
                if (frameNode.getNodeName().equals("file")) {
                    dirList.set(dirList.size() - 1, dirList.get(dirList.size() - 1) + "/"
                            + frameNode.getTextContent());
                    continue;
                }
                if (frameNode.getNodeName().equals("line")) {
                    lineNumber.set(lineNumber.size() - 1, Integer.parseInt(frameNode.getTextContent()));
                }
            }
        }
        return new ErrorNode(what, funcList, dirList, lineNumber);
    }

    public static ErrorsHolder parse(String path) throws ParserException {
        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;
        try {
            db = dbFactory.newDocumentBuilder();
            doc = db.parse(inputFile);
        } catch (ParserConfigurationException e) {
            throw new ParserException("cannot build new DocumentBuilder", e);
        } catch (IOException e) {
            throw new ParserException("cannot read file: " + path, e);
        } catch (SAXException e) {
            try {
                System.out.print(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8).stream().collect(Collectors.joining("\n")));
            }
            catch(IOException ex) {
            }
            throw new ParserException("cannot parse file: " + path, e);
        }
        doc.getDocumentElement().normalize();

        ErrorsHolder errorsHolder = new ErrorsHolder();
        NodeList nList = doc.getElementsByTagName("error");
        for (int i = 0; i < nList.getLength(); ++i) {
            Node errorNode = nList.item(i);
            Error error = new Error();

            NodeList errorList = errorNode.getChildNodes();
            String what = null;
            for (int j = 0; j < errorList.getLength(); ++j) {
                Node errorChild = errorList.item(j);
                if (errorChild.getNodeType() == Node.COMMENT_NODE
                        || errorChild.getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                if (errorChild.getNodeName().equals("kind")) {
                    error.setKind(errorChild.getTextContent());
                    continue;
                }
                if (errorChild.getNodeName().matches(".*?what")) {
                    if (errorChild.getNodeName().equals("xwhat")) {
                        Node child = errorChild.getFirstChild();
                        while (child != errorChild.getLastChild() &&
                                !child.getNodeName().equals("text")) {
                            child = child.getNextSibling();
                        }
                        what = child.getTextContent();
                    } else {
                        what = errorChild.getTextContent();
                    }
                    continue;
                }
                if (errorChild.getNodeName().equals("stack")) {
                    error.add(stackNode(errorChild, what));
                }

            }
            errorsHolder.add(error);
        }
        return errorsHolder;
    }
}
