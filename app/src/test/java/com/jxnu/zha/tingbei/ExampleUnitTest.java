package com.jxnu.zha.tingbei;

import com.jxnu.zha.qinglibrary.util.DensityUtil;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    /**
     * 将异常信息转化成字符串
     * @param t
     * @return
     * @throws IOException
     */
    private String analysisException(Throwable t) throws IOException{
        if(t == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            t.printStackTrace(new PrintStream(baos));
        }finally{
            baos.close();
        }
        return baos.toString();
    }

    @Test
    public void testException(){
        String s = null;
        try{
            s.toString();
        }catch (NullPointerException e){
            try {
                System.out.println("out = start---" + analysisException(e) + "---end");
                if (analysisException(e).indexOf("NullPointerException") != -1){
                    System.out.println("---------------------");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testNullObject(){
        List<String> mLst = new ArrayList<>();
        String ss = null;
        mLst.add(ss);
        for (int i = 0; i < mLst.size(); i ++){
            System.out.println("ss1 = " + ss);
        }
        System.out.println("-----------------------------");
        ss = null;
        mLst.add(ss);
        for (int i = 0; i < mLst.size(); i ++){
            System.out.println("ss2 = " + ss);
        }
        System.out.println("-----------------------------");
        mLst.remove(ss);
        for (int i = 0; i < mLst.size(); i ++){
            System.out.println("ss3 = " + ss.toString());
        }
        System.out.println("-----------------------------");
    }
}