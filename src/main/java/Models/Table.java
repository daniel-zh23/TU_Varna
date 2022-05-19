package Models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="table")
public class Table {

    @XmlElement(name="row")
    private String[][] list;

    public Table(){
    list = new String[4][5];
    }

    public void editCell(int row, int col, String data){
        this.list[row - 1][col - 1] = data;
    }

    private double getCellData(String formula) {
        String[] data = formula.split("");
        try{
            int row = Integer.parseInt(data[1]) - 1;
            int col = Integer.parseInt(data[3]) - 1;
            return Double.parseDouble(this.list[row][col]);
        }
        catch(Exception e){
            return 0;
        }
    }

    private String calculateFormula(String formula){
        try {
            String[] formulaComponents = formula.split(" ");
            double a = 0;
            double b = 0;
            if (formulaComponents[1].contains("R")){
                a = getCellData(formulaComponents[1]);
            } else {
                a = Double.parseDouble(formulaComponents[1]);
            }
            if (formulaComponents[3].contains("R")){
                b = getCellData(formulaComponents[3]);
            }
            else {
                b = Double.parseDouble(formulaComponents[3]);
            }
            double result = switch (formulaComponents[2]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;
                case "^" -> Math.pow(a, b);
                default -> 0;
            };
            return result+"";
        }
        catch (Exception e) {
            return "ERROR";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var row: this.list) {
            sb.append("|");
            for (var cell : row) {
                String cellData;
                if (cell.contains("=")){
                    cellData = calculateFormula(cell);
                }
                else {
                    cellData = cell;
                }
                sb.append(String.format(" %s |", cellData));
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}
