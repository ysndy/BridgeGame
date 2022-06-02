package ui;

import domain.*;
import domain.object.Player;
import domain.object.cell.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GUI extends View {

    class GUIButton extends JButton {

        GUIButton(String name) {
            super(name);
            addActionListener(e -> {
                String command = commandField.getText();
                commandField.setText(command + getText());
            });
        }

    }

    class InfoPanel extends JPanel {

        int playerNumber;
        JLabel pNumber;
        JLabel[] itemCnt;
        JLabel bridgeCardCnt;
        JLabel score;

        InfoPanel(int playerNumber) {
            this.playerNumber = playerNumber;
            setLayout(new GridLayout(2 + model.getPlayers()[playerNumber].getCards().length, 2));
            pNumber = new JLabel("" + playerNumber);
            pNumber.setHorizontalAlignment(SwingConstants.LEFT);
            itemCnt = new JLabel[model.getPlayers()[playerNumber].getCards().length];
            bridgeCardCnt = new JLabel();
            bridgeCardCnt.setHorizontalAlignment(SwingConstants.CENTER);
            score = new JLabel();
            score.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel pLabel = new JLabel("P");
            pLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(pLabel);
            add(pNumber);
            for (int i = 0; i < model.getPlayers()[playerNumber].getCards().length; i++) {
                JLabel itemInfo = new JLabel(new ImageIcon(Data.IMAGESPATH + model.getPlayers()[playerNumber].getCards()[i].getName() + ".png"));
                itemInfo.setBackground(Data.CARDCOLOR[i]);
                itemInfo.setOpaque(true);
                add(itemInfo);
                add(itemCnt[i] = new JLabel(model.getPlayers()[playerNumber].getCards()[i].getCount() + ""));
                itemCnt[i].setHorizontalAlignment(SwingConstants.CENTER);
            }
            JLabel bridgeInfo = new JLabel(new ImageIcon(Data.IMAGESPATH + "Bridge.png"));
            bridgeInfo.setBackground(Data.BridgeColor);
            bridgeInfo.setOpaque(true);
            add(bridgeInfo);
            add(bridgeCardCnt);
            //add(new JLabel("SCORE"));
            //add(score);

        }

        public void update() {
            for (int i = 0; i < model.getPlayers()[playerNumber].getCards().length; i++) {
                itemCnt[i].setText(model.getPlayers()[playerNumber].getCards()[i].getCount() + "");
            }
            bridgeCardCnt.setText(model.getPlayers()[playerNumber].getBridgeCardCnt() + "");
            //score.setText(model.getPlayers()[playerNumber].getScore()+"");

            for (int i = 0; i < pInfo.length; i++) {
                pInfo[i].setBorder(new LineBorder(Color.black));
                if (i == model.getTurn().getPlayer().getNumber()) pInfo[i].setBorder(new LineBorder(Color.red));
            }

        }


    }

    JPanel centerBoardPanel;

    JPanel playerInfoPanel;
    InfoPanel pInfo[];

    JTextField commandField;
    JLabel help;
    JLabel warning;
    JButton inputBtn;

    AtomicBoolean isClicked = new AtomicBoolean(false);

    public GUI(Model model, Controller controller) {
        super(model, controller);
        JFrame frame = new JFrame("Bridge Game");
        frame.setLayout(new BorderLayout());


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        centerBoardPanel = new JPanel();
        centerBoardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Cell[][] map = model.getBoard().getMap();
        centerBoardPanel.setLayout(new GridLayout(map.length, map[0].length));

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                cellLabel label = new cellLabel();
                label.setBorder(new LineBorder(Color.black));
                label.setHorizontalAlignment(SwingConstants.CENTER);

                if (map[i][j] instanceof StartCell) {
                    label.setBackground(new Color(248, 213, 92));
                    label.setText("START");
                } else if (map[i][j] instanceof BridgeCell) {
                    label.setBackground(Data.BridgeColor);
                    label.setIcon(new ImageIcon(Data.IMAGESPATH + "Bridge.png"));
                } else if (map[i][j] instanceof BridgeEndCell) {
                    label.setBackground(new Color(250, 227, 154));
                } else if (map[i][j] instanceof BridgeStartCell) {
                    label.setBackground(new Color(174, 48, 51));
                    label.setIcon(new ImageIcon(Data.IMAGESPATH + "milestone.png"));
                } else if (map[i][j] instanceof EndCell) {
                    label.setBackground(new Color(248, 213, 92));
                    label.setText("END");
                } else if (map[i][j] instanceof ToolCell) {
                    for (int c = 0; c < Data.CARDNAME.length; c++) {
                        if (((ToolCell) map[i][j]).getCard().getName().equals(Data.CARDNAME[c]))
                            label.setBackground(Data.CARDCOLOR[c]);
                    }
                    label.setIcon(new ImageIcon(Data.IMAGESPATH + ((ToolCell) map[i][j]).getCard().getName() + ".png"));
                } else if (map[i][j] instanceof DefaultCell) {
                    label.setBackground(new Color(250, 227, 154));
                } else {
                    label.setBorder(null);
                }

                centerBoardPanel.add(label);

            }
        }


        JPanel centerControlPanel = new JPanel();
        //centerControlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JPanel ccp_btnBox = new JPanel();
        ccp_btnBox.setLayout(new GridLayout(3, 3));
        GUIButton upBtn = new GUIButton("U");
        GUIButton leftBtn = new GUIButton("L");
        GUIButton rightBtn = new GUIButton("R");
        GUIButton downBtn = new GUIButton("D");
        GUIButton stayBtn = new GUIButton("S");
        GUIButton goBtn = new GUIButton("G");

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

        pInfo = new InfoPanel[model.getPlayers().length];
        for (int i = 0; i < model.getPlayers().length; i++) {
            pInfo[i] = new InfoPanel(model.getPlayers()[i].getNumber());
            pInfo[i].setBackground(Color.white);
            playerInfoPanel.add(pInfo[i]);
        }


        JPanel communicationBar = new JPanel();
        communicationBar.setLayout(new GridLayout(3, 1));
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
        inputBtn = new JButton("Enter");
        inputBtn.addActionListener(e -> {
            isClicked.set(true);
        });

        JButton backBtn = new JButton("<");
        backBtn.addActionListener(e -> {
            String str = commandField.getText();
            commandField.setText(str.substring(0, str.length() - 1));
        });

        cb3.add(commandField);
        cb3.add(inputBtn);
        cb3.add(backBtn);
        communicationBar.add(cb3);

        frame.add(playerInfoPanel, BorderLayout.EAST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(communicationBar, BorderLayout.SOUTH);
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    protected void print_playerInfo(Player[] players) {
        for (int i = 0; i < pInfo.length; i++) {
            pInfo[i].update();
        }
    }

    @Override
    protected void print_turnInfo() {

    }

    @Override
    protected void print_score(Player[] players) {

        for (int i = 0; i < pInfo.length; i++) {
            pInfo[i].removeAll();
            pInfo[i].setLayout(new GridLayout(2, 1));
            pInfo[i].add(new JLabel("P" + i));
            pInfo[i].add(new JLabel("SCORE:"+players[i].getScore()));
        }

    }

    @Override
    protected void print_map() {
        //맵 세팅
        for (int i = 0; i < model.getBoard().getMap().length; i++) {
            for (int j = 0; j < model.getBoard().getMap()[i].length; j++) {
                ((cellLabel) centerBoardPanel.getComponent(i * model.getBoard().getMap()[i].length + j)).offPlayerUI();
                if (model.getBoard().getMap()[i][j] != null)
                    ((cellLabel) centerBoardPanel.getComponent(i * model.getBoard().getMap()[i].length + j)).onPlayerUI(model.getBoard().getMap()[i][j].playerNumberOfPieces());
            }
        }
    }

    @Override
    protected void print_helpMsg() {
        help.setText(model.getHelpMsg());
    }

    @Override
    protected void print_warningMsg() {
        warning.setText(model.getWarningMsg());
    }

    @Override
    protected void print_end() {
        commandField.setText("");
    }

    @Override
    public Integer input_playerNumber_logic() {
        return 0;
    }

    @Override
    public Character input_gameMode_logic() {
        return '0';
    }

    @Override
    public Character input_turnMethod_logic() {
        Character input = null;
        while (true) {
            if (isClicked.get()) {
                isClicked.set(false);
                input = commandField.getText().charAt(0);
                return input;
            }
        }
    }

    @Override
    public String input_command_logic() {
        String command = null;
        while (true) {
            if (isClicked.get()) {
                isClicked.set(false);
                command = commandField.getText();
                return command;
            }
        }
    }

    @Override
    public Integer input_mapNumber_logic(int mapNumber) {
        return 0;
    }

    class cellLabel extends JLabel {

        cellLabel() {
            setLayout(new GridLayout(2, 2));
            setOpaque(true);
        }

        public void onPlayerUI(boolean[] playerChecked) {
            for (int i = 0; i < playerChecked.length; i++) {
                if (playerChecked[i]) {
                    JLabel playerUI = new JLabel();
                    playerUI.setText(""+ i);
                    playerUI.setIcon(new ImageIcon(Data.IMAGESPATH+"player.png"));
                    playerUI.setIconTextGap(-19);
                    playerUI.setHorizontalAlignment(JLabel.CENTER);
                    add(playerUI);
                }
            }
        }

        public void offPlayerUI() {
                removeAll();
                repaint();
        }

    }

}
