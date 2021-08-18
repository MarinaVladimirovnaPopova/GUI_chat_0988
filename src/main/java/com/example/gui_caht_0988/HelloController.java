package com.example.gui_caht_0988;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HelloController {
    Socket socket;
    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    @FXML //аннотация
    private void send(){
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String text = textField.getText();
            textField.clear();
            textField.requestFocus();
            textArea.appendText("Вы: "+text+"\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void  connect(){
        try {
            socket = new Socket("localhost", 8188);/*192.168.42.240*/
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String response = in.readUTF();
                            textArea.appendText(response + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}