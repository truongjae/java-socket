/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaintSocket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * * @author thanh
 */
public class PaintForm extends JFrame implements ActionListener{
    private int x=0,y=0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = (int) screenSize.getWidth(),HEIGHT = (int) screenSize.getHeight()-40;
    private JPanel paintPanel = new JPanel();
    private JSlider sizeSlider = new JSlider();
    private static Color color = Color.BLACK;
    private boolean checkDelete = false;
    private Graphics g1;
    private Graphics g2;
    private Client client;
    public PaintForm() throws UnknownHostException, IOException{
        this.setSize(this.WIDTH,this.HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Paint Socket");
        this.createElm();
        this.client = new Client(InetAddress.getLocalHost(),2000);
        this.client.execute();
        drawFromServer();
    }
    public void drawFromServer(){
        Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                       while(true){
                           try{
                               String sms = client.clientReceiveData.getSms();
                               String[] info = sms.split("-");
                               if(info[0].equals("draw")){
                                    int x = Integer.parseInt(info[1]);
                                    int y = Integer.parseInt(info[2]);
                                    int R,G,B;
                                    String RGB = info[3];
                                    int SIZE = Integer.parseInt(info[4]); 
                                    ArrayList<Integer> arr = getColor(RGB);
                                    R = arr.get(0);
                                    G = arr.get(1);
                                    B = arr.get(2);
                                    g2.setColor(new Color(R,G,B));
                                    g2.fillOval(x,y,SIZE,SIZE); 
                               }
                               else if(info[0].equals("delete")){
                                    int x = Integer.parseInt(info[1]);
                                    int y = Integer.parseInt(info[2]);
                                    int SIZE = Integer.parseInt(info[3]); ;
                                    g2.setColor(Color.WHITE);
                                    g2.fillRect(x,y,SIZE,SIZE); 
                               }
                               else if(info[0].equals("clear")){
                                   repaint();
                               }
                                         
                           }
                           catch(Exception ex){
                             g2.setColor(color);
                           }
                       }
                    }  
                });
                t.start();
        
    }
    public void createElm(){ 
        this.paintPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT-100));
        this.paintPanel.setBackground(Color.WHITE);
        this.paintPanel.setVisible(true);
        this.add(this.paintPanel,BorderLayout.SOUTH);
        this.g1 = this.paintPanel.getGraphics();
        this.paintPanel.paintComponents(this.g1);
        this.g2 = this.paintPanel.getGraphics();
        this.paintPanel.paintComponents(this.g2);
        this.paintPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                       int size = sizeSlider.getValue();
                        x = e.getX();
                        y = e.getY();
                        if(checkDelete){
                            client.clientSendData.setSms("delete-"+x+"-"+y+"-"+size);
                            g1.setColor(Color.WHITE);
                            g1.fillRect(x, y,size,size);
                        }
                        else{
                            client.clientSendData.setSms("draw-"+x+"-"+y+"-"+color.toString()+"-"+size);
                            g1.setColor(color);
                            g1.fillOval(x, y,size ,size);
                        }
                    }   
                });
                t.start();     
            }  
        });
        
        // label size
        JLabel labelSize = new JLabel();
        labelSize.setText("Kích Thước");
        labelSize.setSize(100,30);
        labelSize.setLocation(250, 0);
        labelSize.setVisible(true);
        this.add(labelSize);
        
        // size slider
        this.sizeSlider.setSize(200,30);
        this.sizeSlider.setLocation(200,30);
        this.sizeSlider.setValue(20);
        this.sizeSlider.setVisible(true);
        this.add(sizeSlider);
        
        // button clear
        JButton btnClear = new JButton("Làm Mới");
        btnClear.setSize(100,60);
        btnClear.setLocation(420, 0);
        btnClear.setBackground(Color.WHITE);
        btnClear.setVisible(true);
        btnClear.setActionCommand("clear");
        this.add(btnClear);
        btnClear.addActionListener(this);
        
        
        // button delete
        JButton btnDel = new JButton("Xóa");
        btnDel.setSize(100,60);
        btnDel.setLocation(550, 0);
        btnDel.setBackground(Color.WHITE);
        btnDel.setVisible(true);
        btnDel.setActionCommand("del");
        this.add(btnDel);
        btnDel.addActionListener(this);
        
        // create list button color
        Color[] colors = {Color.RED,Color.BLACK,Color.BLUE,Color.GRAY,Color.GREEN,
        Color.PINK,Color.WHITE,Color.YELLOW,Color.ORANGE,Color.LIGHT_GRAY,Color.CYAN,Color.MAGENTA};
        int locationX = 0;
        int locationY = 0;
        int count=0;
        for(Color i : colors){
            JButton btnColor = new JButton();
            btnColor.setSize(30,30);
            btnColor.setBackground(i);
            btnColor.setLocation(locationX,locationY);
            btnColor.setActionCommand(i.toString());
            btnColor.setVisible(true);
            this.add(btnColor);
            btnColor.addActionListener(this);
            locationX+=30;
            if(count == 5){ locationX = 0; locationY=30; }
            count++;
        }
        
    }
    public ArrayList<Integer> getColor(String act){
            String[] cut = act.split("Color");
            String rgb = cut[1];
            rgb = rgb.substring(1, rgb.length()-1);
            String listRGB[] = rgb.split(",");
            int R,G,B;
            R=Integer.parseInt(listRGB[0].split("=")[1]);
            G=Integer.parseInt(listRGB[1].split("=")[1]);
            B=Integer.parseInt(listRGB[2].split("=")[1]);
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(R);
            arr.add(G);
            arr.add(B);
            return arr;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        if(act.equals("clear")) {
            repaint();
            client.clientSendData.setSms("clear");
        }
        else if(act.equals("del")) this.checkDelete = true;
        else{
            this.checkDelete = false;
            int R,G,B;
            ArrayList<Integer> arr = this.getColor(act);
            R = arr.get(0);
            G = arr.get(1);
            B = arr.get(2);
            this.color = new Color(R,G,B);   
        }
        
    }
    public static void main(String[] args) throws IOException {
        PaintForm paintForm = new PaintForm();
    }
    
}
