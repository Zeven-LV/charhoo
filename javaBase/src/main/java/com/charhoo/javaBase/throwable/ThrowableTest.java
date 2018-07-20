package com.charhoo.javaBase.throwable;

import javax.lang.model.type.UnknownTypeException;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.MissingResourceException;

/**
 * throwable有两个子类error和exception
 * error 程序无法处理的错误,如JVM错误，outofmemory；
 * exception 程序本身可以从处理的异常，throw或try-catch
 * uncheckexception(系统异常，运行时异常) 可处理可不处理，不强制try-catch
 * checkException（普通异常，非运行时异常，编译异常）如IOException,SQLException,需要try-catch或throw的异常
 *
 */
public class ThrowableTest {

    public static void main(String[] args) {
        Throwable throwable = new Throwable();
            Error error = new Error();
                VirtualMachineError virtualMachineError = null;
                    StackOverflowError stackOverflowError = new StackOverflowError();
                    OutOfMemoryError outOfMemoryError = new OutOfMemoryError();
                AWTError awtError = new AWTError("msg");
            Exception exception = new Exception();
                IOException ioException = new IOException();
                    EOFException eofException = new EOFException();
                    FileNotFoundException fileNotFoundException = new FileNotFoundException();
                SQLException sqlException = new SQLException();

                RuntimeException runtimeException = new RuntimeException();
                    ArithmeticException arithmeticException = new ArithmeticException();
                    MissingResourceException missingResourceException = new MissingResourceException("s","className","key");
                    ClassNotFoundException classNotFoundException = new ClassNotFoundException();
                    NullPointerException nullPointerException = new NullPointerException();
                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
                    ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
                    UnknownTypeException unknownTypeException = new UnknownTypeException(null, "");

    }

    /**
     * throw与throws
     */
    public void exceptionTest() throws FileNotFoundException {
        File file = new File("123.txt");
        if(file == null){
            throw new FileNotFoundException();
        }
        FileReader fileReader = new FileReader(file);
    }
}
