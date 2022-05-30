import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class GUI extends View {

    class GUIButton extends JButton {

        GUIButton(String name){
            super(name);
            addActionListener(e -> {
                String command = commandField.getText();
                commandField.setText(command+getText());
            });
        }

    }

    GUIButton stayBtn;
    GUIButton goBtn;
    GUIButton upBtn;
    GUIButton downBtn;
    GUIButton leftBtn;
    GUIButton rightBtn;

    JPanel centerPanel;
    JPanel centerBoardPanel;
    JPanel centerControlPanel;
    JPanel ccp_btnBox;

    JPanel playerInfoPanel;
    JPanel pInfo[];

    JPanel communicationBar;
    JTextField commandField;
    JLabel help;
    JLabel warning;
    JButton inputBtn;

    AtomicBoolean isClicked = new AtomicBoolean(false);

    public GUI(Model model, Controller controller) {
        super(model, controller);
        JFrame frame = new JFrame("Bridge Game");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.white);


        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BorderLayout());


        centerBoardPanel = new JPanel();
        centerBoardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Cell[][] map = model.getBoard().getMap();
        centerBoardPanel.setLayout(new GridLayout(map.length, map[0].length));

        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[i].length; j++){

                cellLabel label = new cellLabel();
                label.setBorder(new LineBorder(Color.black));

                if(map[i][j] instanceof StartCell){
                    label.setBackground(Color.YELLOW);
                }else if(map[i][j] instanceof BridgeCell){
                    label.setBackground(Color.GRAY);
                }else if(map[i][j] instanceof BridgeEndCell){
                    label.setBackground(Color.GRAY);
                }else if(map[i][j] instanceof BridgeStartCell){
                    label.setBackground(Color.GRAY);
                }else if(map[i][j] instanceof EndCell){
                    label.setBackground(Color.YELLOW);
                }else if(map[i][j] instanceof ToolCell){
                    label.setBackground(Color.GREEN);
                }else if(map[i][j] instanceof DefaultCell){
                    label.setBackground(Color.WHITE);
                }else {
                    label.setBorder(null);
                }

                centerBoardPanel.add(label);

            }
        }


        centerControlPanel = new JPanel();
        ccp_btnBox = new JPanel();
        ccp_btnBox.setLayout(new GridLayout(3,3));
        upBtn = new GUIButton("U");
        leftBtn = new GUIButton("L");
        rightBtn = new GUIButton("R");
        downBtn = new GUIButton("D");
        stayBtn = new GUIButton("S");
        goBtn = new GUIButton("G");

        ccp_btnBox.add(new JLabel());
        ccp_btnBox.add(upBtn);
        ccp_btnBox.add(new JLabel());
        ccp_btnBox.add(leftBtn);
        ccp_btnBox.add(new JLabel());
        ccp_btnBox.add(rightBtn);
        ccp_btnBox.add(new JLabel());
        ccp_btnBox.add(downBtn);
        ccp_btnBox.add(new JLabel());

        centerControlPanel.add(ccp_btnBox);
        centerControlPanel.add(goBtn);
        centerControlPanel.add(stayBtn);

        centerPanel.add(centerBoardPanel, BorderLayout.CENTER);
        centerPanel.add(centerControlPanel, BorderLayout.SOUTH);


        playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(model.getPlayers().length, 1));

        communicationBar = new JPanel();
        communicationBar.setLayout(new GridLayout(3,1));
        JPanel cb1 = new JPanel();
        cb1.setLayout(new FlowLayout(FlowLayout.LEFT));
        help = new JLabel();
        cb1.add(new JLabel("도움말 : "));
        cb1.add(help);
        communicationBar.add(cb1);

        JPanel cb2 = new JPanel();
        cb2.setLayout(new FlowLayout(FlowLayout.LEFT));
        warning = new JLabel();
        cb2.add(new JLabel("경고 : "));
        cb2.add(warning);
        communicationBar.add(cb2);

        JPanel cb3 = new JPanel();
        commandField = new JTextField(20);
        inputBtn = new JButton("입력");
        inputBtn.addActionListener(e -> {
            isClicked.set(true);
        });
        cb3.add(commandField);
        cb3.add(inputBtn);
        communicationBar.add(cb3);

        frame.add(playerInfoPanel, BorderLayout.EAST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(communicationBar, BorderLayout.SOUTH);
        frame.setSize(1000, 700);
        frame.setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {

        if(model.getState()==Model.STATE_INIT){

        }else if(model.getState()==Model.STATE_PLAY){

        }else if(model.getState()==Model.STATE_END){

        }
        playerInfoPanel.removeAll();
        pInfo = new JPanel[model.getPlayers().length];
        for(int i=0; i<model.getPlayers().length; i++){
            pInfo[i] = new JPanel();
            pInfo[i].setLayout(new GridLayout(3+model.getPlayers()[i].getCards().length, 1));
            pInfo[i].add(new JLabel(model.getPlayers()[i].getNumber()+""));
            for(int j=0; j<model.getPlayers()[i].getCards().length; j++){
                pInfo[i].add(new JLabel(model.getPlayers()[i].getCards()[j].getName()+": "+model.getPlayers()[i].getCards()[j].getCount()));
            }
            pInfo[i].add(new JLabel("BridgeCard: "+model.getPlayers()[i].getBridgeCardCnt()));
            pInfo[i].add(new JLabel("score: "+model.getPlayers()[i].getScore()));
            pInfo[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            playerInfoPanel.add(pInfo[i]);
        }

        //현재 플레이어 나타냄
        for(int i=0; i<pInfo.length; i++){
            pInfo[i].setBorder(null);
        }
        pInfo[model.getTurn().getPlayer().getNumber()].setBorder(new LineBorder(Color.red));

        //맵 세팅
        for(int i=0; i<model.getBoard().getMap().length; i++){
            for(int j=0; j<model.getBoard().getMap()[i].length; j++){
                ((cellLabel)centerBoardPanel.getComponent(i*model.getBoard().getMap()[i].length+j)).offPlayerUI();
                if(model.getBoard().getMap()[i][j]!=null)((cellLabel)centerBoardPanel.getComponent(i*model.getBoard().getMap()[i].length+j)).onPlayerUI(model.getBoard().getMap()[i][j].playerNumberOfPieces());
            }
        }

        //안내문구 세팅
        help.setText(model.getHelpMsg());
        warning.setText(model.getWarningMsg());
        commandField.setText("");

    }

    @Override
    public int input_playerNumber() {
        return 0;
    }

    @Override
    public char input_gameMode() {
        return '0';
    }

    @Override
    public char input_turnMethod() {
        while(true) {
            while(true){
                if(isClicked.get()) break;
            }
            isClicked.set(false);
            char input = commandField.getText().charAt(0);
            if (input_turnMethod_check(input)) return input;
        }
    }

    @Override
    public String input_command() {

        while(true) {
            while (true) {
                if(isClicked.get()) break;
            }
            isClicked.set(false);
            String command = commandField.getText().toString();
            if (input_command_check(command)) return command;
        }
    }

    @Override
    public int input_mapNumber(int mapNumber) {
        return 0;
    }

    class cellLabel extends JLabel{

        JLabel[] playerUI;

        cellLabel(){

            setLayout(new GridLayout(2, 2));
            setOpaque(true);
            playerUI = new JLabel[Data.PLAYER_MAX];

            for(int i=0; i<playerUI.length; i++){
                playerUI[i] = new JLabel();
                playerUI[i].setHorizontalAlignment(JLabel.CENTER);
                add(playerUI[i]);
            }
        }

        public void onPlayerUI(boolean[] playerChecked){
            for(int i=0; i<playerChecked.length; i++){
                if(playerChecked[i]) playerUI[i].setText(i+"");
            }
        }

        public void offPlayerUI(){
            for(int i=0; i<playerUI.length;i++){
                playerUI[i].setText("");
            }
        }

    }

}
