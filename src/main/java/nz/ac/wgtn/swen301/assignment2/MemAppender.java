package nz.ac.wgtn.swen301.assignment2;

import java.util.ArrayList;
import java.util.List;

public class MemAppender {

  List<String> logEvents = new ArrayList();
  private String name = "";

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public List<String> getCurrentLogs(){

  }
}
