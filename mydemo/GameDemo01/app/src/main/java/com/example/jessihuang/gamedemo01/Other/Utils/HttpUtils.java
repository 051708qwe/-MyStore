package com.example.jessihuang.gamedemo01.Other.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/12.15:03
 */
public class HttpUtils {
    /*GET请求*/
    public static Object doGet(String url){
        if(url==null){
            throw  new NullPointerException("get请求的请求地址不能为空");
        }

        InputStream is=null;
        InputStreamReader reader=null;
        BufferedReader bufferedReader=null;
        try {
            URL u=new URL(url);
            HttpURLConnection conn= (HttpURLConnection) u.openConnection();
            /*设置请求方式*/
            conn.setRequestMethod("GET");
            /*设置连接超时时间*/
            conn.setConnectTimeout(5000);
            /*设置读取超时的时间*/
            conn.setReadTimeout(5000);
            /*设置可读写操作*/
            conn.setDoInput(true);
            conn.setDoOutput(true);
            /*开始链接*/
            conn.connect();
            int code=conn.getResponseCode();
            if(code==200){
                is=conn.getInputStream();
                reader=new InputStreamReader(is);
                bufferedReader=new BufferedReader(reader);
                StringBuffer resultBuffer=new StringBuffer();
                String line=null;
                while((line=bufferedReader.readLine())!=null){
                resultBuffer.append(line);
                }
                String result=resultBuffer.toString();
                LogUtil.w("HttpUtils","请求成功"+result);
                return result;

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //如果没有网络 直接返回空，这些流都为空，要避免空指针异常
                if(is==null){
                    return  null;
                }
                //请求结束后关闭流
                is.close();
                reader.close();
                bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e("HttpUtils","请求失败");
        return null;
    }
    /*进行post请求*/
    public  static  Object doPost(String url,Map<String,String> params){
        if(url==null||params==null){
            throw new NullPointerException("post请求参数或者请求地址不能为空");
        }
        //参数处理
        //把map转化成set
        Set<Map.Entry<String, String>> entries = params.entrySet();
        //从set里获取迭代器
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        StringBuffer paramsBuffer=new StringBuffer();
        //获取每一个entry里的建和值
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            paramsBuffer.append(key);
            paramsBuffer.append("=");
            String value = entry.getValue();
            paramsBuffer.append(value);
            paramsBuffer.append("&");
        }
        //去掉最后面一个多余的&
        String paramsString = paramsBuffer.toString();
        String substring = paramsString.substring(0, paramsString.length() - 1);
        InputStream inputStream=null;
        InputStreamReader reader=null;
        BufferedReader bufferedReader=null;
        OutputStream outputStream=null;
        try {
            //创建一个url
            URL u=new URL(url);
            HttpURLConnection conn= (HttpURLConnection) u.openConnection();
            //设置连接的属性
             /*设置请求方式*/
            conn.setRequestMethod("POST");
            /*设置连接超时时间*/
            conn.setConnectTimeout(5000);
            /*设置读取超时的时间*/
            conn.setReadTimeout(5000);
            /*设置可读写操作*/
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            /*开始链接*//*
            conn.connect();*/
            //获取输出流写入数据
             outputStream = conn.getOutputStream();
             outputStream.flush();
            //向服务器写入参数
            outputStream.write(substring.getBytes());
            int code=conn.getResponseCode();
            //如果返回码为200 表示连接成功
            if(code==200){

                     inputStream = conn.getInputStream();
                reader=new InputStreamReader(inputStream);
                bufferedReader=new BufferedReader(reader);
                StringBuffer resultBuffer=new StringBuffer();
                String line=null;
                //循环读取返回的内容
                while((line=bufferedReader.readLine())!=null){
                    resultBuffer.append(line);
                }
                String result=resultBuffer.toString();
                LogUtil.w("HttpUtils","请求成功"+result);
                return result;///
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //如果没有网络 直接返回空，这些流都为空，要避免空指针异常
                if(inputStream==null){
                    return  null;
                }
                //请求结束后关闭流
                inputStream.close();
                reader.close();
                bufferedReader.close();
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        LogUtil.e("HttpUtils","请求失败");

        return null;

    }
    /**
     * 下载图片
     *
     */

    public static Bitmap LoadBitmap(String url){
        InputStream inputStream=null;
        try {

            URL u=new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.connect();
            if(conn.getResponseCode()==200){
                 inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                LogUtil.w("tag","下载图片成功");
                return bitmap;

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LogUtil.e("HttpUtils","请求失败");

        return null;

    }
    /*gET 下载文件操作*/
    public  static File downLoad(File dir,String fileName,String url,DownLoadTask.UpdateProgress progress){
        if(!dir.exists()){
            /*如果文件夹不存在新建*/
            dir.mkdirs();
        }
        File apk=new File(dir,fileName);

        FileOutputStream fos=null;
        InputStream inputStream=null;

        try {
            URL u=new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200){
                inputStream = urlConnection.getInputStream();
                fos=new FileOutputStream(apk);
                byte[] b=new byte[1024];
                int num=0;
                long contentLength = urlConnection.getContentLength();
                long nowLoad=0;
                while(true){
                    /*每次读取时候1024个字节*/
                    num=inputStream.read(b);
                    /*计算现在下载了多少*/
                    nowLoad+=num;
                    int per= (int) (nowLoad*100/contentLength);

                    if(num==-1){
                        per=100;
                        progress.progressInfo(per);
                        break;
                    } else {
                        fos.write(b,0,num);
                        fos.flush();
                        if(per>100){
                            per=100;
                            progress.progressInfo(per);
                            break;

                        }


                    }
                    return  apk;
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LogUtil.e("HttpUtils","请求失败");

        return null;
    }


}
