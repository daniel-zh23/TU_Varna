package Models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.text.DecimalFormat;
import java.util.Arrays;

@XmlRootElement(name="table")
public class Table {

    @XmlElement(name="row")
    private String[][] list;

    public Table(){
    this.list = new String[4][5];
        for (var row: this.list) {
            Arrays.fill(row, "");
        }
    }

    public void editCell(int row, int col, String data){
        this.list[row - 1][col - 1] = data;
    }

    private double getCellData(String formula) { //Gets data from cell address.
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

    private String calculateFormula(String formula){ //Gets formula from caller and calculates it,
        try {                                           // in case of invalid formula or invalid data returns ERROR.
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
            DecimalFormat formater = new DecimalFormat("0.#");
            return formater.format(result);
        }
        catch (Exception e) {
            return "ERROR";
        }
    }

    @Override
    public String toString() {  //When printing all formulas are being calculated.
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
