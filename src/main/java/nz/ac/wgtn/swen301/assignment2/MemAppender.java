package nz.ac.wgtn.swen301.assignment2;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MemAppender extends AppenderSkeleton {

  List<LoggingEvent> logEvents = new ArrayList();
  private String name = "";
  private long discardCount = 0;
  private long maxSize = 1000;

  @Override
  protected void append(LoggingEvent loggingEvent) {
    logEvents.add(loggingEvent);

    while (logEvents.size() > maxSize){
      logEvents.remove(0);
      discardCount++;
    }

  }

  @Override
  public void close() {

  }

  public String getMemName(){
    return name;
  }

  public void setMemName(String name){
    this.name = name;
  }

  @Override
  public boolean requiresLayout() {
    return false;
  }

  public List<LoggingEvent> getCurrentLogs(){
    return new ArrayList<>(logEvents);
  }

  public long getDiscardedLogCount(){
    return discardCount;
  }

  public void exportToJSON(String fileName){
    JSONLayout l = new JSONLayout();
    StringBuilder s = new StringBuilder();

    for (LoggingEvent x : logEvents){
      s.append(l.format(x));
    }

    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      writer.write(s.toString());
      writer.close();
    } catch (Exception ignored) {}


  }

}
