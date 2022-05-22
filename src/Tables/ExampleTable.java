package Tables;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExampleTable extends JFrame {
    private JPanel mainPanel;
    private JTable table1;

    class TableData extends AbstractTableModel{


        int[][]allData;

        public TableData(){
            /*    allData[0]=new int[]{1,2,3};
           allData[1]=new int[]{4,5,6};
            allData[2]=new int[]{7,8,9};*/
            loadFile("data.csv");
        }

        void loadFile(String fileName){
            Path file = FileSystems.getDefault().getPath("",fileName);
            try {
                List<String> lines = Files.readAllLines(file);
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String[] lineArray = line.split(",");
                    //initialize allData array
                    if(allData == null){
                        allData = new int[lines.size()][lineArray.length];
                    }

                    for (int j = 0; j < lineArray.length ; j++) {
                        int parsedInt = Integer.parseInt(lineArray[j]);
                        allData[i][j]=parsedInt;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public int getRowCount() {
            return allData.length;
        }

        @Override
        public int getColumnCount() {
            return allData[0].length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return allData[rowIndex][columnIndex];
        }
    }
    public ExampleTable(){
        setContentPane(mainPanel);
        setTitle("Data Tables");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //table props
        table1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table1.setBounds(20,15,410,243);

        //conect with data
        TableData tableData =new TableData();
        table1.setModel(tableData);
    }


}
