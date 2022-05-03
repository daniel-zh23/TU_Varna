package Core.Contracts;

import Models.Table;

public interface ControllerC {
String open(String fileLocation);
void close();
void saveAs(Table data, String fileLocation);
void save();
}
