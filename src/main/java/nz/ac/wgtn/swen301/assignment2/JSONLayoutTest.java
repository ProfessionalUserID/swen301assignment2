package nz.ac.wgtn.swen301.assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class JSONLayoutTest {

  @Test
  public void test_JSONLayout1(){
    Logger logger = Logger.getLogger(JSONLayout.class.toString());
    long startTime = System.currentTimeMillis();
    String message = "JSONLayout test one";

    LoggingEvent event = new LoggingEvent(this.getClass().toString(),
        logger,
        startTime,
        Level.WARN,
        message,
        Thread.currentThread().getName(),
        null,null,null,null);


    JSONLayout l = new JSONLayout();
    ObjectMapper objM = new ObjectMapper();
    JsonNode node = null;
    try {
      node = objM.readTree(l.format(event));
    } catch (JsonProcessingException e){
      e.printStackTrace();
      fail("JSON node not valid");
    }

    assertEquals(node.get("logger").toString(), "\"class nz.ac.wgtn.swen301.assignment2.JSONLayout\"");
    assertEquals(node.get("level").toString(), "\"WARN\"");
    assertEquals(node.get("starttime").toString(), "\""+startTime+"\"");
    assertEquals(node.get("thread").toString(), "\"main\"");
    assertEquals(node.get("message").toString(), "\"JSONLayout test one\"");


  }

  @Test
  public void test_JSONLayout2(){
    Logger logger = Logger.getLogger(JSONLayout.class.toString()+"foo");
    long startTime = System.currentTimeMillis();
    String message = "JSONLayout test two";

    LoggingEvent event = new LoggingEvent(this.getClass().toString(),
        logger,
        startTime,
        Level.WARN,
        message,
        Thread.currentThread().getName(),
        null,null,null,null);


    JSONLayout l = new JSONLayout();
    ObjectMapper objM = new ObjectMapper();
    JsonNode node = null;
    try {
      node = objM.readTree(l.format(event));
    } catch (JsonProcessingException e){
      e.printStackTrace();
      fail("Test failed");
    }

    assertNotEquals(node.get("logger").toString(), "\"class nz.ac.wgtn.swen301.assignment2.JSONLayout\"");
    assertEquals(node.get("level").toString(), "\"WARN\"");
    assertEquals(node.get("starttime").toString(), "\""+startTime+"\"");
    assertEquals(node.get("thread").toString(), "\"main\"");
    assertEquals(node.get("message").toString(), "\"JSONLayout test two\"");


  }

  @Test
  public void test_JSONLayout3(){
    Logger logger = Logger.getLogger(JSONLayout.class.toString());
    long startTime = System.currentTimeMillis();
    String message = "JSONLayout test three";

    LoggingEvent event = new LoggingEvent(this.getClass().toString(),
        logger,
        startTime,
        Level.ALL,
        message,
        Thread.currentThread().getName(),
        null,null,null,null);


    JSONLayout l = new JSONLayout();
    ObjectMapper objM = new ObjectMapper();
    JsonNode node = null;
    try {
      node = objM.readTree(l.format(event));
    } catch (JsonProcessingException e){
      e.printStackTrace();
      fail("Test failed");
    }

    assertEquals(node.get("logger").toString(), "\"class nz.ac.wgtn.swen301.assignment2.JSONLayout\"");
    assertEquals(node.get("level").toString(), "\"ALL\"");
    assertEquals(node.get("starttime").toString(), "\""+startTime+"\"");
    assertEquals(node.get("thread").toString(), "\"main\"");
    assertEquals(node.get("message").toString(), "\"JSONLayout test three\"");


  }

}
