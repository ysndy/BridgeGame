import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    private Cell[][] map;

    public void setMap(String mapName){
        map = new MapReader(mapName).getMap();
    }

    class MapReader {

        private Cell[][] map;

        MapReader(String mapName){

            try {
                Scanner scanner = new Scanner(new File("./map/"+mapName));

                ArrayList<ArrayList<Cell>> board = new ArrayList<>();
                // 시작 셀
                int x=0, y=0, x_max=0;
                Cell cell = null;
                board.add(new ArrayList<Cell>());
                board.get(0).add(new StartCell('.', 'S'));
                String startStr = scanner.nextLine();

                switch (getNext(startStr)){

                    case 'U': y--; break;
                    case 'D': y++; break;
                    case 'R': x++; break;

                }

                while (scanner.hasNextLine()) {

                    String str = scanner.nextLine();

                    switch (getType(str)) {

                        case 'C': cell = new DefaultCell(getBack(str), getType(str)); break;
                        case 'B': cell = new BridgeStartCell(getBack(str), getType(str)); break;
                        case 'b': cell = new BridgeEndCell(getBack(str), getType(str)); break;
                        case 'H': cell = new ToolCell(new Card("Hammer", 2), getBack(str), getType(str)); break;
                        case 'S': cell = new ToolCell(new Card("Saw", 3), getBack(str), getType(str)); break;
                        case 'P': cell = new ToolCell(new Card("Philips Driver", 1), getBack(str), getType(str)); break;
                        case 'E': cell = new EndCell('.', getType(str)); break;

                    }

                    if(y<0){
                        board.add(0, new ArrayList<Cell>());
                        y=0;

                    }else if(y==board.size()) {
                        board.add(new ArrayList<Cell>());

                    }else {

                    }
                    try {
                        board.get(y).add(x, cell);
                    }catch (IndexOutOfBoundsException e){
                        for(int i=board.get(y).size(); i<x; i++){
                            board.get(y).add(null);
                        }
                        board.get(y).add(x, cell);
                    }

                    //System.out.println("y="+y+", x="+x);
                    if(getType(str)=='B') board.get(y).add(new BridgeCell(getBack(str), getType(str)));
                    switch (getNext(str)){

                        case 'U': y--; break;
                        case 'D': y++; break;
                        case 'R': x++; break;
                        case 'E': x_max = x; continue;

                    }

                }

                this.map = new Cell[board.size()][x_max+1];

                for(int i=0; i<board.size();i++){

                    for(int j=0; j<board.get(i).size();j++){
                        this.map[i][j] = board.get(i).get(j);
                        if(this.map[i][j]!=null) this.map[i][j].setPosition(new Position(j, i));
                    }

                }


            }catch(FileNotFoundException e){
                System.out.println("파일 없음");
            }



        }

        private char getNext(String str){
            String[] tmp = str.split(" ");
            return tmp[tmp.length-1].charAt(0);
        }

        private char getType(String str){
            String[] tmp = str.split(" ");
            return tmp[0].charAt(0);
        }

        private char getBack(String str){
            String[] tmp = str.split(" ");
            return tmp[1].charAt(0);
        }

        public Cell[][] getMap(){
            return map;
        }

    }

    public Cell[][] getMap(){
        return map;
    }

    public Cell getStartCell(){
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j< map[i].length; j++){
                if(map[i][j] instanceof StartCell) return map[i][j];
            }
        }
        return null;
    }

    public Cell getPieceCell(Piece piece){
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j< map[i].length; j++){
                if(map[i][j] != null) {
                    ArrayList<Piece> pieces = map[i][j].getPieces();

                    for(int k=0; k<pieces.size(); k++){
                        if(pieces.get(k).equals(piece)) return map[i][j];
                    }

                }
            }

        }
        return null;
    }

    public Cell getRightCell(Cell cell){
        try {
            return map[cell.getPosition().getY()][cell.getPosition().getX() + 1];
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public Cell getUpCell(Cell cell){
        try {
            return map[cell.getPosition().getY() - 1][cell.getPosition().getX()];
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public Cell getLeftCell(Cell cell){
        try {
            return map[cell.getPosition().getY()][cell.getPosition().getX() - 1];
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public Cell getDownCell(Cell cell){
        try {
            return map[cell.getPosition().getY() + 1][cell.getPosition().getX()];
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public void moveAPiece(Piece piece, String command){

        Cell cell = getPieceCell(piece);

        char[] chars = command.toCharArray();
        cell.leave(piece);

        for (char c : chars) {

            switch (c) {

                case 'U':
                    cell = getUpCell(cell);
                    break;
                case 'D':
                    cell = getDownCell(cell);
                    break;
                case 'R':
                    cell = getRightCell(cell);
                    break;
                case 'L':
                    cell = getLeftCell(cell);
                    break;

            }

            if (cell instanceof Passing) ((Passing) cell).pass(piece);
            if (cell instanceof EndCell) break;
        }

        //5. 도착
        cell.arrive(piece);
        if (cell instanceof On) ((On) cell).on(piece);

    }

}
