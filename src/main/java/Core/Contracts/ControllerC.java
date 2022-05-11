package Core.Contracts;

import Models.Table;

public interface ControllerC {
String open(String fileLocation);
String close();
String saveAs(String fileLocation);
String save();
String print();
String edit(String cell, String data);
}
