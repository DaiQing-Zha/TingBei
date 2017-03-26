package com.jxnu.zha.tingbei;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
}