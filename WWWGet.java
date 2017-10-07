/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wwwget;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class WWWGet {
 
    /**
 * Unicode文字列から元の文字列に変換する ("\u3042" -> "あ")
 * @param unicode
 * @return
 */
 private static String convertToOiginal(String unicode) {
        String tmp = unicode;
        while (tmp.indexOf("\\u") > 0) {
            String str = tmp.substring(tmp.indexOf("\\u"), tmp.indexOf("\\u") + 6);
            int c = Integer.parseInt(str.substring(2), 16);
            tmp = tmp.replaceFirst("\\" + str, new String(new int[]{c}, 0, 1));
        }
        return tmp;
    }
    
    public static void main(String[] args) throws Exception {
        String urlString = "https://cogproject.jp";
        try {
            //System.setProperty("java.net.useSystemProxies", "true");
            //System.setProperty("https.proxyHost", "localhost");
            //System.setProperty("https.proxyPort", "8085");
            
            URL url = new URL(urlString);
                       
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();
            uc.setRequestMethod("POST");
            uc.setRequestProperty("Accept-Language", "jp");
            uc.setDoInput(true);
            uc.setDoOutput(true);//POST可能にする
            OutputStream os = uc.getOutputStream();//POST用のOutputStreamを取得
            
            String postStr = "apikey=XXXX";//POSTするデータ
            //String postStr ="{\"utt\":\"hello\"}";
            //String postStr ="{\"utt\" : \"hello\"}";
            System.out.println(postStr);
            
             // 送信
            PrintWriter pw = new PrintWriter(new BufferedWriter(
                                    new OutputStreamWriter(
                                       os,"utf-8")));
            pw.print(postStr);// content
            pw.close();       // closeで送信完了
            
            
            //PrintStream ps = new PrintStream(os);
            //ps.print(postStr);//データをPOSTする
            //ps.close();
 
            InputStream is = uc.getInputStream();//POSTした結果を取得
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String s;
            String str[];
            String str2[];
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
                str = s.split(" ", 0);
                str2 = str[8].split("\"", 0);
                System.out.println(convertToOiginal(s));
                }
            reader.close();
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL format: " + urlString);
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }
}

