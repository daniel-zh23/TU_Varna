package Models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Arrays;

@XmlRootElement(name="neshtoSi")
public class Table {
    private String[][] list;

    public Table(){
    list = new String[4][5];
    }

    @XmlElement(name="neshto")
    public String[][] getList() {
        return list;
    }

    @Override
    public String toString() {
        //TODO: Check if works
        StringBuilder sb = new StringBuilder();
        for (var row: this.list) {
            sb.append(String.format("| %s |\n", String.join(" | ", row)));
        }
        return sb.toString().trim();
    }
}
